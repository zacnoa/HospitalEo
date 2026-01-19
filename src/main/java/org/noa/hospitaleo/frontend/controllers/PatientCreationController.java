package org.noa.hospitaleo.frontend.controllers;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.frontend.components.IdentifiableComboBox;
import org.noa.hospitaleo.frontend.entity.*;
import org.noa.hospitaleo.frontend.enums.PatientStatus;

import org.noa.hospitaleo.backend.utils.DatabaseUtils;
import org.noa.hospitaleo.frontend.util.DialogUtils;
import org.noa.hospitaleo.frontend.util.StringCheckerUtils;

import java.sql.SQLException;
import java.util.UUID;


public class PatientCreationController {



    @FXML
    private TextField patientName;
    @FXML
    private TextField patientOIB;
    @FXML
    private TextArea patientDiagnosis;
    @FXML
    private CheckBox underageFlag;
    @FXML
    private TextField guardianName;
    @FXML
    private TextField guardianOIB;
    @FXML
    private Label guardianNameLabel;
    @FXML
    private Label guardianOibLabel;

    private final ObjectProperty<UUID> selectedDepartmentId= new SimpleObjectProperty<>();

    private final ObjectProperty<UUID> selectedDoctorId= new SimpleObjectProperty<>();

    private final ObjectProperty<UUID> selectedRoomId = new SimpleObjectProperty<>();

    @FXML
    private IdentifiableComboBox departmentComboBox;
    @FXML
    private IdentifiableComboBox doctorComboBox;
    @FXML
    private IdentifiableComboBox roomComboBox;



    private void updateDoctorSelection() {
        try {
            ObservableList<IdentifiableEntity> options = DatabaseUtils.doctorsIdentifiableObservableList(HospitalEoApplication.getApi().getConnection(),selectedDepartmentId.getValue());
            doctorComboBox.updateItems(options);
        } catch (SQLException e) {
            DialogUtils.showDatabaseErrorDialog("Greska pri dohvacanju doctora");
            HospitalEoApplication.logger.error(e.getMessage(),e);
        }

    }
    private void updateRoomSelection()
    {
        try {
            ObservableList<IdentifiableEntity> options = DatabaseUtils.roomsIdentifiableObservableList(HospitalEoApplication.getApi().getConnection(),selectedDepartmentId.getValue());
            roomComboBox.updateItems(options);
        } catch (SQLException e) {
            DialogUtils.showDatabaseErrorDialog("Greska pri dohvacanju soba");
            HospitalEoApplication.logger.error(e.getMessage(),e);
        }
    }
    @FXML
    private void initialize() {

        try {
            ObservableList<IdentifiableEntity> options = DatabaseUtils.departmentsIdentifiableObservableList(HospitalEoApplication.getApi().getConnection());
            departmentComboBox.setUp(options,selectedDepartmentId,"Department");
        } catch (SQLException e) {
            DialogUtils.showDatabaseErrorDialog("Greska pri dohvacanju department");
            HospitalEoApplication.logger.error(e.getMessage(),e);
        }
        doctorComboBox.setUp(selectedDoctorId,"Doctor");
        roomComboBox.setUp(selectedRoomId,"Room");

        selectedDepartmentId.addListener((obs, oldVal, newVal) ->
        {
            updateRoomSelection();
            updateDoctorSelection();
        });



        underageFlag.selectedProperty().addListener((obs, oldVal, newVal) -> {
            guardianNameLabel.setVisible(!guardianNameLabel.isVisible());
            guardianOibLabel.setVisible(!guardianOibLabel.isVisible());
            guardianName.setVisible(!guardianName.isVisible());
            guardianOIB.setVisible(!guardianOIB.isVisible());
        });

        guardianNameLabel.setVisible(!guardianNameLabel.isVisible());
        guardianOibLabel.setVisible(!guardianOibLabel.isVisible());
        guardianName.setVisible(!guardianName.isVisible());
        guardianOIB.setVisible(!guardianOIB.isVisible());

    }

    @FXML
    private boolean handleSubmit() {
        if (StringCheckerUtils.isNullOrEmpty(patientName.getText(), patientOIB.getText(), patientDiagnosis.getText()))
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim vas ispunite sva polja vezane za ime,OIB i dijagnozu");
            return false;
        } else if (departmentComboBox.getSelectionModel().isEmpty()
                || doctorComboBox.getSelectionModel().isEmpty()
                || roomComboBox.getSelectionModel().isEmpty())
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim vas odaberite odjel i doktora");
            return false;
        }

        if (underageFlag.isSelected() && StringCheckerUtils.isNullOrEmpty(guardianName.getText(), guardianOIB.getText()))
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim vas ispunite sva polja vezana za skrbinka pacijenta");
            return false;
        }

         else if(underageFlag.isSelected())
         {
             UnderagePatient temp= new UnderagePatient(
                     patientName.getText(),
                     patientOIB.getText(),
                     patientDiagnosis.getText(),
                     selectedDoctorId.get(),
                     selectedRoomId.get(),
                     new Visitor(guardianName.getText(), guardianOIB.getText()),
                     PatientStatus.HOSPITALIZED);
              try
              {
                  HospitalEoApplication.getApi().addUnderagePatient(temp,selectedDepartmentId.getValue());
              }catch(SQLException e)
              {
                  HospitalEoApplication.logger.error(e.getMessage(),e);
                  DialogUtils.showDatabaseErrorDialog("Doslo je do pogreske pri zapisivanju underagepatient");
              }

         }
         else {

             Patient temp = new Patient(
                    patientName.getText(),
                    patientOIB.getText(),
                    patientDiagnosis.getText(), selectedDoctorId.get(),
                    selectedRoomId.get(),
                    PatientStatus.HOSPITALIZED);
            try {
                HospitalEoApplication.getApi().addPatient(temp,selectedDepartmentId.getValue());
            } catch (SQLException e) {
                DialogUtils.showDatabaseErrorDialog("Greska pri spremanju pacijenta");
                HospitalEoApplication.logger.error(e.getMessage(),e);
            }

        }
        DialogUtils.showEntityCreationSuccessDialog("Uspjesno je zapisan pacijent:"+" "+patientName.getText());
        HospitalEoApplication.logger.info("Uspjesno je zapisan pacijent:{}",patientName.getText());

        reset();
        return true;
    }

    public void reset()
    {
        departmentComboBox.getSelectionModel().clearSelection();
        doctorComboBox.getSelectionModel().clearSelection();
        roomComboBox.getSelectionModel().clearSelection();
        patientName.clear();
        patientOIB.clear();
        patientDiagnosis.clear();
        underageFlag.setSelected(false);
        guardianName.clear();
        guardianOIB.clear();
    }
}

