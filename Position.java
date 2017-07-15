
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
	// Takes integer from 1-8 to choose direction (1=North, 5=South)
	public Position moveDirection(int direction, int distance) {
		switch (direction) {
		// Move North
		case 1:
			this.row += Math.min(8-row, distance);
			break;
			// Move North-East
		case 2:
			this.column += (char)Math.min(8-((int)column - 96), distance);
			this.row += Math.min(8-row, distance);
			break;
			// Move East
		case 3:
			this.column += (char)Math.min(8-((int)column - 96), distance);
			break;
			// Move South-East
		case 4:
			this.column += (char)Math.min(8-((int)column - 96), distance);
			this.row -= Math.min(row-1, distance);
			break;
			// Move South
		case 5:
			this.row -= Math.min(row-1, distance);
			break;
			// Move South-West
		case 6:
			this.column -= (char)Math.min((int)column-97, distance);
			this.row -= Math.min(row-1, distance);
			break;
			// Move West
		case 7:
			this.column -= (char)Math.min((int)column-97, distance);
			break;
			// Move North-West
		case 8:
			this.column -= (char)Math.min((int)column-97, distance);
			this.row += Math.min(8-row, distance);
			break;
		}
		return this;
	}
	public Position positionAt(int direction, int distance) {
		return this.copy().moveDirection(direction, distance);
	}
	public int[] getIndexes() {
		int[] coords = new int[2];
		coords[0] = (int)column - 96;
		coords[1] = row - 1;
		return coords;
	}
	public boolean isValid() {
		int[] points = this.getIndexes();
		if (points[0] < 0 || points[0] > 7 ||
				points[1] < 0 || points[1] > 7) {
			return false;
		} else {
			return true;
		}
	}
	public Position copy() {
		return new Position(this.getColumn(), this.getRow());
	}
	public String toString() {
		return "" + column + row;
	}
}
