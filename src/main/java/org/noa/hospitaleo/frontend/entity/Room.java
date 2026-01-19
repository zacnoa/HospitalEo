package org.noa.hospitaleo.frontend.entity;

import org.noa.hospitaleo.frontend.interfaces.Identifiable;

import java.io.Serializable;
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