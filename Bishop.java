
public class Bishop extends Piece {
	private Move[] moves;
	
	public Bishop(Move[] moves, String colour) {
		super(new Position(0, 0), colour, 3);
		this.moves = moves;
	}
	public Move[] getMoves() {
		return this.moves;
	}
}
