package game;

public class Position {
	private char column;
	private int row;

	public char getColumn() {
		return this.column;
	}

	public int getRow() {
		return this.row;
	}

	Position(char column, int row) {
		this.column = column;
		this.row = row;
	}

	Position(int row, int column) {
		this.column = (char) (column + 97);
		this.row = 8 - row;
	}

	// Takes integer from 1-8 to choose direction (1=North, 5=South)
	public Position moveDirection(int direction, int distance) {
		switch (direction) {
		// Move North
		case 1:
			this.row += distance;
			break;
		// Move North-East
		case 2:
			this.column += (char) distance;
			this.row += distance;
			break;
		// Move East
		case 3:
			this.column += (char) distance;
			break;
		// Move South-East
		case 4:
			this.column += (char) distance;
			this.row -= distance;
			break;
		// Move South
		case 5:
			this.row -= distance;
			break;
		// Move South-West
		case 6:
			this.column -= (char) distance;
			this.row -= distance;
			break;
		// Move West
		case 7:
			this.column -= (char) distance;
			break;
		// Move North-West
		case 8:
			this.column -= (char) distance;
			this.row += distance;
			break;
		}
		return this;
	}

	public Position positionAt(int direction, int distance) {
		Position copy = this.copy();
		copy.moveDirection(direction, distance);
		return copy;
	}

	public int[] getIndexes() {
		int[] coords = new int[2];
		coords[0] = 8 - this.row;
		coords[1] = (int) this.column - 97;
		return coords;
	}

	public boolean isValid() {
		if ((int) this.column < 97 || this.row < 1 || (int) this.column > 104
				|| this.row > 8) {
			return false;
		} else {
			return true;
		}
	}

	public Position copy() {
		return new Position(this.getColumn(), this.getRow());
	}

	public boolean equals(Position position) {
		if (position.getRow() != this.row
				|| position.getColumn() != this.column) {
			return false;
		} else {
			return true;
		}
	}

	public String toString() {
		return "" + column + row;
	}
}
