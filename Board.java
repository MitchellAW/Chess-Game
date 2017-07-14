
public class Board {
	private static final int ROWS = 8;
	private static final int COLUMNS = 8;
	
	Piece[][] board = new Piece[COLUMNS][COLUMNS];
	Move[] move = new Move[1];

	public Board() {
	}
	public void reset() {
		for (int i=0; i<8; i++) {
			this.board[1][i] = new Rook();
			this.board[6][i] = new Rook();
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
