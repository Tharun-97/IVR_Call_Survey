package org.Pojo;
import java.sql.Date;

public class BookingDetails {
	private int bookingId;
	private String customerName;
	private String hotelName;
	private Date dateVisited;

	public BookingDetails(int bookingId, String customerName, String hotelName, Date dateVisited) {
		this.bookingId = bookingId;
		this.customerName = customerName;
		this.hotelName = hotelName;
		this.dateVisited = dateVisited;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Date getDateVisited() {
		return dateVisited;
	}

	public void setDateVisited(Date dateVisited) {
		this.dateVisited = dateVisited;
	}

}
