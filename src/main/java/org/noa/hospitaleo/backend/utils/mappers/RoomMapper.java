package org.noa.hospitaleo.backend.utils.mappers;

import org.noa.hospitaleo.frontend.entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomMapper {

    private RoomMapper() {}

    public static List<Room> mapToRoomList(ResultSet resultSet) throws SQLException {

        List<Room> rooms = new ArrayList<>();
        while (resultSet.next()) {
            Room temp = new Room(
                    resultSet.getString("name"),
                    resultSet.getObject("id", UUID.class)
            );
            rooms.add(temp);
        }
        return rooms;
    }
    public static Room mapToRoom(ResultSet resultSet) throws SQLException {
       Room temp = null;
        if (resultSet.next()) {
            temp = new Room(
                    resultSet.getString("name"),
                    resultSet.getObject("id", UUID.class)
            );
        }
        return temp;
    }
}
