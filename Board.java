
public class Board {

	private Object[][] board = new Object[8][8];

	public Board() {
		reset();
	}

	public Object[][] getBoard() {
		return this.board;
	}
	
	public void setBoard(Object[][] board) {
		this.board = board;
	}

	public void reset() {
		for (int i = 0; i < 8; i++) {
			this.board[1][i] = new Pawn(new Position((char) (i + 97), 7),
					"Black");
			this.board[2][i] = " ";
			this.board[3][i] = " ";
			this.board[4][i] = " ";
			this.board[5][i] = " ";
			this.board[6][i] = new Pawn(new Position((char) (i + 97), 2),
					"White");
		}
		String currentColour = "Black";
		;
		for (int i = 0; i < 2; i++) {
			if (i != 0) {
				currentColour = "White";
			}
			this.board[7 * i][0] = new Rook(new Position('a', 8 - (7 * i)),
					currentColour);
			this.board[7 * i][1] = new Knight(new Position('b', 8 - (7 * i)),
					currentColour);
			this.board[7 * i][2] = new Bishop(new Position('c', 8 - (7 * i)),
					currentColour);
			this.board[7 * i][3] = new Queen(new Position('d', 8 - (7 * i)),
					currentColour);
			this.board[7 * i][4] = new King(new Position('e', 8 - (7 * i)),
					currentColour);
			this.board[7 * i][5] = new Bishop(new Position('f', 8 - (7 * i)),
					currentColour);
			this.board[7 * i][6] = new Knight(new Position('g', 8 - (7 * i)),
					currentColour);
			this.board[7 * i][7] = new Rook(new Position('h', 8 - (7 * i)),
					currentColour);
		}
	}

	public boolean spotAvailable(Position position) {
		int[] points = position.getIndexes();

		if (position.isValid()
				&& this.board[points[1]][points[0]].equals(" ")) {
			return true;
		}
		return false;
	}

	public boolean isCheckmate() {
		return false;
	}

	public int currentScore(String colour) {
		return 0;
	}

	public Object getPieceAt(Position position) {
		if (position.isValid() == false) {
			return "n/a";
		} else {
			int[] points = position.getIndexes();
			return this.board[points[0]][points[1]];
		}
	}
	
	public void showMoves(Position position) {
		Board boardCopy = this.copy();
		boardCopy.setBoard(this.board);
		
		Position[] moves = ((Piece)getPieceAt(position)).getMoves();
		for (int i = 0; i < moves.length; i++) {
			boardCopy.newPiece(moves[i], "X");
		}
		System.out.println(boardCopy);
	}
	
	public void newPiece(Position position, Object piece) {
		int[] points = position.getIndexes();
		this.board[points[0]][points[1]] = piece;
	}
	
	public Board copy() {
		Board boardCopy = new Board();
		boardCopy.setBoard(this.board);
		return boardCopy;
	}

	public String toString() {
		String boardString = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
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
