import java.awt.Color;
import java.awt.Graphics;

public class Brick {
	public enum BrickState {
		NORMAL, LAST_HIT, BROKEN, BOMB,CATAPULT
	}

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BrickState state;

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
	public BrickState getState() {
		return state;
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
	public void setState(BrickState state) {
		this.state = state;
	}
	
	
	public Brick(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		state = BrickState.NORMAL;
	}
	public void draw(Graphics g){
		//g.setColor(Color.black);
		//g.drawRect(x, y, width, height);
		if(state==BrickState.NORMAL){
		g.setColor(Color.red);}
		if(state==BrickState.BOMB){
			g.setColor(Color.GREEN);
		}
		if(state==BrickState.CATAPULT){
			g.setColor(Color.BLUE);
		}
		if(state==BrickState.LAST_HIT){
			g.setColor(Color.MAGENTA);
		}
		
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		
		if(state==BrickState.BROKEN){
			g.setColor(Color.CYAN);
			g.fillRect(x, y, width, height);
			g.setColor(Color.CYAN);
			g.drawRect(x, y, width, height);
		}
		
	}
}
