package entity;

import java.util.Date;

public class BikeRental {
	
	private int userID;
	private int bikeID;
	private Date startRental;
	private int timeRented;
	private String batteryStatus;
	private boolean bikeIsReturned;
	
	public BikeRental(int userID, int bikeID, Date startRental, int timeRented, String batteryStatus,
			boolean bikeIsReturned) {
		super();
		this.userID = userID;
		this.bikeID = bikeID;
		this.startRental = startRental;
		this.timeRented = timeRented;
		this.batteryStatus = batteryStatus;
		this.bikeIsReturned = bikeIsReturned;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBikeID() {
		return bikeID;
	}

	public void setBikeID(int bikeID) {
		this.bikeID = bikeID;
	}

	public Date getStartRental() {
		return startRental;
	}

	public void setStartRental(Date startRental) {
		this.startRental = startRental;
	}

	public int getTimeRented() {
		return timeRented;
	}

	public void setTimeRented(int timeRented) {
		this.timeRented = timeRented;
	}

	public String getBatteryStatus() {
		return batteryStatus;
	}

	public void setBatteryStatus(String batteryStatus) {
		this.batteryStatus = batteryStatus;
	}

	public boolean isBikeIsReturned() {
		return bikeIsReturned;
	}

	public void setBikeIsReturned(boolean bikeIsReturned) {
		this.bikeIsReturned = bikeIsReturned;
	}
	
	
	
}
