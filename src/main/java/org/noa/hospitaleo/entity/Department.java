package org.noa.hospitaleo.entity;


import jakarta.json.bind.annotation.JsonbTransient;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.interfaces.Identifiable;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class Department implements  Serializable, Identifiable {

    private String name;


    private transient List<UUID> doctorIds = new ArrayList<>();
    private transient List<UUID> roomIds = new ArrayList<>();
    private transient List<UUID> patientIds = new ArrayList<>();
    private transient List<UUID> visitorIds = new ArrayList<>();
    private final UUID id;


    public Department(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }
    public Department(String name, UUID id)
    {
        this.name = name;
        this.id = id;
    }


    @JsonbTransient
    public List<Doctor> getDoctors() {
        return HospitalEoApplication.getRepository().getDoctors(doctorIds);
    }

    @JsonbTransient
    public void setDoctors(List<Doctor> list) {
        this.doctorIds = list.stream().map(Doctor::getId).toList();
    }

    @JsonbTransient
    public List<Room> getRooms() {
        return HospitalEoApplication.getRepository().getRooms(roomIds);
    }

    @JsonbTransient
    public void setRooms(List<Room> list) {
        this.roomIds = list.stream().map(Room::getId).toList();
    }

    @JsonbTransient
    public List<Patient> getPatients() {
        return HospitalEoApplication.getRepository().getPatients(patientIds);
    }

    @JsonbTransient
    public void setPatients(List<Patient> list) {
        this.patientIds = list.stream().map(Patient::getId).toList();
    }

    public List<Visitor> getVisitors() {
        return HospitalEoApplication.getRepository().getVisitors(visitorIds);
    }


    public void setVisitors(List<Visitor> list) {
        this.visitorIds = list.stream().map(Visitor::getId).toList();
    }


    @Override
    public UUID getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<UUID> getDoctorIds() {
        return doctorIds;
    }


    public void setDoctorIds(List<UUID> ids) {
        this.doctorIds = ids;
    }


    public List<UUID> getRoomIds() {
        return roomIds;
    }


    public void setRoomIds(List<UUID> ids) {
        this.roomIds = ids;
    }


    public List<UUID> getPatientIds() {
        return patientIds;
    }


    public void setPatientIds(List<UUID> ids) {
        this.patientIds = ids;
    }




    public List<UUID> getVisitorIds() {
        return visitorIds;
    }


    public void setVisitorIds(List<UUID> ids) {
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