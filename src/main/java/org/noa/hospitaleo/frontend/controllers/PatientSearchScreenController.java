package org.noa.hospitaleo.frontend.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.backend.utils.DatabaseUtils;
import org.noa.hospitaleo.frontend.components.IdentifiableComboBox;
import org.noa.hospitaleo.frontend.entity.Department;
import org.noa.hospitaleo.frontend.entity.Patient;
import org.noa.hospitaleo.frontend.util.DialogUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PatientSearchScreenController {

    @FXML
    private TextField patientName;
    @FXML
    private TextField patientOIB;
    @FXML
    private TextField patientDiagnosis;

    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient,String> colPatientName;

    @FXML
    private TableColumn<Patient,String> colPatientOIB;

    @FXML
    private TableColumn<Patient,String> colPatientDiagnosis;
    @FXML
    private IdentifiableComboBox departmentComboBox;

    private ObjectProperty<UUID> selectedDepartmentId = new SimpleObjectProperty<>();

    @FXML
    private void initialize() {
        selectedDepartmentId.set(null);
        colPatientName.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getName())
        );

        colPatientOIB.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getOib())
        );

        colPatientDiagnosis.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getDiagnosis())
        );
        try {
            departmentComboBox.setUp(DatabaseUtils.departmentsIdentifiableObservableList(HospitalEoApplication.getApi().getConnection()),selectedDepartmentId,"Department");
        } catch (SQLException e) {
            HospitalEoApplication.logger.error(e.getMessage());
            DialogUtils.showDatabaseErrorDialog("Greska pri dohvatu departments");
        }

    }



    private void clear()
    {
        patientName.clear();
        patientOIB.clear();
        patientDiagnosis.clear();
        departmentComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void searchPatientByParameter() {

        List<Patient> temp = new ArrayList<>();
        String name = patientName.getText();
        String oib = patientOIB.getText();
        String diagnosis = patientDiagnosis.getText();


        if (name.isEmpty() && oib.isEmpty() && diagnosis.isEmpty() && departmentComboBox.getSelectionModel().isEmpty()) {
            DialogUtils.showSearchScreenErrorDialog("Molim vas ispunite barem jedno polje");
        }

        else {

            try
            {
                 temp.addAll(HospitalEoApplication.getApi().patientSearch(name,oib,diagnosis,selectedDepartmentId.get()));
            }catch(Exception ex)
            {
                DialogUtils.showDatabaseErrorDialog("Greska pri pretrazi za pacijentom");
                HospitalEoApplication.logger.error(ex.getMessage(),ex);
            }

            ObservableList<Patient> observableList = FXCollections.observableList(temp);
            patientTable.setItems(observableList);
        }


    }



}
