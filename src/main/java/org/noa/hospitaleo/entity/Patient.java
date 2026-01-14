package org.noa.hospitaleo.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.enums.PatientStatus;

import java.io.Serializable;
import java.util.UUID;


public class Patient extends Person implements  Serializable {

    private UUID doctorId;
    private String diagnosis;
    private PatientStatus status;
    private UUID roomId;

    public Patient(String name, String oib, String diagnosis,UUID doctorId,UUID roomId, PatientStatus status) {
        super(name, oib);
        this.diagnosis = diagnosis;
        this.doctorId = doctorId;
        this.status = status;
        this.roomId = roomId;
    }




    @JsonbTransient
    public Doctor getDoctor() {
        return HospitalEoApplication.getRepository().getDoctor(doctorId);
    }

    @JsonbTransient
    public void setDoctor(Doctor doctor) {
        this.doctorId = doctor != null ? doctor.getId() : null;
    }
    @JsonbTransient
    public Room getRoom() {
        return HospitalEoApplication.getRepository().getRoom(roomId);
    }

    @JsonbTransient
    public void setRoom(Room room) {
        this.roomId = room != null ? room.getId() : null;
    }


    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID id) {
        this.doctorId = id;
    }


    public UUID getRoomId() {
        return roomId;
    }


    public void setRoomId(UUID id) {
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