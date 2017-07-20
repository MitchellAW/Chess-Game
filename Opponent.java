import java.util.Random;

public class Opponent {

	Random rand = new Random();
	String colour;

	public Opponent(String colour) {
		this.colour = colour;
	}

	public Position[] getRandomMove(Board board) {
		
		Position[] fromTo = new Position[2];
		
		Piece[] pieces = getPieces(board);
		Piece randomPiece = pieces[rand.nextInt(pieces.length)];
		Position curr = randomPiece.getPosition();
		Position[] moves = randomPiece.getMoves(board);
		
		Position randomMove = moves[rand.nextInt(moves.length)];
		
		fromTo[0] = curr;
		fromTo[1] = randomMove;
		
		return fromTo;
		
	}
	public int countPieces(Board board) {
		int pieceCount = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Object pieceAt = board.getPieceAt(i, j);
				if (pieceAt instanceof Piece) {
					if (((Piece)pieceAt).getColour() == this.colour && 
							((Piece)pieceAt).canMove(board)) {
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
					if (((Piece)pieceAt).getColour() == this.colour && 
							((Piece)pieceAt).canMove(board)) {
						pieces[index] = (Piece)pieceAt;
						index++;
					}
				}
			}
		}
		return pieces;
	}
}
