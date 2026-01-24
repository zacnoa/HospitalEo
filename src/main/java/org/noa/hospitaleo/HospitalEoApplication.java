package org.noa.hospitaleo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.noa.hospitaleo.backend.DatabaseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.noa.hospitaleo.backend.utils.DatabaseUtils;
import org.noa.hospitaleo.frontend.util.DialogUtils;

import java.io.IOException;
import java.sql.SQLException;

public class HospitalEoApplication extends Application {


    private static  Stage mainStage;
    public static final Logger logger = LoggerFactory.getLogger(HospitalEoApplication.class);
    private static DatabaseAPI api;

    @SuppressWarnings("java:S2696") // Nemozemo promijeniti signature metode posto radimo override
    public  void start(Stage stage) {
        mainStage = stage;
        try {
            api=new DatabaseAPI(DatabaseUtils.getConnection());
            FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource("main-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            DialogUtils.showIOError("Greska pri otvaranju aplikacije");
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            DialogUtils.showDatabaseErrorDialog("Greska pri spajanju na  bazu podataka");
            logger.error(ex.getMessage(), ex);
        }

        mainStage.setOnCloseRequest(event -> Platform.exit());
    }
    public static Stage getMainStage() {
        return mainStage;
    }

    public static DatabaseAPI getApi() {
        return api;
    }



}
