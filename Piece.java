
public class Piece {
	
	private String colour;
	private String type;
	private int[][] moves;

	public Piece(String colour, String type, int[][] moves) {
		this.colour = colour;
		this.type = type;
		this.moves = moves;
	}
	public String getColour() {
		return this.colour;
	}
	public String getType() {
		return this.type;
	}
	public int[][] getMoves() {
		return this.moves;
	}
}
