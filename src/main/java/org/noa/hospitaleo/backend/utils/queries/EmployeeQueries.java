package org.noa.hospitaleo.backend.utils.queries;

public enum EmployeeQueries {

    INSERT_EMPLOYEE("""
                INSERT INTO EMPLOYEES (id, salary)
                VALUES (?, ?)
        """);

    private final String query;
    EmployeeQueries(String query)
    {
        this.query = query;
    }
    public String getQuery()
    {
        return query;
    }
}
