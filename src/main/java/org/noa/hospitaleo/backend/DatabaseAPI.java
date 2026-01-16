package org.noa.hospitaleo.backend;

import org.noa.hospitaleo.entity.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseAPI {
    private static Connection connection;
    public DatabaseAPI(Connection connection) {
        DatabaseAPI.connection = connection;
    }
    public static List<Doctor> getDepartmentDoctors(UUID departmentId ) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = """
                SELECT PERSONS.name,
                PERSONS.oib,
                EMPLOYEES.salary,
                DOCTORS.id,
                DOCTORS.specialty
                FROM DOCTORS
                JOIN PERSONS ON DOCTORS.id = PERSONS.id
                JOIN EMPLOYEES ON DOCTORS.id = EMPLOYEES.id 
                WHERE DOCTORS.department.id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, departmentId);
            try (ResultSet rs = statement.executeQuery()) {

                while(rs.next())
                {
                    Doctor temp= new Doctor(
                            rs.getString("name"),
                            rs.getString("oib"),
                            rs.getString("specialty"),
                            rs.getDouble("salary"),
                            rs.getObject("id", UUID.class)

                    );
                    doctors.add(temp);
                }
            }
        }
        return doctors;
    }
}
