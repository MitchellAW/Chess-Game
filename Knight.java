
public class Knight extends Piece {
	private int[] directions = { 8, 1, 2, 3, 4, 5, 6, 7, 8 };
	private int maxDistance = 5;

	public Knight(Position position, String colour) {
		super(position, colour, 3);
	}

	public Position[] getMoves() {
		Position[] moves = new Position[countMoves()];
		Position curr = this.getPosition();
		int index = 0;

		for (int i = 1; i < directions.length; i += 2) {
			// Move north, east, south or west
			Position newPos = curr.positionAt(directions[i], 1);

			// Then check positions west and east of the bearing
			// e.g. If at north, check north west and north east
			if (newPos.positionAt(directions[i - 1], 1).isValid()) {
				moves[index] = newPos.positionAt(directions[i - 1], 1);
				index++;
			}
			if (newPos.positionAt(directions[i + 1], 1).isValid()) {
				moves[index] = newPos.positionAt(directions[i + 1], 1);
				index++;
			}
		}
		return moves;
	}

	public int countMoves() {
		int moveCount = 0;
		Position curr = this.getPosition();

		for (int i = 1; i < directions.length; i += 2) {
			// Move north, east, south or west
			Position newPos = curr.positionAt(directions[i], 1);

			// Then check positions west and east of the bearing
			// e.g. If at north, check north west and north east
			if (newPos.positionAt(directions[i - 1], 1).isValid()) {
				moveCount++;
			}
			if (newPos.positionAt(directions[i + 1], 1).isValid()) {
				moveCount++;
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
