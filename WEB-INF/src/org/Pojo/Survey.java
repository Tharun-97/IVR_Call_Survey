package org.Pojo;

import java.util.List;

public class Survey {
	private BookingDetails bookingId;
	private List<Question> questions;
	
	public BookingDetails getBookingId() {
		return bookingId;
	}

	public void setBookingId(BookingDetails bookingId) {
		this.bookingId = bookingId;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
