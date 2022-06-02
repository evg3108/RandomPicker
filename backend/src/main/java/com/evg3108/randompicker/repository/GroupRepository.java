package com.evg3108.randompicker.repository;

import com.evg3108.randompicker.model.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

   private static Connection connection = ConnectionManager.getConnection();

    public static Group addNewGroup(String title) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO entry_group (title) VALUES (?)");
            statement.setString(1, title);
            statement.executeUpdate();
            return findGroupByTitle(title);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Group> getListOfGroups() {
        List<Group> groups = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM entry_group";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                groups.add(new Group(id, title));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public static Group editGroup(long id, String newTitle) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE entry_group SET title=? WHERE id=?");
            statement.setString(1, newTitle);
            statement.setLong(2, id);
            statement.executeQuery();
            return findGroupById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteGroup(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM entry_group WHERE id=?");
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Group findGroupById(long id) {
        Group group = new Group();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entry_group WHERE id=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            group.setId(resultSet.getLong("id"));
            group.setTitle(resultSet.getString("title"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public static Group findGroupByTitle(String title) {
        Group group = new Group();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entry_group WHERE title=?");
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            group.setId(resultSet.getLong("id"));
            group.setTitle(resultSet.getString("title"));
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
