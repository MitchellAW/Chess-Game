
public class Board {
	public static final int ROWS = 8;
	public static final int COLUMNS = 8;

	public Object[][] board = new Object[ROWS][COLUMNS];

	public Board() {
	}

	public void reset() {
		for (int i=0; i<8; i++) {
			this.board[1][i] = new Pawn(new Position((char)(i + 97), 1), "Black");
			this.board[2][i] = " ";
			this.board[3][i] = " ";
			this.board[4][i] = " ";
			this.board[5][i] = " ";
			this.board[6][i] = new Pawn(new Position((char)(i + 97), 1), "White");
		}
		String currentColour = "Black";;
		for (int i=0; i<2; i++) {
			if (i != 0) {
				currentColour = "White";
			}
			this.board[7*i][0] = new Rook(new Position('a', 8 - (8*i)), currentColour);
			this.board[7*i][1] = new Knight(new Position('b', 8 - (8*i)), currentColour);
			this.board[7*i][2] = new Bishop(new Position('c', 8 - (8*i)), currentColour);
			this.board[7*i][3] = new Queen(new Position('d', 8 - (8*i)), currentColour);
			this.board[7*i][4] = new King(new Position('e', 8 - (8*i)), currentColour);
			this.board[7*i][5] = new Bishop(new Position('f', 8 - (8*i)), currentColour);
			this.board[7*i][6] = new Knight(new Position('g', 8 - (8*i)), currentColour);
			this.board[7*i][7] = new Rook(new Position('h', 8 - (8*i)), currentColour);
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
	public String toString() {
		String boardString = "";
		for (int i=0; i<ROWS; i++) {
			for (int j=0; j<COLUMNS; j++) {
				boardString += board[i][j];
			}
			if (i != ROWS - 1) {
				boardString += "\n";
			}
		}
		return boardString;
	}
}
