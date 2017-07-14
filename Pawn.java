
public class Pawn extends Piece {
	private int[][] moves = {{1, 1}};

	public Pawn(Position position, String colour) {
		super(position, colour, 1);
	}
	public int[][] getMoves() {
		return this.moves;
	}
	public String toString() {
		if (this.getColour().equals("White")) {
			return "♙";
		} else {
			return "♟";
		}
	}
}
