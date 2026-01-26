package org.noa.hospitaleo.backend.utils.queries;

public enum PatientQueries {

    GET_DEPARTMENT_PATIENTS( """
                SELECT PERSONS.name,
                       PERSONS.oib,
                       PATIENTS.id,
                       PATIENTS.diagnosis,
                       PATIENTS.doctorId,
                       PATIENTS.departmentId,
                       PATIENTS.roomId,
                       PATIENTS.status,
                       PATIENTS.createdAt
                FROM PATIENTS
                JOIN PERSONS ON PATIENTS.id = PERSONS.id
                WHERE PATIENTS.departmentId = ?
                """),
    GET_PATIENT("""
                SELECT PERSONS.name,
                       PERSONS.oib,
                       PATIENTS.id,
                       PATIENTS.diagnosis,
                       PATIENTS.doctorId,
                       PATIENTS.departmentId,
                       PATIENTS.roomId,
                       PATIENTS.status,
                       PATIENTS.createdAt
                FROM PATIENTS
                JOIN PERSONS ON PATIENTS.id = PERSONS.id
                WHERE PATIENTS.id = ?
                """),
    INSERT_PATIENT("""
                INSERT INTO PATIENTS (id, departmentId, roomId, doctorId, status, diagnosis,createdAt)
                VALUES (?, ?, ?, ?, ?, ?,?)
                """),
    GET_ROOM_PATIENTS("""
                SELECT PERSONS.name,
                       PERSONS.oib,
                       PATIENTS.id,
                       PATIENTS.diagnosis,
                       PATIENTS.doctorId,
                       PATIENTS.departmentId,
                       PATIENTS.roomId,
                       PATIENTS.status,
                       PERSONS.createdAt
                FROM PATIENTS
                JOIN PERSONS ON PATIENTS.id = PERSONS.id
                WHERE PATIENTS.roomId = ?
                """),
    PATIENT_SEARCH("""
                SELECT
                    p.id,
                    p.name,
                    p.oib,
                    pat.diagnosis,
                    pat.status,
                    pat.doctorId,
                    pat.roomId,
                    pat.createdAt
                FROM PATIENTS pat
                JOIN PERSONS p ON pat.id = p.id
                WHERE
                    ( ? = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', ?, '%')) )
                AND ( ? = '' OR p.oib = ? )
                AND ( ? = '' OR LOWER(pat.diagnosis) LIKE LOWER(CONCAT('%', ?, '%')) )
                AND ( ? IS NULL OR pat.departmentId = ?)
                """),
    LATEST_PATIENT("""
            SELECT *
            FROM PATIENTS
            JOIN PERSONS ON PATIENTS.id = PERSONS.id
            ORDER BY createdAt DESC
            LIMIT 1;
            """);

    private final String query;
    PatientQueries(String query)
    {
        this.query = query;
    }
    public String getQuery()
    {
        return query;
    }
}
