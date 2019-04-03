public class Intersection {
	

	String IntersectionID;
	double distance;
	double longitude;
	double latitude;
	boolean known;
	Intersection path;
	
	public String getIntersectionID() {
		return IntersectionID;
	}

	public void setIntersectionID(String intersectionID) {
		IntersectionID = intersectionID;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public boolean isKnown() {
		return known;
	}

	public void setKnown(boolean known) {
		this.known = known;
	}

	public Intersection getPath() {
		return path;
	}

	public void setPath(Intersection path) {
		this.path = path;
	}
	
}
