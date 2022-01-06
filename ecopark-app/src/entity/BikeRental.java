package entity;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.BikeDB;
import entity.BikeRental;


public class BikeRental {
	public int getRentalID() {
		return rentalID;
	}

	public void setRentalID(int rentalID) {
		this.rentalID = rentalID;
	}

	private int rentalID;
	private int userID;
	private int bikeID;
	private Date startRental;
	private int timeRented;
	private String batteryStatus;
	private boolean bikeIsReturned;

	public BikeRental(){

	}
	
	public BikeRental(int rentalID, int userID, int bikeID, Date startRental, int timeRented, String batteryStatus,
			boolean bikeIsReturned) {
		super();
		this.rentalID = rentalID;
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
	
//	public List getAllInformation() throw SQLException{
//		Statement stm = BikeDB.getConnection().createStatement();
//        ResultSet res = stm.executeQuery("select * from BikeRental");
//        ArrayList medium = new ArrayList<>();
//        while (res.next()) {
//            BikeRental bikeRental = new BikeRental()
//                .setUserID(res.getInt("userid"))
//                .setBikeID(res.getInt("bikeid"))
//                .setStartRental(res.getDate("startrental"))
//                .setTimeRented(res.getInt("TimeRented"))
//                .setBatteryStatus(res.getString("battery"))
//                .setBikeIsReturned(res.getBoolean("isreturned"));
//            medium.add(BikeRental);
//        }
//	}
}
	
	
	

