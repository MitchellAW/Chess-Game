
public class Rook extends Piece {
	private Move[] moves;
	
	public Rook(Move[] moves, String colour) {
		super(new Position(0, 0), colour, 5);
		this.moves = moves;
	}
	public Move[] getMoves() {
		return this.moves;
	}
}
