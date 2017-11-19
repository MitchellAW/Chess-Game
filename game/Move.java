package game;

import pieces.Piece;

public class Move {

	private Board board;
	private Position startPosition;
	private Position endPosition;
	private Piece pieceMoved;
	private Piece pieceTaken;

	public Move(Board board, Position startPosition, Position endPosition) {
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.board = board;
		
		this.pieceMoved = board.getPieceAt(startPosition);
		this.pieceTaken = board.getPieceAt(endPosition);
	}

	public Board getBoard() {
		return this.board;
	}

	public Position getStartPosition() {
		return this.startPosition;
	}

	public Position getEndPosition() {
		return this.endPosition;
	}

	public Piece getPieceMoved() {
		return this.pieceMoved;
	}

	public Piece getPieceTaken() {
		return this.pieceTaken;
	}
	
	@Override
	public String toString() {
		String movementText = "";		
		if (this.pieceMoved != null) {
			movementText += this.pieceMoved.toString();
		}
		if (this.pieceTaken != null) {
			movementText += "x";
		}
		movementText += this.endPosition.toString();
		
		return movementText;
	}

}
