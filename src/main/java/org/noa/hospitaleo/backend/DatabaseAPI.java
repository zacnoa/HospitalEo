package org.noa.hospitaleo.backend;

import org.noa.hospitaleo.backend.routes.*;
import org.noa.hospitaleo.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DatabaseAPI {
    private  Connection connection;
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
        DoctorRoutes.insertDoctor(doctor,connection,departmentId);
    }
    public void addPatient(Patient patient, UUID departmentId) throws SQLException
    {
        PatientRoutes.insertPatientTransactional(connection,patient,departmentId);
    }
    public void addUnderagePatient(UnderagePatient underagePatient, UUID departmentId) throws SQLException
    {

       UnderagePatientRoutes.insertUnderagePatient(underagePatient,departmentId,connection);
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
