package pieces;
import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Move;
import game.Position;

public class Pawn extends Piece {
	private int[] directions = new int[3];
	private boolean queen = false;

	// Unlike other pieces, pawn can only go one direction
	// Chooses correct direction based on colour
	public Pawn(Position position, String colour) {
		super(position, colour, 1);
		if (this.getColour().equals("White")) {
			this.directions[0] = 8;
			this.directions[1] = 1;
			this.directions[2] = 2;
		} else {
			this.directions[0] = 4;
			this.directions[1] = 5;
			this.directions[2] = 6;
		}
	}

	public Pawn(Pawn other) {
		super(other);
		this.directions = other.directions;
	}

	// Get all possible (valid) moves
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
	
	public boolean isQueen() {
		return this.queen;
	}
	
	public void setQueen(boolean queen) {
		this.queen = queen;
	}

	// Max distance of pawn is 2 if hasn't moved
	public int getMaxDistance() {
		if (this.getMoveCount() > 0) {
			return 1;
		} else {
			return 2;
		}
	}

	public int[] getDirections() {
		if (this.getColour().equals("White")) {
			if (this.getPosition().getRow() == 8) {
				this.directions = new int[8];
				for (int i = 0; i < 8; i++) {
					this.directions[i] = i + 1;
				}
				return this.directions;
			}
		}
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
