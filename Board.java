
public class Board {

	private Object[][] board = new Object[8][8];
	Object[][] moves = new Object[1][4];

	public Board() {
		reset();
	}

	// Resets the board with pieces in starting positions
	public void reset() {
		// Setup Pawns
		for (int i = 0; i < 8; i++) {
			this.board[1][i] = new Pawn(new Position((char) (i + 97), 7),
					"Black");
			this.board[2][i] = "";
			this.board[3][i] = "";
			this.board[4][i] = "";
			this.board[5][i] = "";
			this.board[6][i] = new Pawn(new Position((char) (i + 97), 2),
					"White");
		}
		// Setup higher ranked pieces for black side, then white side
		String currentColour = "Black";
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

	// Will return if the move is valid
	public boolean isValid(Piece piece, Position position) {
		if (position.isValid() == false) {
			return false;
		} else {
			if (getPieceAt(position) instanceof Piece) {
				if (((Piece) getPieceAt(position)).getColour() == piece
						.getColour()) {
					return false;
				}
			}
		}
		return true;
	}

	// Will return the colour of the piece at position
	public String getColourAt(Position position) {
		if (getPieceAt(position) instanceof Piece) {
			return ((Piece) getPieceAt(position)).getColour();
		} else {
			return "";
		}
	}

	// Will return object at position using position
	public Object getPieceAt(Position position) {
		if (position.isValid() == false) {
			return "n/a";
		} else {
			int[] points = position.getIndexes();
			return this.board[points[0]][points[1]];
		}
	}

	// Will return the object at position using row/column
	public Object getPieceAt(int row, int column) {
		return this.board[row][column];
	}

	// Will place a new piece at position onto the board
	public void newPiece(Position position, Object piece) {
		int[] points = position.getIndexes();
		this.board[points[0]][points[1]] = piece;
		if (piece instanceof Piece) {
			((Piece) piece).setPosition(position);
		}
	}

	// Will return true if colour is currently in check
	public boolean isCheck(String colour) {
		String oppColour;
		if (colour.equals("Black")) {
			oppColour = "White";
		} else {
			oppColour = "Black";
		}

		Position[][] oppMoves = getAllMoves(oppColour);

		for (int i = 0; i < oppMoves[1].length; i++) {
			if (getPieceAt(oppMoves[1][i]) instanceof King) {
				if (((King) getPieceAt(oppMoves[1][i])).getColour()
						.equals(colour)) {
					return true;
				}
			}
		}
		return false;
	}

	// Will return true if the colour is currently in checkmate.
	// Checks every move colour can make, if any of them can get them out of
	// check, then they are not in checkmate
	public boolean isCheckmate(String colour) {
		Position[][] moves = getAllMoves(colour);

		if (isCheck(colour) == false) {
			return false;
		}
		for (int i = 0; i < moves[0].length; i++) {
			move(moves[0][i], moves[1][i]);
			if (isCheck(colour) == false) {
				undo();
				return false;
			}
			undo();
		}
		return true;
	}

	// Counts all of the possible moves that can be made by colour
	public int countAllMoves(String colour) {
		int moveCount = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getPieceAt(i, j) instanceof Piece) {
					if (((Piece) getPieceAt(i, j)).getColour() == colour) {
						moveCount += ((Piece) getPieceAt(i, j))
								.countMoves(this);
					}
				}
			}
		}
		return moveCount;
	}

	// Gathers all of the possible moves that can be made by colour
	public Position[][] getAllMoves(String colour) {
		Position[] preMoves = new Position[countAllMoves(colour)];
		Position[] allMoves = new Position[countAllMoves(colour)];

		int index = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getPieceAt(i, j) instanceof Piece) {
					if (((Piece) getPieceAt(i, j)).getColour() == colour) {

						Position[] moves = ((Piece) getPieceAt(i, j))
								.getMoves(this);
						for (int k = 0; k < moves.length; k++) {
							allMoves[index] = moves[k];
							preMoves[index] = new Position(i, j);
							index++;
						}
					}
				}
			}
		}
		Position[][] movements = new Position[2][countAllMoves(colour)];
		movements[0] = preMoves;
		movements[1] = allMoves;
		return movements;
	}

	// Undoes the changes made by the last move, using the moves array
	public void undo() {
		if (this.moves.length > 0) {
			// Undo the last move
			Object piecePreMove = this.moves[moves.length - 1][0];
			Object pieceAtMove = this.moves[moves.length - 1][1];

			Position from = (Position) this.moves[moves.length - 1][2];
			Position to = (Position) this.moves[moves.length - 1][3];

			newPiece(from, piecePreMove);
			newPiece(to, pieceAtMove);
			
			// Remove the last move from moves array
			Object[][] newMoves = new Object[moves.length - 1][4];
			
			for (int i = 0; i < this.moves[0].length; i++) {
				newMoves[0][i] = moves[0][i];
				newMoves[1][i] = moves[1][i];
			}
			
			this.moves = newMoves;
		}
	}

	// Adds a move to the moves array
	public void addMove(Object[] piecesMoved, Object[] positions) {
		Object[][] newMoves = new Object[moves.length + 1][4];
		if (this.moves.length > 1) {
			for (int i = 0; i < this.moves[0].length; i++) {
				newMoves[0][i] = moves[0][i];
				newMoves[1][i] = moves[1][i];
			}
		}
		newMoves[moves.length][0] = piecesMoved[0];
		newMoves[moves.length][1] = piecesMoved[1];
		newMoves[moves.length][2] = positions[0];
		newMoves[moves.length][3] = positions[1];

		this.moves = newMoves;
	}

	// Makes the move and stores its history in moves
	public void move(Position from, Position to) {
		Object piecePreMove = getPieceAt(from);
		Object pieceAtMove = getPieceAt(to);
		if (piecePreMove instanceof Piece) {
			newPiece(from, "");
			newPiece(to, piecePreMove);

			Object[] piecesMoved = { piecePreMove, pieceAtMove };
			Position[] positions = { from, to };
			addMove(piecesMoved, positions);

		}
	}

	// Board as a string
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
