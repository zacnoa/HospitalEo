package org.noa.hospitaleo.entity;

public class IdentifiableEntity {
    private String id;
    private String name;

    public IdentifiableEntity(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
