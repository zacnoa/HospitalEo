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
            HospitalEoApplication.logger.error(ex.getMessage(),ex);
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
            HospitalEoApplication.logger.error(ex.getMessage(),ex);
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
            HospitalEoApplication.logger.error(ex.getMessage(),ex);
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
            HospitalEoApplication.logger.error(ex.getMessage(),ex);
        }
    }
    public void showPatientCreationScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    HospitalEoApplication.class.getResource("patient-creation-screen.fxml")
            );

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Patient Creation");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            DialogUtils.showDisplayScreenErrorDialog("Došlo je do greške pri prikazivanju ekrana za kreiranje pacijenta");
            HospitalEoApplication.logger.error(ex.getMessage(),ex);
        }
    }
    public void showDoctorCreationScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    HospitalEoApplication.class.getResource("doctor-creation-screen.fxml")
            );
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Doctor Creation");
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex)
        {
            DialogUtils.showDisplayScreenErrorDialog("Doslo je do greske pri prikazivanju ekrana za kreiranje doktora");
            HospitalEoApplication.logger.error(ex.getMessage(), ex);
        }

    }
    public void showRoomCreationScreen()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource("room-creation-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Room Creation");
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex)
        {
            DialogUtils.showDisplayScreenErrorDialog("Doslo je do greske pri prikazivanju ekrana za kreiranje soba");
            HospitalEoApplication.logger.error(ex.getMessage(),ex);
        }
    }
    public void showDepartmentCreationScreen()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource("department-creation-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Department Creation");
            stage.setScene(scene);
            stage.show();
        }catch(IOException e)
        {
            DialogUtils.showDisplayScreenErrorDialog("Doslo je do greske pri prikazivanju ekrana za kreiranje odjela");
            HospitalEoApplication.logger.error(e.getMessage(),e);
        }
    }
    public void showLogOverviewScreen()
    {

        Stage stage = HospitalEoApplication.getMainStage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HospitalEoApplication.class.getResource("log-overview-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Log Overview");
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex)
        {
            DialogUtils.showDisplayScreenErrorDialog("Doslo je do greske pri prikazivanju ekrana za pregled loga");
            HospitalEoApplication.logger.error("Doslo je do greske pri prikazivanju ekrana za pregled loga",ex);
        }
    }

}

