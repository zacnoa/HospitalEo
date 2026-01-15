package util;

import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.entity.Department;
import org.noa.hospitaleo.entity.Doctor;
import org.noa.hospitaleo.entity.Room;
import org.noa.hospitaleo.enums.Tables;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseUtils {



    public static Connection getConnection() throws IOException, SQLException
    {
        Connection connection = null;
            PropertiesHandler props= new PropertiesHandler();
            String url= props.getProperty("H2_URL");
            String username= props.getProperty("USER");
            String password= props.getProperty("PASSWORD");

            connection= DriverManager.getConnection(
                    url,
                    username,
                    password
            );

        return connection;
    }
    public static void fillDepartment(Department department) throws SQLException
    {
        department.setPatientIds(getChildrenIds(department.getId(),Tables.PATIENTS, "departmentId"));
        department.setRoomIds(getChildrenIds(department.getId(),Tables.ROOMS,"departmentId"));
        department.setDoctorIds(getChildrenIds(department.getId(),Tables.DOCTORS,"departmentId"));
        department.setVisitorIds(getChildrenIds(department.getId(),Tables.VISITORS,"departmentId"));
    }
    public static void  fillRoom(Room room) throws SQLException
    {
        room.setPatientsId(getChildrenIds(room.getId(),Tables.PATIENTS, "roomId"));
    }
    public static void fillDoctor(Doctor doctor)  throws SQLException
    {
        doctor.setPatientIds(getChildrenIds(doctor.getId(),Tables.PATIENTS,"doctorId"));
    }


    private static List<UUID> getChildrenIds(UUID parentId, Tables childrenTable, String propertyName) throws SQLException {
        List<UUID> idList = new ArrayList<>();
        Connection connection = HospitalEoApplication.getConnection();
        String query = "SELECT id FROM " + childrenTable.getTable() + " WHERE " + propertyName + " = ?";

        try (PreparedStatement prmst = connection.prepareStatement(query)) {
            prmst.setObject(1, parentId);

            try (ResultSet rs = prmst.executeQuery()) {
                while (rs.next()) {
                    idList.add(rs.getObject("id", UUID.class));
                }
            }
        }

        return idList;
    }

}
