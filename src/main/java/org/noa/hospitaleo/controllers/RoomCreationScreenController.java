package org.noa.hospitaleo.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.components.IdentifiableComboBox;
import org.noa.hospitaleo.entity.Department;
import org.noa.hospitaleo.entity.IdentifiableEntity;
import org.noa.hospitaleo.entity.Room;

import util.DatabaseUtils;
import util.DialogUtils;
import util.Utils;
import util.StringCheckerUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class RoomCreationScreenController {


    @FXML
    private TextField roomName;

    @FXML
    private ObjectProperty<UUID> selectedDepartmentId=new SimpleObjectProperty<>();
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
        else if(departmentComboBox.getSelectionModel().isEmpty())
        {
            DialogUtils.showEntityCreationsErrorDialog("Molim odaberite odjel");
            return false;
        }

        Room temp=new Room(roomName.getText());
       try
       {
            HospitalEoApplication.getApi().addRoom(temp,selectedDepartmentId.getValue());
       }catch(SQLException e)
       {
           DialogUtils.showDatabaseErrorDialog("Greska pri zapisivanju sobe");
           HospitalEoApplication.logger.error(e.getMessage(),e);
       }


        DialogUtils.showEntityCreationSuccessDialog("Uspjesno je zapisana soba:"+" "+roomName.getText());
        HospitalEoApplication.logger.info("Uspjesno je zapisana soba:{}",roomName.getText());
        reset();
        return true;
    }

}
