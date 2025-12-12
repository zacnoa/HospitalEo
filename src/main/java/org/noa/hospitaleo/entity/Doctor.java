package org.noa.hospitaleo.entity;

import jakarta.json.bind.annotation.JsonbTransient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.noa.hospitaleo.interfaces.PrintableMenuSelection;
import org.noa.hospitaleo.repository.JSONEntityRepository;


public final class Doctor extends Employee implements PrintableMenuSelection, Serializable {

    private String specialty;
    private List<String> patientIds = new ArrayList<>();


    public Doctor() {}

    private Doctor(DoctorBuilder doctorBuilder) {
        super(doctorBuilder.name, doctorBuilder.OIB, doctorBuilder.salary);
        this.specialty = doctorBuilder.specialty;
        this.patientIds = doctorBuilder.patients;
    }



    @JsonbTransient
    public List<Patient> getPatients() {
        return JSONEntityRepository.getPatientsByIds(patientIds);
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


    @Override
    public void basicInformation() {
        System.out.println("Doktor: " + super.getName() + " specijalizacija: " + specialty);
    }

    public static class DoctorBuilder {
        private String name;
        private String OIB;
        private String specialty;
        private Double salary;
        private List<String> patients = new ArrayList<>();

        public DoctorBuilder(String name, String OIB, String specialty, Double salary) {
            this.name = name;
            this.OIB = OIB;
            this.specialty = specialty;
            this.salary = salary;
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
