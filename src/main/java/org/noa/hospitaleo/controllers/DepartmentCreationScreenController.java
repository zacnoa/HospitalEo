package org.noa.hospitaleo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.entity.Department;
import util.DialogUtils;
import util.StringCheckerUtils;

import java.sql.SQLException;


public class DepartmentCreationScreenController {

    @FXML
    private TextField departmentName;


    private void reset()
    {
        departmentName.clear();
    }
    @FXML
    private boolean handleSubmit()
    {
        if(StringCheckerUtils.isNullOrEmpty(departmentName.getText()))
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim vas ispunite sva polja");
            return false;
        }
        Department temp=new Department(departmentName.getText());
        try {
            HospitalEoApplication.getApi().addDepartment(temp);
        } catch (SQLException e) {
            DialogUtils.showDatabaseErrorDialog("Greska pri spremanju doktora u bazu");
            HospitalEoApplication.logger.error(e.getMessage(),e);
        }


        DialogUtils.showEntityCreationSuccessDialog("Uspjesno je zapisan odjel:"+" "+departmentName.getText());
        HospitalEoApplication.logger.info("Uspjesno je zapisan odjel:{}",departmentName.getText());
        reset();
        return true;
    }



}
