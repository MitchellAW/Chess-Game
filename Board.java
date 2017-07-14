
public class Board {	
	Piece[][] board = new Piece[8][8];
	Move[] move = new Move[1];

	public Board() {
	}
	public void reset() {
		for (int i=0; i<8; i++) {
			this.board[1][i] = new Piece(Piece.COLOUR.BLACK, Piece.RANK.PAWN, move);
			this.board[6][i] = new Piece(Piece.COLOUR.WHITE, Piece.RANK.PAWN, move);
		}	
	}
	public boolean spotAvailable() {
		return true;
	}
	public boolean isCheckmate() {
		return true;
	}
	public int currentScore(String colour) {
		return 0;
	}
}
