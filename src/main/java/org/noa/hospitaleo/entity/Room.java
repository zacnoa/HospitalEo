package org.noa.hospitaleo.entity;

import org.noa.hospitaleo.repository.RuntimeEntityRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Predstavlja sobu
 */

public class Room implements  Serializable {

    private List<String> patients;
    private String id;

    /**
     * Konstruktor
     */
    public Room() {
        this.id = UUID.randomUUID().toString();
        this.patients = new ArrayList<>();
    }

    /**
     * @return Id
     */
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Polje pacijenata (objekti)
     */

    public List<Patient> getPatients() {
        return RuntimeEntityRepository.getPatients(patients);
    }


    public void setPatients(List<Patient> patients) {
        this.patients = patients.stream()
                .map(Patient::getId)
                .toList();
    }

    /**
     * @return Lista ID-ova pacijenata (za serijalizaciju)
     */

    public List<String> getPatientsId() {
        return patients;
    }


    public void setPatientsId(List<String> patients) {
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