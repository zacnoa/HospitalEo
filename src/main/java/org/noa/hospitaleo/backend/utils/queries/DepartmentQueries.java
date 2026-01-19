package org.noa.hospitaleo.backend.utils.queries;

public enum DepartmentQueries {

    GET_ALL_DEPARTMENTS( """
              SELECT * FROM DEPARTMENTS 
               """),
    INSERT_DEPARTMENT("""
                INSERT INTO DEPARTMENTS (name, id)
                VALUES(?, ?)""");

    private final String query;
    DepartmentQueries(String query) {
      this.query = query;
    }
    public String getQuery() {
        return query;
    }
}
