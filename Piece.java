
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
	public abstract int[][] getMoves();

	public void move(Board board, int direction, int distance) {
		if (this.position.positionAt(direction, distance).isValid()) {
			this.position = this.position.moveDirection(direction, distance);
		}
	}
}
