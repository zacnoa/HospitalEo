package org.noa.hospitaleo.database;

public class DatabaseConnectionFactory {

    private DatabaseConnectionFactory(){}

    public static class Holder
    {
        private Holder() {}
        private static final DatabaseConnection connection=new DatabaseConnection();
    }
    public static DatabaseConnection getInstance()
    {
        return Holder.connection;
    }
}
