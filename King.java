
public class King extends Piece {
	private Move[] moves;
	
	public King(Move[] moves, String colour) {
		super(new Position(0, 0), colour, 100);
		this.moves = moves;
	}
	public Move[] getMoves() {
		return this.moves;
	}
}
