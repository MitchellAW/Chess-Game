
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
	public String toString() {
		return "" + column + row;
	}
	public void moveDirection(int direction, int distance) {
		switch (direction) {
		// Move North
		case 1:
			this.row += distance;
			break;
			// Move North-East
		case 2:
			this.column += (char)distance;
			this.row += distance;
			break;
			// Move East
		case 3:
			this.column += (char)distance;
			break;
			// Move South-East
		case 4:
			this.column += (char)distance;
			this.row -= distance;
			break;
			// Move South
		case 5:
			this.row -= distance;
			break;
			// Move South-West
		case 6:
			this.column -= (char)distance;
			this.row -= distance;
			break;
			// Move West
		case 7:
			this.column -= (char)distance;
			break;
			// Move North-West
		case 8:
			this.column -= (char)distance;
			this.row += distance;
			break;
		}
	}
	public int[] toIndexes() {
		int[] coords = new int[2];
		coords[0] = (int)column - 96;
		coords[1] = row - 1;
		return coords;
	}
	public String algebraicNotation() {
		return Character.toString((char)(column + 97)) + (8 - row);
	}
}
