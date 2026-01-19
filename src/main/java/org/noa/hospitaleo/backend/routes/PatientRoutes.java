package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.backend.utils.mappers.PatientMapper;
import org.noa.hospitaleo.backend.utils.queries.PatientQueries;
import org.noa.hospitaleo.frontend.entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PatientRoutes {

    private PatientRoutes() {}
    public static List<Patient> getDepartmentPatients(Connection connection, UUID departmentId) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = PatientQueries.GET_DEPARTMENT_PATIENTS.getQuery();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, departmentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                patients = PatientMapper.mapToPatientList(resultSet);
            }
        }
        return patients;
    }

    public static Patient getPatient(Connection connection, UUID id) throws SQLException {
        Patient temp = null;
        String query = PatientQueries.GET_PATIENT.getQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                temp = PatientMapper.mapToPatient(rs);
            }
        }
        return temp;
    }

    public static void insertPatient(Connection connection, Patient patient, UUID departmentId) throws SQLException {

        String query = PatientQueries.INSERT_PATIENT.getQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, patient.getId());
            statement.setObject(2, departmentId);
            statement.setObject(3, patient.getRoomId());
            statement.setObject(4, patient.getDoctorId());
            statement.setString(5, patient.getStatus().getValue());
            statement.setString(6, patient.getDiagnosis());
            statement.executeUpdate();
        }
    }

    public static List<Patient> getRoomPatients(Connection connection, UUID roomId) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = PatientQueries.GET_ROOM_PATIENTS.getQuery();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, roomId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                patients= PatientMapper.mapToPatientList(resultSet);
            }
        }
        return patients;
    }

    public static List<Patient> patientSearch(Connection connection, String name, String oib, String diagnosis) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = PatientQueries.PATIENT_SEARCH.getQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, name);

            statement.setString(3, oib);
            statement.setString(4, oib);

            statement.setString(5, diagnosis);
            statement.setString(6, diagnosis);

            try (ResultSet resultSet = statement.executeQuery()) {

                patients= PatientMapper.mapToPatientList(resultSet);

            }
        }
        return patients;
    }
}
