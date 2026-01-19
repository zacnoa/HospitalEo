package org.noa.hospitaleo.backend.utils.queries;

public enum UnderagePatientQueries {

    INSERT_UNDERAGEPATIENT("""
                INSERT INTO UNDERAGEPATIENTS(id,visitorId)
                VALUES(?,?)""");

    private final String query;
    UnderagePatientQueries(String query) {
        this.query = query;
    }
    public String getQuery() {
        return query;
    }
}
