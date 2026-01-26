package org.noa.hospitaleo.frontend.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import org.noa.hospitaleo.frontend.enums.PatientStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;


public class Patient extends Person implements  Serializable {

    private UUID doctorId;
    private String diagnosis;
    private PatientStatus status;
    private UUID roomId;
    private LocalDateTime admittedAt;

    public Patient(String name, String oib, String diagnosis,UUID doctorId,UUID roomId, PatientStatus status) {
        super(name, oib);
        this.diagnosis = diagnosis;
        this.doctorId = doctorId;
        this.status = status;
        this.roomId = roomId;
        this.admittedAt=LocalDateTime.now();
    }
    public Patient(String name, String oib, String diagnosis, UUID doctorId, UUID roomId, PatientStatus status, UUID id, LocalDateTime admittedAt) {
        super(name, oib,id);
        this.diagnosis = diagnosis;
        this.doctorId = doctorId;
        this.status = status;
        this.roomId = roomId;
        this.admittedAt=admittedAt;
    }



    @JsonbTransient
    public void setDoctor(Doctor doctor) {
        this.doctorId = doctor != null ? doctor.getId() : null;
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

    public LocalDateTime getAdmittedAt() {
        return admittedAt;
    }
    public void setAdmittedAt(LocalDateTime admittedAt) {
        this.admittedAt = admittedAt;
    }





}