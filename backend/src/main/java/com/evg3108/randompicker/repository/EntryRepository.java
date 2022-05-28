package com.evg3108.randompicker.repository;

import com.evg3108.randompicker.model.Entry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntryRepository {
    private static Connection connection = ConnectionManager.getConnection();

    public static boolean createEntry(String title, long groupId) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO entry (title, entry_group_id) VALUES ('" + title + "', " + groupId + ")";
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Entry findEntryByID(long id) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM entry WHERE id=" + id;
            ResultSet resultSet = statement.executeQuery(query);
            Entry foundEntry = new Entry();
            while (resultSet.next()) {
                foundEntry.setId(resultSet.getLong("id"));
                foundEntry.setTitle(resultSet.getString("title"));
                foundEntry.setGroupId(resultSet.getLong("entry_group_id"));

            }
            return foundEntry;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Entry> findEntryByTitle(String title) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM entry WHERE title='" + title + "'";
            ResultSet resultSet = statement.executeQuery(query);
            List<Entry> foundEntries = new ArrayList<>();
            while (resultSet.next()) {
                Entry foundEntry = new Entry();
                foundEntry.setId(resultSet.getLong("id"));
                foundEntry.setTitle(resultSet.getString("title"));
                foundEntry.setGroupId(resultSet.getLong("entry_group_id"));
                foundEntries.add(foundEntry);
            }
            return foundEntries;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Entry> findEntryByTitleAndGroupId(String title, long groupId) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM entry WHERE title='" + title + "' AND entry_group_id=" + groupId;
            ResultSet resultSet = statement.executeQuery(query);
            List<Entry> foundEntries = new ArrayList<>();
            while (resultSet.next()) {
                Entry foundEntry = new Entry();
                foundEntry.setId(resultSet.getLong("id"));
                foundEntry.setTitle(resultSet.getString("title"));
                foundEntry.setGroupId(resultSet.getLong("entry_group_id"));
                foundEntries.add(foundEntry);
            }
            return foundEntries;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
