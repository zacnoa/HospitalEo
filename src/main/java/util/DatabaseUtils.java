package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.backend.routes.DepartmentRoutes;
import org.noa.hospitaleo.backend.routes.DoctorRoutes;
import org.noa.hospitaleo.backend.routes.RoomRoutes;
import org.noa.hospitaleo.entity.Department;
import org.noa.hospitaleo.entity.Doctor;
import org.noa.hospitaleo.entity.IdentifiableEntity;
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


    public static ObservableList<IdentifiableEntity> departmentsIdentifiableObservableList(Connection connection) throws SQLException
    {

        List<Department> result = DepartmentRoutes.getAllDepartments(connection);
        return Utils.listToIdentifiableObservableList(result);

    }
    public static ObservableList<IdentifiableEntity> doctorsIdentifiableObservableList(Connection connection, UUID departmentId) throws SQLException
    {

        List<Doctor> result = DoctorRoutes.getDepartmentDoctors(departmentId,connection);
        return Utils.listToIdentifiableObservableList(result);

    }
    public static ObservableList<IdentifiableEntity> roomsIdentifiableObservableList(Connection connection,UUID departmentId) throws SQLException
    {

        List<Room> result = RoomRoutes.getDepartmentRooms(departmentId,connection);
        return Utils.listToIdentifiableObservableList(result);

    }

}
