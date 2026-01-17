package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.entity.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoctorRoutes {
    public static List<Doctor> getDepartmentDoctors(UUID departmentId, Connection connection ) throws SQLException {
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
                WHERE DOCTORS.departmentId = ?
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
    public static Doctor getDoctor(UUID doctorId,Connection connection) throws SQLException {
        Doctor temp = null;
        String query = """
                SELECT PERSONS.name,
                PERSONS.oib,
                EMPLOYEES.salary,
                DOCTORS.id,
                DOCTORS.specialty
                FROM DOCTORS
                JOIN PERSONS ON DOCTORS.id = PERSONS.id
                JOIN EMPLOYEES ON DOCTORS.id = EMPLOYEES.id
                WHERE DOCTORS.id = ? """;


        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setObject(1,doctorId);
            try(ResultSet rs = statement.executeQuery())
            {
                while(rs.next())
                {
                    temp = new Doctor(
                            rs.getString("name"),
                            rs.getString("oib"),
                            rs.getString("specialty"),
                            rs.getDouble("salary"),
                            rs.getObject("id", UUID.class)
                    );
                }
            }
        }
        return temp;
    }
    public static void insertDoctor(Doctor doctor,Connection connection,UUID departmentId) throws SQLException
    {
        connection.setAutoCommit(false);
        try {
            PersonRoutes.insertPerson(connection, doctor);
            EmployeeRoutes.insertEmployee(connection, doctor);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }

        String query = """
               INSERT INTO DOCTORS (id,departmentId,specialty) 
               VALUES (?,?,?)
                """;
        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setObject(1,doctor.getId());
            statement.setObject(2,departmentId);
            statement.setObject(3,doctor.getSpecialty());
            statement.executeUpdate();
        }
    }
    public static List<Doctor> searchDoctors(Connection connection, String name, String oib, String specialty) throws SQLException
    {
        List<Doctor> doctors = new ArrayList<>();
        String query = """
                SELECT
                    d.id,
                    p.name,
                    p.oib,
                    d.specialty,
                    e.salary
                FROM DOCTORS d
                JOIN PERSONS p ON d.id = p.id
                JOIN EMPLOYEES e ON d.id = e.id
                WHERE
                    ( ? = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', ?, '%')) )
                AND ( ? = '' OR p.oib = ? )
                AND ( ? = '' OR LOWER(d.specialty) LIKE LOWER(CONCAT('%', ?, '%')) );
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, name);

            statement.setString(3, oib);
            statement.setString(4, oib);

            statement.setString(5, specialty);
            statement.setString(6, specialty);

            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    Doctor temp = new Doctor(
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
