package org.noa.hospitaleo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.noa.hospitaleo.entity.*;

public class JSONEntityRepository implements EntitiyRepository {

    public static Map<String,Doctor> doctorStorage=new HashMap<>();
    public static Map<String,Patient> patientStorage=new HashMap<>();
    public static Map<String,Room> roomStorage=new HashMap<>();
    public static Map<String,Visitor> visitorStorage =new HashMap<>();
    public static List<Department> departmentStorage=new ArrayList<>();


    public static Doctor findDoctor(String id)
    {
        return doctorStorage.get(id);
    }
    public static Patient findPatient(String id)
    {
        return patientStorage.get(id);
    }
    public static Room findRoom(String id)
    {
        return roomStorage.get(id);
    }
    public static Visitor findVisitor(String id)
    {
        return visitorStorage.get(id);
    }

    public static List<Doctor> allDoctorsAsList()
    {
        return doctorStorage.values().stream()
                .toList();
    }
    public static List<Patient> allPatientsAsList()
    {
        return patientStorage.values().stream()
                .toList();
    }
    public static List<Room> allRoomsAsList()
    {
        return roomStorage.values().stream()
                .toList();
    }
    public static List<Visitor> allVisitorsAsList()
    {
        return visitorStorage.values().stream()
                .toList();
    }

    public static List<Patient> getPatientsByIds( List<String> ids)
    {
        return ids.stream()
                .map(id->findPatient(id))
                .toList();

    }
    public static List<Doctor> getDoctorsByIds( List<String> ids)
    {
        return ids.stream()
                .map(id->findDoctor(id))
                .toList();

    }
    public static List<Room> getRoomsByIds( List<String> ids)
    {
        return ids.stream()
                .map(id->findRoom( id))
                .toList();

    }
    public static List<Visitor> getVisitorsByIds(List<String> ids)
    {
        return ids.stream()
                .map(id->findVisitor(id))
                .toList();
    }

    public static List<Department> getDepartmentStorage()
    {
        return departmentStorage;
    }
    public static void setDepartmentStorage(List<Department> departments)
    {
       departmentStorage=departments;
    }


    @Override
    public void loadAll() {

    }
}
