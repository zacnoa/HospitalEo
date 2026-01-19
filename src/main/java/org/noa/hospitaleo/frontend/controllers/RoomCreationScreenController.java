package org.noa.hospitaleo.frontend.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.frontend.components.IdentifiableComboBox;
import org.noa.hospitaleo.frontend.entity.IdentifiableEntity;
import org.noa.hospitaleo.frontend.entity.Room;

import org.noa.hospitaleo.backend.utils.DatabaseUtils;
import org.noa.hospitaleo.frontend.util.DialogUtils;
import org.noa.hospitaleo.frontend.util.StringCheckerUtils;

import java.sql.SQLException;
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
