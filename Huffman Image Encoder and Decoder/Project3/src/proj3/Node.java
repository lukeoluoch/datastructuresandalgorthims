package proj3;

public class Node {
	public Node left;
	public Node right;
	public char character;
	public int frequency;

	public Node(Node node) {

		this.character = node.character;
		this.left = node.left;
		this.right = node.right;
		this.frequency = node.frequency;
	}

	public Node(Node left, Node right, char character, int freq) {

		this.right = right;
		this.left = left;
		this.character = character;
		this.frequency = frequency;
	}

	

	public boolean isLeaf() {
		assert ((left == null) && (right == null)) || ((left != null) && (right != null));
		return (left == null) && (right == null);
	}
	public int compare(Node node){
		return this.frequency-node.frequency;
	}
}
