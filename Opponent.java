import java.util.Random;

public class Opponent {

	Random rand = new Random();
	String colour;

	public Opponent(String colour) {
		this.colour = colour;
	}

	public Position[] getRandomMove(Board board) {

		Position[] fromTo = new Position[2];

		Position[][] allMoves = board.getAllMoves(this.colour);

		int choice = rand.nextInt(allMoves[0].length);

		fromTo[0] = allMoves[0][choice];
		fromTo[1] = allMoves[1][choice];

		return fromTo;
	}

	public int countPieces(Board board) {
		int pieceCount = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Object pieceAt = board.getPieceAt(i, j);
				if (pieceAt instanceof Piece) {
					if (((Piece) pieceAt).getColour() == this.colour
							&& ((Piece) pieceAt).canMove(board)) {
						pieceCount++;
					}
				}
			}
		}
		return pieceCount;
	}

	public Piece[] getPieces(Board board) {
		Piece[] pieces = new Piece[countPieces(board)];
		int index = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Object pieceAt = board.getPieceAt(i, j);
				if (pieceAt instanceof Piece) {
					if (((Piece) pieceAt).getColour() == this.colour
							&& ((Piece) pieceAt).canMove(board)) {
						pieces[index] = (Piece) pieceAt;
						index++;
					}
				}
			}
		}
		return pieces;
	}
}
