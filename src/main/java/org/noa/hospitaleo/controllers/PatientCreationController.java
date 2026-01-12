package org.noa.hospitaleo.controllers;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.components.IdentifiableComboBox;
import org.noa.hospitaleo.entity.*;
import org.noa.hospitaleo.enums.PatientStatus;

import util.DialogUtils;
import util.RepositoryUtils;
import util.StringCheckerUtils;


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

    private final ObjectProperty<String> selectedDepartmentId= new SimpleObjectProperty<>();

    private final ObjectProperty<String> selectedDoctorId= new SimpleObjectProperty<>();

    private final ObjectProperty<String> selectedRoomId = new SimpleObjectProperty<>();

    @FXML
    private IdentifiableComboBox departmentComboBox;
    @FXML
    private IdentifiableComboBox doctorComboBox;
    @FXML
    private IdentifiableComboBox roomComboBox;



    private void updateDoctorSelection() {
        ObservableList<IdentifiableEntity> options = RepositoryUtils.listToIdentifiableObservableList(
                HospitalEoApplication.getRepository().getDepartment(selectedDepartmentId.get()).getDoctors());
        doctorComboBox.updateItems(options);

    }
    private void updateRoomSelection()
    {
        ObservableList<IdentifiableEntity> options = RepositoryUtils.listToIdentifiableObservableList(
                HospitalEoApplication.getRepository().getDepartment(selectedDepartmentId.get()).getRooms());
        roomComboBox.updateItems(options);
    }
    @FXML
    private void initialize() {

        ObservableList<IdentifiableEntity> options = RepositoryUtils.mapToIdentifiableObservableList(HospitalEoApplication.getRepository().getDepartmentMap());
        departmentComboBox.setUp(options,selectedDepartmentId,"Department");
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
        Patient temp;
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
              temp= new UnderagePatient(
                     patientName.getText(),
                     patientOIB.getText(),
                     patientDiagnosis.getText(),
                     selectedDoctorId.get(),
                     selectedRoomId.get(),
                     new Visitor(guardianName.getText(), guardianOIB.getText()),
                     PatientStatus.HOSPITALIZED);

         }
         else {
             temp = new Patient(
                    patientName.getText(),
                    patientOIB.getText(),
                    patientDiagnosis.getText(), selectedDoctorId.get(),
                    selectedRoomId.get(),
                    PatientStatus.HOSPITALIZED);
        }
        HospitalEoApplication.getRepository().getDoctor(selectedDoctorId.get()).addPatient(temp);
        HospitalEoApplication.getRepository().getDepartment(selectedDepartmentId.get()).addPatient(temp);
        HospitalEoApplication.getRepository().getRoom(selectedRoomId.get()).addPatient(temp);
        HospitalEoApplication.getRepository().getPatientMap().put(temp.getId(), temp);
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

