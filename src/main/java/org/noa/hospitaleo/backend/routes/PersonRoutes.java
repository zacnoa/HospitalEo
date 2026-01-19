package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.backend.utils.queries.PersonQueries;
import org.noa.hospitaleo.frontend.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonRoutes {

    private PersonRoutes() {}

    public static void insertPerson(Connection con, Person person)throws SQLException
    {
        String query = PersonQueries.INSERT_PERSON.getQuery();

        try(PreparedStatement pstmt = con.prepareStatement(query))
        {
            pstmt.setString(1, person.getName());
            pstmt.setObject(2, person.getId());
            pstmt.setString(3, person.getOib());
            pstmt.executeUpdate();
        }
    }
}
