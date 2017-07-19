
public class King extends Piece {
	private int[] directions = {1, 2, 3, 4, 5, 6, 7, 8};
	private int maxDistance = 1;

	public King(Position position, String colour) {
		super(position, colour, 100);
	}
	public int[] getDirections() {
		return this.directions;
	}
	public int getMaxDistance() {
		return this.maxDistance;
	}
	public String toString() {
		if (this.getColour().equals("White")) {
			return "♔";
		} else {
			return "♚";
		}
	}
}
