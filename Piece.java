
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
	public String getColour() {
		return this.colour;
	}
	public int getValue() {
		return this.value;
	}
	public abstract Move[] getMoves();
}
