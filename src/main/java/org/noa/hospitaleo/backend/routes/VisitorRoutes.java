package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.backend.utils.queries.VisitorQueries;
import org.noa.hospitaleo.frontend.entity.Visitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class VisitorRoutes {

    private VisitorRoutes() {}

    public static void insertVisitor(Visitor visitor, Connection connection, UUID departmentId) throws SQLException
    {
        String query = VisitorQueries.INSERT_VISITOR.getQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setObject(1, visitor.getId());
            preparedStatement.setObject(2, departmentId);
            preparedStatement.executeUpdate();
        }
    }

}
