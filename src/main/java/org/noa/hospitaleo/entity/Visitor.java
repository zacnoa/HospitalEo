package org.noa.hospitaleo.entity;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Scanner;



public class Visitor extends Person implements Serializable {

    public Visitor(String ime, String oib) {
        super(ime, oib);
    }

    public Visitor() {
    }


}