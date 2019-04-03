
public class Road {
	
	String roadID;
	String intersect1;
	String intersect2;
	double distance;
	

	public Road(String road, String int1, String int2, double dist) {
		roadID = road;
		intersect1 = int1;
		intersect2 = int2;
		distance = dist;
	}

	public String getRoadID() {
		return roadID;
	}


	public void setRoadID(String roadID) {
		this.roadID = roadID;
	}


	public String getIntersect1() {
		return intersect1;
	}


	public void setIntersect1(String intersect1) {
		this.intersect1 = intersect1;
	}


	public String getIntersect2() {
		return intersect2;
	}


	public void setIntersect2(String intersect2) {
		this.intersect2 = intersect2;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}

}
