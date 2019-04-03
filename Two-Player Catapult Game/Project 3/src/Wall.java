import java.awt.*;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.*;

public class Wall extends JComponent{
	protected Brick[][] bricks;

	public Wall(int rows, int cols) {
		bricks = new Brick[rows][cols];
		
		
		Dimension size=getSize();
		size.setSize(0, 420);
		
		int x = 0;
		int y = size.height;
		System.out.println(y);
		for(int i=0;i<rows;i++){
			x=210;
			for(int j=0;j<cols;j++){
				bricks[i][j]=new Brick(x,y,30,20);
				x=x+30;
			}
			y=y-20;
		}
		Random rand=new Random();
		int a=rand.nextInt(rows);
		int b=rand.nextInt(cols);
		int newx=bricks[a][b].getX();
		int newy=bricks[a][b].getY();
		bricks[a][b]=new Brick(newx,newy,30,20);
		bricks[a][b].setState(bricks[a][b].getState().BOMB);
	}
	
	public void draw(Graphics g){
		for (int i=0;i<bricks.length;i++){
			for(int j=0;j<bricks[i].length;j++){
				Brick brick=bricks[i][j];
				brick.draw(g);
			}
		}
	}
}
