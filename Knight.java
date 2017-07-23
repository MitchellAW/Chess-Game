import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
	private int[] directions = { 8, 1, 2, 3, 4, 5, 6, 7, 8 };
	private int maxDistance = 5;

	public Knight(Position position, String colour) {
		super(position, colour, 3);
	}

	// Get all possible (valid) moves
	public List<Position> getMoves(Board board) {
		List<Position> moves = new ArrayList<Position>();
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
							moves.add(newPos2);
						}
					} else {
						moves.add(newPos2);
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
}
