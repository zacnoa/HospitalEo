package org.noa.hospitaleo.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.components.IdentifiableComboBox;
import org.noa.hospitaleo.entity.Doctor;
import org.noa.hospitaleo.entity.IdentifiableEntity;

import util.DialogUtils;
import util.RepositoryUtils;
import util.StringCheckerUtils;

import java.util.UUID;

public class DoctorCreationScreenController {

    @FXML

    private TextField doctorName;
    @FXML
    private TextField doctorOib;
    @FXML
    private TextField doctorSpecialty;
    @FXML
    private TextField doctorSalary;
    @FXML
    private final ObjectProperty<UUID> selectedDepartmentId=new SimpleObjectProperty<>();
    @FXML
    private IdentifiableComboBox departmentComboBox;




    @FXML
    private void initialize() {
        ObservableList<IdentifiableEntity> options = RepositoryUtils.mapToIdentifiableObservableList(HospitalEoApplication.getRepository().getDepartmentMap());
        departmentComboBox.setUp(options,selectedDepartmentId,"Department");
    }
    private void reset()
    {
        doctorName.clear();
        doctorOib.clear();
        doctorSpecialty.clear();
    }

    @FXML
    private boolean handleSubmit()
    {
        if(StringCheckerUtils.isNullOrEmpty(doctorName.getText(),doctorName.getText(),doctorSpecialty.getText(),doctorSalary.getText()))
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim vas ispunite sva polja");
            return false;
        }
        else if(departmentComboBox.getSelectionModel().isEmpty())
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim odaberite odjel");
            return false;
        }

        doctorSalary.setText(doctorSalary.getText().replace(",","."));
        double salary;
        try {
             salary = Double.parseDouble(doctorSalary.getText());
        }catch(NumberFormatException _)
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim vas unesite valjani broj");
            return false;
        }
        Doctor temp= new Doctor(doctorName.getText(),doctorOib.getText(),doctorSpecialty.getText(),salary);
        HospitalEoApplication.getRepository().getDepartment(selectedDepartmentId.get()).addDoctor(temp);
        DialogUtils.showEntityCreationSuccessDialog("Uspjesno je zapisan doktor:"+" "+doctorName.getText());
        HospitalEoApplication.logger.info("Uspjesno je zapisan doktor:{}",doctorName.getText());
        reset();
        return true;
    }



}
