package org.noa.hospitaleo.backend.utils.queries;

public enum DepartmentQueries {

    GET_ALL_DEPARTMENTS( """
              SELECT * FROM DEPARTMENTS 
               """),
    INSERT_DEPARTMENT("""
                INSERT INTO DEPARTMENTS (name, id)
                VALUES(?, ?)"""),
    GET_STATISTICS("""
            SELECT
            (SELECT COUNT(*) FROM DOCTORS WHERE departmentId=?) AS doctors,
            (SELECT COUNT(*) FROM ROOMS WHERE departmentId=?) AS rooms,
            (SELECT COUNT(*) FROM PATIENTS WHERE departmentId=?) AS patients
            """

    );
    private final String query;
    DepartmentQueries(String query) {
      this.query = query;
    }
    public String getQuery() {
        return query;
    }
}
