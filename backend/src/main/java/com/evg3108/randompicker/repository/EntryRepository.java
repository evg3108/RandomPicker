package com.evg3108.randompicker.repository;

import com.evg3108.randompicker.model.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntryRepository {
    private static Connection connection = ConnectionManager.getConnection();

    public static boolean addNewEntry(String title, long groupId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO entry (title, entry_group_id) VALUES (?, ?)"
            );
            statement.setString(1, title);
            statement.setLong(2, groupId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Entry editEntry(long entryId, String newTitle) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE entry SET title=? WHERE id=?"
            );
            statement.setString(1, newTitle);
            statement.setLong(2, entryId);
            statement.executeUpdate();
            return findEntryByID(entryId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteEntry(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM entry WHERE id=?");
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Entry findEntryByID(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entry WHERE id=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return extractEntry(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Entry> findEntryByTitle(String title) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entry WHERE title=?");
            statement.setString(1, title);
            return extractListOfEntries(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Entry> findEntryByTitleAndGroupId(String title, long groupId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM entry WHERE title=? AND entry_group_id=?"
            );
            statement.setString(1, title);
            statement.setLong(2, groupId);
            return extractListOfEntries(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Entry> findEntriesByGroupId(long groupId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM entry WHERE entry_group_id=?"
            );
            statement.setLong(1, groupId);
            return extractListOfEntries(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<Entry> extractListOfEntries(PreparedStatement statement){
        try{
            ResultSet resultSet = statement.executeQuery();
            List<Entry> foundEntries = new ArrayList<>();
            while (resultSet.next()) {
                foundEntries.add(extractEntry(resultSet));
            }
            return foundEntries;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Entry extractEntry(ResultSet resultSet){
        try{
            Entry entry = new Entry();
            entry.setId(resultSet.getLong("id"));
            entry.setTitle(resultSet.getString("title"));
            entry.setGroupId(resultSet.getLong("entry_group_id"));
            return entry;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
