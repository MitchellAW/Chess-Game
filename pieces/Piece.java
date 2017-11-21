package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Direction;
import game.Move;
import game.Position;

/**
 * A chess piece with an alliance colour, chess position and the value of the 
 * piece.
 * @author Mitchell
 *
 */
public abstract class Piece {

	/**
	 * The alliance colour of the chess piece.
	 */
	private String colour;
	/**
	 * The board position of the chess piece.
	 */
	private Position position;
	/**
	 * The number of moves that the piece has made.
	 */
	private int moveCount = 0;

	/**
	 * The value of the chess piece for score evaluation of the board.
	 */
	private int value;

	/**
	 * Creates a chess piece.
	 * @param position The position of the chess piece.
	 * @param colour The alliance colour of the chess piece.
	 * @param value The value of the chess piece.
	 */
	public Piece(Position position, String colour, int value) {
		this.position = position;
		this.colour = colour;
		this.value = value;
	}

	/**
	 * Creates a copy of another chess piece.
	 * @param other The chess piece to copy.
	 */
	public Piece(Piece other) {
		this.position = new Position(other.position);
		this.colour = other.colour;
		this.value = other.value;
		this.moveCount = other.moveCount;
	}

	/**
	 * Gets the position of the piece.
	 * @return The board position of the chess piece.
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Sets the position of the piece..
	 * @param position The board position of the chess piece.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	/**
	 * Gets the alliance colour of the piece.
	 * @return The alliance colour of the chess piece.
	 */
	public String getColour() {
		return this.colour;
	}

	/**
	 * Gets the value of the chess piece.
	 * @return The value of the chess piece.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Gets the move count.
	 * @return The number of moves that this piece has made.
	 */
	public int getMoveCount() {
		return this.moveCount;
	}

	/**
	 * Increases the move count by 1
	 */
	public void incrementMoveCount() {
		this.moveCount++;
	}
	
	/**
	 * Decreases the move count by 1
	 */
	public void decrementMoveCount() {
		this.moveCount--;
	}

	/**
	 * Gets a list of the moves that the piece can make.
	 * @param board The board to check possible moves in.
	 * @return The list of moves that the piece can make.
	 */
	public List<Move> getMoves(Board board) {
		// List of possible moves of piece
		List<Move> moves = new ArrayList<Move>();
		
		// Get the starting position and directions of the piece
		Position startPos = this.getPosition();
		Direction[] directions = this.getDirections();

		// Loop through all the directions + distances
		for (int i = 0; i < directions.length; i++) {
			for (int j = 1; j <= this.getMaxDistance(); j++) {
				// Check if the movement position is valid
				Position endPos = startPos.positionAt(directions[i], j);
				if (endPos.isValid()) {
					
					Piece pieceAt = board.getPieceAt(endPos);
					if (pieceAt != null) {
						if (pieceAt.getColour() != this.getColour()) {
							moves.add(new Move(board, startPos, endPos));
							break;
						} else {
							break;
						}
					} else {
						moves.add(new Move(board, startPos, endPos));
					}
				}
			}
		}
		return moves;
	}
	
	/**
	 * Checks if the piece is white or black.
	 * @return true if the piece is white
	 */
	public boolean isWhite() {
		return this.colour == "White";
	}

	/**
	 * Gets the max distance of the piece.
	 * @return The max distance of the chess piece.
	 */
	public abstract int getMaxDistance();

	/**
	 * Gets the directions that the piece can move in.
	 * @return The directions that the chess piece can move in.
	 */
	public abstract Direction[] getDirections();

	/**
	 * Gets a copy of this piece.
	 * @return A copy of this chess piece.
	 */
	public abstract Piece copy();
}
