package org.noa.hospitaleo.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import org.noa.hospitaleo.enums.PatientStatus;
import org.noa.hospitaleo.interfaces.PrintableMenuSelection;
import org.noa.hospitaleo.repository.JSONEntityRepository;

import java.io.Serializable;
import java.util.Optional;


public class Patient extends Person implements PrintableMenuSelection, Serializable {

    private String doctorId;
    private String diagnosis;
    public PatientStatus status;
    private String roomId;

    protected Patient(PatientBuilder patientBuilder) {
        super(patientBuilder.name, patientBuilder.OIB);
        this.diagnosis = patientBuilder.diagnosis;
        this.status = patientBuilder.status;
    }

    public Patient() {}


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





    @Override
    public void basicInformation() {
        System.out.println("Pacijent: " + super.getName() + " OIB: " + super.getOib());
    }



    public static class PatientBuilder<T extends PatientBuilder<T>> {
        String name;
        String OIB;
        String diagnosis;
        PatientStatus status;
        Doctor doctor;
        Room room;

        public PatientBuilder(String name, String OIB, String diagnosis, PatientStatus status) {
            this.name = name;
            this.OIB = OIB;
            this.diagnosis = diagnosis;
            this.status = status;
        }

        public T doctor(Doctor doctor) {
            this.doctor = doctor;
            return (T) this;
        }

        public T room(Room room) {
            this.room = room;
            return (T) this;
        }

        public Patient build() {
            return new Patient(this);
        }
    }
}