package entity;

public class Bike {
	
	private int bikeID;
	private String dockName;
	private String bikeType;
	private String licensePlate;
	private String battery;

	public Bike(){

	}
	
	public Bike(int bikeID, String dockName, String bikeType, String licensePlate, String battery) {
		super();
		this.bikeID = bikeID;
		this.dockName = dockName;
		this.bikeType = bikeType;
		this.licensePlate = licensePlate;
		this.battery = battery;
	}
	
	public int getBikeID() {
		return bikeID;
	}
	public void setBikeID(int bikeID) {
		this.bikeID = bikeID;
	}
	public String getDockName() {
		return dockName;
	}
	public void setDockName(String dockName) {
		this.dockName = dockName;
	}
	public String getBikeType() {
		return bikeType;
	}
	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}

}
