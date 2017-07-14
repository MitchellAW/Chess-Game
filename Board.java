
public class Board {
	private static final int ROWS = 8;
	private static final int COLUMNS = 8;
	
	Piece[][] board = new Piece[ROWS][COLUMNS];

	public Board() {
	}
	public void reset() {
		for (int i=0; i<8; i++) {
			this.board[1][i] = new Rook(new Move[2], "White");
			this.board[6][i] = new Rook(new Move[2], "Black");
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
