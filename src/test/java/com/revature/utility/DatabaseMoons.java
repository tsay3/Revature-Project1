package com.revature.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMoons {

    public static boolean forcePlanetDisowningMoons(int planetId) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            String updateStatement = "UPDATE moons SET myPlanetId = 9999 WHERE myPlanetId = ?";
            ps = conn.prepareStatement(updateStatement);
            ps.setInt(1, planetId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean forceOwningMoon(String planet, String moon) {
        // first, we need the planet id
        int planetId = DatabasePlanets.getPlanetId(planet);
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String getMoonId = "SELECT * FROM moons WHERE myPlanetId = ?";
            ps = conn.prepareStatement(getMoonId);
            ps.setInt(1, planetId);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                // no such moon found
                String insertStatement = "INSERT INTO moons (name, myPlanetId) VALUES (?, ?)";
                ps = conn.prepareStatement(insertStatement);
                ps.setString(1, moon);
                ps.setInt(2, planetId);
                ps.executeUpdate();
            } else {
                // a moon is found, we have to update the data
                String updateStatement = "UPDATE moons SET myPlanetId = ? WHERE name = ?";
                ps = conn.prepareStatement(updateStatement);
                ps.setInt(1, planetId);
                ps.setString(2, moon);
                ps.executeUpdate();
            }
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean forceMoonRemoval(String moon) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String deleteStatement = "DELETE FROM moons WHERE name = ?";
            ps = conn.prepareStatement(deleteStatement);
            ps.setString(1, moon);
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
