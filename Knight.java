
public class Knight extends Piece {
	private int[] directions = { 8, 1, 2, 3, 4, 5, 6, 7, 8 };
	private int maxDistance = 5;

	public Knight(Position position, String colour) {
		super(position, colour, 3);
	}

	public Position[] getMoves(Board board) {
		Position[] moves = new Position[countMoves(board)];
		Position curr = this.getPosition();
		int index = 0;

		for (int i = 1; i < directions.length; i += 2) {
			for (int j = -1; j < 2; j += 2) {
				// Move north, east, south or west
				Position newPos = curr.positionAt(directions[i], 1);
				if (newPos.positionAt(directions[i + j], 1).isValid()) {
					Position newPos2 = newPos.positionAt(directions[i + j], 1);
					Object pieceAt = board.getPieceAt(newPos2);
					if (pieceAt instanceof Piece) {
						if (((Piece) pieceAt).getColour() != this.getColour()) {
							moves[index] = newPos2;
							index++;
						}
					} else {
						moves[index] = newPos2;
						index++;
					}
				}
			}
		}
		return moves;
	}

	public int countMoves(Board board) {
		int moveCount = 0;
		Position curr = this.getPosition();

		for (int i = 1; i < directions.length; i += 2) {
			for (int j = -1; j < 2; j += 2) {
				// Move north, east, south or west
				Position newPos = curr.positionAt(directions[i], 1);
				if (newPos.positionAt(directions[i + j], 1).isValid()) {
					Position newPos2 = newPos.positionAt(directions[i + j], 1);
					Object pieceAt = board.getPieceAt(newPos2);
					if (pieceAt instanceof Piece) {
						if (((Piece) pieceAt).getColour() != this.getColour()) {
							moveCount++;
						}
					} else {
						moveCount++;
					}
				}
			}
		}
		return moveCount;
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
}
