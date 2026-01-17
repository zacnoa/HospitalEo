package org.noa.hospitaleo.entity;

import javafx.scene.chart.PieChart;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.backend.routes.PatientRoutes;
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
    private UUID id;

    /**
     * Konstruktor
     */
    public Room(String name) {
        this.id = UUID.randomUUID();

        this.name = name;
    }
    public Room(String name, UUID id)
    {
        this.name = name;
        this.id = id;
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



}