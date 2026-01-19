package org.noa.hospitaleo.frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.frontend.util.DialogUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogOverviewScreenController {

    @FXML
    private TextArea logTextArea;

    @FXML
    public void initialize()
    {
        try {
           String temp = Files.readString(Paths.get("runTimeLogs/pogreske.log"));
           logTextArea.setText(temp);
        } catch (IOException ex) {
            DialogUtils.showIOError("Doslo je do greske pri citanju logova");
            HospitalEoApplication.logger.error(ex.getMessage(),ex);
        }
    }
}
