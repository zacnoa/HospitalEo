package org.noa.hospitaleo.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.noa.hospitaleo.HospitalEoApplication;
import util.DialogUtils;

import java.io.IOException;

public class MenuController {

    public void showDoctorSearchScreen()
    {
        Stage stage = HospitalEoApplication.getMainStage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource("doctor-search-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Doctor Search");
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex)
        {
            DialogUtils.showDisplayScreenErrorDialog("Doslo je do greske pri prikazivanju ekrana za upravljanje doktorima");
            ex.printStackTrace();
        }

    }

    public void showPatientSearchScreen()
    {
        Stage stage = HospitalEoApplication.getMainStage();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource("patient-search-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Patient Search");
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex)
        {
            DialogUtils.showDisplayScreenErrorDialog("Doslo je do greske pri prikazivanju ekrana za upravljanje pacijentima");
        }

    }
    public void showMainScreen()
    {
        Stage stage = HospitalEoApplication.getMainStage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource("main-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex)
        {
            DialogUtils.showDisplayScreenErrorDialog("Doslo je do greske pri prikazivanju glavnog ekrana");
        }
    }
    public void showDepartmentOverviewScreen()
    {
        Stage stage = HospitalEoApplication.getMainStage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource("department-overview-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Department Overview");
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex)
        {
            DialogUtils.showDisplayScreenErrorDialog("Doslo je do greske pri prikazivanju ekrana za pregled odjeljka");
            ex.printStackTrace();
        }
    }

}

