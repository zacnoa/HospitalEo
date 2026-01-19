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
                       PATIENTS.status
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
                       PATIENTS.status
                FROM PATIENTS
                JOIN PERSONS ON PATIENTS.id = PERSONS.id
                WHERE PATIENTS.id = ?
                """),
    INSERT_PATIENT("""
                INSERT INTO PATIENTS (id, departmentId, roomId, doctorId, status, diagnosis)
                VALUES (?, ?, ?, ?, ?, ?)
                """),
    GET_ROOM_PATIENTS("""
                SELECT PERSONS.name,
                       PERSONS.oib,
                       PATIENTS.id,
                       PATIENTS.diagnosis,
                       PATIENTS.doctorId,
                       PATIENTS.departmentId,
                       PATIENTS.roomId,
                       PATIENTS.status
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
                    pat.roomId
                FROM PATIENTS pat
                JOIN PERSONS p ON pat.id = p.id
                WHERE
                    ( ? = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', ?, '%')) )
                AND ( ? = '' OR p.oib = ? )
                AND ( ? = '' OR LOWER(pat.diagnosis) LIKE LOWER(CONCAT('%', ?, '%')) );
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
