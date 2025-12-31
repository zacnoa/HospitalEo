package org.noa.hospitaleo.entity;

public class SearchbyNameEntity {

    private final String name;
    private final String id;

    public SearchbyNameEntity(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public String getId()
    {
        return id;
    }

}
