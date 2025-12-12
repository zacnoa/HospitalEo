package org.noa.hospitaleo.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.noa.hospitaleo.entity.Doctor;
import org.noa.hospitaleo.repository.MockEntitiyRepository;
import util.DialogUtils;


import java.util.ArrayList;
import java.util.List;

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
    private void initialize() {
        colDoctorName.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getName())
        );

        colDoctorOIB.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getOib())
        );

        colDoctorSpecialty.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getSpecialty())
        );
    }



    private void clear()
        {
            doctorName.clear();
            doctorOIB.clear();
            doctorSpecialty.clear();
        }

        @FXML
        private void searchDoctorByParameter() {

            String name = doctorName.getText();
            String oib = doctorOIB.getText();
            String specialty = doctorSpecialty.getText();
            List<Doctor> result = new ArrayList<>();

            if (name.isEmpty() && oib.isEmpty() && specialty.isEmpty()) {
                DialogUtils.showSearchScreenErrorDialog("Molim vas ispunite barem jedno polje");
                clear();
            }

            else {
                result=MockEntitiyRepository.doctorStorage.values().stream()
                        .filter(d -> name.isEmpty() || d.getName().toLowerCase().contains(name.toLowerCase()))
                        .filter(d -> oib.isEmpty() || d.getOib().equals(oib))
                        .filter(d -> specialty.isEmpty() || d.getSpecialty().toLowerCase().contains(specialty.toLowerCase()))
                        .toList();
                ObservableList<Doctor> observableList = FXCollections.observableList(result);
                doctorTable.setItems(observableList);
            }

        }



}
