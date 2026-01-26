package org.noa.hospitaleo.frontend.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.frontend.entity.Doctor;
import org.noa.hospitaleo.frontend.entity.Patient;
import org.noa.hospitaleo.frontend.entity.Room;
import org.noa.hospitaleo.frontend.util.DialogUtils;

import java.sql.SQLException;

public class MainScreenController {


    @FXML
    private TextField patientName;
    @FXML
    private TextField patientOIB;
    @FXML
    private TextArea patientDiagnosis;
    @FXML
    private TextField departmentName;
    @FXML
    private TextField doctorName;
    @FXML
    private TextField roomName;

    @FXML
    private void initialize() {
        Thread.startVirtualThread(() -> {
            try {
                Patient temp = HospitalEoApplication.getApi().getLatestPatient();

                Room tempRoom = HospitalEoApplication.getApi().getRoom(temp.getRoomId());
                Doctor tempDoctor = HospitalEoApplication.getApi().getDoctor(temp.getDoctorId());

                String tempDepartment = HospitalEoApplication.getApi().getDepartmentNameByRoom(tempRoom.getId());

                Platform.runLater(() -> {
                    patientName.setText(temp.getName());
                    patientOIB.setText(temp.getOib());
                    patientDiagnosis.setText(temp.getDiagnosis());
                    departmentName.setText(tempDepartment);
                    doctorName.setText(tempDoctor.getName());
                    roomName.setText(tempRoom.getName());
                });

            } catch (SQLException e) {
                HospitalEoApplication.logger.error(e.getMessage(), e);

                Platform.runLater(() -> DialogUtils.showDatabaseErrorDialog("Došlo je do greške pri dohvaćanju podataka"));
            }
        });
    }

}
