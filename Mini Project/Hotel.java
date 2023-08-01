package com.miniproject.hotelFeedback;

public class Hotel implements FeedbackSubmission{
	private int hotelId;
    private String name;
    private String location;
    private String contactInfo;
    
    public Hotel() {
    	
    }
    
	public Hotel(int hotelId, String name, String location, String contactInfo) {
		super();
		this.hotelId = hotelId;
		this.name = name;
		this.location = location;
		this.contactInfo = contactInfo;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	@Override
    public void submitFeedback(Hotel hotel, int rating, String comments) {
        FeedbackManager.submitFeedback(null, hotel, rating, comments);
    }
	
}
