
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Project4 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the map you would like:");
		String fileloc = "src/"; // change
																						// to
																						// file
																						// location
		String maptext = in.nextLine();
		if (maptext.equals("UR") || maptext.equals("University of Rochester") || maptext.equals("Rochester")) {
			maptext = "ur.txt";
			fileloc += maptext;
			MapGUI.URMap = true;
		} else if (maptext.equals("Monroe")) {
			maptext = "monroe.txt";
			fileloc += maptext;
		} else if (maptext.equals("NY") || maptext.equals("New York State") || maptext.equals("New York")) {
			maptext = "nys.txt";
			fileloc += maptext;
		} else {
			System.out.println("Enter a valid map. Restart program");
			System.exit(0);
			;
		}

		System.out.println("Enter what commands you want");// --directions or
															// --show map (ALL
															// IN ONE LINE)
															// (--directions
															// needs to be
															// followed by the
															// start point and
															// end point)
		String commands = in.nextLine();
		String[] commands2;
		commands2 = commands.split(" ");
		System.out.println(Arrays.toString(commands2));
		File mapData = new File(fileloc);

		Scanner scan = new Scanner(mapData); // only for counting number of
												// intersects
		Scanner scan2 = new Scanner(mapData); // for adding roads and maps

		int intersects = 0;
		while (scan.nextLine().startsWith("i")) {
			intersects++;
		}

		String intersectionID;
		double lat;
		double longitude;
		Intersection v;

		// create the Map
		Map map = new Map(intersects);
		String curr = scan2.nextLine();

		String[] info;

		// adding intersections to map
		while (curr.startsWith("i")) {

			info = curr.split("\t");
			intersectionID = info[1];
			lat = Double.parseDouble(info[2]);
			longitude = Double.parseDouble(info[3]);

			v = new Intersection();
			v.distance = Integer.MAX_VALUE;
			v.IntersectionID = intersectionID;
			v.latitude = lat;
			v.longitude = longitude;
			v.known = false;

			curr = scan2.nextLine();

			// add the new intersection into the map
			map.insert(v);
		}

		String roadID, int1, int2;

		Intersection w, x;

		double distance;

		// adding roads to the Map
		while (curr.startsWith("r")) {

			info = curr.split("\t");
			roadID = info[1];
			int1 = info[2];
			int2 = info[3];
			w = Map.intersectLookup(int1);
			x = Map.intersectLookup(int2);
			distance = Map.roadDist(w, x);

			map.insert(new Road(roadID, int1, int2, distance));

			if (scan2.hasNextLine() == false) {
				break;
			}

			curr = scan2.nextLine();

		}

		boolean showMap = false;
		boolean dijkstras = false;
		String directionsStart = null;
		String directionsEnd = null;

		for (int i = 0; i < commands2.length; i++) {
			if (commands2[i].equals("--show")) {
				showMap = true;
			}

			if (commands2[i].equals("--directions")) {
				dijkstras = true;

				directionsStart = commands2[i + 1];
				directionsEnd = commands2[i + 2];
			}

		}

		// --show command
		String fileName;
		if (maptext.equals("ur_campus.txt")) {
			fileName = "University of Rochester";
		} else if (maptext.equals("monroe.txt")) {
			fileName = "Monroe County";
		} else if (maptext.equals("nys.txt")) {
			fileName = "New York";
		} else {
			fileName = "Generic Map";
		}

		if (showMap == true) {

			JFrame frame = new JFrame(fileName);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.getContentPane()
					.add(new MapGUI(Map.roads, Map.intersectionMap, Map.minLat, Map.maxLat, Map.minLong, Map.maxLong));
			frame.pack();
			frame.setVisible(true);
		}

		// --directions command
		if (dijkstras == true) {

			map.dijkstraAlgorithm(directionsStart);

			System.out.println("\nThe shortest path from " + directionsStart + " to " + directionsEnd + " is: ");
			System.out.println(Map.formPath(directionsEnd));
			if (fileName.equals("University of Rochester")) { // show distance
																// in meters
				System.out.println("Length of the path from " + directionsStart + " to " + directionsEnd + " is: "
						+ Map.dijkstraPath[0].distance + " meters.");
			} else { // shows distance in miles
				System.out.println("Length of the path from " + directionsStart + " to " + directionsEnd + " is: "
						+ Map.dijkstraPath[0].distance * 0.000621371 + " miles.");
			}

		}

		in.close();
		scan.close();
		scan2.close();
	}

}
