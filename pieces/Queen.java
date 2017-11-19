package pieces;
import game.Position;

public class Queen extends Piece {
	private int[] directions = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private int maxDistance = 7;

	public Queen(Position position, String colour) {
		super(position, colour, 10);
	}
	
	public Queen(Queen other) {
		super(other);
	}

	public int[] getDirections() {
		return this.directions;
	}

	public int getMaxDistance() {
		return this.maxDistance;
	}

	public String toString() {
		if (this.getColour().equals("White")) {
			return "♕";
		} else {
			return "♛";
		}
	}

	@Override
	public Piece copy() {
		return new Queen(this);
	}
	
	
}
