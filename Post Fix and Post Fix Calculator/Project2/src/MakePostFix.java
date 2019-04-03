
public class MakePostFix {
	private MyStack theStack;
	private String input;
	private String output = "";
 
	public MakePostFix(String in) {
		input = in;
		int stackSize = input.length();
		theStack = new MyStack(stackSize);
	}

	public String rearrange() {
		for (int j = 0; j < input.length(); j++) {
			char character = input.charAt(j);
			switch (character) {
			case ' ':
				//do nothing
			case '+':
			case '-':
				operator(character, 1);
				break;
			case '*':
			case '/':
				operator(character, 2);
				break;
			case '(':
				theStack.push(character);
				break;
			case ')':
				parentheses(character);
				break;
			default:
				output = output + character+" ";
				break;
			}
		}
		while (!theStack.isEmpty()) {
			output = output +theStack.pop();
		}
		;
		return output;
	}

	public void operator(char currentOperation, int precedence) {
		while (!theStack.isEmpty()) {
			char topOperation = theStack.pop();
			if (topOperation == '(') {
				theStack.push(topOperation);
				break;
			} else {
				int priority; // Multiplication and Division Have a Priority higher than Add/Subtract
				if (topOperation == '+' || topOperation == '-')
					priority = 1;
				else
					priority = 2;
				if (priority < precedence) {
					theStack.push(topOperation);
					break;
				} else
					output = output + topOperation+ " ";
			}
		}
		theStack.push(currentOperation);
	}

	public void parentheses(char character) {//Takes care of Parentheses
		while (!theStack.isEmpty()) {
			char charac = theStack.pop();
			if (charac == '(')
				break;
			else
				output = output + charac+ " ";
		}
	}
}
