
public abstract class Piece {

	private String colour;
	private Position position;

	private int value;

	public Piece(Position position, String colour, int value) {
		this.position = position;
		this.colour = colour;
		this.value = value;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getColour() {
		return this.colour;
	}

	public int getValue() {
		return this.value;
	}

	public Position[] getMoves() {
		Position[] moves = new Position[countMoves()];
		Position curr = this.getPosition();
		int index = 0;
		for (int i = 0; i < this.getDirections().length; i++) {
			for (int j = 1; j <= this.getMaxDistance(); j++) {
				if (curr.positionAt(this.getDirections()[i], j).isValid()) {
					moves[index] = curr.positionAt(this.getDirections()[i], j);
					index++;
				}
			}
		}
		return moves;
	}

	public int countMoves() {
		int moveCount = 0;
		Position curr = this.getPosition();
		for (int i = 0; i < this.getDirections().length; i++) {
			for (int j = 1; j <= this.getMaxDistance(); j++) {
				if (curr.positionAt(this.getDirections()[i], j).isValid()) {
					moveCount++;
				}
			}
		}
		return moveCount;
	}

	public abstract int getMaxDistance();

	public abstract int[] getDirections();

	public void move(Board board, int direction, int distance) {
		if (this.position.positionAt(direction, distance).isValid()) {
			this.position = this.position.moveDirection(direction, distance);
		}
	}
}
