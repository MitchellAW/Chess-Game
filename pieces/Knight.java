package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Direction;
import game.Move;
import game.Position;

/**
 * A knight. can move in an L shape.
 * @author Mitchell
 *
 */
public class Knight extends Piece {
	
	/**
	 * The directions that the knight can move.
	 */
	private Direction[] directions = { 
			Direction.NORTH_WEST,
			Direction.NORTH,
			Direction.NORTH_EAST,
			Direction.EAST,
			Direction.SOUTH_EAST,
			Direction.SOUTH,
			Direction.SOUTH_WEST,
			Direction.WEST,
			Direction.NORTH_WEST };
	
	/**
	 * The max distance that the knight can move.
	 */
	private int maxDistance = 5;

	/**
	 * Creates a knight in the position and with the alliance colour.
	 * @param position The position for the knight.
	 * @param colour The alliance colour of the knight.
	 */
	public Knight(Position position, String colour) {
		super(position, colour, 3);
	}

	/**
	 * Creates a copy of another knight.
	 * @param other The knight to copy.
	 */
	public Knight(Knight other) {
		super(other);
	}

	// Get all possible (valid) moves
	public List<Move> getMoves(Board board) {
		List<Move> moves = new ArrayList<Move>();
		Position startPos = this.getPosition();

		for (int i = 1; i < directions.length; i += 2) {
			for (int j = -1; j < 2; j += 2) {
				// Perform first half of knight movement
				Position halfPos = startPos.positionAt(directions[i], 1);
				if (halfPos.positionAt(directions[i + j], 1).isValid()) {
					// Perform second half of knight movement
					Position endPos = halfPos.positionAt(directions[i + j], 1);
					Piece pieceTaken = board.getPieceAt(endPos);
					if (pieceTaken != null
							&& pieceTaken.getColour() != this.getColour()) {
						moves.add(new Move(board, startPos, endPos));
					} else if (pieceTaken == null) {
						moves.add(new Move(board, startPos, endPos));
					}
				}
			}
		}
		return moves;
	}

	public Direction[] getDirections() {
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

	@Override
	public Piece copy() {
		return new Knight(this);
	}
}
