import java.util.List;
import java.util.Random;

public class Opponent {

	Random rand = new Random();
	String colour;

	public Opponent(String colour) {
		this.colour = colour;
	}

	public Position[] getMove(Board board) {

		Position[] bestMove = new Position[2];
		List<List<Position>> allMoves = board.getAllLegalMoves(this.colour);

		for (int i = 0; i < allMoves.get(0).size(); i++) {
			board.move(allMoves.get(0).get(i), allMoves.get(1).get(i));
			// If a move will checkmate the opponent, take it
			if (board.isCheckmate(getOppositeColour())) {
				board.undo();
				bestMove[0] = allMoves.get(0).get(i);
				bestMove[1] = allMoves.get(1).get(i);
				return bestMove;
			}
			board.undo();
		}

		for (int i = 0; i < allMoves.get(0).size(); i++) {
			board.move(allMoves.get(0).get(i), allMoves.get(1).get(i));
			// If a move will check the opponent, take it
			if (board.isCheck(getOppositeColour())) {
				board.undo();
				bestMove[0] = allMoves.get(0).get(i);
				bestMove[1] = allMoves.get(1).get(i);
				return bestMove;
			}
			board.undo();
		}

		int highestValue = 0;

		// Take the piece of highest value
		for (int i = 0; i < allMoves.get(0).size(); i++) {
			if (board.getColourAt(allMoves.get(1).get(i))
					.equals(getOppositeColour())) {
				Piece piece = ((Piece) board
						.getPieceAt(allMoves.get(1).get(i)));
				if (piece.getValue() > highestValue) {
					highestValue = piece.getValue();
					bestMove[0] = allMoves.get(0).get(i);
					bestMove[1] = allMoves.get(1).get(i);
				}
			}
		}

		if (highestValue > 0) {
			return bestMove;
		}

		// Make a random move
		int choice = rand.nextInt(allMoves.get(0).size());

		bestMove[0] = allMoves.get(0).get(choice);
		bestMove[1] = allMoves.get(1).get(choice);

		return bestMove;
	}

	public String getOppositeColour() {
		if (this.colour == "Black") {
			return "White";
		} else {
			return "Black";
		}
	}
}
