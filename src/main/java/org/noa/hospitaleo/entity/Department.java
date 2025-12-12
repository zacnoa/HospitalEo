package org.noa.hospitaleo.entity;


import jakarta.json.bind.annotation.JsonbTransient;
import org.noa.hospitaleo.interfaces.PrintableMenuSelection;
import org.noa.hospitaleo.repository.JSONEntityRepository;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class Department implements PrintableMenuSelection, Serializable {

    private String name;


    private transient List<String> doctorIds = new ArrayList<>();
    private transient List<String> roomIds = new ArrayList<>();
    private transient List<String> patientIds = new ArrayList<>();
    private transient List<String> visitorIds = new ArrayList<>();


    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }


    @JsonbTransient
    public List<Doctor> getDoctors() {
        return JSONEntityRepository.getDoctorsByIds(doctorIds);
    }

    @JsonbTransient
    public void setDoctors(List<Doctor> list) {
        this.doctorIds = list.stream().map(Doctor::getId).toList();
    }

    @JsonbTransient
    public List<Room> getRooms() {
        return JSONEntityRepository.getRoomsByIds(roomIds);
    }

    @JsonbTransient
    public void setRooms(List<Room> list) {
        this.roomIds = list.stream().map(Room::getId).toList();
    }

    @JsonbTransient
    public List<Patient> getPatients() {
        return JSONEntityRepository.getPatientsByIds(patientIds);
    }

    @JsonbTransient
    public void setPatients(List<Patient> list) {
        this.patientIds = list.stream().map(Patient::getId).toList();
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<String> getDoctorIds() {
        return doctorIds;
    }


    public void setDoctorIds(List<String> ids) {
        this.doctorIds = ids;
    }


    public List<String> getRoomIds() {
        return roomIds;
    }


    public void setRoomIds(List<String> ids) {
        this.roomIds = ids;
    }


    public List<String> getPatientIds() {
        return patientIds;
    }


    public void setPatientIds(List<String> ids) {
        this.patientIds = ids;
    }


    public List<Visitor> getVisitors() {
        return JSONEntityRepository.getVisitorsByIds(visitorIds);
    }


    public void setVisitors(List<Visitor> list) {
        this.visitorIds = list.stream().map(Visitor::getId).toList();
    }


    public List<String> getVisitorIds() {
        return visitorIds;
    }


    public void setVisitorIds(List<String> ids) {
        this.visitorIds = ids;
    }
    public void addPatient(Patient patient) {
        patientIds.add(patient.getId());
    }
    public  void addDoctor(Doctor doctor) {
        doctorIds.add(doctor.getId());
    }
    public void addVisitor(Visitor visitor) {
        visitorIds.add(visitor.getId());
    }
    public void addRoom(Room room) {
        roomIds.add(room.getId());
    }



    public List<Doctor> doctorSearchBySpecialty(Scanner sc) {
        System.out.println("Unesite traženu specijalizaciju");
        String specialty = sc.nextLine();
        return getDoctors().stream().filter(d -> specialty.equals(d.getSpecialty())).toList();
    }


    public List<Patient> patientSearchByDiagnosis(Scanner sc) {
        System.out.println("Unesite traženu dijagnozu");
        String diagnosis = sc.nextLine();
        return getPatients().stream().filter(p -> diagnosis.equals(p.getDiagnosis())).toList();
    }

    public Map<String, List<Patient>> groupByDoctor() {
        return getPatients().stream().collect(Collectors.groupingBy(p -> p.getDoctor().getName()));
    }

    @JsonbTransient
    public String getSelectionLine() {
        return name;
    }

    public Optional<Patient> findPatientByName(String name) {
        return Optional.of(getPatients().stream()
                .filter(p -> name.equalsIgnoreCase(p.getName()))
                .findAny()
                .orElseThrow(NoSuchElementException::new));
    }
}