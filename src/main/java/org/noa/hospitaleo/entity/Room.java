package org.noa.hospitaleo.entity;

import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.interfaces.Identifiable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Predstavlja sobu
 */

public class Room implements  Serializable, Identifiable {

    private String name;
    private List<UUID> patients;
    private UUID id;

    /**
     * Konstruktor
     */
    public Room(String name) {
        this.id = UUID.randomUUID();
        this.patients = new ArrayList<>();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Id
     */
    @Override
    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return Polje pacijenata (objekti)
     */

    public List<Patient> getPatients() {
        return HospitalEoApplication.getRepository().getPatients(patients);
    }


    public void setPatients(List<Patient> patients) {
        this.patients = patients.stream()
                .map(Patient::getId)
                .toList();
    }

    /**
     * @return Lista ID-ova pacijenata (za serijalizaciju)
     */

    public List<UUID> getPatientsId() {
        return patients;
    }


    public void setPatientsId(List<UUID> patients) {
        this.patients = patients;
    }

    /**
     * Dodaje pacijenta u sobu
     * @param patient pacijent
     */
    public void addPatient(Patient patient) {
        patients.add(patient.getId());
    }


}