package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Move;
import game.Position;

public abstract class Piece {

	private String colour;
	private Position position;
	private int moveCount = 0;

	private int value;

	public Piece(Position position, String colour, int value) {
		this.position = position;
		this.colour = colour;
		this.value = value;
	}

	public Piece(Piece other) {
		this.position = other.position;
		this.colour = other.colour;
		this.value = other.value;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getColour() {
		return this.colour;
	}

	public String getOppositeColour() {
		if (this.colour.equals("White")) {
			return "Black";
		} else {
			return "White";
		}
	}

	public int getValue() {
		return this.value;
	}

	public int getMoveCount() {
		return this.moveCount;
	}

	public void incrementMoveCount() {
		this.moveCount++;
	}
	
	public void decrementMoveCount() {
		this.moveCount--;
	}

	public boolean canMove(Board board) {
		return (getMoves(board).size() > 0);
	}

	// Gets all the moves that piece can make given the current board
	public List<Move> getMoves(Board board) {
		// List of possible moves of piece
		List<Move> moves = new ArrayList<Move>();
		
		// Get the starting position and directions of the piece
		Position startPos = this.getPosition();
		int[] directions = this.getDirections();

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

	public abstract int getMaxDistance();

	public abstract int[] getDirections();

	public abstract Piece copy();
}
