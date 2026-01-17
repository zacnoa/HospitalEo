package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.entity.Visitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class VisitorRoutes {

    public static void insertVisitor(Visitor visitor, Connection connection, UUID departmentId) throws SQLException
    {
        PersonRoutes.insertPerson(connection,visitor);
        String query = """
                INSERT INTO VISITORS (id,departmentId)
                VALUES (?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setObject(1, visitor.getId());
            preparedStatement.setObject(2, departmentId);
            preparedStatement.executeUpdate();
        }
    }
    public static void insertVisitorTransactional(Visitor visitor, Connection connection, UUID departmentId) throws SQLException {
        try {
            connection.setAutoCommit(false);

            PersonRoutes.insertPerson(connection, visitor);

            String query = """
                INSERT INTO VISITORS (id, departmentId)
                VALUES (?, ?)
                """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setObject(1, visitor.getId());
                preparedStatement.setObject(2, departmentId);
                preparedStatement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
