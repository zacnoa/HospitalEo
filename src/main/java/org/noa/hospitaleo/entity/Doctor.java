package org.noa.hospitaleo.entity;

import jakarta.json.bind.annotation.JsonbTransient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.noa.hospitaleo.HospitalEoApplication;


public final class Doctor extends Employee implements  Serializable {

    private String specialty;
    private List<UUID> patientIds;

    public Doctor(String name, String oib, String specialty,Double salary)
    {
        super(name,oib,salary);
        this.specialty = specialty;
        this.patientIds = new ArrayList<>();
    }
    public Doctor(String name, String oib,UUID id,Double salary,String specialty)
    {
        super(name,oib,salary,id);
        this.specialty = specialty;
        this.patientIds = new ArrayList<>();
    }



    @JsonbTransient
    public List<Patient> getPatients() {
        return HospitalEoApplication.getRepository().getPatients(patientIds);
    }

    @JsonbTransient
    public void setPatients(List<Patient> patients) {
        this.patientIds = patients.stream()
                .map(Patient::getId)
                .toList();
    }

    public String getSpecialty() {
        return specialty;
    }


    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<UUID> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(List<UUID> patients) {
        this.patientIds = patients;
    }

    public void addPatient(Patient patient) {
        patientIds.add(patient.getId());
    }


}
