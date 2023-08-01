package com.miniproject.hotelFeedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class HotelMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("WELCOME TO THE ONLINE HOTEL FEEDBACK SYSTEM!");
            System.out.println();
            System.out.println("1. Submit Feedback");
            System.out.println("2. Respond to Reviews");
            System.out.println("3. View Response");
            System.out.println("4. Register Hotel");
            System.out.println("5. view Guest by Hotel Location");
            System.out.println("6. Update Guest Details");
            System.out.println("7. Exit");
            System.out.println();
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // Consume the invalid input to clear the scanner buffer
                choice = -1;    // Set an invalid value to repeat the loop
            }

            switch (choice) {
                case 1:
                    // Collect feedback from the guest and store it in the database
                    handleFeedbackSubmission();
                    break;
                case 2:
                    // Allow hotel management to respond to reviews
                    handleReviewResponse();
                    break;
                case 3:
                    // View reviews for both guests and hotel management
                    viewReviews();
                    break;
                case 4:
                	handleHotelRegistration();
                	break;
                case 5:
                	handleGuestsByHotelLocation();
                    break;
                case 6:
                	handleUpdateGuestDetails();
                    break;
                case 7:
                    System.out.println("Thank you for using the Online Hotel Feedback System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 7);

        sc.close();
    }

    
    private static void handleUpdateGuestDetails() {
        Scanner sc = new Scanner(System.in);

        // Get guest email for which details need to be updated
        System.out.print("Enter guest email to update details: ");
        String guestEmail = sc.nextLine();

        // Check if the guest exists in the database
        Guest guest = GuestManager.getGuestByEmail(guestEmail);
        if (guest == null) {
            System.out.println("Guest with the provided email not found. Unable to update details.");
            return;
        }

        // Get updated guest information
        System.out.print("Enter updated guest name: ");
        String updatedName = sc.nextLine();
        System.out.print("Enter updated guest email: ");
        String updatedEmail = sc.nextLine();

        // Create an updated guest object
        Guest updatedGuest = new Guest();
        updatedGuest.setGuestId(guest.getGuestId()); // Set the guest ID from the existing guest
        updatedGuest.setName(updatedName);
        updatedGuest.setEmail(updatedEmail);

        // Update the guest details in the database
        GuestManager.updateGuest(guestEmail, updatedGuest);
        System.out.println("Guest details updated successfully!");
        System.out.println();
    }

    
    private static void handleFeedbackSubmission() {
        Scanner sc = new Scanner(System.in);

        // Get guest information
        System.out.print("Enter guest name: ");
        String guestName = sc.nextLine();
        System.out.print("Enter guest email: ");
        String guestEmail = sc.nextLine();

        Guest guest = GuestManager.getGuestByEmail(guestEmail);
        if (guest == null) {
            // Guest not registered, register a new guest
            guest = new Guest();
            guest.setName(guestName);
            guest.setEmail(guestEmail);
            GuestManager.registerGuest(guest);
        }

     // Get hotel information
        System.out.print("Enter hotel name: ");
        String hotelName = sc.nextLine();
        System.out.print("Enter hotel location: ");
        String hotelLocation = sc.nextLine();

        Hotel hotel = HotelManager.getHotelByNameAndLocation(hotelName, hotelLocation);
        if (hotel == null) {
            System.out.println("Hotel not found. Please check the hotel name and location or contact hotel management.");
            sc.close();
            return;
        }
        
     // Check if the feedback already exists for the given guest and hotel
        boolean feedbackExists = FeedbackManager.checkFeedbackExists(guest.getGuestId(), hotel.getHotelId());
        if (feedbackExists) {
            System.out.println("You have already submitted feedback for this hotel. Do you want to delete your existing feedback?");
            System.out.println("1. Delete Feedback");
            System.out.println("2. Cancel");
            int option = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    // Delete existing feedback
                    FeedbackManager.deleteFeedback(guest.getGuestId(), hotel.getHotelId());
                    System.out.println("Feedback deleted successfully!");
                    break;
                case 2:
                    // Cancel
                    System.out.println("Feedback submission canceled.");
                    break;
                default:
                    System.out.println("Invalid option. Feedback submission canceled.");
                    break;
            }
        } else {
            // Get hotel ID and feedback details
            System.out.print("Enter rating (1 to 5): ");
            int rating = sc.nextInt();
            sc.nextLine(); // Clear the input buffer
            System.out.print("Enter comments: ");
            String comments = sc.nextLine();

            FeedbackManager.submitFeedback(guest, hotel, rating, comments);
            System.out.println("Feedback submitted successfully!");
            System.out.println();
        }
        
    }


    private static void handleHotelRegistration() {
        Scanner sc = new Scanner(System.in);

        // Get hotel information
        System.out.print("Enter hotel name: ");
        String hotelName = sc.nextLine();
        System.out.print("Enter hotel location: ");
        String hotelLocation = sc.nextLine();
        System.out.print("Enter hotel contact info: ");
        String hotelContactInfo = sc.nextLine();

        Hotel newHotelBranch = new Hotel();
        newHotelBranch.setName(hotelName);
        newHotelBranch.setLocation(hotelLocation);
        newHotelBranch.setContactInfo(hotelContactInfo);

        HotelManager.registerHotel(newHotelBranch);
        System.out.println();
        
    }
    private static void handleReviewResponse() {
        Scanner sc = new Scanner(System.in);

        // Get review ID and response from hotel management
        System.out.print("Enter review ID to respond to: ");
        int reviewId = sc.nextInt();
        sc.nextLine(); // Clear the input buffer
        System.out.print("Enter response: ");
        String response = sc.nextLine();

        ResponseManger.respondToReview(reviewId, response);
        System.out.println();
        
    }

    private static void handleGuestsByHotelLocation() {
        Scanner sc = new Scanner(System.in);

        // Get hotel location to fetch guests who provided feedback for hotels in that location
        System.out.print("Enter hotel location to fetch guests: ");
        String hotelLocation = sc.nextLine();

        List<Guest> guests = GuestManager.getGuestsByHotelLocation(hotelLocation);

        if (!guests.isEmpty()) {
            System.out.println("Guests who provided feedback for hotels in " + hotelLocation + ":");
            System.out.println("+==========+======================+======================+");
            System.out.printf("| %-8s | %-20s | %-20s |%n", "Guest ID", "Name", "Email");
            System.out.println("+==========+======================+======================+");
            for (Guest guest : guests) {
                System.out.printf("| %-8d | %-20s | %-20s |%n", guest.getGuestId(), guest.getName(), guest.getEmail());
            }
            System.out.println("+----------+----------------------+----------------------+");
        } else {
            System.out.println("No guests found who provided feedback for hotels in " + hotelLocation);
        }
        System.out.println();
    }


    
    private static void viewReviews() {
        Scanner sc = new Scanner(System.in);

        // Get guest email to fetch their reviews
        System.out.print("Enter your email to view your feedback response: ");
        String guestEmail = sc.nextLine();
         System.out.println();
        try (Connection connection = DBConnection.getConnection()) {
            String selectQuery = "SELECT review_id, rating, comments, response FROM Review " +
                                 "JOIN Guest ON Review.guest_id = Guest.guest_id " +
                                 "WHERE Guest.email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, guestEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean hasFeedback = false; // To check if the guest has any feedback
            System.out.println("============ Your Feedback ============");
            System.out.println();
            while (resultSet.next()) {
                hasFeedback = true;
                int reviewId = resultSet.getInt("review_id");
                int rating = resultSet.getInt("rating");
                String comments = resultSet.getString("comments");
                String response = resultSet.getString("response");

                System.out.println("Review " + reviewId + ": Rating - " + rating + ", Comments - " + comments);
                if (response != null) {
                    System.out.println("Response - " + response);
                } else {
                    System.out.println("No response yet.");
                }
                System.out.println(); // Add an empty line for better readability
            }

            if (!hasFeedback) {
                System.out.println("No feedback found for the provided email.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

}
