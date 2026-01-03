package org.noa.hospitaleo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.entity.Department;
import org.noa.hospitaleo.repository.MockEntityRepository;
import util.DialogUtils;
import util.StringCheckerUtils;


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
        MockEntityRepository.getDepartmentMap().put(temp.getId(),temp);
        DialogUtils.showEntityCreationSuccessDialog("Uspjesno je zapisan odjel:"+" "+departmentName.getText());
        reset();
        return true;
    }



}
