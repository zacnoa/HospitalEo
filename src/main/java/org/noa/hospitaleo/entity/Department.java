package org.noa.hospitaleo.entity;


import jakarta.json.bind.annotation.JsonbTransient;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.interfaces.Identifiable;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class Department implements  Serializable, Identifiable {

    private String name;
    private final UUID id;


    public Department(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }
    public Department(String name, UUID id)
    {
        this.name = name;
        this.id = id;
    }


    @Override
    public UUID getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }






}