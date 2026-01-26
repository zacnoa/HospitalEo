package org.noa.hospitaleo.frontend.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.frontend.components.IdentifiableComboBox;
import org.noa.hospitaleo.frontend.entity.Doctor;
import org.noa.hospitaleo.frontend.entity.IdentifiableEntity;

import org.noa.hospitaleo.backend.utils.DatabaseUtils;
import org.noa.hospitaleo.frontend.util.DialogUtils;
import org.noa.hospitaleo.frontend.util.StringCheckerUtils;

import java.sql.SQLException;
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
        try {
            ObservableList<IdentifiableEntity> options = DatabaseUtils.departmentsIdentifiableObservableList(HospitalEoApplication.getApi().getConnection());
            departmentComboBox.setUp(options,selectedDepartmentId,"Department");
        } catch (SQLException e) {
            HospitalEoApplication.logger.error(e.getMessage(),e);
            DialogUtils.showDatabaseErrorDialog("Greska pri dohvacanju department");
        }
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
        try
        {
            HospitalEoApplication.getApi().addDoctor(temp,selectedDepartmentId.getValue());
        }catch(SQLException e)
        {
            DialogUtils.showDatabaseErrorDialog("Greska pri pisanju doktora");
            HospitalEoApplication.logger.error(e.getMessage(),e);
            reset();
            return false;
        }
        DialogUtils.showEntityCreationSuccessDialog("Uspjesno je zapisan doktor:"+" "+doctorName.getText());
        HospitalEoApplication.logger.info("Uspjesno je zapisan doktor:{}",doctorName.getText());
        reset();
        return true;
    }



}
