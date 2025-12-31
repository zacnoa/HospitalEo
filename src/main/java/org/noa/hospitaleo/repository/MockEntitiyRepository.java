package org.noa.hospitaleo.repository;

import org.noa.hospitaleo.entity.*;
import org.noa.hospitaleo.enums.PatientStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockEntitiyRepository implements EntitiyRepository {

    protected  static final Map<String, Doctor> doctorStorage=new HashMap<>();
    protected  static final Map<String, Patient> patientStorage=new HashMap<>();
    protected  static final Map<String, Room> roomStorage=new HashMap<>();
    protected   static final Map<String, Visitor> visitorStorage =new HashMap<>();
    protected   static final List<Department> departmentStorage=new ArrayList<>();


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


    @Override
    public void loadAll() {

        Department d1 = new Department("Neurologija");
        Department d2 = new Department("Kardiologija");

        departmentStorage.add(d1);
        departmentStorage.add(d2);


        Doctor d1Doc1 = new Doctor.DoctorBuilder("Ivan Barisic", "12232144", "neurolog", 12.5).build();
        d1.addDoctor(d1Doc1);


        Room d1Room1 = new Room();
        d1.addRoom(d1Room1);

        Patient d1Patient1 = new Patient("Luka Lukacevic", "129495", "java", PatientStatus.HOSPITALIZED);
        d1Patient1.setDoctor(d1Doc1);
        d1Patient1.setRoom(d1Room1);
        d1.addPatient(d1Patient1);


        doctorStorage.put(d1Doc1.getId(),  d1Doc1);
        roomStorage.put(d1Room1.getId(),  d1Room1);
        patientStorage.put(d1Patient1.getId(),  d1Patient1);


        Doctor d1Doc2 = new Doctor.DoctorBuilder("Marija Novak", "98765432", "neurolog", 10.0).build();
        d1.addDoctor(d1Doc2);

        Room d1Room2 = new Room();
        d1.addRoom(d1Room2);

        Patient d1Patient2 = new Patient("Ana Anic", "559912", "migrena", PatientStatus.UNDER_DIAGNOSIS);
        d1Patient2.setDoctor(d1Doc2);
        d1Patient2.setRoom(d1Room2);
        d1.addPatient(d1Patient2);

        doctorStorage.put(d1Doc2.getId(),  d1Doc2);
        roomStorage.put(d1Room2.getId(),  d1Room2);
        patientStorage.put(d1Patient2.getId(),  d1Patient2);


        Doctor d2Doc1 = new Doctor.DoctorBuilder("Marko Horvat", "22334455", "kardiolog", 15.0).build();
        d2.addDoctor(d2Doc1);

        Room d2Room1 = new Room();
        d2.addRoom(d2Room1);

        Patient d2Patient1 = new Patient("Petar Peric", "778899", "aritmija", PatientStatus.HOSPITALIZED);
        d2Patient1.setDoctor(d2Doc1);
        d2Patient1.setRoom(d2Room1);
        d2.addPatient(d2Patient1);


        doctorStorage.put(d2Doc1.getId(),  d2Doc1);
        roomStorage.put(d2Room1.getId(),  d2Room1);
        patientStorage.put(d2Patient1.getId(),  d2Patient1);


        Doctor d2Doc2 = new Doctor.DoctorBuilder("Ivana Krizman", "66778899", "kardiolog", 13.0).build();
        d2.addDoctor(d2Doc2);

        Room d2Room2 = new Room();
        d2.addRoom(d2Room2);

        Patient d2Patient2 = new Patient("Marija Marinkovic", "443322", "hipertenzija", PatientStatus.UNDER_DIAGNOSIS);
        d2Patient2.setDoctor(d2Doc2);
        d2Patient2.setRoom(d2Room2);
        d2.addPatient(d2Patient2);

        doctorStorage.put(d2Doc2.getId(),  d2Doc2);
        roomStorage.put(d2Room2.getId(),  d2Room2);
        patientStorage.put(d2Patient2.getId(),  d2Patient2);


    }







}

