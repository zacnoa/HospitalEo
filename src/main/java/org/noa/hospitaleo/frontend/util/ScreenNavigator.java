package org.noa.hospitaleo.frontend.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.frontend.enums.Screen;

import java.io.IOException;

public class ScreenNavigator {

    public static void  ChangeScene(Screen screen)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource(screen.getFxml()));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = screen.isUseMainStage() ? HospitalEoApplication.getMainStage() : new Stage();

            stage.setTitle(screen.getTitle());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            DialogUtils.showDisplayScreenErrorDialog(screen.getErrorMessage());
            HospitalEoApplication.logger.error(ex.getMessage(),ex);
        }

    }
}
