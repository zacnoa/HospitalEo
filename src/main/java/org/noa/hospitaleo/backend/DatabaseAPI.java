package org.noa.hospitaleo.backend;

import org.noa.hospitaleo.backend.routes.*;
import org.noa.hospitaleo.frontend.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DatabaseAPI {
    private final  Connection connection;
    public DatabaseAPI(Connection connection) {
        this.connection = connection;
    }
    public Connection getConnection() {
        return connection;
    }

    public  void addDepartment(Department department) throws SQLException
    {
        DepartmentRoutes.insertDepartment(connection,department);
    }
    public void addRoom(Room room,UUID departmentId) throws SQLException
    {
        RoomRoutes.insertRoom(connection,room,departmentId);

    }
    public List<Department> getAllDepartments() throws SQLException
    {
        return DepartmentRoutes.getAllDepartments(connection);
    }
    public void addDoctor(Doctor doctor,UUID departmentId) throws SQLException
    {
        connection.setAutoCommit(false);
        try {
            PersonRoutes.insertPerson(connection, doctor);
            EmployeeRoutes.insertEmployee(connection, doctor);
            DoctorRoutes.insertDoctor(doctor, connection, departmentId);
            connection.commit();
        }catch(SQLException e)
        {
            connection.rollback();
            throw e;
        }

    }
    public void addPatient(Patient patient, UUID departmentId) throws SQLException
    {
        connection.setAutoCommit(false);
        try {
            PersonRoutes.insertPerson(connection, patient);
            PatientRoutes.insertPatient(connection, patient, departmentId);
            connection.commit();
        }catch(SQLException e)
        {
            connection.rollback();
            throw e;
        }

    }
    public void addUnderagePatient(UnderagePatient underagePatient, UUID departmentId) throws SQLException
    {
        connection.setAutoCommit(false);
        try {
            PersonRoutes.insertPerson(connection, underagePatient);
            PatientRoutes.insertPatient(connection, underagePatient, departmentId);

            PersonRoutes.insertPerson(connection, underagePatient.getLegalGuardian());
            VisitorRoutes.insertVisitor(underagePatient.getLegalGuardian(), connection, departmentId);

            UnderagePatientRoutes.insertUnderagePatient(underagePatient, connection);

            connection.commit();
        }catch(SQLException e)
        {
            connection.rollback();
            throw e;
        }
    }
    public List<Patient> getRoomPatients(UUID roomId) throws SQLException
    {
        return PatientRoutes.getRoomPatients(connection,roomId);
    }
    public Patient getPatient(UUID id) throws SQLException
    {
        return PatientRoutes.getPatient(connection,id);
    }
    public List<Patient> patientSearch(String name,String oib,String diagnosis) throws SQLException
    {
        return PatientRoutes.patientSearch(connection,name,oib,diagnosis);

    }
    public List<Doctor> doctorSearch(String name,String oib,String specialty) throws SQLException
    {
        return DoctorRoutes.searchDoctors(connection,name,oib,specialty);
    }

}
