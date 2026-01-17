package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.entity.Patient;
import org.noa.hospitaleo.enums.PatientStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PatientRoutes {

    public static List<Patient> getDepartmentPatients(Connection connection, UUID departmentId) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = """
                SELECT PERSONS.name,
                       PERSONS.oib,
                       PATIENTS.id,
                       PATIENTS.diagnosis,
                       PATIENTS.doctorId,
                       PATIENTS.departmentId,
                       PATIENTS.roomId,
                       PATIENTS.status
                FROM PATIENTS
                JOIN PERSONS ON PATIENTS.id = PERSONS.id
                WHERE PATIENTS.departmentId = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, departmentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    PatientStatus status = PatientStatus.fromValue(resultSet.getString("status"));

                    Patient temp = new Patient(
                            resultSet.getString("name"),
                            resultSet.getString("oib"),
                            resultSet.getString("diagnosis"),
                            resultSet.getObject("doctorId", UUID.class),
                            resultSet.getObject("roomId", UUID.class),
                            status,
                            resultSet.getObject("id", UUID.class)
                    );
                    patients.add(temp);
                }
            }
        }
        return patients;
    }

    public static Patient getPatient(Connection connection, UUID id) throws SQLException {
        Patient temp = null;
        String query = """
                SELECT PERSONS.name,
                       PERSONS.oib,
                       PATIENTS.id,
                       PATIENTS.diagnosis,
                       PATIENTS.doctorId,
                       PATIENTS.departmentId,
                       PATIENTS.roomId,
                       PATIENTS.status
                FROM PATIENTS
                JOIN PERSONS ON PATIENTS.id = PERSONS.id
                WHERE PATIENTS.id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    PatientStatus status = PatientStatus.fromValue(rs.getString("status"));

                    temp = new Patient(
                            rs.getString("name"),
                            rs.getString("oib"),
                            rs.getString("diagnosis"),
                            rs.getObject("doctorId", UUID.class),
                            rs.getObject("roomId", UUID.class),
                            status,
                            rs.getObject("id", UUID.class)
                    );
                }
            }
        }
        return temp;
    }

    public static void insertPatient(Connection connection, Patient patient, UUID departmentId) throws SQLException {
        PersonRoutes.insertPerson(connection, patient);

        String query = """
                INSERT INTO PATIENTS (id, departmentId, roomId, doctorId, status, diagnosis)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

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
    public static void insertPatientTransactional(Connection connection, Patient patient, UUID departmentId) throws SQLException {
        try {
            connection.setAutoCommit(false);
            insertPatient(connection, patient, departmentId);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public static List<Patient> getRoomPatients(Connection connection, UUID roomId) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = """
                SELECT PERSONS.name,
                       PERSONS.oib,
                       PATIENTS.id,
                       PATIENTS.diagnosis,
                       PATIENTS.doctorId,
                       PATIENTS.departmentId,
                       PATIENTS.roomId,
                       PATIENTS.status
                FROM PATIENTS
                JOIN PERSONS ON PATIENTS.id = PERSONS.id
                WHERE PATIENTS.roomId = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, roomId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    PatientStatus status = PatientStatus.fromValue(resultSet.getString("status"));

                    Patient temp = new Patient(
                            resultSet.getString("name"),
                            resultSet.getString("oib"),
                            resultSet.getString("diagnosis"),
                            resultSet.getObject("doctorId", UUID.class),
                            resultSet.getObject("roomId", UUID.class),
                            status,
                            resultSet.getObject("id", UUID.class)
                    );
                    patients.add(temp);
                }
            }
        }
        return patients;
    }

    public static List<Patient> patientSearch(Connection connection, String name, String oib, String diagnosis) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = """
                SELECT
                    p.id,
                    p.name,
                    p.oib,
                    pat.diagnosis,
                    pat.status,
                    pat.doctorId,
                    pat.roomId
                FROM PATIENTS pat
                JOIN PERSONS p ON pat.id = p.id
                WHERE
                    ( ? = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', ?, '%')) )
                AND ( ? = '' OR p.oib = ? )
                AND ( ? = '' OR LOWER(pat.diagnosis) LIKE LOWER(CONCAT('%', ?, '%')) );
                """;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, name);

            statement.setString(3, oib);
            statement.setString(4, oib);

            statement.setString(5, diagnosis);
            statement.setString(6, diagnosis);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PatientStatus status = PatientStatus.fromValue(resultSet.getString("status"));

                    Patient temp = new Patient(
                            resultSet.getString("name"),
                            resultSet.getString("oib"),
                            resultSet.getString("diagnosis"),
                            resultSet.getObject("doctorId", UUID.class),
                            resultSet.getObject("roomId", UUID.class),
                            status,
                            resultSet.getObject("id", UUID.class)
                    );
                    patients.add(temp);
                }
            }
        }
        return patients;
    }
}
