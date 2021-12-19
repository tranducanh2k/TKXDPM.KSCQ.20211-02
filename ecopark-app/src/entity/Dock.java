package entity;

public class Dock {
	private String dockName;
	private String location;
	private int area;
	private int numberOfBike;
	
	public Dock(String dockName, String location, int area, int numberOfBike) {
		super();
		this.dockName = dockName;
		this.location = location;
		this.area = area;
		this.numberOfBike = numberOfBike;
	}

	public String getDockName() {
		return dockName;
	}

	public void setDockName(String dockName) {
		this.dockName = dockName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getNumberOfBike() {
		return numberOfBike;
	}

	public void setNumberOfBike(int numberOfBike) {
		this.numberOfBike = numberOfBike;
	}
	
	
	
}
