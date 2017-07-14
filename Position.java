
public class Position {
	private int row;
	private int column;
	
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	
	Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	public String toString() {
		return "(" + column + "," + row + ")";
	}
}
