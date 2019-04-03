
import java.util.*;
public class Map {
	public static HashMap<String, MyLinkedList> graph;
	public static int numIntersections;
	public static ArrayList<Road> roads;
	public static HashMap<String, Intersection> intersectionMap;
	public static PriorityQueue<Intersection> unknownIntersectionsHeap;
	public static HashMap<String, HashSet<String>> intersectionSets;
	public static ArrayList<Road> minWeightSpanTree;
	public static Intersection[] dijkstraPath;
	public static double minLat;
	public static double maxLat;
	public static double minLong;
	public static double maxLong;

	// constructor for the Map
	public Map(int vertices) {
		graph = new HashMap<String, MyLinkedList>();
		numIntersections = vertices;
		roads = new ArrayList<Road>();
		intersectionMap = new HashMap<String, Intersection>();

		Comparator<Intersection> comparator = new Comparator<Intersection>() { // comparator for intersection distance
			@Override
			public int compare(Intersection i1, Intersection i2) {
				if (i1.distance < i2.distance) {
					return -1;
				} else {
					return 1;
				}
			}
		};

		unknownIntersectionsHeap = new PriorityQueue<Intersection>(vertices, comparator);
		minLat = minLong = Integer.MAX_VALUE;
		maxLat = maxLong = Integer.MIN_VALUE;

	}

	public int size() { // Current size of the graph
		return graph.size();
	}

	// Method for finding the path for Dijkstra's algorithm

	public static String formPath(String endID) {

		Intersection temp = intersectionMap.get(endID); // get the intersection that has endID as its ID
		String[] path = new String[intersectionMap.size()]; //path array will contain the order of the nodes from the end to the start vertex								
		int counter = 0;
		
		while (temp.path != null) { // adds the vertex's path to the path array until the beginning vertex is reached
			path[counter] = temp.IntersectionID;
			temp = temp.path;
			counter++;
		}
		
		path[counter] = temp.IntersectionID; // add the start vertex to the path array								
		int totalPath = 0;
		for (int i = 0; i < path.length; i++) { // calculate total length of path							
			if (path[i] == null) {
				totalPath = i;
				break;
			}
		}

		dijkstraPath = new Intersection[totalPath]; // dijkstraPath is an array of intersections used to graph the directions
										
		for (int i = 0; i < totalPath; i++) {
			dijkstraPath[i] = intersectionMap.get(path[i]);
		}

		String finalPath = "";

		for (int i = counter; i > -1; i--) { //since the arrays are ordered from end to start, this creates a string from the start path to the end path
			finalPath = finalPath + path[i] + "\n";
		}
		return finalPath;
	}

	// Method used in Dijkstra's algorithm for smallest unknown vertex/intersection
	public static Intersection smallestUnknownVertex() {
		Intersection temp = unknownIntersectionsHeap.remove();
		return intersectionMap.get(temp.IntersectionID);

	}

	// Method that calculates shortest distance
	// adapted from Geeks For Geeks and lecture notes
	public void dijkstraAlgorithm(String intersectionID) {
		
		Intersection start = intersectionMap.get(intersectionID);
		unknownIntersectionsHeap.remove(start);
		start.distance = 0;
		unknownIntersectionsHeap.add(start);
		
		double cost;
		int numUnknownVertices = intersectionMap.size();
		while (numUnknownVertices > 0) {
			Intersection temp = smallestUnknownVertex();
			temp.known = true;
			numUnknownVertices--;
			MyLinkedList currentVertex = graph.get(temp.IntersectionID);
			Edge currentRoad = currentVertex.head.edge;
			Intersection currentIntersection;
			while (currentRoad != null) {
				if (currentRoad.road.intersect1.equals(temp.IntersectionID)) {
					currentIntersection = intersectionMap.get(currentRoad.road.intersect2);
				} else {
					currentIntersection = intersectionMap.get(currentRoad.road.intersect1);
				}
				if (currentIntersection.known == false) {
					cost = findCost(temp, currentIntersection);
					if (temp.distance + cost < currentIntersection.distance) {
						unknownIntersectionsHeap.remove(currentIntersection);
						currentIntersection.distance = temp.distance + cost;
						currentIntersection.path = temp;
						unknownIntersectionsHeap.add(currentIntersection);
					}
				}
				currentRoad = currentRoad.next;
			}
		}
	}
	// getting cost between two intersections
	public double findCost(Intersection int1, Intersection int2) {
		MyLinkedList temp = graph.get(int1.IntersectionID);
		return temp.findCost(int2);
	}

	public boolean connected(Intersection int1, Intersection int2) {
		MyLinkedList temp = graph.get(int1.IntersectionID);
		return temp.connected(int2);
	}
	public void insert(Intersection i) {
		if (i.latitude < minLat) {
			minLat = i.latitude;
		}

		if (i.latitude > maxLat) {
			maxLat = i.latitude;
		}

		if (i.longitude < minLong) {
			minLong = i.longitude;
		}

		if (i.longitude > maxLong) {
			maxLong = i.longitude;
		}
		intersectionMap.put(i.IntersectionID, i);
		unknownIntersectionsHeap.add(i);
		MyLinkedList temp = new MyLinkedList();
		temp.insert(i);
		graph.put(i.IntersectionID, temp);
	}
	public void insert(Road e) {
		MyLinkedList int1 = graph.get(e.intersect1);
		MyLinkedList int2 = graph.get(e.intersect2);
		int1.insert(e);
		int2.insert(e);
		roads.add(e);
	}
	public static Intersection intersectLookup(String intersectID) {
		return intersectionMap.get(intersectID);
	}

	// method that calculates the distance between two intersection objects
	public static double roadDist(Intersection int1, Intersection int2) {

		return calcDist(int1.latitude, int1.longitude, int2.latitude, int2.longitude);

	}

	// method that calculates the distance between two pairs of longitude and latitude coordinates
	// gotten and edited from http://www.movable-type.co.uk/scripts/latlong.html
	public static double calcDist(double lat1, double long1, double lat2, double long2) {
		int earthRad = 6371000;
		lat1 = Math.toRadians(lat1);
		long1 = Math.toRadians(long1);
		lat2 = Math.toRadians(lat2);
		long2 = Math.toRadians(long2);
		double delLat = lat2 - lat1;
		double delLong = long2 - long1;
		double a = (Math.sin(delLat / 2) * Math.sin(delLat / 2)) + (Math.cos(lat1) * Math.cos(lat2) * Math.sin(delLong / 2) * Math.sin(delLong / 2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return earthRad * c;

	}

}
