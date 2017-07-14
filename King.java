
public class King extends Piece {
	private int[][] moves;

	public King(Position position, String colour) {
		super(position, colour, 100);
	}
	public int[][] getMoves() {
		return this.moves;
	}
	public String toString() {
		if (this.getColour().equals("White")) {
			return "♔";
		} else {
			return "♚";
		}
	}
}
