import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

	private String colour;
	private Position position;

	private int value;

	public Piece(Position position, String colour, int value) {
		this.position = position;
		this.colour = colour;
		this.value = value;
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

	public boolean canMove(Board board) {
		return (getMoves(board).size() > 0);
	}

	// Gets all the moves that piece can make given the current board
	public List<Position> getMoves(Board board) {
		List<Position> moves = new ArrayList<Position>();
		Position curr = this.getPosition();

		for (int i = 0; i < this.getDirections().length; i++) {
			for (int j = 1; j <= this.getMaxDistance(); j++) {
				if (curr.positionAt(this.getDirections()[i], j).isValid()) {
					Object pieceAt = board.getPieceAt(
							curr.positionAt(this.getDirections()[i], j));
					if (pieceAt instanceof Piece) {
						if (((Piece) pieceAt).getColour() != this.getColour()) {
							moves.add(curr.positionAt(this.getDirections()[i],
									j));
							break;
						} else {
							break;
						}
					} else {
						moves.add(curr.positionAt(this.getDirections()[i], j));
					}
				}
			}
		}
		return moves;
	}

	public abstract int getMaxDistance();

	public abstract int[] getDirections();
}
