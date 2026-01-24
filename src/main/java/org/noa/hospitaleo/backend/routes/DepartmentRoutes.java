package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.backend.utils.mappers.DepartmentMapper;
import org.noa.hospitaleo.backend.utils.queries.DepartmentQueries;
import org.noa.hospitaleo.frontend.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    public static Map<String,Integer> getDepartmentStatistics(Connection connection, UUID departmentId) throws SQLException
    {
        Map<String,Integer> statistics = new HashMap<>();
        Integer doctorCount = 0;
        Integer patientCount = 0;
        Integer roomCount = 0;
        String query= DepartmentQueries.GET_STATISTICS.getQuery();
        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setObject(1,departmentId);
            statement.setObject(2,departmentId);
            statement.setObject(3,departmentId);
            try(ResultSet rs = statement.executeQuery())
            {
                rs.next();
                doctorCount = rs.getInt("doctors");
                roomCount = rs.getInt("rooms");
                patientCount = rs.getInt("patients");
            }
        }
        statistics.put("doctors", doctorCount);
        statistics.put("patients", patientCount);
        statistics.put("rooms", roomCount);
        return statistics;
    }
}
