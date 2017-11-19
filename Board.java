import java.util.ArrayList;
import java.util.List;

public class Board {

	public static final int ROWS = 8;
	public static final int COLS = 8;
	private Object[][] board = new Object[ROWS][COLS];
	private List<Object[]> moveHistory = new ArrayList<Object[]>();

	public Board() {
		// Reset Move History
		moveHistory = new ArrayList<Object[]>();

		// Insert all pawns into the board
		for (int col = 0; col < this.board.length; col++) {
			char posChar = (char) (col + 97);
			this.board[1][col] = new Pawn(new Position(posChar, 7), "Black");
			this.board[2][col] = "";
			this.board[3][col] = "";
			this.board[4][col] = "";
			this.board[5][col] = "";
			this.board[6][col] = new Pawn(new Position(posChar, 2), "White");
		}
		// Setup higher ranked pieces for black side, then white side
		String colour = "Black";
		for (int i = 0; i < 2; i++) {
			if (i != 0) {
				colour = "White";
			}
			// Row number of piece
			int row = 7 * i;

			// Calculator Number Position for piece
			int pos = 8 - row;

			// Place all major pieces in correct positions
			this.board[row][0] = new Rook(new Position('a', pos), colour);
			this.board[row][1] = new Knight(new Position('b', pos), colour);
			this.board[row][2] = new Bishop(new Position('c', pos), colour);
			this.board[row][3] = new Queen(new Position('d', pos), colour);
			this.board[row][4] = new King(new Position('e', pos), colour);
			this.board[row][5] = new Bishop(new Position('f', pos), colour);
			this.board[row][6] = new Knight(new Position('g', pos), colour);
			this.board[row][7] = new Rook(new Position('h', pos), colour);
		}
	}

	// Undo all moves that have been made
	public void reset() {
		while (this.moveHistory.size() > 0) {
			undo();
		}
	}

	public List<Object[]> getMoveHistory() {
		return this.moveHistory;
	}

	// Will return if the move is valid
	public boolean isValid(Piece piece, Position position) {
		if (position.isValid() == false) {
			return false;
		} else if (getPieceAt(position) instanceof Piece) {
			if (((Piece) getPieceAt(position)).getColour() == piece
					.getColour()) {
				return false;
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
		String oppColour = "Black";
		if (colour.equals("Black")) {
			oppColour = "White";
		}

		// Loop through opponents moves
		List<List<Position>> oppMoves = getAllMoves(oppColour);
		for (int i = 0; i < oppMoves.get(1).size(); i++) {
			// Check if king is in path of move
			if (getPieceAt(oppMoves.get(1).get(i)) instanceof King) {
				if (((King) getPieceAt(oppMoves.get(1).get(i))).getColour()
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
		List<List<Position>> moves = getAllMoves(colour);

		if (isCheck(colour) == false) {
			return false;
		}
		for (int i = 0; i < moves.get(0).size(); i++) {
			move(moves.get(0).get(i), moves.get(1).get(i));
			if (isCheck(colour) == false) {
				undo();
				return false;
			}
			undo();
		}
		return true;
	}

	// Determine if side is currently in stalemate where the player whose turn
	// it is to move is not in check but has no legal move.
	public boolean isStalemate(String colour) {
		List<List<Position>> moves = getAllMoves(colour);

		// Player must not be in check in order to be in a stalemate
		if (isCheck(colour) == false) {
			// Check if any moves lead don't lead to check
			for (int i = 0; i < moves.get(0).size(); i++) {
				move(moves.get(0).get(i), moves.get(1).get(i));
				if (isCheck(colour) == false) {
					undo();
					return false;
				}
				undo();
			}
			return true;
		} else {
			return false;
		}

	}

	// Gathers all of the possible moves that can be made by colour
	public List<List<Position>> getAllMoves(String colour) {
		List<Position> preMoves = new ArrayList<Position>();
		List<Position> allMoves = new ArrayList<Position>();

		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (getPieceAt(i, j) instanceof Piece) {
					if (((Piece) getPieceAt(i, j)).getColour() == colour) {
						List<Position> moves = ((Piece) getPieceAt(i, j))
								.getMoves(this);
						for (int k = 0; k < moves.size(); k++) {
							allMoves.add(moves.get(k));
							preMoves.add(new Position(i, j));
						}
					}
				}
			}
		}
		List<List<Position>> movements = new ArrayList<List<Position>>();
		movements.add(preMoves);
		movements.add(allMoves);

		return movements;
	}

	// Counts all of the possible moves that can be made by colour
	public List<List<Position>> getAllLegalMoves(String colour) {
		List<List<Position>> allMoves = getAllMoves(colour);
		List<Position> preLegalMoves = new ArrayList<Position>();
		List<Position> allLegalMoves = new ArrayList<Position>();

		for (int i = 0; i < allMoves.get(0).size(); i++) {
			move(allMoves.get(0).get(i), allMoves.get(1).get(i));
			if (isCheck(colour) == false) {
				preLegalMoves.add(allMoves.get(0).get(i));
				allLegalMoves.add(allMoves.get(1).get(i));
			}
			undo();
		}
		List<List<Position>> movements = new ArrayList<List<Position>>();

		movements.add(preLegalMoves);
		movements.add(allLegalMoves);
		return movements;
	}

	// Undoes the changes made by the last move, using the moveHistory list
	public void undo() {
		if (this.moveHistory.size() > 0) {

			// Get the last move performed
			Object[] lastMove = this.moveHistory.get(moveHistory.size() - 1);

			// Get the pieces at the start and end positions of the last move
			Object pieceBeforeMove = lastMove[0];
			Object pieceAfterMove = lastMove[1];

			// Get the positions the piece moved to and from
			Position from = (Position) lastMove[2];
			Position to = (Position) lastMove[3];

			// Undo the move
			newPiece(from, pieceBeforeMove);
			newPiece(to, pieceAfterMove);

			// Remove the last move from the history
			this.moveHistory.remove(moveHistory.size() - 1);
		}
	}

	public int[] countPieces(String colour) {

		// Number Of Pawns, Rooks, Knights, Bishops, Queens and Kings
		int[] pieceCounts = { 0, 0, 0, 0, 0, 0 };

		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (getColourAt(new Position(i, j)).equals(colour)) {
					if (this.board[i][j] instanceof Pawn) {
						pieceCounts[0]++;
					} else if (this.board[i][j] instanceof Rook) {
						pieceCounts[1]++;
					} else if (this.board[i][j] instanceof Knight) {
						pieceCounts[2]++;
					} else if (this.board[i][j] instanceof Bishop) {
						pieceCounts[3]++;
					} else if (this.board[i][j] instanceof Queen) {
						pieceCounts[4]++;
					} else if (this.board[i][j] instanceof King) {
						pieceCounts[5]++;
					}
				}
			}
		}
		return pieceCounts;
	}

	// Makes the move and stores its history in moves
	public void move(Position from, Position to) {
		// Get the pieces at the to and from positions
		Object pieceBeforeMove = getPieceAt(from);
		Object pieceAfterMove = getPieceAt(to);
		newPiece(from, "");
		newPiece(to, pieceBeforeMove);

		Object[] piecesMoved = { pieceBeforeMove, pieceAfterMove, from, to };
		this.moveHistory.add(piecesMoved);
	}

	// Board as a string
	public String toString() {
		String boardString = "";
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				boardString += "|" + board[i][j] + "";
			}
			if (i != this.board.length - 1) {
				boardString += "|\n";
			} else {
				boardString += "|";
			}
		}
		return boardString;
	}
}
