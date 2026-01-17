package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeRoutes {

    public static void insertEmployee(Connection connection, Employee employee) throws SQLException
    {
        String query   = """
                INSERT INTO EMPLOYEES (id, salary)
                VALUES (?, ?)
        """;
        try(PreparedStatement s = connection.prepareStatement(query))
        {
            s.setObject(1, employee.getId());
            s.setObject(2, employee.getSalary());
            s.executeUpdate();
        }
    }
}
