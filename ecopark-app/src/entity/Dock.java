package entity;

public class Dock {
	private String dockName;
	private String location;
	private String area;
	private int numberOfBike;
	private String image;
	
	public Dock(String dockName, String location, String area, int numberOfBike, String image) {
		super();
		this.dockName = dockName;
		this.location = location;
		this.area = area;
		this.numberOfBike = numberOfBike;
		this.image = image;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getNumberOfBike() {
		return numberOfBike;
	}

	public void setNumberOfBike(int numberOfBike) {
		this.numberOfBike = numberOfBike;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
