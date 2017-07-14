
public class Queen extends Piece {
	private int[][] moves;

	public Queen(Position position, String colour) {
		super(position, colour, 10);
	}
	public int[][] getMoves() {
		return this.moves;
	}
	public String toString() {
		if (this.getColour().equals("White")) {
			return "♕";
		} else {
			return "♛";
		}
	}
}
