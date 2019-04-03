import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class practice {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Random R = new Random();
		int round = 1;
		int score = 0;

		String go_on = "";
		double y;
		double x;
		double v;
		double theta;

		System.out.print("Welcome to game! If you want to begin, enter 'Go'!");
		String entry = scan.next();
		if (entry.equals("Go")) {
			System.out.println("Are you sure, pal? If not, enter 'Quit' otherwise, enter anything to begin");
			go_on = scan.next();
			while (!go_on.equals("Quit")) {
				x = R.nextInt(100);
				y = R.nextInt(100);
				System.out.println("Round:" + round);
				System.out.println("Score:" + score);
				int j = 0;
				while (j != 1) {

					System.out.println("The wall is currently " + x + " meters away and " + y + " meters tall.");
					System.out.println("Enter Velocity in m/s");
					v = scan.nextDouble();
					System.out.println("Enter Angle of orginal shot in degrees");
					theta = scan.nextDouble();
					double equation = x * Math.tan(Math.toRadians(theta)) - ((9.81 * x * x)
							/ (2 * (v * Math.cos(Math.toRadians(theta)) * (v * Math.cos(Math.toRadians(theta))))));

					if (equation - y <= 3 && equation > y) {
						System.out.println("Your height was:" + equation);
						System.out.println("Close Clear!");
						round += 1;
						score += 5;
						j += 1;
						System.out.println("If you want to quit, enter 'Quit', otherwise enter anything else.");
						go_on = scan.next();
						
					}

					else if (equation - y > 3 && equation > y) {
						System.out.println("Your height was:" + equation);
						System.out.println("Far Clear!");
						round += 1;
						score += 3;
						j += 1;
						System.out.println("If you want to quit, enter 'Quit', otherwise enter anything else.");
						go_on = scan.next();
						
					}

					else if (y - equation < 3 && y > equation) {
						System.out.println("Your height was:" + equation);
						System.out.println("Not quite over...");
						score -= 2;
						System.out.println("If you want to quit, enter 'Quit', otherwise enter anything else.");
						go_on = scan.next();
						
					}

					else if (y - equation > 3 && y > equation) {
						System.out.println("Your height was:" + equation);
						System.out.println("Oof! Sorry, but you were way off!");
						score -= 4;
						System.out.println("If you want to quit, enter 'Quit', otherwise, enter anything");
						go_on = scan.next();
						
					}
					System.out.println("You have chosen to '"+go_on+ "'! Thanks for playing.");
					if (go_on.equals("Quit")) {
						j = 1;
					}

				}
			}

		}

	}

}
