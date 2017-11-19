package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Move;
import game.Position;

public class Knight extends Piece {
	private int[] directions = { 8, 1, 2, 3, 4, 5, 6, 7, 8 };
	private int maxDistance = 5;

	public Knight(Position position, String colour) {
		super(position, colour, 3);
	}

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

	public int[] getDirections() {
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
