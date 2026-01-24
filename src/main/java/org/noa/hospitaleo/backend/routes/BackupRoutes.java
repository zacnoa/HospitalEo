package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.backend.utils.queries.BackupQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BackupRoutes {

    public static void dropBackupTable(Connection connection) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(BackupQueries.DROP_TABLE.getQuery());
        statement.executeUpdate();

    }
    public static void createBackup(Connection connectin) throws SQLException
    {
        PreparedStatement statement = connectin.prepareStatement(BackupQueries.CREATE_BACKUP.getQuery());
        statement.executeUpdate();
    }
}
