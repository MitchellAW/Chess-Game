
public class Pawn extends Piece {
	private Move[] moves;
	
	public Pawn(Move[] moves, String colour) {
		super(new Position(0, 0), colour, 1);
		this.moves = moves;
	}
	public Move[] getMoves() {
		return this.moves;
	}
}
