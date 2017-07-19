
public class Bishop extends Piece {
	private int[] directions = { 2, 4, 6, 8 };
	private int maxDistance = 7;

	public Bishop(Position position, String colour) {
		super(position, colour, 3);
	}

	public int[] getDirections() {
		return this.directions;
	}

	public int getMaxDistance() {
		return this.maxDistance;
	}

	public String toString() {
		if (this.getColour().equals("White")) {
			return "♗";
		} else {
			return "♝";
		}
	}
}
