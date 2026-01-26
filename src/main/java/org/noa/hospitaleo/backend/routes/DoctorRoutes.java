package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.backend.utils.mappers.DoctorMapper;
import org.noa.hospitaleo.backend.utils.queries.DoctorQueries;
import org.noa.hospitaleo.frontend.entity.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoctorRoutes {

    private DoctorRoutes() {}

    public static List<Doctor> getDepartmentDoctors(UUID departmentId, Connection connection ) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = DoctorQueries.GET_DEPARTMENT_DOCTORS.getQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, departmentId);
            try (ResultSet rs = statement.executeQuery()) {
                doctors= DoctorMapper.mapToDoctorList(rs);
            }
        }
        return doctors;
    }
    public static Doctor getDoctor(Connection connection, UUID doctorId) throws SQLException {
        Doctor temp = null;
        String query = DoctorQueries.GET_DOCTOR.getQuery();


        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setObject(1,doctorId);
            try(ResultSet rs = statement.executeQuery())
            {
                temp= DoctorMapper.mapToDoctor(rs);
            }
        }
        return temp;
    }
    public static void insertDoctor(Doctor doctor,Connection connection,UUID departmentId) throws SQLException
    {
        String query =DoctorQueries.INSERT_DOCTOR.getQuery();
        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setObject(1,doctor.getId());
            statement.setObject(2,departmentId);
            statement.setObject(3,doctor.getSpecialty());
            statement.executeUpdate();
        }
    }
    public static List<Doctor> searchDoctors(Connection connection, String name, String oib, String specialty,UUID departmentId) throws SQLException
    {
        List<Doctor> doctors = new ArrayList<>();
        String query = DoctorQueries.DOCTOR_SEARCH.getQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, name);

            statement.setString(3, oib);
            statement.setString(4, oib);

            statement.setString(5, specialty);
            statement.setString(6, specialty);

            statement.setObject(7, departmentId);
            statement.setObject(8, departmentId);

            try (ResultSet rs = statement.executeQuery()) {

                doctors= DoctorMapper.mapToDoctorList(rs);

            }
        }
        return doctors;
    }
}
