package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.entity.UnderagePatient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class UnderagePatientRoutes {

    public  static void insertUnderagePatient(UnderagePatient u, UUID departmentId, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);

            PatientRoutes.insertPatient(connection, u, departmentId);
            VisitorRoutes.insertVisitor(u.getLegalGuardian(), connection, departmentId);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
