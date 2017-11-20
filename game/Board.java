package game;

import java.util.ArrayList;
import java.util.List;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board {

	public static final int ROWS = 8;
	public static final int COLS = 8;
	private Piece[][] board = new Piece[ROWS][COLS];
	private List<Move> moveHistory = new ArrayList<Move>();
	private String currentPlayer = "Black";

	public Board() {
		// Reset Move History
		moveHistory = new ArrayList<Move>();

		// Insert all pawns into the board
		for (int col = 0; col < this.board.length; col++) {
			char posChar = (char) (col + 97);
			this.board[1][col] = new Pawn(new Position(posChar, 7), "Black");
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
	
	public void clear() {
		this.board = new Piece[ROWS][COLS];
	}

	public List<Move> getMoveHistory() {
		return this.moveHistory;
	}

	// Will return if the move is valid
	public boolean isValid(Piece piece, Position position) {
		if (position.isValid() == false) {
			return false;
		} else if (getPieceAt(position) != null) {
			if (getPieceAt(position).getColour() == piece.getColour()) {
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
	public Piece getPieceAt(Position position) {
		if (!position.isValid()) {
			return null;
		} else {
			int[] points = position.getIndexes();
			return this.board[points[0]][points[1]];
		}
	}

	// Will return the object at position using row/column
	public Piece getPieceAt(int row, int column) {
		if (!new Position(row, column).isValid()) {
			return null;
		} else {
			return this.board[row][column];
		}
	}

	// Will place a new piece at position onto the board
	public void newPiece(Position position, Piece piece) {
		int[] points = position.getIndexes();
		this.board[points[0]][points[1]] = piece;
		if (piece != null) {
			piece.setPosition(position);
		}
	}

	public void updatePieces() {
		for (int row = 0; row < this.board.length; row++) {
			for (int col = 0; col < this.board[row].length; col++) {
				Piece currentPiece = this.board[row][col];
				if (currentPiece instanceof Pawn) {
					if (currentPiece.getColour().equals("White")) {
						if (currentPiece.getPosition().getRow() >= 8) {
							this.board[row][col] = new Queen(
									currentPiece.getPosition(), "White");
						}
					} else {
						if (currentPiece.getPosition().getRow() <= 1) {
							this.board[row][col] = new Queen(
									currentPiece.getPosition(), "Black");
						}
					}
				}
			}
		}
	}

	// Will return true if colour is currently in check
	public boolean isCheck(String colour) {
		String oppColour = "Black";
		if (colour.equals("Black")) {
			oppColour = "White";
		}

		// Loop through opponents moves
		List<Move> oppMoves = getAllMoves(oppColour);
		for (int i = 0; i < oppMoves.size(); i++) {
			// Check if king is in path of move
			if (getPieceAt(oppMoves.get(i).getEndPosition()) instanceof King) {
				if (((King) getPieceAt(oppMoves.get(i).getEndPosition()))
						.getColour().equals(colour)) {
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
		List<Move> moves = getAllMoves(colour);
		
		if (isCheck(colour) == false) {
			return false;
		}
		for (int i = 0; i < moves.size(); i++) {
			move(moves.get(i));
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
	public boolean isStalemate() {
		List<Move> whiteMoves = getAllLegalMoves("White");
		List<Move> blackMoves = getAllLegalMoves("Black");

		// Player must not be in check in order to be in a stalemate
		if (!isCheck("Black") && blackMoves.size() <= 0) {
			return true;
		} else if (!isCheck("White") && whiteMoves.size() <= 0) {
			return true;
		}
		return false;

	}

	// Determine if the game is over or not
	public boolean isGameOver() {
		return isCheckmate("Black") || isStalemate() || isCheckmate("White");
	}

	// Gathers all of the possible moves that can be made by colour
	public List<Move> getAllMoves(String colour) {
		List<Move> allMoves = new ArrayList<Move>();

		for (int row = 0; row < this.board.length; row++) {
			for (int col = 0; col < this.board[row].length; col++) {
				Piece currPiece = getPieceAt(row, col);
				if (currPiece != null && currPiece.getColour() == colour) {
					// Get all moves of current piece
					List<Move> currentMoves = currPiece.getMoves(this);
					for (int i = 0; i < currentMoves.size(); i++) {
						// Add all the pieces moves
						if (currentMoves.get(i).isValid()) {
							allMoves.add(currentMoves.get(i));
						}
					}
				}
			}
		}
		return allMoves;
	}

	// Counts all of the possible moves that can be made by colour
	public List<Move> getAllLegalMoves(String colour) {
		List<Move> allMoves = getAllMoves(colour);
		List<Move> allLegalMoves = new ArrayList<Move>();

		for (int i = 0; i < allMoves.size(); i++) {
			Move currentMove = allMoves.get(i);
			move(currentMove);
			if (!isCheck(colour)) {
				allLegalMoves.add(currentMove);
			}
			undo();
		}
		return allLegalMoves;
	}

	// Undoes the changes made by the last move, using the moveHistory list
	public void undo() {
		if (this.moveHistory.size() > 0) {

			// Get the last move performed
			Move lastMove = this.moveHistory.get(moveHistory.size() - 1);

			// Get the pieces moved and taken
			Piece pieceMoved = lastMove.getPieceMoved();
			Piece pieceTaken = lastMove.getPieceTaken();

			// Get the positions the piece moved to and from
			Position startPosition = lastMove.getStartPosition();
			Position endPosition = lastMove.getEndPosition();

			// Decrement moveCount
			if (pieceMoved != null) {
				pieceMoved.decrementMoveCount();
			}

			// Undo the move
			newPiece(startPosition, pieceMoved);
			newPiece(endPosition, pieceTaken);

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
	public void move(Position start, Position end) {
		move(new Move(this, start, end));
	}

	public void move(Move move) {
		if (move != null) {
			if (move.getPieceMoved() != null) {
				move.getPieceMoved().incrementMoveCount();
			}
			newPiece(move.getStartPosition(), null);
			newPiece(move.getEndPosition(), move.getPieceMoved());

			this.moveHistory.add(move);

			if (this.currentPlayer == "White") {
				this.currentPlayer = "Black";
			} else {
				this.currentPlayer = "White";
			}
		}
	}

	public String getCurrentPlayer() {
		return this.currentPlayer;
	}

	// Returns the score of the board
	public int score() {
		return 0;
	}

	// Board as a string
	public String toString() {
		String boardString = "";
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				
				boardString += "|";
				
				if (this.board[i][j] != null) {
					boardString += this.board[i][j].toString();
				} else {
					boardString += " ";
				}
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
