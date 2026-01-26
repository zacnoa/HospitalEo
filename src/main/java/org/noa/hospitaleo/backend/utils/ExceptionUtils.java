package org.noa.hospitaleo.backend.utils;

import java.sql.SQLException;

public class ExceptionUtils {


    public static void handleDatabaseThreadException(Exception e) throws SQLException {


        switch (e)
        {
            case InterruptedException ex -> throw new SQLException("Thread interrupted while fetching from database",ex);
            case SQLException ex -> throw new SQLException("Error while fetching from database",ex);
            default -> throw new SQLException("Unkown exception",e);
        }

    }
}
