import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.lang.*;

public class Catapult extends JPanel{
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	//protected JPanel panel;
	protected JButton launch1;
	//protected JButton launch2;
	protected JLabel angle1;
	//protected JLabel angle2;
	protected JLabel v1;

	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public JButton getLaunch1() {
		return launch1;
	}

	
	public JLabel getAngle1() {
		return angle1;
	}

	

	public JLabel getV1() {
		return v1;
	}

	

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setLaunch1(JButton launch1) {
		this.launch1 = launch1;
	}

	
	public void setAngle1(JLabel angle1) {
		this.angle1 = angle1;
	}

	public void setV1(JLabel v1) {
		this.v1 = v1;
	}

	

	public Catapult (int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
//		panel=new JPanel();
//		panel.setLayout(new FlowLayout());
//		panel.setOpaque(false);
//		
//		launch1=new JButton("Player 1 Fire");
//		panel.add(launch1);
//		
//		angle1=new JLabel("Press the left and right keys to change angle");
//		panel.add(angle1);
//		
//		JTextField field = new JTextField(20);
//		panel.add(field);
//		
//		add(panel,BorderLayout.NORTH);
		
		
		
	}

	public void draw(Graphics g) {
		// g.setColor(Color.black);
		// g.drawRect(x, y, width, height);

		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
	}
	public void Fire(Graphics g){
		g.setColor(Color.BLACK);
		//int w= 
		//g.drawLine(getX(),getY(),8,9);
	}

}
