package org.noa.hospitaleo.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.entity.Patient;
import util.DialogUtils;

import java.util.ArrayList;
import java.util.List;


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
    private void initialize() {
        colPatientName.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getName())
        );

        colPatientOIB.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getOib())
        );

        colPatientDiagnosis.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getDiagnosis())
        );
    }



    private void clear()
    {
        patientName.clear();
        patientOIB.clear();
        patientDiagnosis.clear();
    }

    @FXML
    private void searchPatientByParameter() {

        List<Patient> temp = new ArrayList<>();
        String name = patientName.getText();
        String oib = patientOIB.getText();
        String diagnosis = patientDiagnosis.getText();


        if (name.isEmpty() && oib.isEmpty() && diagnosis.isEmpty()) {
            DialogUtils.showSearchScreenErrorDialog("Molim vas ispunite barem jedno polje");
            clear();
        }

        else {

            try
            {
                 temp.addAll(HospitalEoApplication.getApi().patientSearch(name,oib,diagnosis));
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
