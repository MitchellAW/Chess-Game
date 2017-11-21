package game;

/**
 * A position on the chess board. Represented with algebraic notation.
 * @author Mitchell
 *
 */
public class Position {
	/**
	 * The character representation of the positions column (a > h).
	 */
	private char column;
	/**
	 * The row number of the position (1 > 8).
	 */
	private int row;

	/**
	 * Creates a chess piece position using algebraic notation.
	 * @param column The character representation of the positions column 
	 * (a > h).
	 * @param row The row number of the position (1 > 8).
	 */
	public Position(char column, int row) {
		this.column = column;
		this.row = row;
	}

	/**
	 * Creates a chess piece position using a row and column number, converts
	 * the column number to the character representation of the column
	 * @param row The row number of the position (1 > 8).
	 * @param column The column number of the position (1 > 8).
	 */
	public Position(int row, int column) {
		this.column = (char) (column + 97);
		this.row = 8 - row;
	}

	/**
	 * Creates a copy of another chess position.
	 * @param other The chess position to copy.
	 */
	public Position(Position other) {
		this.column = other.column;
		this.row = other.row;
	}

	/**
	 * Gets the column of the chess position.
	 * @return The column of the chess position (a > h).
	 */
	public char getColumn() {
		return this.column;
	}

	/**
	 * Gets the row of the chess position.
	 * @return The row of the chess position (1 > 8).
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Takes a direction (1-8) and distance and adjusts the position if it 
	 * moved in that direction. 1 = North, 3 = East, 5 = South, 7 = West.
	 * @param direction The direction to move.
	 * @param distance The distance/how far to move.
	 * @return The position after it has moved in that direction.
	 */
	public Position moveDirection(Direction direction, int distance) {
		switch (direction) {
		case NORTH:
			this.row += distance;
			break;
		case NORTH_EAST:
			this.column += (char) distance;
			this.row += distance;
			break;
		case EAST:
			this.column += (char) distance;
			break;
		case SOUTH_EAST:
			this.column += (char) distance;
			this.row -= distance;
			break;
		case SOUTH:
			this.row -= distance;
			break;
		case SOUTH_WEST:
			this.column -= (char) distance;
			this.row -= distance;
			break;
		case WEST:
			this.column -= (char) distance;
			break;
		case NORTH_WEST:
			this.column -= (char) distance;
			this.row += distance;
			break;
		}
		return this;
	}

	/**
	 * Gets the position at the distance and direction.
	 * @param direction The direction to move.
	 * @param distance The distance/how far to move.
	 * @return The position in a particular direction and distance from
	 * this position.
	 */
	public Position positionAt(Direction direction, int distance) {
		Position copy = this.copy();
		copy.moveDirection(direction, distance);
		return copy;
	}

	/**
	 * The indexes (row/col) of this position. Used for positioning chess 
	 * pieces in the board array.
	 * @return The indexes of this position.
	 */
	public int[] getIndexes() {
		int[] coords = new int[2];
		coords[0] = 8 - this.row;
		coords[1] = (int) this.column - 97;
		return coords;
	}

	/**
	 * Checks if this position is a valid chess position.
	 * @return true if the position is valid.
	 */
	public boolean isValid() {
		if ((int) this.column < 97 || this.row < 1 || (int) this.column > 104
				|| this.row > 8) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gets a copy of this position.
	 * @return The copy of this position.
	 */
	public Position copy() {
		return new Position(this);
	}

	/**
	 * Gets a string representation of the chess position using algebraic
	 * notation.
	 */
	@Override
	public String toString() {
		return "" + column + row;
	}
}
