package com.revature.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUsers {

    public static boolean forceUserAndPassword(String username, String password) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String getPassword = "SELECT password FROM users WHERE username = ?";
            ps = conn.prepareStatement(getPassword);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                // no such user found, we have to insert it
                String insertStatement = "INSERT INTO users (username, password) VALUES (?, ?)";
                ps = conn.prepareStatement(insertStatement);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();
            } else {
                // a user is found, we have to update the data
                String updateStatement = "UPDATE users SET password = ? WHERE username = ?";
                ps = conn.prepareStatement(updateStatement);
                ps.setString(1, password);
                ps.setString(2, username);
                ps.executeUpdate();
            }
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean forceUserRemoval(String username) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String getUser = "SELECT * FROM users WHERE username = ?";
            ps = conn.prepareStatement(getUser);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                // no such user found, success
            } else {
                // a user is found, we have to delete it
                String deleteStatement = "DELETE FROM users WHERE username = ?";
                ps = conn.prepareStatement(deleteStatement);
                ps.setString(1, username);
                ps.executeUpdate();
            }
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int addDummyUser() {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String username = "Dummy" + String.valueOf(Math.floor(Math.random() * 1000000));
            String deleteStatement = "INSERT INTO users (username, password) VALUES (?, ?)";
            ps = conn.prepareStatement(deleteStatement);
            ps.setString(1, username);
            ps.setString(2, "Password1");
            ps.executeUpdate();
            String getUser = "SELECT id FROM users WHERE username = ?";
            ps = conn.prepareStatement(getUser);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.getInt("id");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void removeAllDummyUsers() {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String deleteStatement = "DELETE FROM users WHERE username LIKE 'Dummy%'";
            ps = conn.prepareStatement(deleteStatement);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
