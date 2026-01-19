package org.noa.hospitaleo.backend.utils.mappers;

import org.noa.hospitaleo.frontend.entity.Department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartmentMapper {

    private DepartmentMapper() {}

    public static List<Department> mapToDepartmentList(ResultSet rs) throws SQLException {

        List<Department> departments = new ArrayList<>();
        while(rs.next())
        {
            Department temp = new Department(
                    rs.getString("name"),
                    rs.getObject("id", UUID.class)
            );
            departments.add(temp);
        }
        return departments;
    }
    public static Department mapToDepartment(ResultSet rs) throws SQLException {
        if(rs.next()) {
            return new Department(
                    rs.getString("name"),
                    rs.getObject("id", UUID.class)
            );
        }
        return null;
    }
}
