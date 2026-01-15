package org.noa.hospitaleo.entity;


import org.noa.hospitaleo.interfaces.Identifiable;

import java.io.Serializable;
import java.util.*;

/**
 * Predstavlja osobu
 * Omogucuje obradu osobnih informacije osobe
 */

public abstract class Person implements Serializable, Identifiable {

    private String name;
    private String oib;
    private UUID id;


    /**
     * @param name Ime
     * @param oib OIB
     */
    protected Person(String name, String oib) {
        this.name = name;
        this.oib = oib;
        this.id = UUID.randomUUID();

    }
    protected Person(String name, String oib, UUID id)
    {
        this.name = name;
        this.oib = oib;
        this.id = id;
    }

    /**
     * @return Ime
     */
    @Override
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return OIB
     */

    public String getOib() {
        return oib;
    }


    public void setOib(String oib) {
        this.oib = oib;
    }

    @Override
    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }




}