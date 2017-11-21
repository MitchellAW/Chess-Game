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

/**
 * The chess board, keeps track of all the pieces, their positions, the
 * move history of the match and the current player.
 * @author Mitchell
 *
 */
public class Board {
	
	/**
	 * The chess board. An array of chess pieces, each with their own 
	 * positions.
	 */
	private Piece[][] board = new Piece[8][8];
	/**
	 * A list of all the chess moves that have been made throughout the match.
	 */
	private List<Move> moveHistory = new ArrayList<Move>();
	/**
	 * The alliance colour of the current player whose turn it is. White goes
	 * first.
	 */
	private String currentPlayer = "White";

	/**
	 * Creates a chess board and fills it with all the default chess pieces 
	 * in their correct positions.
	 */
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

	/**
	 * Creates a copy of another chess board.
	 * @param other The chess board to copy.
	 */
	public Board(Board other) {
		this.currentPlayer = other.currentPlayer;
		for (int i = 0; i < other.moveHistory.size(); i++) {
			this.moveHistory.add(new Move(other.moveHistory.get(i)));
		}
		for (int row = 0; row < other.board.length; row++) {
			for (int col = 0; col < other.board[row].length; col++) {
				if (other.board[row][col] != null) {
					this.board[row][col] = other.board[row][col].copy();
				}
			}
		}
	}

	/**
	 * Gets the chess piece that is in any valid position within the chess 
	 * board.
	 * @param position The position of the piece.
	 * @return The chess piece at the position.
	 */
	public Piece getPieceAt(Position position) {
		if (!position.isValid()) {
			return null;
		} else {
			int[] points = position.getIndexes();
			return this.board[points[0]][points[1]];
		}
	}

	/**
	 * Gets the chess piece at any valid row and column within the chess board.
	 * @param row The row of the chess board.
	 * @param column The column of the chess board.
	 * @return The piece at the row and column.
	 */
	public Piece getPieceAt(int row, int column) {
		if (!new Position(row, column).isValid()) {
			return null;
		} else {
			return this.board[row][column];
		}
	}

	/**
	 * Places a chess piece at any valid position on the chess board.
	 * @param position The position to place the chess piece in.
	 * @param piece The piece to place on the chess board.
	 */
	public void newPiece(Position position, Piece piece) {
		int[] points = position.getIndexes();
		this.board[points[0]][points[1]] = piece;
		if (piece != null) {
			piece.setPosition(position);
		}
	}

	/**
	 * Will perform the chess move, moving the chess piece that is moved from
	 * the starting position of the move to the end position of the move.
	 * @param move The chess move.
	 */
	public void move(Move move) {
		if (move != null) {
			if (move.getPieceMoved() != null) {
				move.getPieceMoved().incrementMoveCount();
			}
			// Make the move
			newPiece(move.getStartPosition(), null);
			newPiece(move.getEndPosition(), move.getPieceMoved());

			// Add the move to the history and switch current player
			this.moveHistory.add(move);
			switchPlayer();
		}
	}
	
	/**
	 * Will move any chess piece from starting position to the end position.
	 * @param start The starting position of the chess move.
	 * @param end The end position of the chess move.
	 */
	public void move(Position start, Position end) {
		move(new Move(this, start, end));
	}

	/**
	 * Undoes the last move performed throughout the chess game
	 */
	public void undo() {
		if (this.moveHistory.size() > 0) {
			// Get the last move performed
			Move lastMove = this.moveHistory.get(moveHistory.size() - 1);

			// Undo the move
			newPiece(lastMove.getStartPosition(), lastMove.getPieceMoved());
			newPiece(lastMove.getEndPosition(), lastMove.getPieceCaptured());

			// Decrement moveCount of piece moved back
			if (lastMove.getPieceMoved() != null) {
				lastMove.getPieceMoved().decrementMoveCount();
			}
			
			// Remove the last move from the history and switch current player
			this.moveHistory.remove(moveHistory.size() - 1);
			switchPlayer();
		}
	}

	/**
	 * Gets a list of all the possible moves that can be performed by a
	 * particular alliance colour.
	 * @param colour The alliance colour of the player (White/Black).
	 * @return The list of all the possible moves that can be performed.
	 */
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

	/**
	 * Gets a list of all the possible, and legal moves that can be performed
	 * by a particular alliance colour. If a move leads to check, then it is 
	 * not legal.
	 * @param colour The alliance colour of the player (White/Black).
	 * @return The list of all the possible legal moves that can be performed.
	 */
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

	/**
	 * Checks if the chess board is currently in a stalemate.
	 * @return true if the chess board is currently in a stalemate.
	 */
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

	/**
	 * Checks if any pieces owned by a particular alliance colour are
	 * currently in check.
	 * @param colour The alliance colour of the of the player (White/Black).
	 * @return true if the player is currently in check.
	 */
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

	/**
	 * Checks if a particular alliance colour is in checkmate. Loops through
	 * all the possible moves of the player, performs the move and then checks
	 * if the player is still in check. If any of the moves get the player out
	 * of check, then the player is not in checkmate.
	 * @param colour The alliance colour of the player (White/Black).
	 * @return true if the player is in checkmate.
	 */
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

