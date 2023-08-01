package com.miniproject.hotelFeedback;

import java.sql.Date;

public class Review {
	private int reviewId;
    private int guestId;
    private int hotelId;
    private int rating;
    private String comments;
    private Date submissionDate;
    private String response;
    
    
    
    public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Review() {
    	
    }
    
	
	public Review(int reviewId, int guestId, int hotelId, int rating, String comments, Date submissionDate,
			String response) {
		super();
		this.reviewId = reviewId;
		this.guestId = guestId;
		this.hotelId = hotelId;
		this.rating = rating;
		this.comments = comments;
		this.submissionDate = submissionDate;
		this.response = response;
	}

	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
    
    
}
