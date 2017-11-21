package pieces;

import game.Direction;
import game.Position;

/**
 * A bishop. Can move diagonally for any distance.
 * @author Mitchell
 *
 */
public class Bishop extends Piece {
	
	/**
	 * The directions that the bishop can move.
	 */
	private Direction[] directions = { 
			Direction.NORTH_EAST,
			Direction.SOUTH_EAST, 
			Direction.SOUTH_WEST, 
			Direction.NORTH_WEST };
	
	/**
	 * The maximum distance that the bishop can move.
	 */
	private int maxDistance = 7;

	/**
	 * Creates a bishop in the position and with the alliance colour.
	 * @param position The position for the bishop.
	 * @param colour The alliance colour of the bishop.
	 */
	public Bishop(Position position, String colour) {
		super(position, colour, 3);
	}

	/**
	 * Constructs a copy of another bishop.
	 * @param other The bishop to copy.
	 */
	public Bishop(Bishop other) {
		super(other);
	}

	public Direction[] getDirections() {
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

	@Override
	public Piece copy() {
		return new Bishop(this);
	}
}
