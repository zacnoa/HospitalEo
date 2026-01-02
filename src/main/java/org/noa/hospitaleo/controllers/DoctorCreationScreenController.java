package org.noa.hospitaleo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import util.DialogUtils;
import util.StringCheckerUtils;

public class DoctorCreationScreenController {

    @FXML
    private TextField doctorName;
    @FXML
    private TextField doctorOib;
    @FXML
    private TextField doctorSpecialty;



    private void reset()
    {
        doctorName.clear();
        doctorOib.clear();
        doctorSpecialty.clear();
    }

    @FXML
    private boolean handleSubmit()
    {
        if(StringCheckerUtils.isNullOrEmpty(doctorName.getText(),doctorName.getText(),doctorSpecialty.getText()))
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim vas ispunite sva polja");
            return false;
        }

        return true;
    }



}
