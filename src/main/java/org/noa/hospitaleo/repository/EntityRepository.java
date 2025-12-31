package org.noa.hospitaleo.repository;

import org.noa.hospitaleo.entity.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EntityRepository {

    protected static final Map<String, Doctor> doctorMap = new HashMap<>();
    protected static final Map<String, Patient> patientMap = new HashMap<>();
    protected static final Map<String, Room> roomMap = new HashMap<>();
    protected static final Map<String, Visitor> visitorMap = new HashMap<>();
    protected static final Map<String, Department> departmentMap = new HashMap<>();

    protected EntityRepository() {}

    public static Map<String, Department> getDepartmentMap() {
        return departmentMap;
    }
    public static Map<String, Doctor> getDoctorMap() {
        return doctorMap;
    }
    public static Map<String, Patient> getPatientMap() {
        return patientMap;
    }
    public static Map<String, Room> getRoomMap() {
        return roomMap;
    }
    public static Map<String, Visitor> getVisitorMap() {
        return visitorMap;
    }


    public static List<Department> allDepartmentsAsList()
    {
        return departmentMap.values().stream().toList();
    }
    public static Department getDepartment(String id) {
        return departmentMap.get(id);
    }
    public static List<Department> getDepartments(List<String> ids) {
        return ids.stream().map(EntityRepository::getDepartment).toList();
    }

    public static Doctor getDoctor(String id) {
        return doctorMap.get(id);
    }

    public static Patient getPatient(String id) {
        return patientMap.get(id);
    }

    public static Room getRoom(String id) {
        return roomMap.get(id);
    }

    public static Visitor getVisitor(String id) {
        return visitorMap.get(id);
    }

    public static List<Doctor> allDoctorsAsList() {
        return doctorMap.values().stream()
                .toList();
    }

    public static List<Patient> allPatientsAsList() {
        return patientMap.values().stream()
                .toList();
    }

    public static List<Room> allRoomsAsList() {
        return roomMap.values().stream()
                .toList();
    }

    public static List<Visitor> allVisitorsAsList() {
        return visitorMap.values().stream()
                .toList();
    }

    public static List<Patient> getPatients(List<String> ids) {
        return ids.stream()
                .map(id -> getPatient(id))
                .toList();

    }

    public static List<Doctor> getDoctors(List<String> ids) {
        return ids.stream()
                .map(id -> getDoctor(id))
                .toList();

    }

    public static List<Room> getRooms(List<String> ids) {
        return ids.stream()
                .map(id -> getRoom(id))
                .toList();

    }

    public static List<Visitor> getVisitors(List<String> ids) {
        return ids.stream()
                .map(id -> getVisitor(id))
                .toList();
    }

}