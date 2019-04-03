import java.awt.*;

public class Square {
	int number;
	Color textcolor=Color.BLACK;
	int size=80;
	
	public Square(int n) {
		number = n;
	}

	public Color SquareColor() {
		if (number == 0) {
			return Color.WHITE;}
		if (number == 2) {
			return Color.CYAN;}
		if (number == 4) {
			return Color.BLUE;}
		if (number == 8) {
			return Color.MAGENTA;}	
		if (number == 16) {
			return Color.RED;}
		if (number == 32) {
			return Color.GREEN;}
		if (number == 64) {
			return Color.ORANGE;}
		if (number == 128) {
			return Color.PINK;}
		if (number == 256) {
			return Color.YELLOW;}
		if (number == 512) {
			return Color.LIGHT_GRAY;}
		if (number == 1024) {
			return Color.DARK_GRAY;}
		if (number == 2048) {
			return Color.BLACK;}
		return null;

	}
	public boolean empty(){
		if (number==0){
			return true;
		}
		else{
			return false;
		}
	}

}
