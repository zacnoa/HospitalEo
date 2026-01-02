package org.noa.hospitaleo.controllers;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.noa.hospitaleo.entity.Patient;
import org.noa.hospitaleo.entity.SearchbyNameEntity;
import org.noa.hospitaleo.entity.UnderagePatient;
import org.noa.hospitaleo.entity.Visitor;
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
    @FXML
    private String selectedDepartmentId;
    @FXML
    private String selectedDoctorId;
    @FXML
    private String selectedRoomId;

    @FXML
    private ComboBox<SearchbyNameEntity> departmentComboBox;
    @FXML
    private ComboBox<SearchbyNameEntity> doctorComboBox;
    @FXML
    private ComboBox<SearchbyNameEntity> roomComboBox;



    private void updateDoctorSelection() {
        ObservableList<SearchbyNameEntity> options = RepositoryUtils.listToObservableList(
                MockEntityRepository.getDepartment(selectedDepartmentId).getDoctors());
        doctorComboBox.setItems(options);
        doctorComboBox.setConverter(RepositoryUtils.stringConverterFactory(options));

    }
    private void updateRoomSelection()
    {
        ObservableList<SearchbyNameEntity> options = RepositoryUtils.listToObservableList(
                MockEntityRepository.getDepartment(selectedDepartmentId).getRooms());
        roomComboBox.setItems(options);
        roomComboBox.setConverter(RepositoryUtils.stringConverterFactory(options));
    }
    @FXML
    private void initialize() {

        ObservableList<SearchbyNameEntity> options = RepositoryUtils.mapToObservableList(MockEntityRepository.getDepartmentMap());

        departmentComboBox.setItems(options);

        departmentComboBox.setConverter(RepositoryUtils.stringConverterFactory(options));

        departmentComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedDepartmentId = newVal.getId();
                updateDoctorSelection();
                updateRoomSelection();
            }
        });

        doctorComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedDoctorId = newVal.getId();
            }
        });
        doctorComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedDoctorId = newVal.getId();
            }
        });
        roomComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedRoomId = newVal.getId();
            }
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
                || departmentComboBox.getSelectionModel().isEmpty()
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
                     selectedDoctorId,
                     selectedRoomId,
                     new Visitor(legalGuardianName.getText(),legalGuardianOIB.getText()),
                     PatientStatus.HOSPITALIZED);

         }
         else {
             temp = new Patient(
                    patientName.getText(),
                    patientOIB.getText(),
                    patientDiagnosis.getText(), selectedDoctorId,
                    selectedRoomId,
                    PatientStatus.HOSPITALIZED);
        }
        MockEntityRepository.getDoctor(selectedDoctorId).addPatient(temp);
        MockEntityRepository.getDepartment(selectedDepartmentId).addPatient(temp);
        MockEntityRepository.getRoom(selectedRoomId).addPatient(temp);
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

