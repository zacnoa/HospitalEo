package org.noa.hospitaleo.repository;

import org.noa.hospitaleo.entity.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EntityRepository {

    protected  final Map<String, Doctor> doctorMap = new HashMap<>();
    protected  final Map<String, Patient> patientMap = new HashMap<>();
    protected  final Map<String, Room> roomMap = new HashMap<>();
    protected  final Map<String, Visitor> visitorMap = new HashMap<>();
    protected  final Map<String, Department> departmentMap = new HashMap<>();

    protected EntityRepository() {}

    public  Map<String, Department> getDepartmentMap() {
        return departmentMap;
    }
    public  Map<String, Doctor> getDoctorMap() {
        return doctorMap;
    }
    public  Map<String, Patient> getPatientMap() {
        return patientMap;
    }
    public  Map<String, Room> getRoomMap() {
        return roomMap;
    }
    public  Map<String, Visitor> getVisitorMap() {
        return visitorMap;
    }


    public  List<Department> allDepartmentsAsList()
    {
        return departmentMap.values().stream().toList();
    }
    public  Department getDepartment(String id) {
        return departmentMap.get(id);
    }

    public  List<Department> getDepartments(List<String> ids) {
        return ids.stream().map(this::getDepartment).toList();
    }

    public  Doctor getDoctor(String id) {
        return doctorMap.get(id);
    }

    public  Patient getPatient(String id) {
        return patientMap.get(id);
    }

    public  Room getRoom(String id) {
        return roomMap.get(id);
    }

    public  Visitor getVisitor(String id) {
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

    public  List<Patient> getPatients(List<String> ids) {
        return ids.stream()
                .map(this::getPatient)
                .toList();

    }

    public  List<Doctor> getDoctors(List<String> ids) {
        return ids.stream()
                .map(this::getDoctor)
                .toList();

    }

    public  List<Room> getRooms(List<String> ids) {
        return ids.stream()
                .map(this::getRoom)
                .toList();

    }

    public  List<Visitor> getVisitors(List<String> ids) {
        return ids.stream()
                .map(this::getVisitor)
                .toList();
    }

}