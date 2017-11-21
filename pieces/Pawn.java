package pieces;
import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Direction;
import game.Move;
import game.Position;

/**
 * A pawn chess piece.
 * @author Mitchell
 *
 */
public class Pawn extends Piece {
	/**
	 * The directions that the piece can go
	 */
	private Direction[] directions = new Direction[3];

	/**
	 * Create a pawn chess piece with a position and colour. If it is white it 
	 * can go in all northern directions and if it is black then it can go in 
	 * all southern directions.
	 * @param position The position of the chess piece
	 * @param colour The alliance colour of the player
	 */
	public Pawn(Position position, String colour) {
		super(position, colour, 1);
		if (this.getColour().equals("White")) {
			this.directions[0] = Direction.NORTH_WEST;
			this.directions[1] = Direction.NORTH;
			this.directions[2] = Direction.NORTH_EAST;
		} else {
			this.directions[0] = Direction.SOUTH_EAST;
			this.directions[1] = Direction.SOUTH;
			this.directions[2] = Direction.SOUTH_WEST;
		}
	}

	/**
	 * Creates a copy of another pawn.
	 * @param other The pawn to copy.
	 */
	public Pawn(Pawn other) {
		super(other);
		this.directions = other.directions;
	}

	@Override
	public List<Move> getMoves(Board board) {
		List<Move> moves = new ArrayList<Move>();
		Position curr = this.getPosition();

		for (int i = 0; i < directions.length; i++) {
			if (i % 2 == 0) {
				if (curr.positionAt(this.directions[i], 1).isValid()) {
					Object pieceAt = board
							.getPieceAt(curr.positionAt(directions[i], 1));
					if (pieceAt instanceof Piece) {
						if (((Piece) pieceAt).getColour() != this.getColour()) {
							moves.add(new Move(board, curr, curr.positionAt(directions[i], 1)));
						}
					}
				}
			} else {
				if (curr.positionAt(this.directions[i], 1).isValid()) {
					for (int j = 1; j <= this.getMaxDistance(); j++) {
						Object pieceAt = board
								.getPieceAt(curr.positionAt(directions[i], j));
						if (pieceAt instanceof Piece) {
							// Don't allow pawn to go through other pieces
							break;
						} else {
							moves.add(new Move(board, curr, curr.positionAt(directions[i], j)));
						}
					}
				}
			}
		}
		return moves;
	}

	/**
	 * Gets the max distance that the pawn can move. If it has already moved,
	 * then it can only move one space. If it hasn't, then it can move two
	 * spaces.
	 */
	public int getMaxDistance() {
		if (this.getMoveCount() > 0) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * Get the directions that the pawn can move.
	 */
	public Direction[] getDirections() {
		return this.directions;
	}

	public String toString() {
		if (this.getColour().equals("White")) {
			return "♙";
		} else {
			return "♟";
		}
	}

	@Override
	public Piece copy() {
		return new Pawn(this);
	}
}
