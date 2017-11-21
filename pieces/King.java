package pieces;
import game.Direction;
import game.Position;

/**
 * A king. Can move in any direction.
 * @author Mitchell
 *
 */
public class King extends Piece {
	
	/**
	 * The directions that the king can move.
	 */
	private Direction[] directions = { Direction.NORTH,
			Direction.NORTH_EAST,
			Direction.EAST,
			Direction.SOUTH_EAST,
			Direction.SOUTH,
			Direction.SOUTH_WEST,
			Direction.WEST,
			Direction.NORTH_WEST };
	
	/**
	 * The maximum distance that the king can move.
	 */
	private int maxDistance = 1;

	/**
	 * Creates a kingin the position and with the alliance colour.
	 * @param position The position for the king.
	 * @param colour The alliance colour of the king.
	 */
	public King(Position position, String colour) {
		super(position, colour, 100);
	}
	
	/**
	 * Creates a copy of another king.
	 * @param other The king to copy.
	 */
	public King(King other) {
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
			return "♔";
		} else {
			return "♚";
		}
	}

	@Override
	public Piece copy() {
		return new King(this);
	}
}
