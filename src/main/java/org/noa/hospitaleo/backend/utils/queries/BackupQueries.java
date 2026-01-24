package org.noa.hospitaleo.backend.utils.queries;

public enum BackupQueries {

    DROP_TABLE("DROP TABLE IF EXISTS BACKUP_PATIENTS"),
    CREATE_BACKUP("CREATE TABLE BACKUP_PATIENTS AS SELECT * FROM PATIENTS");

    private final String query;
     BackupQueries(String query) {
        this.query = query;
    }
    public String getQuery() {
        return query;
    }
}
