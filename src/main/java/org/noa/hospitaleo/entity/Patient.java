package org.noa.hospitaleo.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import org.noa.hospitaleo.enums.PatientStatus;
import org.noa.hospitaleo.repository.JSONEntityRepository;

import java.io.Serializable;


public class Patient extends Person implements  Serializable {

    private String doctorId;
    private String diagnosis;
    private PatientStatus status;
    private String roomId;

    public Patient(String name, String oib, String diagnosis, PatientStatus status) {
        super(name, oib);
        this.diagnosis = diagnosis;
        this.status = status;
    }




    @JsonbTransient
    public Doctor getDoctor() {
        return JSONEntityRepository.findDoctor(doctorId);
    }

    @JsonbTransient
    public void setDoctor(Doctor doctor) {
        this.doctorId = doctor != null ? doctor.getId() : null;
    }
    @JsonbTransient
    public Room getRoom() {
        return JSONEntityRepository.findRoom(roomId);
    }

    @JsonbTransient
    public void setRoom(Room room) {
        this.roomId = room != null ? room.getId() : null;
    }


    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String id) {
        this.doctorId = id;
    }


    public String getRoomId() {
        return roomId;
    }


    public void setRoomId(String id) {
        this.roomId = id;
    }



    public String getDiagnosis() {
        return diagnosis;
    }


    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }





}