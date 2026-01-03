package org.noa.hospitaleo.entity;


import jakarta.json.bind.annotation.JsonbTransient;
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
    private String id;


    /**
     * @param name Ime
     * @param oib OIB
     */
    protected Person(String name, String oib) {
        this.name = name;
        this.oib = oib;
        this.id = UUID.randomUUID().toString();

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
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    @JsonbTransient
    public String getSelectionLine() {
        return this.name;
    }


}