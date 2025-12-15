package org.noa.hospitaleo.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.entity.Patient;
import org.noa.hospitaleo.repository.MockEntitiyRepository;
import util.DialogUtils;




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

        String name = patientName.getText();
        String oib = patientOIB.getText();
        String diagnosis = patientDiagnosis.getText();


        if (name.isEmpty() && oib.isEmpty() && diagnosis.isEmpty()) {
            DialogUtils.showSearchScreenErrorDialog("Molim vas ispunite barem jedno polje");
            clear();
        }

        else {

            ObservableList<Patient> observableList = FXCollections.observableList(
                    MockEntitiyRepository.allPatientsAsList().stream()
                            .filter(d -> name.isEmpty() || d.getName().toLowerCase().contains(name.toLowerCase()))
                            .filter(d -> oib.isEmpty() || d.getOib().equals(oib))
                            .filter(d -> diagnosis.isEmpty() || d.getDiagnosis().toLowerCase().contains(diagnosis.toLowerCase()))
                            .toList()
            );
            patientTable.setItems(observableList);
        }


    }



}
