package org.noa.hospitaleo.backend.utils.queries;

public enum VisitorQueries {

    INSERT_VISITOR("""
                INSERT INTO VISITORS (id,departmentId)
                VALUES (?, ?)
                """);

    private final String query;
    VisitorQueries(String query)
    {
        this.query = query;
    }
    public String getQuery()
    {
        return query;
    }
}
