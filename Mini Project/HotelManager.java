package com.miniproject.hotelFeedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HotelManager {
    // Method to register a new hotel in the database
    public static void registerHotel(Hotel hotel) {
        try (Connection connection = DBConnection.getConnection()) {
            String insertQuery = "INSERT INTO Hotel (name, location, contact_info) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getLocation());
            preparedStatement.setString(3, hotel.getContactInfo());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int hotelId = generatedKeys.getInt(1);
                hotel.setHotelId(hotelId); // Set the auto-generated hotel_id back to the hotel object
            }
            System.out.println("Hotel registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve a hotel by its name and location from the database
    public static Hotel getHotelByNameAndLocation(String name, String location) {
        try (Connection connection = DBConnection.getConnection()) {
            String selectQuery = "SELECT * FROM Hotel WHERE name = ? AND location = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, location);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int hotelId = resultSet.getInt("hotel_id");
                String hotelName = resultSet.getString("name");
                String hotelLocation = resultSet.getString("location");
                String contactInfo = resultSet.getString("contact_info");
                return new Hotel(hotelId, hotelName, hotelLocation, contactInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Hotel not found
    }

    // Method to retrieve a hotel by its hotel_id from the database
    public static Hotel getHotelById(int hotelId) {
        try (Connection connection = DBConnection.getConnection()) {
            String selectQuery = "SELECT * FROM Hotel WHERE hotel_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String contactInfo = resultSet.getString("contact_info");
                return new Hotel(hotelId, name, location, contactInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Hotel not found
    }
}
