package pieces;
import game.Direction;
import game.Position;

/**
 * A queen. Can move in any direction for any distance.
 * @author Mitchell
 *
 */
public class Queen extends Piece {
	
	/**
	 * The directions that the queen can move in.
	 */
	private Direction[] directions = { 
			Direction.NORTH,
			Direction.NORTH_EAST,
			Direction.EAST,
			Direction.SOUTH_EAST,
			Direction.SOUTH,
			Direction.SOUTH_WEST,
			Direction.WEST,
			Direction.NORTH_WEST };
	
	/**
	 * The maximum distance that the queen can move.
	 */
	private int maxDistance = 7;

	/**
	 * Creates a queen in the position and with the alliance colour.
	 * @param position The position for the queen.
	 * @param colour The alliance colour of the queen.
	 */
	public Queen(Position position, String colour) {
		super(position, colour, 10);
	}
	
	/**
	 * Creates a copy of another queen.
	 * @param other The queen to copy.
	 */
	public Queen(Queen other) {
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
