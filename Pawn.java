
public class Pawn extends Piece {
	private int[] directions = new int[3];
	private int maxDistance = 1;

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

	// Count all possible (valid) moves
	public int countMoves(Board board) {
		int moveCount = 0;
		Position curr = this.getPosition();

		for (int i = 0; i < directions.length; i++) {
			if (i % 2 == 0) {
				if (curr.positionAt(this.directions[i], 1).isValid()) {
					Object pieceAt = board
							.getPieceAt(curr.positionAt(directions[i], 1));
					if (pieceAt instanceof Piece) {
						if (((Piece) pieceAt).getColour() != this.getColour()) {
							moveCount++;
						}
					}
				}
			} else {
				if (curr.positionAt(this.directions[i], 1).isValid()) {
					for (int j = 1; j <= this.getMaxDistance(); j++) {
						Object pieceAt = board
								.getPieceAt(curr.positionAt(directions[i], j));
						if (pieceAt instanceof Piece) {
							break;
						} else {
							moveCount++;
						}
					}
				}
			}
		}
		return moveCount;
	}

	// Get all possible (valid) moves
	public Position[] getMoves(Board board) {
		Position[] moves = new Position[countMoves(board)];
		Position curr = this.getPosition();
		int index = 0;

		for (int i = 0; i < directions.length; i++) {
			if (i % 2 == 0) {
				if (curr.positionAt(this.directions[i], 1).isValid()) {
					Object pieceAt = board
							.getPieceAt(curr.positionAt(directions[i], 1));
					if (pieceAt instanceof Piece) {
						if (((Piece) pieceAt).getColour() != this.getColour()) {
							moves[index] = curr.positionAt(directions[i], 1);
							index++;
						}
					}
				}
			} else {
				if (curr.positionAt(this.directions[i], 1).isValid()) {
					for (int j = 1; j <= this.getMaxDistance(); j++) {
						Object pieceAt = board
								.getPieceAt(curr.positionAt(directions[i], j));
						if (pieceAt instanceof Piece) {
							break;
						} else {
							moves[index] = curr.positionAt(directions[i], j);
							index++;
						}
					}
				}
			}
		}

		return moves;
	}
	
	// Max distance of pawn is 2 if hasn't moved
	public int getMaxDistance() {
		if (this.getColour().equals("White")) {
			if (this.getPosition().getRow() == 2 && this.maxDistance == 1) {
				return 2;
			} else if (this.getPosition().getRow() == 8) {
				this.maxDistance = 7;
				return this.maxDistance;
			} else {
				return 1;
			}
		} else {
			if (this.getPosition().getRow() == 7 && this.maxDistance == 1) {
				return 2;
			} else if (this.getPosition().getRow() == 1) {
				this.maxDistance = 7;
				return this.maxDistance;
			} else {
				return 1;
			}
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
}
