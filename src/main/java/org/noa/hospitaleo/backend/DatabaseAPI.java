package org.noa.hospitaleo.backend;

import org.noa.hospitaleo.backend.routes.*;
import org.noa.hospitaleo.frontend.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
    public Department getDepartment(UUID departmentId) throws SQLException
    {
        return DepartmentRoutes.getDepartment(connection,departmentId);
    }
    public void addRoom(Room room,UUID departmentId) throws SQLException
    {
        RoomRoutes.insertRoom(connection,room,departmentId);

    }
    public Room getRoom(UUID roomId) throws SQLException
    {
        return RoomRoutes.getRoom(connection,roomId);
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
    public Doctor getDoctor(UUID doctorId) throws SQLException
    {
        return DoctorRoutes.getDoctor(connection,doctorId);
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
    public List<Patient> patientSearch(String name,String oib,String diagnosis,UUID departmentId) throws SQLException
    {
        return PatientRoutes.patientSearch(connection,name,oib,diagnosis,departmentId);

    }
    public List<Doctor> doctorSearch(String name,String oib,String specialty,UUID departmentId) throws SQLException
    {
        return DoctorRoutes.searchDoctors(connection,name,oib,specialty,departmentId);
    }
    public Map<String,Integer> getDepartmentStatistics(Department department) throws SQLException
    {
       return DepartmentRoutes.getDepartmentStatistics(connection,department.getId());
    }
    public Patient getLatestPatient() throws SQLException
    {
        return PatientRoutes.getLatestPatient(connection);
    }
    public String getDepartmentNameByRoom(UUID roomId) throws SQLException
    {
        return DepartmentRoutes.getDepartmentNameByRoom(connection,roomId);
    }

}
