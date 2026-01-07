package org.noa.hospitaleo.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.components.IdentifiableComboBox;
import org.noa.hospitaleo.entity.IdentifiableEntity;
import org.noa.hospitaleo.entity.Room;

import util.DialogUtils;
import util.RepositoryUtils;
import util.StringCheckerUtils;

public class RoomCreationScreenController {

    @FXML
    private TextField roomName;

    @FXML
    private ObjectProperty<String> selectedDepartmentId=new SimpleObjectProperty<>();
    @FXML
    private IdentifiableComboBox departmentComboBox;

    @FXML
    private void initialize() {
        ObservableList<IdentifiableEntity> options = RepositoryUtils.mapToIdentifiableObservableList(HospitalEoApplication.getRepository().getDepartmentMap());
        departmentComboBox.setUp(options,selectedDepartmentId,"Department");
    }

    private void reset()
    {
        roomName.clear();
    }
    @FXML
    private boolean handleSubmit()
    {
        if(StringCheckerUtils.isNullOrEmpty(roomName.getText()))
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim vas ispunite sva polja");
            return false;
        }
        else if(selectedDepartmentId.get().isEmpty())
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim odaberite odjel");
            return false;
        }

        Room temp=new Room(roomName.getText());
        HospitalEoApplication.getRepository().getDepartment(selectedDepartmentId.get()).addRoom(temp);
        DialogUtils.showEntityCreationSuccessDialog("Uspjesno je zapisana soba:"+" "+roomName.getText());
        reset();
        return true;
    }

}
