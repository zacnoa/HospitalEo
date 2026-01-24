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
import org.noa.hospitaleo.frontend.entity.Doctor;

import org.noa.hospitaleo.frontend.util.DialogUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class DoctorSearchScreenController {

        @FXML
        private TextField doctorName;
        @FXML
        private TextField doctorOIB;
        @FXML
        private TextField doctorSpecialty;

        @FXML
        private TableView<Doctor> doctorTable;

         @FXML
         private TableColumn<Doctor,String> colDoctorName;

         @FXML
         private TableColumn<Doctor,String> colDoctorOIB;

         @FXML
         private TableColumn<Doctor,String> colDoctorSpecialty;

         @FXML
         private IdentifiableComboBox departmentComboBox;

         private ObjectProperty<UUID> selectedDepartmentId = new SimpleObjectProperty<>();

    @FXML
    private void initialize() {
        selectedDepartmentId.set(null);
        colDoctorName.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getName())
        );

        colDoctorOIB.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getOib())
        );

        colDoctorSpecialty.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getSpecialty())
        );
        try {
            departmentComboBox.setUp(DatabaseUtils.departmentsIdentifiableObservableList(HospitalEoApplication.getApi().getConnection()),selectedDepartmentId,"Departments");
        } catch (SQLException e) {
            HospitalEoApplication.logger.error(e.getMessage());
            DialogUtils.showDatabaseErrorDialog("Greska pri dohvacanju departments");
        }
    }



    private void clear()
    {
            doctorName.clear();
            doctorOIB.clear();
            doctorSpecialty.clear();
            departmentComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void searchDoctorByParameter() {

            List<Doctor> temp = new ArrayList<>();
            String name = doctorName.getText();
            String oib = doctorOIB.getText();
            String specialty = doctorSpecialty.getText();


            if (name.isEmpty() && oib.isEmpty() && specialty.isEmpty() && departmentComboBox.getSelectionModel().isEmpty()) {
                DialogUtils.showSearchScreenErrorDialog("Molim vas ispunite barem jedno polje");
            }

            else
            {
                try
            {
                 temp.addAll(HospitalEoApplication.getApi().doctorSearch(name,oib,specialty,selectedDepartmentId.get()));
            }catch(Exception ex)
            {
                DialogUtils.showDatabaseErrorDialog("Greska pri pretrazi za doktorom");
                HospitalEoApplication.logger.error(ex.getMessage(),ex);
            }

                ObservableList<Doctor> observableList = FXCollections.observableList(temp);
                doctorTable.setItems(observableList);
            }
            clear();
    }
}
