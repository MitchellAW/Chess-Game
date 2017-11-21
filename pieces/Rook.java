package pieces;
import game.Direction;
import game.Position;

/**
 * A rook. Can move for any distance in north, south, east or west direction.
 * @author Mitchell
 *
 */
public class Rook extends Piece {
	
	/**
	 * The directions that the rook can move in.
	 */
	private Direction[] directions = { 
			Direction.NORTH, 
			Direction.EAST, 
			Direction.SOUTH, 
			Direction.WEST };
	
	/**
	 * The maximum distance that the rook can move.
	 */
	private int maxDistance = 7;

	/**
	 * Creates a rook in the position and with the alliance colour.
	 * @param position The position for the rook.
	 * @param colour The alliance colour of the rook.
	 */
	public Rook(Position position, String colour) {
		super(position, colour, 5);
	}

	/**
	 * Creates a copy of another rook.
	 * @param other The rook to copy.
	 */
	public Rook(Rook other) {
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
			return "♖";
		} else {
			return "♜";
		}
	}

	@Override
	public Piece copy() {
		return new Rook(this);
	}
	
	
}
