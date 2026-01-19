package org.noa.hospitaleo.frontend.entity;


import org.noa.hospitaleo.frontend.interfaces.Identifiable;
import java.io.Serializable;
import java.util.*;


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