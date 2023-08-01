package com.miniproject.hotelFeedback;

public class Guest extends Person implements FeedbackSubmission {
	private int guestId;
    
    public Guest(int guestId, String name, String email) {
        super(name, email); // Call the parameterized constructor of the Person class
        this.guestId = guestId;
    }
     public Guest() {
    	 super("Default Name", "default@example.com");
     }
	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	@Override
	public void submitFeedback(Hotel hotel, int rating, String comments) {
		FeedbackManager.submitFeedback(this, hotel, rating, comments);
	}
    
}
