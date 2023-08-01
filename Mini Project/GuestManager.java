package com.miniproject.hotelFeedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestManager {
    // Method to register a new guest in the database
    public static void registerGuest(Guest guest) {
        try (Connection connection = DBConnection.getConnection()) {
            String insertQuery = "INSERT INTO Guest (name, email) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, guest.getName());
            preparedStatement.setString(2, guest.getEmail());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int guestId = generatedKeys.getInt(1);
                guest.setGuestId(guestId); // Set the auto-generated guest_id back to the guest object
            }

            System.out.println("Guest registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve a guest by email from the database
    public static Guest getGuestByEmail(String email) {
        try (Connection connection = DBConnection.getConnection()) {
            String selectQuery = "SELECT * FROM Guest WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int guestId = resultSet.getInt("guest_id");
                String name = resultSet.getString("name");
                String guestEmail = resultSet.getString("email");
                return new Guest(guestId, name, guestEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Guest not found
    }
    
    public static void updateGuest(String oldEmail, Guest updatedGuest) {
        try (Connection connection = DBConnection.getConnection()) {
            String updateQuery = "UPDATE Guest SET name = ?, email = ? WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, updatedGuest.getName());
            preparedStatement.setString(2, updatedGuest.getEmail());
            preparedStatement.setString(3, oldEmail);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Guest details updated successfully!");
            } else {
                System.out.println("No guest found with the provided old email.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Guest> getGuestsByHotelLocation(String hotelLocation) {
        List<Guest> guests = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            // Use a subquery to fetch guest IDs based on the hotel location
            String subquery = "SELECT guest_id FROM Review " +
                              "JOIN Hotel ON Review.hotel_id = Hotel.hotel_id " +
                              "WHERE Hotel.location = ?";
            String selectQuery = "SELECT * FROM Guest WHERE guest_id IN (" + subquery + ")";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, hotelLocation);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int guestId = resultSet.getInt("guest_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                guests.add(new Guest(guestId, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guests;
    }
}
