package org.noa.hospitaleo.entity;


import java.io.Serializable;
import java.util.UUID;


public class Visitor extends Person implements Serializable {

    public Visitor(String ime, String oib) {
        super(ime, oib);
    }
    public Visitor(String ime,String oib, UUID id)
    {
        super(ime,oib,id);
    }


}