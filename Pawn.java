
public class Pawn extends Piece {
	private int[] directions = {1};
	private int maxDistance = 1;

	public Pawn(Position position, String colour) {
		super(position, colour, 1);
	}
	public int[] getDirections() {
		return this.directions;
	}
	public int getMaxDistance() {
		return this.maxDistance;
	}
	public String toString() {
		if (this.getColour().equals("White")) {
			return "♙";
		} else {
			return "♟";
		}
	}
}
