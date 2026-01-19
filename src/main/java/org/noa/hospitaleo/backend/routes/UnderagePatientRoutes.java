package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.backend.utils.queries.UnderagePatientQueries;
import org.noa.hospitaleo.frontend.entity.UnderagePatient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UnderagePatientRoutes {

    private UnderagePatientRoutes() {}

    public  static void insertUnderagePatient(UnderagePatient u, Connection connection) throws SQLException {

        String query = UnderagePatientQueries.INSERT_UNDERAGEPATIENT.getQuery();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1,u.getId());
            ps.setObject(2,u.getLegalGuardian().getId());
            ps.executeUpdate();
        }
    }

}
