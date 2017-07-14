
public class Knight extends Piece {
	private Move[] moves;
	
	public Knight(Move[] moves, String colour) {
		super(new Position(0, 0), colour, 3);
		this.moves = moves;
	}
	public Move[] getMoves() {
		return this.moves;
	}
}
