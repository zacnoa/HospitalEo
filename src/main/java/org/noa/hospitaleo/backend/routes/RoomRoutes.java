package org.noa.hospitaleo.backend.routes;

import org.noa.hospitaleo.backend.utils.mappers.RoomMapper;
import org.noa.hospitaleo.frontend.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomRoutes {

    private RoomRoutes() {}

    public static List<Room> getDepartmentRooms(UUID departmentId, Connection connection) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String query = """
                SELECT ROOMS.id,
                       ROOMS.name
                FROM ROOMS
                WHERE ROOMS.departmentId = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, departmentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                rooms = RoomMapper.mapToRoomList(resultSet);
            }
        }
        return rooms;
    }

    public static Room getRoom(Connection connection, UUID roomId) throws SQLException {
        Room temp = null;
        String query = """
                SELECT ROOMS.id,
                       ROOMS.name
                FROM ROOMS
                WHERE ROOMS.id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, roomId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                temp = RoomMapper.mapToRoom(resultSet);
            }
        }
        return temp;
    }

    public static void insertRoom(Connection connection, Room room, UUID departmentId) throws SQLException {
        String query = """
                INSERT INTO ROOMS (name, id, departmentId)
                VALUES (?, ?, ?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, room.getName());
            preparedStatement.setObject(2, room.getId());
            preparedStatement.setObject(3, departmentId);
            preparedStatement.executeUpdate();
        }
    }
}
