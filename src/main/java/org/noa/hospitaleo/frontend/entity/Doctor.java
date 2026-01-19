package org.noa.hospitaleo.frontend.entity;

import java.io.Serializable;
import java.util.UUID;


public final class Doctor extends Employee implements  Serializable {

    private String specialty;

    public Doctor(String name, String oib, String specialty, Double salary) {
        super(name, oib, salary);
        this.specialty = specialty;
    }

    public Doctor(String name, String oib, String specialty, Double salary, UUID id) {
        super(name, oib, salary, id);
        this.specialty = specialty;
    }


    public String getSpecialty() {
        return specialty;
    }


    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}


