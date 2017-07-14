
public class Rook extends Piece {
	private int[][] moves;

	public Rook(Position position, String colour) {
		super(position, colour, 5);
	}
	public int[][] getMoves() {
		return this.moves;
	}
	public String toString() {
		if (this.getColour().equals("White")) {
			return "♖";
		} else {
			return "♜";
		}
	}
}
