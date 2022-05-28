package com.evg3108.randompicker.repository;

import com.evg3108.randompicker.model.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

   private static Connection connection = ConnectionManager.getConnection();

    public static Group addNewGroup(String title) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO entry_group (title) VALUES ('" + title + "')";
            statement.executeUpdate(query);
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

    public static void editGroup(Group group) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE entry_group SET title='" + group.getTitle() + "' WHERE id=" + group.getId();
            ResultSet resultSet = statement.executeQuery(query);
            group.setId(resultSet.getLong("id"));
            group.setTitle(resultSet.getString("title"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean deleteGroup(long id) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM entry_group WHERE id=" + id;
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Group findGroupById(long id) {
        Group group = new Group();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM entry_group WHERE id=" + id;
            ResultSet resultSet = statement.executeQuery(query);
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
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM entry_group WHERE title='" + title + "'";
            ResultSet resultSet = statement.executeQuery(query);
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
