package com.revature.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePlanets {

    public static boolean forceOwningPlanet(String planet) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement ps;
            ResultSet rs;
            String getPlanet = "SELECT * FROM planets WHERE name = ?";
            ps = conn.prepareStatement(getPlanet);
            ps.setString(1, planet);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                // no such planet found, we have to insert it
                String insertStatement = "INSERT INTO planets (name, owner_id) VALUES (?, 1)";
                ps = conn.prepareStatement(insertStatement);
                ps.setString(1, planet);
                ps.executeUpdate();
            } else {
                // a user is found, we have to update the data
                String updateStatement = "UPDATE planets SET owner_id = 1 WHERE name = ?";
                ps = conn.prepareStatement(updateStatement);
                ps.setString(1, planet);
                ps.executeUpdate();
            }
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
