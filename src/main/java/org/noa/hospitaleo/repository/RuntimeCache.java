package org.noa.hospitaleo.repository;


import java.sql.SQLException;

public class RuntimeCache extends EntityRepository {

    public RuntimeCache()
    {
        super();
    }

    public void loadAll() throws SQLException {

        DatabaseAPI.getAllDepartments()
                .forEach(dept ->getDepartmentMap().put(dept.getId(), dept));

        DatabaseAPI.getAllRooms()
                .forEach(room ->getRoomMap().put(room.getId(), room));

        DatabaseAPI.getAllVisitors()
                .forEach(visitor ->getVisitorMap().put(visitor.getId(), visitor));

        DatabaseAPI.getAllDoctors()
                .forEach(doctor ->getDoctorMap().put(doctor.getId(), doctor));

        DatabaseAPI.getAllPatients()
                .forEach(patient ->getPatientMap().put(patient.getId(), patient));
    }

}
