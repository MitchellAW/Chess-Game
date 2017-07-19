
public class Pawn extends Piece {
	private int[] directions = new int[1];
	private int maxDistance = 1;

	public Pawn(Position position, String colour) {
		super(position, colour, 1);
		if (this.getColour().equals("White")) {
			this.directions[0] = 1;
		} else {
			this.directions[0] = 5;
		}
	}

	public int[] getDirections() {
		return this.directions;
	}

	public int getMaxDistance() {
		if (this.getColour().equals("White")) {
			if (this.getPosition().getRow() == 2) {
				return 2;
			} else {
				return 1;
			}
		} else {
			if (this.getPosition().getRow() == 7) {
				return 2;
			} else {
				return 1;
			}
		}
	}

	public String toString() {
		if (this.getColour().equals("White")) {
			return "♙";
		} else {
			return "♟";
		}
	}
}
