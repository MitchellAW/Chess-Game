package game;

import pieces.Piece;

/**
 * A chess move. Contains the board the move was made in, the start and end 
 * position of the chess move and the piece that was moved, as well as any
 * piece that may have been captured during the move.
 * @author Mitchell
 *
 */
public class Move {

	/**
	 * The chess board that the move was made in.
	 */
	private Board board;
	/**
	 * The starting position of the move.
	 */
	private Position startPosition;
	/**
	 * The end position of the move.
	 */
	private Position endPosition;
	/**
	 * The chess piece that was moved.
	 */
	private Piece pieceMoved;
	/**
	 * The chess piece that was captured, null if no piece is captured.
	 */
	private Piece pieceCaptured;

	/**
	 * Creates a chess move for a chess board with a start and end position.
	 * @param board The board that the move is made in.
	 * @param startPosition The starting position of the move.
	 * @param endPosition The end position of the move.
	 */
	public Move(Board board, Position startPosition, Position endPosition) {
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.board = board;

		this.pieceMoved = board.getPieceAt(startPosition);
		this.pieceCaptured = board.getPieceAt(endPosition);
	}

	/**
	 * Creates a copy of another chess move.
	 * @param other The chess move to copy.
	 */
	public Move(Move other) {
		this.board = other.board;
		this.startPosition = new Position(other.startPosition);
		this.endPosition = new Position(other.endPosition);

		if (other.pieceMoved != null) {
			this.pieceMoved = other.pieceMoved.copy();
		}

		if (other.pieceCaptured != null) {
			this.pieceCaptured = other.pieceCaptured.copy();
		}
	}

	/**
	 * Gets the board that the move was made in.
	 * @return The board that the move was made in.
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * Gets the starting position of the move.s
	 * @return The starting position of the move.
	 */
	public Position getStartPosition() {
		return this.startPosition;
	}

	/**
	 * Gets the end position of the move.
	 * @return The end position of the move.
	 */
	public Position getEndPosition() {
		return this.endPosition;
	}

	/**
	 * Gets the piece that was moved.
	 * @return The piece that was moved.
	 */
	public Piece getPieceMoved() {
		return this.pieceMoved;
	}

	/**
	 * Gets the piece that was captured during the move.
	 * @return The piece that was captured.
	 */
	public Piece getPieceCaptured() {
		return this.pieceCaptured;
	}

	/**
	 * Checks if the chess move is valid.
	 * @return true if the chess move is valid.
	 */
	public boolean isValid() {
		return this.startPosition.isValid() && this.endPosition.isValid();
	}

	/**
	 * Gets the string representation of the chess move using algebraic 
	 * notation.
	 */
	@Override
	public String toString() {
		String movementText = "";
		if (this.pieceMoved != null) {
			movementText += this.pieceMoved.toString();
		}
		if (this.pieceCaptured != null) {
			movementText += "x";
		}
		movementText += this.endPosition.toString();

		return movementText;
	}

}
