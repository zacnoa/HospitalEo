package org.noa.hospitaleo.backend.utils.mappers;

import org.noa.hospitaleo.frontend.entity.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoctorMapper {

    private DoctorMapper() {}

    public static List<Doctor>  mapToDoctorList(ResultSet rs) throws SQLException
    {

        List<Doctor> doctors = new ArrayList<>();
        while(rs.next()) {
            Doctor temp = new Doctor(
                    rs.getString("name"),
                    rs.getString("oib"),
                    rs.getString("specialty"),
                    rs.getDouble("salary"),
                    rs.getObject("id", UUID.class)

            );
            doctors.add(temp);
        }
        return doctors;
    }
    public static Doctor mapToDoctor(ResultSet rs) throws SQLException
    {

        if(rs.next()) {
            return new Doctor(
                    rs.getString("name"),
                    rs.getString("oib"),
                    rs.getString("specialty"),
                    rs.getDouble("salary"),
                    rs.getObject("id", UUID.class)
            );
        }
        return null;
    }
}
