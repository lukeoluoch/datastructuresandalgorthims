
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class MapGUI extends JPanel {

	public static ArrayList<Road> roads;
	public static HashMap<String, Intersection> intersectionMap;
	public static boolean URMap = false;
	public static double minLat;
	public static double minLong;
	public static double maxLat;
	public static double maxLong;
	public static double xScale;
	public static double yScale;

	public MapGUI(ArrayList<Road> roads, HashMap<String, Intersection> intersectMap, double minimumLat,
			double maximumLat, double minimumLong, double maximumLong) {

		MapGUI.roads = roads;
		MapGUI.intersectionMap = intersectMap;

		minLat = minimumLat;
		maxLat = maximumLat;
		minLong = minimumLong;
		maxLong = maximumLong;

		setPreferredSize(new Dimension(1000, 1000));

	}

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);

		if (URMap) {
			g2.setColor(new Color(11, 63, 105)); // Rochester Blue
		} else {
			g2.setColor(Color.BLACK);
		}

		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		xScale = this.getWidth() / (maxLong - minLong);
		yScale = this.getHeight() / (maxLat - minLat);

		g2.setColor(Color.WHITE);
		if (URMap) {
			BasicStroke thicc = new BasicStroke(5);
			g2.setStroke(thicc);//there are less roads on the UR Map, so i would like to see them thicker
			BufferedImage rocky = null;//a little UR flair
			AffineTransform xform = new AffineTransform();
			xform.scale(0.06125, 0.06125);
			try {
				rocky = ImageIO.read(new File("src/Rocky.png"));
			} catch (IOException e) {
			}

			g2.drawImage(rocky, xform, this);

		}
		Intersection int1, int2;

		double x1, y1, x2, y2;

		// road drawing method
		for (Road r : roads) {

			scale();

			int1 = intersectionMap.get(r.intersect1);
			int2 = intersectionMap.get(r.intersect2);

			x1 = int1.longitude;
			y1 = int1.latitude;
			x2 = int2.longitude;
			y2 = int2.latitude;

			g2.draw(new Line2D.Double((x1 - minLong) * xScale, getHeight() - ((y1 - minLat) * yScale),
					(x2 - minLong) * xScale, getHeight() - ((y2 - minLat) * yScale)));

		}

		// Mapping the path using dijsktra's algorithm
		if (Map.dijkstraPath != null) {
			if (URMap) {
				g2.setColor(Color.YELLOW);// Dandelion Yellow
			} else {
				g2.setColor(Color.RED);
			}

			for (int i = 0; i < Map.dijkstraPath.length - 1; i++) {

				x1 = Map.dijkstraPath[i].longitude;
				y1 = Map.dijkstraPath[i].latitude;
				x2 = Map.dijkstraPath[i + 1].longitude;
				y2 = Map.dijkstraPath[i + 1].latitude;

				g2.draw(new Line2D.Double((x1 - minLong) * xScale, getHeight() - ((y1 - minLat) * yScale),
						(x2 - minLong) * xScale, getHeight() - ((y2 - minLat) * yScale)));

			}

		}

	}

	// scaling method
	public void scale() {

		xScale = this.getWidth() / (maxLong - minLong);
		yScale = this.getHeight() / (maxLat - minLat);

	}

}
