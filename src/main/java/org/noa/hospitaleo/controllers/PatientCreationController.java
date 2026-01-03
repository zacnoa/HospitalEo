package org.noa.hospitaleo.controllers;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.noa.hospitaleo.components.IdentifiableComboBox;
import org.noa.hospitaleo.entity.*;
import org.noa.hospitaleo.enums.PatientStatus;
import org.noa.hospitaleo.repository.MockEntityRepository;
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
    private TextField legalGuardianName;
    @FXML
    private TextField legalGuardianOIB;
    @FXML
    private Label nameLabel;
    @FXML
    private Label oibLabel;

    private ObjectProperty<String> selectedDepartmentId= new SimpleObjectProperty<>();

    private ObjectProperty<String> selectedDoctorId= new SimpleObjectProperty<>();

    private ObjectProperty<String> selectedRoomId = new SimpleObjectProperty<>();

    @FXML
    private IdentifiableComboBox departmentComboBox;
    @FXML
    private IdentifiableComboBox doctorComboBox;
    @FXML
    private IdentifiableComboBox roomComboBox;



    private void updateDoctorSelection() {
        ObservableList<IdentifiableEntity> options = RepositoryUtils.listToIdentifiableObservableList(
                MockEntityRepository.getDepartment(selectedDepartmentId.get()).getDoctors());
        doctorComboBox.updateItems(options);

    }
    private void updateRoomSelection()
    {
        ObservableList<IdentifiableEntity> options = RepositoryUtils.listToIdentifiableObservableList(
                MockEntityRepository.getDepartment(selectedDepartmentId.get()).getRooms());
        roomComboBox.updateItems(options);
    }
    @FXML
    private void initialize() {

        ObservableList<IdentifiableEntity> options = RepositoryUtils.mapToIdentifiableObservableList(MockEntityRepository.getDepartmentMap());
        departmentComboBox.setUp(options,selectedDepartmentId,"Department");
        doctorComboBox.setUp(selectedDoctorId,"Doctor");
        roomComboBox.setUp(selectedRoomId,"Room");

        selectedDepartmentId.addListener((obs, oldVal, newVal) ->
        {
            updateRoomSelection();
            updateDoctorSelection();
        });



        underageFlag.selectedProperty().addListener((obs, oldVal, newVal) -> {
            nameLabel.setVisible(!nameLabel.isVisible());
            oibLabel.setVisible(!oibLabel.isVisible());
            legalGuardianName.setVisible(!legalGuardianName.isVisible());
            legalGuardianOIB.setVisible(!legalGuardianOIB.isVisible());
        });

        nameLabel.setVisible(!nameLabel.isVisible());
        oibLabel.setVisible(!oibLabel.isVisible());
        legalGuardianName.setVisible(!legalGuardianName.isVisible());
        legalGuardianOIB.setVisible(!legalGuardianOIB.isVisible());

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

        if (underageFlag.isSelected() && StringCheckerUtils.isNullOrEmpty(legalGuardianName.getText(), legalGuardianOIB.getText()))
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
                     new Visitor(legalGuardianName.getText(),legalGuardianOIB.getText()),
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
        MockEntityRepository.getDoctor(selectedDoctorId.get()).addPatient(temp);
        MockEntityRepository.getDepartment(selectedDepartmentId.get()).addPatient(temp);
        MockEntityRepository.getRoom(selectedRoomId.get()).addPatient(temp);
        MockEntityRepository.getPatientMap().put(temp.getId(), temp);
        DialogUtils.showEntityCreationSuccessDialog("Uspjesno je zapisan pacijent:"+" "+patientName.getText());
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
        legalGuardianName.clear();
        legalGuardianOIB.clear();
    }
}

