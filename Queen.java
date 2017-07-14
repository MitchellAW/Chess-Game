
public class Queen extends Piece {
	private Move[] moves;
	
	public Queen(Move[] moves, String colour) {
		super(new Position(0, 0), colour, 10);
		this.moves = moves;
	}
	public Move[] getMoves() {
		return this.moves;
	}
}
