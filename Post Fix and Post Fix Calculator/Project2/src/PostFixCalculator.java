import java.util.*;

public class PostFixCalculator {

	public static HashMap<String, Double> variables = new HashMap<String, Double>();

	public static String calculate(String s) {
		List<String> list = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();

		boolean equalsign = false;
		boolean varX = false;
		boolean takenVariable = false;
		String variableKey = "";

		if (s.isEmpty() != true) {
			StringTokenizer tokenizer = new StringTokenizer(s);

			while (tokenizer.hasMoreTokens()) {
				String entry = tokenizer.nextToken();
				list.add(entry);
			}
		} else {
			return "Error";
		}
		if (s.contains("=")) {
			equalsign = true;
		}
		Iterator<String> tokens = list.iterator();

		while (tokens.hasNext()) {
			String iteration = tokens.next();
			System.out.println(iteration);

			if (iteration.matches("\\d+")) {
				stack.push(iteration);
			} else if (iteration.equals("+") || iteration.equals("-") || iteration.equals("*") || iteration.equals("/")
					|| iteration.equals("=")) {

				String operator = iteration;

				if (operator.equals("+")) {
					double num2 = Double.parseDouble(stack.pop());

					double num1 = Double.parseDouble(stack.pop());
					System.out.println(num1);
					double solution = num1 + num2;
					stack.push(String.valueOf(solution));
				} else if (operator.equals("-")) {
					double num2 = Double.parseDouble(stack.pop());
					double num1 = Double.parseDouble(stack.pop());
					double solution = num1 - num2;
					stack.push(String.valueOf(solution));
				} else if (operator.equals("*")) {
					double num2 = Double.parseDouble(stack.pop());
					double num1 = Double.parseDouble(stack.pop());
					double solution = num1 * num2;
					stack.push(String.valueOf(solution));
				} else if (operator.equals("/")) {
					double num2 = Double.parseDouble(stack.pop());
					double num1 = Double.parseDouble(stack.pop());
					double solution = num1 / num2;
					stack.push(String.valueOf(solution));
				}

			} else if (iteration.matches("[a-zA-z]")) {
				if (varX == false) {
					variableKey = iteration;
					System.out.println("key!");
				}
				if (variables.get(iteration) == null && equalsign == false) { // if
																				// the
																				// variable
																				// is
																				// empty,
																				// but
																				// there's
																				// no
																				// equal
																				// sign
					System.out.println(iteration + " is not registered as  a variable");
					return "";
				}

				else if (variables.containsKey(iteration)) {
					stack.push(String.valueOf(variables.get(variableKey)));
					System.out.println("pushed!");
				} else if (iteration.equals("=")) {
					System.out.println("equals!");
					varX = true;
				}
				// else {
				// System.out.println(iteration);
				// return "Error 2";
				// }

			} else {
				return "Error 3";
			}
		}
		String ans = stack.pop();
		if (equalsign == true) {
			variables.put(variableKey, Double.parseDouble(ans));
		}
		return ans;
	}

	public static void main(String arg[]) {
		System.out.println("Enter your calculations");
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			
			String input = scan.nextLine();
			String var = new String();
			String A = "clear all";
			String B = "clear" + var;
			String C = "exit";

			if (input.equals(A)) {
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			} else if (input.equals(B + var)) {
				for (Map.Entry<String, Double> a : variables.entrySet()) {
					if (var.equals(a)) {
						a.setValue(null);
					}
				}
			} else if (input.equals(C)) {
				System.exit(1);
			} else {
				String output = new MakePostFix(input).rearrange();
				System.out.println(calculate(output));
			}
		}
	}
}
