package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.backend.utils.mappers.DepartmentMapper;
import org.noa.hospitaleo.backend.utils.queries.DepartmentQueries;
import org.noa.hospitaleo.frontend.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRoutes {

    private DepartmentRoutes() {}

    public static List<Department> getAllDepartments(Connection connection) throws SQLException
    {
       List<Department> departments = new ArrayList<>();
       String query = DepartmentQueries.GET_ALL_DEPARTMENTS.getQuery();
       try(PreparedStatement statement = connection.prepareStatement(query))
       {
           try(ResultSet rs = statement.executeQuery())
           {
               departments= DepartmentMapper.mapToDepartmentList(rs);
           }
       }
       return departments;
    }

    public static void insertDepartment(Connection connection, Department department) throws SQLException
    {
        String query = DepartmentQueries.INSERT_DEPARTMENT.getQuery();

        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, department.getName());
            statement.setObject(2, department.getId());
            statement.executeUpdate();
        }
    }
}
