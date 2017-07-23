import java.util.ArrayList;
import java.util.List;

public class Board {

	private Object[][] board = new Object[8][8];
	private List<Object[]> moveHistory = new ArrayList<Object[]>();

	public Board() {
		reset();
	}

	// Resets the board with pieces in starting positions
	public void reset() {
		// Reset Move History
		moveHistory = new ArrayList<Object[]>();

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

	public List<Object[]> getMoveHistory() {
		return this.moveHistory;
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

		List<List<Position>> oppMoves = getAllMoves(oppColour);

		for (int i = 0; i < oppMoves.get(1).size(); i++) {
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

	// Gathers all of the possible moves that can be made by colour
	public List<List<Position>> getAllMoves(String colour) {
		List<Position> preMoves = new ArrayList<Position>();
		List<Position> allMoves = new ArrayList<Position>();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
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

	// Undoes the changes made by the last move, using the moves array
	public void undo() {
		if (this.moveHistory.size() > 0) {
			// Undo the last move
			Object piecePreMove = this.moveHistory
					.get(moveHistory.size() - 1)[0];
			Object pieceAtMove = this.moveHistory
					.get(moveHistory.size() - 1)[1];

			Position from = (Position) this.moveHistory
					.get(moveHistory.size() - 1)[2];
			Position to = (Position) this.moveHistory
					.get(moveHistory.size() - 1)[3];

			newPiece(from, piecePreMove);
			newPiece(to, pieceAtMove);

			this.moveHistory.remove(moveHistory.size() - 1);
		}
	}

	public int[] countPieces(String colour) {

		// # Of Pawns, Rooks, Knights, Bishops, Queens and Kings
		int[] pieceCounts = { 0, 0, 0, 0, 0, 0 };

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
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
		Object piecePreMove = getPieceAt(from);
		Object pieceAtMove = getPieceAt(to);
		newPiece(from, "");
		newPiece(to, piecePreMove);

		Object[] piecesMoved = { piecePreMove, pieceAtMove, from, to };
		this.moveHistory.add(piecesMoved);
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
