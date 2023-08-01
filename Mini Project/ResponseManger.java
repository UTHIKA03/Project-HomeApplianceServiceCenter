package com.miniproject.hotelFeedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResponseManger {
	public static void respondToReview(int reviewId, String response) {
        try (Connection connection = DBConnection.getConnection()) {
            String updateQuery = "UPDATE Review SET response = ? WHERE review_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, response);
            preparedStatement.setInt(2, reviewId);
            preparedStatement.executeUpdate();
            System.out.println("Response submitted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
