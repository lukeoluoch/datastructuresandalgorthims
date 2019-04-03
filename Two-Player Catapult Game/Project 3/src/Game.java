import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Game extends JComponent implements KeyListener {
	protected Wall wall;
	protected Catapult catapult1;
	protected JPanel panel;
	protected Catapult catapult2;
	protected int destination;
	protected int ang1, ang2, v1, v2;
	protected int xpos1, ypos1, xpos2, ypos2;

	public Wall getWall() {
		return wall;
	}

	public Catapult getCatapult() {
		return catapult1;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}

	public void setCatapult(Catapult catapult1) {
		this.catapult1 = catapult1;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Catapult getCatapult1() {
		return catapult1;
	}

	public Catapult getCatapult2() {
		return catapult2;
	}

	public int getDestination() {
		return destination;
	}

	public int getAng1() {
		return ang1;
	}

	public int getAng2() {
		return ang2;
	}

	public int getV1() {
		return v1;
	}

	public int getV2() {
		return v2;
	}

	public int getXpos() {
		return xpos1;
	}

	public int getYpos() {
		return ypos1;
	}

	public void setCatapult1(Catapult catapult1) {
		this.catapult1 = catapult1;
	}

	public void setCatapult2(Catapult catapult2) {
		this.catapult2 = catapult2;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public void setAng1(int ang1) {
		this.ang1 = ang1;
	}

	public void setAng2(int ang2) {
		this.ang2 = ang2;
	}

	public void setV1(int v1) {
		this.v1 = v1;
	}

	public void setV2(int v2) {
		this.v2 = v2;
	}

	public void setXpos(int xpos) {
		this.xpos1 = xpos;
	}

	public void setYpos(int ypos) {
		this.ypos1 = ypos;
	}

	public Game() {
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setOpaque(true);

		wall = new Wall(20, 5);
		catapult2 = new Catapult(360 + 150, 420, 30, 20);
		catapult1 = new Catapult(210 - 150, 420, 30, 20);

		addKeyListener(this);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension size = getSize();
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, size.width, size.height);
		wall.draw(g);
		catapult1.draw(g);
		catapult2.draw(g);

		xpos1 = catapult1.getX();
		ypos1 = catapult1.getY();
		xpos2 = catapult2.getX();
		ypos2 = catapult2.getY();

		// xpos = 0;
		// ypos = 0;

		g.setColor(Color.BLACK);
		g.fillRect(xpos1, ypos1, 2, 2);

		Timer time = new Timer(5000, new ActionListener() { //I put a timer to refresh the canvas every 5 seconds to both add a sense of urgency and tension and to make the canvas repaint automatically.

			@Override			
			public void actionPerformed(ActionEvent arg0) {
				repaint();

			}

		});
		time.start();
		// catapult1.Fire(g);
		// Fire(catapult1);
	}

	double x = 230;
	boolean id = false;
	double x2;

	public void Fire1(Catapult c) { //Fire Method for Catapult 1

		
		double y = xpos1 * Math.tan(Math.toRadians(ang1)) - ((9.81 * xpos1 * xpos1)
				/ (2 * (v1 * Math.cos(Math.toRadians(ang1)) * (v1 * Math.cos(Math.toRadians(ang1))))));
		do {
			getGraphics().fillRect(xpos1, ypos1, 2, 2);
			xpos1 = (int) (xpos1 + 1);
			ypos1 = ypos1 - ((int) (xpos1 * Math.tan(Math.toRadians(ang1)) - ((9.81 * xpos1 * xpos1)
					/ (2 * (v1 * Math.cos(Math.toRadians(ang1)) * (v1 * Math.cos(Math.toRadians(ang1))))))));

		} while (xpos1 <= x);

		for (int i = 0; i < wall.bricks.length; i++) {
			for (int j = 0; j < wall.bricks[i].length; j++) {
				if (wall.bricks[i][j].state == wall.bricks[i][j].getState().BROKEN) {

					x++;
				}

			}
		}

		System.out.println("Actual final Y: " + y);
		System.out.println("Angle: " + ang1 + "Vel:  " + v1);
		System.out.println("Y-Pos: " + ypos1 + " X-Pos: " + xpos1);

	}

	public void Fire2(Catapult c) { //Fire MEthod for catapult 2

		x2 = 450;
		double y = xpos2 * Math.tan(Math.toRadians(ang2)) - ((9.81 * xpos2 * xpos2)
				/ (2 * (v2 * Math.cos(Math.toRadians(ang2)) * (v2 * Math.cos(Math.toRadians(ang2))))));
		do {
			getGraphics().fillRect(xpos2, ypos2, 2, 2);
			xpos2 = (int) (xpos2 - 1);
			ypos2 = ypos2 - ((int) (xpos2 * Math.tan(Math.toRadians(ang2)) - ((9.81 * xpos2 * xpos2)
					/ (2 * (v2 * Math.cos(Math.toRadians(ang2)) * (v2 * Math.cos(Math.toRadians(ang2))))))));

		} while (xpos2 >= x2);

		for (int i = 0; i < wall.bricks.length; i++) {
			for (int j = 0; j < wall.bricks[i].length; j++) {
				if (wall.bricks[i][j].state == wall.bricks[i][j].getState().BROKEN) {

					x2--;
				}

			}
		}

		System.out.println("Actual final Y: " + y);
		System.out.println("Angle: " + ang1 + "Vel:  " + v1);
		System.out.println("Y-Pos: " + ypos2 + " X-Pos: " + xpos2);

	}

	public void resetWall(Wall w) {
		for (int i = 0; i < w.bricks.length - 1; i++) {
			for (int j = 0; j < w.bricks[i].length; j++) {

				if (j == 0) {
					if (w.bricks[i][j].state == w.bricks[i][j].state.BROKEN
							&& w.bricks[i][j + 1].state == w.bricks[i][j].state.BROKEN
							&& w.bricks[i][j + 2].state == w.bricks[i][j].state.BROKEN
							&& w.bricks[i][j + 3].state == w.bricks[i][j].state.BROKEN
							&& w.bricks[i][j + 4].state == w.bricks[i][j].state.BROKEN)
							//&& w.bricks[i][4].state == w.bricks[i][j].state.BROKEN) 
					{
						System.out.println("ouch!");
						id = true;
						Wall wall = new Wall(w.bricks.length - 1, w.bricks[i].length);
						for (int k = 0; k < w.bricks[i].length; k++) {
							if (k != i) {
								wall.bricks[i][j] = w.bricks[k][j];
							}
						}
						setWall(wall);
						// wall.bricks[i][j].setState(w.bricks[i][j].getState().BOMB);
					}

				}
				// if (j>=4){
				// if (wall.bricks[i][j].state.BROKEN == wall.bricks[i][j -
				// 1].state.BROKEN) {
				// wall.bricks[i][j].setState(w.bricks[i][j].getState().BOMB);
				// }}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent k) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
