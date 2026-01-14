package org.noa.hospitaleo.database;

import org.noa.hospitaleo.enums.APIKeys;
import util.DialogUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

     private static Connection connection;
    public DatabaseConnection()
    {
        try {
            connection= DriverManager.getConnection(
                    APIKeys.DATABASE_URL.getKey(),
                    APIKeys.DATABASE_USERNAME.getKey()
                    , APIKeys.DATABASE_PASSWORD.getKey()
            );
        } catch (SQLException e) {
            DialogUtils.showDatabaseErrorDialog("Greska pri spajanju na bazu podataka");
        }

    }
    public  Connection getConnection()
    {
        return connection;
    }




}

