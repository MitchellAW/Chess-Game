
public class Piece {
	public enum COLOUR {BLACK, WHITE};
	public enum RANK {KING, QUEEN, KNIGHT, BISHOP, ROOK, PAWN};
	
	private COLOUR colour;
	private RANK rank;
	private Move[] moves;

	public Piece(COLOUR colour, RANK rank, Move[] moves) {
		this.colour = colour;
		this.rank = rank;
		this.moves = moves;
	}
	public COLOUR getColour() {
		return this.colour;
	}
	public RANK getType() {
		return this.rank;
	}
	public Move[] getMoves() {
		return this.moves;
	}
}
