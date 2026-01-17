package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartmentRoutes {


    public static List<Department> getAllDepartments(Connection connection) throws SQLException
    {
       List<Department> departments = new ArrayList<>();
       String query = """
              SELECT * FROM DEPARTMENTS 
               """;
       try(PreparedStatement statement = connection.prepareStatement(query))
       {
           try(ResultSet rs = statement.executeQuery())
           {
               while(rs.next())
               {
                   Department temp = new Department(
                           rs.getString("name"),
                           rs.getObject("id", UUID.class)
                   );
                   departments.add(temp);
               }
           }
       }
       return departments;
    }

    public static void insertDepartment(Connection connection, Department department) throws SQLException
    {
        String query = """
                INSERT INTO DEPARTMENTS (name, id)
                VALUES(?, ?)""";

        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, department.getName());
            statement.setObject(2, department.getId());
            statement.executeUpdate();
        }
    }
}
