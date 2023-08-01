package com.miniproject.hotelFeedback;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackManager {
    public static void submitFeedback(Guest guest, Hotel hotel, int rating, String comments) {
        try (Connection connection = DBConnection.getConnection()) {
            String insertQuery = "INSERT INTO Review (guest_id, hotel_id, rating, comments, submission_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, guest.getGuestId());
            preparedStatement.setInt(2, hotel.getHotelId());
            preparedStatement.setInt(3, rating);
            preparedStatement.setString(4, comments);
            preparedStatement.setDate(5, new Date(System.currentTimeMillis())); // Set current date as submission date
            preparedStatement.executeUpdate();
            System.out.println("Feedback submitted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkFeedbackExists(int guestId, int hotelId) {
        try (Connection connection = DBConnection.getConnection()) {
            String selectQuery = "SELECT COUNT(*) AS count FROM Review WHERE guest_id = ? AND hotel_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, guestId);
            preparedStatement.setInt(2, hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("count");
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void deleteFeedback(int guestId, int hotelId) {
        try (Connection connection = DBConnection.getConnection()) {
            String deleteQuery = "DELETE FROM Review WHERE guest_id = ? AND hotel_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, guestId);
            preparedStatement.setInt(2, hotelId);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
