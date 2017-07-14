
public class Piece {	

	private String colour;
	private Position position;

	private int value;

	private Move[] moves;
	private int[] directions;

	public Piece(Position position, String colour, int[] directions, int value) {
		this.position = position;
		this.colour = colour;
		this.directions = directions;
		this.value = value;
	}
	public int getNumOfDirections() {
		return this.directions.length;
	}
	public int[] getDirections() {
		return this.directions;
	}
	public Move[] getMoves() {
		return this.moves;
	}
	public Position getPosition() {
		return this.position;
	}
	public String getColour() {
		return this.colour;
	}
}
