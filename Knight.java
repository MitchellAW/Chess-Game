
public class Knight extends Piece {
	private int[] directions = {1, 2, 3, 4, 5, 6, 7, 8};
	private int maxDistance = 1;

	public Knight(Position position, String colour) {
		super(position, colour, 3);
	}
	public Position[] getMoves() {
		return new Position[0];
	}
	public int countMoves() {
		return 0;
	}
	public int[] getDirections() {
		return this.directions;
	}
	public int getMaxDistance() {
		return this.maxDistance;
	}
	public String toString() {
		if (this.getColour().equals("White")) {
			return "♘";
		} else {
			return "♞";
		}
	}
}
