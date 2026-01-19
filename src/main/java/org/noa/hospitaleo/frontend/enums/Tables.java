package org.noa.hospitaleo.frontend.enums;

public enum Tables {

    ROOMS("ROOMS"),
    DOCTORS("DOCTORS"),
    PATIENTS("PATIENTS"),
    VISITORS("VISITORS"),
    PERSONS("PERSONS"),
    EMPLOYEES("EMPLOYEES"),
    UNDERAGEPATIENTS("UNDERAGEPATIENTS"),
    DEPARTMENTS("DEPARTMENTS");


    private final String table;

     Tables(String table) {
        this.table = table;
    }
    public String getTable() {
         return table;
    }

}

