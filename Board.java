
public class Board {

	private Object[][] board = new Object[8][8];

	public Board() {
	}
	public Object[][] getBoard() {
		return this.board;
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
	public boolean spotAvailable(Position position) {
		int[] points = position.getIndexes();
		
		if (position.isValid() &&
				this.board[points[1]][points[0]].equals(" ")) {
			return true;	
		}
		return false;
	}
	public boolean isCheckmate() {
		return true;
	}
	public int currentScore(String colour) {
		return 0;
	}
	public String toString() {
		String boardString = "";
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				boardString += "|" + board[i][j] + "";
			}
			if (i != 8 - 1) {
				boardString += "|\n";
			} else {
				boardString += "|";
			}
		}
		return boardString;
	}
}
