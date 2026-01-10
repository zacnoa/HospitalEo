package org.noa.hospitaleo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.noa.hospitaleo.repository.MockEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HospitalEoApplication extends Application {

    private static  Stage mainStage;
    private static MockEntityRepository mockRepository;
    public static final Logger logger = LoggerFactory.getLogger(HospitalEoApplication.class);
    @Override
    @SuppressWarnings("java:S2696") // Nemozemo promijeniti signature metode posto radimo override
    public  void start(Stage stage) throws IOException {
         mockRepository=new MockEntityRepository();
        mockRepository.loadAll();
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource("main-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }
    public static MockEntityRepository getRepository() {
        return mockRepository;
    }


}
