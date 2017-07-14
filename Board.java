
public class Board {	
	Piece[][] board = new Piece[8][8];
	int[][] move = new int[1][1];

	public Board() {
	}
	public void reset() {
		for (int i=0; i<8; i++) {
			this.board[1][i] = new Piece("Black", move);
			this.board[6][i] = new Piece("White", move);
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