	/**
	 * Checks if the chess board is in a game over state. If the chess board
	 * is in checkmate or stalemate, then the game is over.
	 * @return true if the game is over.
	 */
	public boolean isGameOver() {
		return isCheckmate("Black") || isCheckmate("White") || isStalemate();
	}

	/**
	 * Replaces any pawns at the edge of the board with a queen. 
	 * Loops through the pieces and checks the positions of the pawns, if any
	 * pawn has reached their edge, then they are automatically replaced with
	 * a queen.
	 */
	public void updatePieces() {
		for (int row = 0; row < this.board.length; row++) {
			for (int col = 0; col < this.board[row].length; col++) {
				Piece currentPiece = this.board[row][col];
				if (currentPiece instanceof Pawn) {
					if (currentPiece.isWhite()) {
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

	/**
	 * Counts the number of each different chess piece of a particular alliance
	 * colour in the chess board. The number of different pieces are returned
	 * in this format: {Pawns, Rooks, Knights, Bishops, Queens, Kings}
	 * @param colour The alliance colour of the player (White/Black).
	 * @return An array of integers, representing the number of Pawns,
	 * Rooks, Knights, Bishops, Queens and Kings (in that order).
	 */
	public int[] countPieces(String colour) {
		// Number Of Pawns, Rooks, Knights, Bishops, Queens and Kings
		int[] pieceCounts = { 0, 0, 0, 0, 0, 0 };

		for (int row = 0; row < this.board.length; row++) {
			for (int col = 0; col < this.board[row].length; col++) {
				if (this.board[row][col] != null
						&& this.board[row][col].getColour().equals(colour)) {
					if (this.board[row][col] instanceof Pawn) {
						pieceCounts[0]++;
					} else if (this.board[row][col] instanceof Rook) {
						pieceCounts[1]++;
					} else if (this.board[row][col] instanceof Knight) {
						pieceCounts[2]++;
					} else if (this.board[row][col] instanceof Bishop) {
						pieceCounts[3]++;
					} else if (this.board[row][col] instanceof Queen) {
						pieceCounts[4]++;
					} else if (this.board[row][col] instanceof King) {
						pieceCounts[5]++;
					}
				}
			}
		}
		return pieceCounts;
	}

	/**
	 * Returns the current value/score of the chess board in its current state.
	 * If black is in a favourable position, the score is higher, if white is
	 * in a favourable position, the score is lower. Loops through and uses
	 * the different values of each piece. Needs to be changed for a more 
	 * accurate evaluation of the board state.
	 * @return The score evaluation of the board state.
	 */
	public int score() {
		int score = 0;
		if (isCheckmate("Black")) {
			return Integer.MIN_VALUE;
		} else if (isCheckmate("White")) {
			return Integer.MAX_VALUE;
		}
		for (int row = 0; row < this.board.length; row++) {
			for (int col = 0; col < this.board[row].length; col++) {
				if (this.board[row][col] != null) {
					if (!this.board[row][col].isWhite()) {
						score += this.board[row][col].getValue();
					} else {
						score -= this.board[row][col].getValue();
					}
				}
			}
		}
		return score;
	}

	/**
	 * Undoes all moves that have been performed throughout the match.
	 */
	public void reset() {
		while (this.moveHistory.size() > 0) {
			undo();
		}
	}

	/**
	 * Completely clears/empties the board of all chess pieces.
	 */
	public void clear() {
		this.board = new Piece[8][8];
	}
	
	/**
	 * Checks if the chess piece in the position is white.
	 * @param piecePosition The position of the piece.
	 * @return true if the chess piece is white.
	 */
	public boolean isWhite(Position piecePosition) {
		Piece pieceCheck = getPieceAt(piecePosition);
		if (pieceCheck == null) {
			return false;
		}
		return pieceCheck.isWhite();
	}

	/**
	 * Gets the move history of the match.
	 * @return The move history of the match.
	 */
	public List<Move> getMoveHistory() {
		return this.moveHistory;
	}

	/**
	 * Gets the alliance colour of the player that is currently performing 
	 * their turn.
	 * @return The alliance colour of the player.
	 */
	public String getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * Switches the player whose turn it is.
	 */
	public void switchPlayer() {
		if (this.currentPlayer == "White") {
			this.currentPlayer = "Black";
		} else {
			this.currentPlayer = "White";
		}
	}

	/**
	 * Gets a copy of the current chess board.
	 * @return The copy of the board.
	 */
	public Board copy() {
		return new Board(this);
	}

	/**
	 * Gets a string representation of the chess board
	 */
	@Override
	public String toString() {
		String boardString = "";
		for (int row = 0; row < this.board.length; row++) {
			for (int col = 0; col < this.board[row].length; col++) {

				boardString += "|";

				if (this.board[row][col] != null) {
					boardString += this.board[row][col].toString();
				} else {
					boardString += " ";
				}
			}
			if (row != this.board.length - 1) {
				boardString += "|\n";
			} else {
				boardString += "|";
			}
		}
		return boardString;
	}
}
