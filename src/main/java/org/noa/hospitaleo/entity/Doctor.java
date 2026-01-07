package org.noa.hospitaleo.entity;

import jakarta.json.bind.annotation.JsonbTransient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.noa.hospitaleo.HospitalEoApplication;



public final class Doctor extends Employee implements  Serializable {

    private String specialty;
    private List<String> patientIds;



    private Doctor(DoctorBuilder doctorBuilder) {
        super(doctorBuilder.name, doctorBuilder.oib, doctorBuilder.salary);
        this.specialty = doctorBuilder.specialty;
        this.patientIds = doctorBuilder.patients;
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

    public List<String> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(List<String> patients) {
        this.patientIds = patients;
    }

    public void addPatient(Patient patient) {
        patientIds.add(patient.getId());
    }




    public static class DoctorBuilder {
        private final String name;
        private final String oib;
        private final String specialty;
        private final Double salary;
        private List<String> patients;

        public DoctorBuilder(String name, String oib, String specialty, Double salary) {
            this.name = name;
            this.oib = oib;
            this.specialty = specialty;
            this.salary = salary;
            this.patients = new ArrayList<>();
        }

        public DoctorBuilder patients(List<Patient> patients) {
            this.patients = patients.stream()
                    .map(Patient::getId)
                    .toList();
            return this;
        }

        public Doctor build() {
            return new Doctor(this);
        }
    }
}
