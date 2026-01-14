package org.noa.hospitaleo.database;

import org.noa.hospitaleo.HospitalEoApplication;
import util.DialogUtils;
import util.PropertiesHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

     private  Connection connection;
    public DatabaseConnection()
    {
        try {
            PropertiesHandler props= new PropertiesHandler();
            String url= props.getProperty("H2_URL");
            String username= props.getProperty("USER");
            String password= props.getProperty("PASSWORD");

            connection= DriverManager.getConnection(
                    url,
                    username,
                    password
            );
        }catch(IOException ex)
        {
            HospitalEoApplication.logger.error(ex.getMessage(),ex);
        }catch (SQLException ex) {
            DialogUtils.showDatabaseErrorDialog("Greska pri spajanju na bazu podataka");
            HospitalEoApplication.logger.error(ex.getMessage(),ex);
        }

    }
    public  Connection getConnection()
    {
        return connection;
    }




}

