package org.noa.hospitaleo.backend.utils.queries;

public enum PersonQueries {


    INSERT_PERSON("""
                INSERT INTO PERSONS (name,id,oib)
                VALUES (?,?,?)""");

    private final String query;
    PersonQueries(String query)
    {
        this.query = query;
    }
    public String getQuery()
    {
        return query;
    }
}
