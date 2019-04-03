import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class Game2048work extends JPanel {
	public Square[][] array = new Square[5][5];
	Random r = new Random();
	public int rand1 = r.nextInt(4);
	public int rand2 = r.nextInt(4);
	public int rand3 = r.nextInt(4);
	public int rand4 = r.nextInt(4);
	public int counter = 0;

	public Game2048work() {
		fillArray();
		setPreferredSize(new Dimension(340, 400));
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					left();
					System.out.println("Counter: " + counter);
					Win();
					Lose();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // I left a quick reset for ease of testing
					reset();
				} else if (e.getKeyCode() == KeyEvent.VK_D) {

					right();
					System.out.println("Counter: " + counter);
					Win();
					Lose();
				} else if (e.getKeyCode() == KeyEvent.VK_W) {
					up();
					System.out.println("Counter: " + counter);
					Win();
					Lose();
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					down();
					System.out.println("Counter: " + counter);
					Win();
					Lose();
				} else if (e.getKeyCode() == KeyEvent.VK_R) {
					System.out.println("Are you sure you want to reset? Y/N. Answer using the console");
					Scanner scan = new Scanner(System.in);
					String ans = scan.nextLine();
					if (ans.equals("Y") || ans.equals("y")) {
						reset();
						System.out.println("Gane has been reset");
					} else if (ans.equals("N") || ans.equals("n")) {
						System.out.println("Alright. Back to playing");

					} else {
						System.out.println("That wasn't an option. If you want to reset, press 'R' again");
					}

				} else if (e.getKeyCode() == KeyEvent.VK_Q) {
					System.out.println("Are you sure you want to quit? Y/N. Answer using the console");
					Scanner scan = new Scanner(System.in);
					String ans = scan.nextLine();
					if (ans.equals("Y") || ans.equals("y")) {
						System.exit(0);
						System.out.println("Thanks for playing. Your score counter was " + counter);
					} else if (ans.equals("N") || ans.equals("n")) {
						System.out.println("Alright. Back to playing");

					} else {
						System.out.println("That wasn't an option. If you want to quit, press 'Q' again");
					}

					
				}

				repaint();
			}
		});

	}

	public boolean fullArray(int row, int col) {
		if (row == 3 && col == 3) {
			return true; // true means there are no 0 values
		} else {
			if (array[row][col].empty()) {
				return false;
			} else if (col < 3) {
				return fullArray(row, col + 1);
			} else {
				return fullArray(row + 1, 0);
			}
		}

	}

	public void addSquare() {
		int prob = r.nextInt(5);
		int x = r.nextInt(4);
		int y = r.nextInt(4);
		while (!array[x][y].empty()) {
			x = r.nextInt(4);
			y = r.nextInt(4);
		}
		if (prob == 4 && array[x][y].empty()) {
			array[x][y] = new Square(4);
		} else if (prob != 4 && array[x][y].empty()) {
			array[x][y] = new Square(2);
		}
	}

	public void left() {
		Square[][] copy = new Square[5][5];
		copy = array.clone();
		// System.out.println(fullArray(0,0));
		if (!fullArray(0, 0)) {
			for (int i = 0; i < 4; i++) {
				if (array[0][i].empty()) {
					array[0][i] = array[1][i];
					array[1][i] = array[2][i];
					array[2][i] = array[3][i];
					array[3][i] = array[4][1];

				}
				if (array[1][i].empty()) {
					array[1][i] = array[2][i];
					array[2][i] = array[3][i];
					array[3][i] = array[4][1];
				}
				if (array[2][i].empty()) {
					array[2][i] = array[3][i];
					array[3][i] = array[4][1];
				}
				if (array[3][i].empty()) {
					array[3][i] = array[4][1];

				}
				if (array[0][i].number == array[1][i].number) {
					array[0][i] = new Square(array[0][i].number * 2);
					array[1][i] = array[2][i];
					array[2][i] = array[3][i];
					array[3][i] = array[4][1];

				}
				if (array[1][i].number == array[2][i].number) {
					array[1][i] = new Square(array[2][i].number * 2);
					array[2][i] = array[3][i];
					array[3][i] = array[4][1];
				}
				if (array[2][i].number == array[3][i].number) {
					array[2][i] = new Square(array[3][i].number * 2);
					array[3][i] = array[4][1];

				}

			}
			if (copy != array) {
				addSquare();
				counter++;
			}
		} else {
			System.out.println("Invalid Move");
		}
	}

	public void right() {
		Square[][] copy = new Square[5][5];
		copy = array.clone();
		// System.out.println(fullArray(0,0));
		if (!fullArray(0, 0)) {
			for (int i = 0; i < 4; i++) {
				
				if (array[3][i].empty()) {
					array[3][i] = array[2][i];
					array[2][i] = array[1][i];
					array[1][i] = array[0][i];
					array[0][i] = new Square(0);
				}
				if (array[2][i].empty()) {
					array[2][i] = array[1][i];
					array[1][i] = array[0][i];
					array[0][i] = new Square(0);

				}
				if (array[1][i].empty()) {
					array[1][i] = array[0][i];
					array[0][i] = new Square(0);

				}

				if (array[3][i].number == array[2][i].number) {
					array[3][i] = new Square(array[2][i].number * 2);
					array[2][i] = array[1][i];
					array[1][i] = array[0][i];
					array[0][i] = new Square(0);

				}
				if (array[2][i].number == array[1][i].number) {
					array[2][i] = new Square(array[1][i].number * 2);
					array[1][i] = array[0][i];
					array[0][i] = new Square(0);

				}
				if (array[1][i].number == array[0][i].number) {
					array[1][i] = new Square(array[0][i].number * 2);
					array[0][i] = new Square(0);

				}
			}
			if (copy != array) {
				addSquare();
				counter++;
			}
		} else {
			System.out.println("Invalid Move");
		}
	}

	public void up() {
		Square[][] copy = new Square[5][5];
		copy = array.clone();
		if (!fullArray(0, 0)) {
			for (int i = 0; i < 4; i++) {
				if (array[i][0].empty()) {
					array[i][0] = array[i][1];
					array[i][1] = array[i][2];
					array[i][2] = array[i][3];
					array[i][3] = array[i][4];
					

				}
				if (array[i][1].empty()) {
					array[i][1] = array[i][2];
					array[i][2] = array[i][3];
					array[i][3] = array[i][4];
					
				}
				if (array[i][2].empty()) {
					array[i][2] = array[i][3];
					array[i][3] = array[i][4];
					
				}
				if (array[i][3].empty()) {
					array[i][3] = array[i][4];
					
				}
				if (array[i][0].number == array[i][1].number) {
					array[i][0] = new Square(array[i][1].number * 2);
					array[i][1] = array[i][2];
					array[i][2] = array[i][3];
					array[i][3] = new Square(0);

				}
				if (array[i][1].number == array[i][2].number) {
					array[i][1] = new Square(array[i][2].number * 2);
					array[i][2] = array[i][3];
					array[i][3] = new Square(0);

				}
				if (array[i][2].number == array[i][3].number) {
					array[i][2] = new Square(array[i][3].number * 2);
					array[i][3] = new Square(0);

				}
			}
			if (copy != array) {
				addSquare();
				counter++;
			}
		} else {
			System.out.println("Invalid Move");
		}
	}

	public void down() {
		Square[][] copy = new Square[5][5];
		copy = array.clone();
		System.out.println(fullArray(0, 0));
		if (!fullArray(0, 0)) {
			for (int i = 0; i < 4; i++) {
				
				if (array[i][3].empty()) {
					array[i][3] = array[i][2];
					array[i][2] = array[i][1];
					array[i][1] = array[i][0];
					array[i][0] = new Square(0);
				}
				if (array[i][2].empty()) {
					array[i][2] = array[i][1];
					array[i][1] = array[i][0];
					array[i][0] = new Square(0);
				}
				if (array[i][1].empty()) {
					array[i][1] = array[i][0];
					array[i][0] = new Square(0);
				}
				if (array[i][3].number == array[i][2].number) {
					array[i][3] = new Square(array[i][2].number * 2);
					array[i][2] = array[i][1];
					array[i][1] = array[i][0];
					array[i][0] = new Square(0);
				}
				if (array[i][2].number == array[i][1].number) {
					array[i][2] = new Square(array[i][1].number * 2);
					array[i][1] = array[i][0];
					array[i][0] = new Square(0);
				}
				if (array[i][1].number == array[i][0].number) {
					array[i][1] = new Square(array[i][0].number * 2);
					array[i][0] = new Square(0);

				}
			}
			if (copy != array) {
				addSquare();
				counter++;
			}
		} else {
			System.out.println("Invalid Move");
		}
	}

	public void reset() {
		rand1 = r.nextInt(4);
		rand2 = r.nextInt(4);
		rand3 = r.nextInt(4);
		rand4 = r.nextInt(4);
		while (rand1 == rand3 && rand2 == rand4) {
			rand1 = r.nextInt(4);
			rand2 = r.nextInt(4);
			rand3 = r.nextInt(4);
			rand4 = r.nextInt(4);
		}
		fillArray();
	}

	public void fillArray() {
		int w = r.nextInt(5);
		int w2 = r.nextInt(5);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == rand1 && j == rand2) {
					if (w != 4) {
						array[i][j] = new Square(2);
					} else {
						array[i][j] = new Square(4);
					}
				} else if (i == rand3 && j == rand4) {
					if (w2 != 4) {
						array[i][j] = new Square(2);
					} else {
						array[i][j] = new Square(4);
					}
				} else {
					array[i][j] = new Square(0);

				}
			}
		}
		for (int i = 0; i < 5; i++) {
			array[i][4] = new Square(0);
			array[4][i] = new Square(0);
		}
	}

	public void Win() {
		for (Square[] i : array) {
			for (Square j : i) {
				if (j.number == 2048) {
					System.exit(0);
					System.out.println("You win! You're final count was " + counter);
				} else {
					boolean pointless = true;
				}
			}
		}
	}

	public void Lose() {
		boolean loser = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (array[i][j].empty()) {
					loser = false;
				}
				if (j != 3) {
					if (array[i][j].number == array[i][j + 1].number && j != 3) {
						loser = false;
					}
				}
				if (j != 0) {
					if (array[i][j].number == array[i][j - 1].number) {
						loser = false;
					}
				}
				if (i != 3) {
					if (array[i][j].number == array[i + 1][j].number && i != 3) {
						loser = false;
					}
				}
				if (i != 0) {
					if (array[i][j].number == array[i - 1][j].number && j != 0) {
						loser = false;
					}
				}
			}
		}
		if (loser) {
			System.exit(0);
			System.out.println("You have lost. Your counter score is " + counter);
		} else {
			boolean pointless = true;
		}
	}

	@Override
	public void paint(Graphics g) {
		// fillArray();
		// ArrayList<Square> u =new ArrayList<Square>();
		// u.add(new Square(16));
		// array

		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1800, 1800);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				paintSquare(g, array[i][j], 80 * i, 80 * j);
				// paintSquare(g,array.get(i+1).get(j+1),40*i,40*j);
			}
		}
		// paintSquare(g,test,80*3,80*3);
	}

	public void paintSquare(Graphics g2, Square s, int x, int y) {
		Graphics2D g = (Graphics2D) g2;
		g.setColor(s.SquareColor());
		g.fillRect(x, y, s.size, s.size);
		if (s.number != 0) {
			g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
			g.setColor(s.textcolor);
			g.drawString(Integer.toString(s.number), x + 30, y + 50);
		}
	}

	public static void main(String[] args) {
		JFrame game = new JFrame();
		game.setTitle("Project 1: 2048");
		game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		game.setSize(325, 350);
		game.setResizable(false);

		game.add(new Game2048work());

		game.setLocationRelativeTo(null);
		game.setVisible(true);
	}
}
