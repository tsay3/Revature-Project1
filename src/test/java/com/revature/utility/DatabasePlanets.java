package com.revature.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePlanets {

    public static boolean forceOwningPlanet(String planet) {
        return forceUserOwningPlanet(1, planet);
    }

    public static boolean forceNotOwningPlanet(String planet) {
        // since all of our viewing tests involve the user with an id of 1,
        // to force "not owning", we can duplicate "force owning", but with an id of 2
        return forceUserOwningPlanet(2, planet);
    }

    public static boolean forceUserDisowningPlanets(int userId) {
        int dummyId = DatabaseUsers.addDummyUser();
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            String updateStatement = "UPDATE planets SET ownerId = ? WHERE ownerId = ?";
            ps = conn.prepareStatement(updateStatement);
            ps.setInt(1, dummyId);
            ps.setInt(2, userId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean forceUserOwningPlanet(int user, String planet) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String getPlanet = "SELECT * FROM planets WHERE name = ?";
            ps = conn.prepareStatement(getPlanet);
            ps.setString(1, planet);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                // no such planet found, we have to insert it
                String insertStatement = "INSERT INTO planets (name, ownerId) VALUES (?, ?)";
                ps = conn.prepareStatement(insertStatement);
                ps.setString(1, planet);
                ps.setInt(2, user);
                ps.executeUpdate();
            } else {
                // a user is found, we have to update the data
                String updateStatement = "UPDATE planets SET ownerId = ? WHERE name = ?";
                ps = conn.prepareStatement(updateStatement);
                ps.setInt(1, user);
                ps.setString(2, planet);
                ps.executeUpdate();
            }
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getPlanetId(String planet) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String getPlanet = "SELECT id FROM planets WHERE name = ?";
            ps = conn.prepareStatement(getPlanet);
            ps.setString(1, planet);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                // no such planet found, we have to insert it
                String insertStatement = "INSERT INTO planets (name, ownerId) VALUES (?, 1)";
                ps = conn.prepareStatement(insertStatement);
                ps.setString(1, planet);
                ps.executeUpdate();
            } else {
                // a planet is found, return the data
                return rs.getInt("id");
            }
            ps = conn.prepareStatement(getPlanet);
            ps.setString(1, planet);
            rs = ps.executeQuery();
            return rs.getInt("id");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean forcePlanetRemoval(String planet) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String deleteStatement = "DELETE FROM planets WHERE name = ?";
            ps = conn.prepareStatement(deleteStatement);
            ps.setString(1, planet);
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int addNewPlanet(String planet) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String deleteStatement = "INSERT INTO planets (name, ownerId) VALUES (?, ?)";
            ps = conn.prepareStatement(deleteStatement);
            ps.setString(1, planet);
            ps.setInt(2, 2);
            ps.executeUpdate();
            String selectStatement = "SELECT id FROM planets WHERE name = ?";
            ps.setString(1, planet);
            rs = ps.executeQuery();
            return rs.getInt("id");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int addDummyPlanet() {
        int dummyId = DatabaseUsers.addDummyUser();
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String name = "Dummy" + String.valueOf((int)Math.floor(Math.random() * 1000000));
            System.out.println(String.format("Trying to add planet '%s' to database", name));
            String deleteStatement = "INSERT INTO planets (name, ownerId) VALUES (?, ?)";
            ps = conn.prepareStatement(deleteStatement);
            ps.setString(1, name);
            ps.setInt(2, dummyId);
            ps.executeUpdate();
            String getPlanet = "SELECT id FROM planets WHERE name = ?";
            ps = conn.prepareStatement(getPlanet);
            ps.setString(1, name);
            rs = ps.executeQuery();
            return rs.getInt("id");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void removeAllDummyPlanets() {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String deleteStatement = "DELETE FROM planets WHERE name LIKE 'Dummy%'";
            ps = conn.prepareStatement(deleteStatement);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
