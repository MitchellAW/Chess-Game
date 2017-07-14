
public class Knight extends Piece {
	private int[][] moves;

	public Knight(Position position, String colour) {
		super(position, colour, 3);
	}
	public int[][] getMoves() {
		return this.moves;
	}
	public String toString() {
		if (this.getColour().equals("White")) {
			return "♘";
		} else {
			return "♞";
		}
	}
}
