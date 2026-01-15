package org.noa.hospitaleo.repository;

import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.entity.*;
import org.noa.hospitaleo.enums.PatientStatus;
import org.noa.hospitaleo.enums.Tables;
import util.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseAPI {

    public static List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        Connection connection = HospitalEoApplication.getConnection();
        String query = "SELECT id, name FROM " + Tables.DEPARTMENTS;

        try (PreparedStatement prstmt = connection.prepareStatement(query)) {
            try (ResultSet rs = prstmt.executeQuery()) {
                while (rs.next()) {
                    Department temp = new Department(
                            rs.getString("name"),
                            rs.getObject("id", UUID.class)
                    );
                    DatabaseUtils.fillDepartment(temp);
                    departments.add(temp);
                }
            }
        }

        return departments;
    }

    public static List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        Connection connection = HospitalEoApplication.getConnection();
        String query = "SELECT id, name FROM " + Tables.ROOMS;

        try (PreparedStatement prstmt = connection.prepareStatement(query)) {
            try (ResultSet rs = prstmt.executeQuery()) {
                while (rs.next()) {
                    Room temp = new Room(
                            rs.getString("name"),
                            rs.getObject("id", UUID.class)
                    );
                    DatabaseUtils.fillRoom(temp);
                    rooms.add(temp);
                }
            }
        }

        return rooms;
    }

    public static List<Visitor> getAllVisitors() throws SQLException {
        List<Visitor> visitors = new ArrayList<>();
        Connection connection = HospitalEoApplication.getConnection();
        String query = """
            SELECT PERSONS.name,
                   PERSONS.oib,
                   PERSONS.id
            FROM VISITORS
            JOIN PERSONS ON VISITORS.personId = PERSONS.id
            """;

        try (PreparedStatement prsmt = connection.prepareStatement(query)) {
            try (ResultSet rs = prsmt.executeQuery()) {
                while (rs.next()) {
                    Visitor temp = new Visitor(
                            rs.getString("name"),
                            rs.getString("oib"),
                            rs.getObject("id", UUID.class)
                    );
                    visitors.add(temp);
                }
            }
        }

        return visitors;
    }

    public static List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = HospitalEoApplication.getConnection();
        String query = """
            SELECT PERSONS.name,
                   PERSONS.oib,
                   PERSONS.id,
                   EMPLOYEES.salary,
                   DOCTORS.specialty
            FROM DOCTORS
            JOIN PERSONS ON DOCTORS.personId = PERSONS.id
            JOIN EMPLOYEES ON DOCTORS.personId = EMPLOYEES.personId
            """;

        try (PreparedStatement prsmt = connection.prepareStatement(query)) {
            try (ResultSet rs = prsmt.executeQuery()) {
                while (rs.next()) {
                    Doctor temp = new Doctor(
                            rs.getString("name"),
                            rs.getString("oib"),
                            rs.getObject("id", UUID.class),
                            rs.getDouble("salary"),
                            rs.getString("specialty")
                    );
                    DatabaseUtils.fillDoctor(temp);
                    doctors.add(temp);
                }
            }
        }

        return doctors;
    }

    public static List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        Connection connection = HospitalEoApplication.getConnection();
        String query = """
            SELECT PERSONS.name,
                   PERSONS.oib,
                   PERSONS.id,
                   PATIENTS.diagnosis,
                   PATIENTS.doctorId,
                   PATIENTS.roomId,
                   PATIENTS.status
            FROM PATIENTS
            JOIN PERSONS ON PATIENTS.personId = PERSONS.id
            """;

        try (PreparedStatement prsmt = connection.prepareStatement(query)) {
            try (ResultSet rs = prsmt.executeQuery()) {
                while (rs.next()) {
                    String statusStr = rs.getString("status");
                    PatientStatus status = switch (statusStr) {
                        case "Hospitaliziran" -> PatientStatus.HOSPITALIZED;
                        case "Otpušten" -> PatientStatus.DISCHARGED;
                        case "Na pregledu" -> PatientStatus.UNDER_DIAGNOSIS;
                        default -> throw new IllegalArgumentException("Nepoznat status: " + statusStr);
                    };

                    Patient temp = new Patient(
                            rs.getString("name"),
                            rs.getString("oib"),
                            rs.getString("diagnosis"),
                            rs.getObject("doctorId", UUID.class),
                            rs.getObject("roomId", UUID.class),
                            status
                    );
                    patients.add(temp);
                }
            }
        }

        return patients;
    }
}
