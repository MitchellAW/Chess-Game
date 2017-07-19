
public class Rook extends Piece {
	private int[] directions = {1, 3, 5, 7};
	private int maxDistance = 7;

	public Rook(Position position, String colour) {
		super(position, colour, 5);
	}
	public int[] getDirections() {
		return this.directions;
	}
	public int getMaxDistance() {
		return this.maxDistance;
	}
	public String toString() {
		if (this.getColour().equals("White")) {
			return "♖";
		} else {
			return "♜";
		}
	}
}
