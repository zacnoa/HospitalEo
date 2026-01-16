package org.noa.hospitaleo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.noa.hospitaleo.backend.EntityRepository;
import org.noa.hospitaleo.backend.RuntimeCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DatabaseUtils;
import util.DialogUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HospitalEoApplication extends Application {


    private static  Stage mainStage;
    private  static EntityRepository repository;
    public static final Logger logger = LoggerFactory.getLogger(HospitalEoApplication.class);
    private static  Connection connection;
    @SuppressWarnings("java:S2696") // Nemozemo promijeniti signature metode posto radimo override
    public  void start(Stage stage) {
        try {
            connection = DatabaseUtils.getConnection();
            repository = new RuntimeCache();
            repository.loadAll();
            mainStage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource("main-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            DialogUtils.showIOError("Greska pri otvaranju aplikacije");
            logger.error(ex.getMessage(), ex);
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            DialogUtils.showDatabaseErrorDialog("Greska pri ucitavanju podataka iz baze");
            logger.error(ex.getMessage(), ex);
        }

        mainStage.setOnCloseRequest(event -> Platform.exit());
    }
    public static Stage getMainStage() {
        return mainStage;
    }
    public static EntityRepository getRepository() {
        return repository;
    }
    public static Connection getConnection() {
        return connection;
    }


}
