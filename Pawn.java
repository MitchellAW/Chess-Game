
public class Pawn extends Piece {
	private Move[] moves;
	
	public Pawn(Move[] moves, String colour) {
		super(new Position('a',1), colour, 1);
		this.moves = moves;
	}
	public Move[] getMoves() {
		return this.moves;
	}
}
