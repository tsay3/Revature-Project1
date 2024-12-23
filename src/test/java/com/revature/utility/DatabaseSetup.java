package com.revature.utility;

import com.revature.utility.DatabaseConnector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.stream.Stream;

public class DatabaseSetup {
    public static void main(String[] args) {
        resetTestDatabase();
    }
    public static void resetTestDatabase() {
        Path sql = Path.of("src/test/resources/setup-reset.sql");
        StringBuilder sqlBuilder = new StringBuilder();
        try (Connection conn = DatabaseConnector.getConnection(); Stream<String> lines = Files.lines(sql)) {
            conn.setAutoCommit(false);
            lines.forEach(sqlBuilder::append);
            String sqlString = sqlBuilder.toString();
            String [] sqlStatements = sqlString.split(";");
            int imageCount = 1;
            for (String sqlStatement : sqlStatements) {
                if (sqlStatement.contains("?")){
                    String type = sqlStatement.contains("moons") ? "moon" : "planet";
                    try(PreparedStatement ps = conn.prepareStatement(sqlStatement)){
                        byte[] imageData = convertImgToByteArray(String.format("src/test/resources/Celestial-Images/%s-%d.jpg", type, imageCount));
                        ps.setBytes(1, imageData);
                        ps.executeUpdate();
                        imageCount = imageCount == 2 ? 1 : 2;
                    }
                } else {
                    try (Statement stmt = conn.createStatement()) {
                        stmt.executeUpdate(sqlStatement);
                    }
                }

            }
            conn.commit();
            System.out.println("Database setup complete");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

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

    public static byte[] convertImgToByteArray(String filePath) throws IOException {
        byte[] imageBytes = Files.readAllBytes(Paths.get(filePath));
        return imageBytes;
    }
}
