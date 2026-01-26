package org.noa.hospitaleo.backend.utils.mappers;

import org.noa.hospitaleo.frontend.entity.Patient;
import org.noa.hospitaleo.frontend.enums.PatientStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PatientMapper {

    PatientMapper() {}

    public static List<Patient> mapToPatientList(ResultSet resultSet) throws SQLException
    {
        List<Patient> patients = new ArrayList<>();
        while (resultSet.next()) {
            PatientStatus status = PatientStatus.fromValue(resultSet.getString("status"));

            Patient temp = new Patient(
                    resultSet.getString("name"),
                    resultSet.getString("oib"),
                    resultSet.getString("diagnosis"),
                    resultSet.getObject("doctorId", UUID.class),
                    resultSet.getObject("roomId", UUID.class),
                    status,
                    resultSet.getObject("id", UUID.class),
                    resultSet.getObject("createdAt", java.time.LocalDateTime.class)
            );
            patients.add(temp);
        }
        return patients;
    }
    public static Patient mapToPatient(ResultSet rs) throws SQLException {

        Patient temp = null;
        if (rs.next()) {
            PatientStatus status = PatientStatus.fromValue(rs.getString("status"));

            temp = new Patient(
                    rs.getString("name"),
                    rs.getString("oib"),
                    rs.getString("diagnosis"),
                    rs.getObject("doctorId", UUID.class),
                    rs.getObject("roomId", UUID.class),
                    status,
                    rs.getObject("id", UUID.class),
                    rs.getObject("createdAt", java.time.LocalDateTime.class)
            );
        }
        return temp;
    }
}
