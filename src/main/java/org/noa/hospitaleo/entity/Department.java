package org.noa.hospitaleo.entity;


import jakarta.json.bind.annotation.JsonbTransient;
import org.noa.hospitaleo.interfaces.Identifiable;
import org.noa.hospitaleo.repository.RuntimeEntityRepository;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class Department implements  Serializable, Identifiable {

    private String name;


    private transient List<String> doctorIds = new ArrayList<>();
    private transient List<String> roomIds = new ArrayList<>();
    private transient List<String> patientIds = new ArrayList<>();
    private transient List<String> visitorIds = new ArrayList<>();
    private final String id;


    public Department(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }


    @JsonbTransient
    public List<Doctor> getDoctors() {
        return RuntimeEntityRepository.getDoctors(doctorIds);
    }

    @JsonbTransient
    public void setDoctors(List<Doctor> list) {
        this.doctorIds = list.stream().map(Doctor::getId).toList();
    }

    @JsonbTransient
    public List<Room> getRooms() {
        return RuntimeEntityRepository.getRooms(roomIds);
    }

    @JsonbTransient
    public void setRooms(List<Room> list) {
        this.roomIds = list.stream().map(Room::getId).toList();
    }

    @JsonbTransient
    public List<Patient> getPatients() {
        return RuntimeEntityRepository.getPatients(patientIds);
    }

    @JsonbTransient
    public void setPatients(List<Patient> list) {
        this.patientIds = list.stream().map(Patient::getId).toList();
    }

    @Override
    public String getId() {
        return id;
    }
    @Override
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
        return RuntimeEntityRepository.getVisitors(visitorIds);
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


    public Map<String, List<Patient>> groupByDoctor() {
        return getPatients().stream().collect(Collectors.groupingBy(p -> p.getDoctor().getName()));
    }



}