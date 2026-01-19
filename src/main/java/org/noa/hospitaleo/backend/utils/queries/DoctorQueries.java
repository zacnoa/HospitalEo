package org.noa.hospitaleo.backend.utils.queries;

public enum DoctorQueries {



    GET_DEPARTMENT_DOCTORS("""
                SELECT PERSONS.name,
                PERSONS.oib,
                EMPLOYEES.salary,
                DOCTORS.id,
                DOCTORS.specialty
                FROM DOCTORS
                JOIN PERSONS ON DOCTORS.id = PERSONS.id
                JOIN EMPLOYEES ON DOCTORS.id = EMPLOYEES.id 
                WHERE DOCTORS.departmentId = ?
                """),
    GET_DOCTOR("""
                SELECT PERSONS.name,
                PERSONS.oib,
                EMPLOYEES.salary,
                DOCTORS.id,
                DOCTORS.specialty
                FROM DOCTORS
                JOIN PERSONS ON DOCTORS.id = PERSONS.id
                JOIN EMPLOYEES ON DOCTORS.id = EMPLOYEES.id
                WHERE DOCTORS.id = ?"""),
    INSERT_DOCTOR( """
               INSERT INTO DOCTORS (id,departmentId,specialty) 
               VALUES (?,?,?)
                """),
    DOCTOR_SEARCH("""
                SELECT
                    d.id,
                    p.name,
                    p.oib,
                    d.specialty,
                    e.salary
                FROM DOCTORS d
                JOIN PERSONS p ON d.id = p.id
                JOIN EMPLOYEES e ON d.id = e.id
                WHERE
                    ( ? = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', ?, '%')) )
                AND ( ? = '' OR p.oib = ? )
                AND ( ? = '' OR LOWER(d.specialty) LIKE LOWER(CONCAT('%', ?, '%')) );
                """);


    private final String query;
    DoctorQueries(String query)
    {
        this.query = query;
    }
    public String getQuery()
    {
        return query;
    }
}
