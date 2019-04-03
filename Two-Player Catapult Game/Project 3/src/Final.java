import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Final extends JFrame implements ActionListener, ChangeListener, KeyEventDispatcher {
	protected Game game;
	// protected JPanel panel;
	protected JButton launch1;
	protected JButton launch2;
	protected JLabel angle1;
	protected JLabel angle2, score1, score2;
	protected int a1, a2, v1, v2, s1, s2;
	protected JLabel vel1, vel2;
	protected double ypos;

	public double getYpos() {
		return ypos;
	}

	public void setYpos(double ypos) {
		this.ypos = ypos;
	}

	public JButton getLaunch1() {
		return launch1;
	}

	public JButton getLaunch2() {
		return launch2;
	}

	public void setLaunch1(JButton launch1) {
		this.launch1 = launch1;
	}

	public void setLaunch2(JButton launch2) {
		this.launch2 = launch2;
	}

	public Game getGame() {
		return game;
	}

	public int getA1() {
		return a1;
	}

	public int getA2() {
		return a2;
	}

	public int getV1() {
		return v1;
	}

	public int getV2() {
		return v2;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setA1(int a1) {
		this.a1 = a1;
	}

	public void setA2(int a2) {
		this.a2 = a2;
	}

	public void setV1(int v1) {
		this.v1 = v1;
	}

	public void setV2(int v2) {
		this.v2 = v2;
	}

	public Final() {
		super("Catapults");
		game = new Game();
		add(game, BorderLayout.CENTER);

		s1 = 0;
		s2 = 0;

		launch1 = new JButton("Player 1 Fire");
		launch2 = new JButton("Player 2 Fire");
		angle1 = new JLabel("Player 1 use Left and Right to set angle.");
		angle2 = new JLabel("Player 2 use A and D to set angle.");
		vel1 = new JLabel("Player 1 use Up and Down to set velocity.");
		vel2 = new JLabel("Player 2 use W and S to set velocity.");
		score1 = new JLabel("Player 1:" + s1);
		score2 = new JLabel("Player 2: 0" + s2);
		launch1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				score1.setText("Player 1:" + s1);
				score2.setText("Player 2:" + s2);
				game.Fire1(game.catapult1);

				game.resetWall(game.wall);
				for (int i = 0; i < game.wall.bricks.length; i++) {
					for (int j = 0; j < game.wall.bricks[i].length; j++) {

						if (game.getWall().bricks[i][j].getX() - game.xpos1 < 15
								&& Math.abs(game.getWall().bricks[i][j].getY() - game.ypos1) < 10) {
							if (game.getWall().bricks[i][j].getState() == game.wall.bricks[i][j].state.NORMAL) {
								game.getWall().bricks[i][j].setState(game.wall.bricks[i][j].getState().LAST_HIT); // from a normal brick to a last hit brick

								break;
							}
							if (game.getWall().bricks[i][j].getState() == game.wall.bricks[i][j].state.LAST_HIT) { //From a last hit brick to a broken one
								game.getWall().bricks[i][j].setState(game.wall.bricks[i][j].getState().BROKEN);
								s1 += 10;
								break;
							}
							if (game.getWall().bricks[i][j].getState() == game.wall.bricks[i][j].state.BOMB) { // This makes the rows disappear when the bomb is hit
								for (int k = 0; k < game.wall.bricks[i].length; k++) {
									game.getWall().bricks[i][k].setState(game.wall.bricks[i][j].getState().BROKEN);
								}
								s1 += 200;
								s2-= 400;
								break;
							}

						}
						if(game.getWall().bricks[i][j].getX() - game.catapult2.x < 15
						&& Math.abs(game.getWall().bricks[i][j].getY() - game.catapult2.y) < 10){
							if(game.wall.bricks[i][j].getState().BOMB!=game.wall.bricks[i][j].getState()){ //if you hit your opponent
								s1+=500;
								
								
							}
							else{
								s1+=150;
							}
						}
						else{
							s1-=10;
						}
					}
				}
			}

		});
		launch2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { //it's all mirrored for player two
				game.Fire2(game.getCatapult2());

				game.resetWall(game.wall);
				score1.setText("Player 1:" + s1);
				score2.setText("Player 2:" + s2);
				for (int i = 0; i < game.wall.bricks.length; i++) {
					for (int j = 0; j < game.wall.bricks[i].length; j++) {

						if (game.getWall().bricks[i][j].getX() - game.xpos2 < 15
								&& Math.abs(game.getWall().bricks[i][j].getY() - game.ypos2) < 10) {
							if (game.getWall().bricks[i][j].getState() == game.wall.bricks[i][j].state.NORMAL) {
								game.getWall().bricks[i][j].setState(game.wall.bricks[i][j].getState().LAST_HIT);

								break;
							}
							if (game.getWall().bricks[i][j].getState() == game.wall.bricks[i][j].state.LAST_HIT) {
								game.getWall().bricks[i][j].setState(game.wall.bricks[i][j].getState().BROKEN);
								s2 += 10;
								break;
							}
							if (game.getWall().bricks[i][j].getState() == game.wall.bricks[i][j].state.BOMB) {
								for (int k = 0; k < game.wall.bricks[i].length; k++) {
									game.getWall().bricks[k][j].setState(game.wall.bricks[i][j].getState().BROKEN);
								}
								s2 += 200;
								s1-= 400;
								break;
							}

						}
						if(game.getWall().bricks[i][j].getX() - game.catapult1.x < 15
						&& Math.abs(game.getWall().bricks[i][j].getY() - game.catapult1.y) < 10){
							if(game.wall.bricks[i][j].getState().BOMB!=game.wall.bricks[i][j].getState()){
								s2+=500;
								
								
							}
							else{
								s2+=150;
							}
						}
						else{
							s2-=10;
						}
					}
				}
			}

		});

		add(launch1, BorderLayout.WEST);
		add(launch2, BorderLayout.EAST);

		a1 = 0;
		a2 = 0;
		v1 = 0;
		v2 = 0;
		
		
		JPanel subPanel2 = new JPanel();
		JPanel subPanel = new JPanel();
		subPanel.add(angle1);
		subPanel.add(vel1);
		subPanel.add(angle2);
		subPanel.add(vel2);

		subPanel2.add(score1);
		subPanel2.add(score2);

		add(subPanel, BorderLayout.SOUTH);
		add(subPanel2, BorderLayout.NORTH);

		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);
		setFocusable(true);
		// getTopLevelAncestor().requestFocus();

		setSize(640 + 210, 480 + 60);

	}

	public static void main(String[] args) {
		Final frame = new Final();
		// frame.add(new Game());
		// frame.add;
		frame.pack();
		frame.setSize(640 + 210, 480 + 60);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// game.Fire1(game.catapult1);
		// setSize(640,480);

	}

	// @Override
	// public void keyPressed(KeyEvent k) {
	// int key = k.getKeyCode();
	//
	// //System.out.println(key);
	// // 39 is right, 37 is left, A is 65, D is 68
	// //38 is up, 40 is down, 87 is W, 83 is S
	// switch (key) {
	// case 37:
	// if(a1<180){
	// a1++;}
	// game.setAng1(a1);
	// angle1.setText("P1: "+a1+" degrees.");
	// break;
	// case 39:
	// if(a1>0){
	// game.setAng1(a1);
	// a1--;
	// angle1.setText("P1: "+a1+" degrees.");
	// }
	// break;
	//
	// case 38:
	// if(v1<200){
	// v1++;}
	// game.setV1(v1);
	//
	// vel1.setText("P1: "+v1+" m/s.");
	// break;
	// case 40:
	// if(v1>0){
	// v1--;
	// vel1.setText("P1: "+v1+" m/s.");
	// }
	// game.setV1(v1);
	// break;
	// case 65:
	//
	// break;
	// case 68:
	//
	// break;
	// }
	//
	// }

	// @Override
	// public void keyReleased(KeyEvent arg0) {
	// // TODO Auto-generated method stub
	//
	// }

	// @Override
	// public void keyTyped(KeyEvent arg0) {
	//
	//
	// }

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {

		if (e.getID() == KeyEvent.KEY_PRESSED) {
			int key = e.getKeyCode();

			// System.out.println(key);
			// 39 is right, 37 is left, A is 65, D is 68
			// 38 is up, 40 is down, 87 is W, 83 is S
			switch (key) {
			case 39:
				if (a1 < 180) {
					a1++;
				}
				game.setAng1(a1);
				angle1.setText("P1: " + a1 + " degrees.");
				break;
			case 37:
				if (a1 > 0) {
					game.setAng1(a1);
					a1--;
					angle1.setText("P1: " + a1 + " degrees.");
				}
				break;

			case 38:
				if (v1 < 200) {
					v1++;
				}
				game.setV1(v1);

				vel1.setText("P1: " + v1 + " m/s.");
				break;
			case 40:
				if (v1 > 0) {
					v1--;
					vel1.setText("P1: " + v1 + " m/s.");
				}
				game.setV1(v1);
				break;
			case 68:
				if (a2 < 180) {
					a2++;
				}
				game.setAng2(a2);
				angle2.setText("P2: " + a2 + " degrees.");
				break;
			case 65:
				if (a2 > 0) {
					a2--;
				}
				game.setAng1(a2);
				angle2.setText("P2: " + a2 + " degrees.");
				break;
			case 87:
				if (v2 < 200) {
					v2++;
				}
				game.setV2(v2);

				vel2.setText("P2: " + v2 + " m/s.");
				break;
			case 83:
				if (v2 > 0) {
					v2--;
				}
				game.setV2(v2);

				vel2.setText("P2: " + v2 + " m/s.");
				break;
			}
		} else if (e.getID() == KeyEvent.KEY_RELEASED) {

		} else if (e.getID() == KeyEvent.KEY_TYPED) {

		}
		return false;
	}
}