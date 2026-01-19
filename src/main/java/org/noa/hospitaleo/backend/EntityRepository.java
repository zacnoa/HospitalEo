package org.noa.hospitaleo.backend;

import org.noa.hospitaleo.frontend.entity.*;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class EntityRepository {

    private  final Map<UUID, Doctor> doctorMap = new HashMap<>();
    private  final Map<UUID, Patient> patientMap = new HashMap<>();
    private  final Map<UUID, Room> roomMap = new HashMap<>();
    private  final Map<UUID, Visitor> visitorMap = new HashMap<>();
    private  final Map<UUID, Department> departmentMap = new HashMap<>();

    protected EntityRepository() {}

    public  Map<UUID, Department> getDepartmentMap() {
        return departmentMap;
    }
    public  Map<UUID, Doctor> getDoctorMap() {
        return doctorMap;
    }
    public  Map<UUID, Patient> getPatientMap() {
        return patientMap;
    }
    public  Map<UUID, Room> getRoomMap() {
        return roomMap;
    }
    public  Map<UUID, Visitor> getVisitorMap() {
        return visitorMap;
    }



    public  List<Department> allDepartmentsAsList()
    {
        return departmentMap.values().stream().toList();
    }
    public  Department getDepartment(UUID id) {
        return departmentMap.get(id);
    }

    public  List<Department> getDepartments(List<UUID> ids) {
        return ids.stream().map(this::getDepartment).toList();
    }

    public  Doctor getDoctor(UUID id) {
        return doctorMap.get(id);
    }

    public  Patient getPatient(UUID id) {
        return patientMap.get(id);
    }

    public  Room getRoom(UUID id) {
        return roomMap.get(id);
    }

    public  Visitor getVisitor(UUID id) {
        return visitorMap.get(id);
    }

    public  List<Doctor> allDoctorsAsList() {
        return doctorMap.values().stream()
                .toList();
    }

    public  List<Patient> allPatientsAsList() {
        return patientMap.values().stream()
                .toList();
    }

    public  List<Room> allRoomsAsList() {
        return roomMap.values().stream()
                .toList();
    }

    public  List<Visitor> allVisitorsAsList() {
        return visitorMap.values().stream()
                .toList();
    }

    public  List<Patient> getPatients(List<UUID> ids) {
        return ids.stream()
                .map(this::getPatient)
                .toList();

    }

    public  List<Doctor> getDoctors(List<UUID> ids) {
        return ids.stream()
                .map(this::getDoctor)
                .toList();

    }

    public  List<Room> getRooms(List<UUID> ids) {
        return ids.stream()
                .map(this::getRoom)
                .toList();

    }

    public  List<Visitor> getVisitors(List<UUID> ids) {
        return ids.stream()
                .map(this::getVisitor)
                .toList();
    }

    public abstract void loadAll()  throws SQLException;
}