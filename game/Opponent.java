package game;

import java.util.List;
import java.util.Random;

import pieces.Piece;

public class Opponent {

	private Random rand = new Random();
	private String colour;

	public Opponent(String colour) {
		this.colour = colour;
	}

	public Move getMove(Board board) {
		List<Move> allMoves = board.getAllLegalMoves(this.colour);
		Move bestMove = null;

		//		for (int i = 0; i < allMoves.size(); i++) {
		//			board.move(allMoves.get(i));
		//			// If a move will checkmate the opponent, take it
		//			if (board.isCheckmate(getOppositeColour())) {
		//				board.undo();
		//				bestMove = allMoves.get(i);
		//				return bestMove;
		//			}
		//			board.undo();
		//		}
		//
		//		for (int i = 0; i < allMoves.size(); i++) {
		//			board.move(allMoves.get(i));
		//			// If a move will check the opponent, take it
		//			if (board.isCheck(getOppositeColour())) {
		//				board.undo();
		//				bestMove = allMoves.get(i);
		//				return bestMove;
		//			}
		//			board.undo();
		//		}
		//
		//		int highestValue = 0;
		//
		//		// Take the piece of highest value
		//		for (int i = 0; i < allMoves.size(); i++) {
		//			if (board.getColourAt(allMoves.get(i).getEndPosition())
		//					.equals(getOppositeColour())) {
		//				Piece piece = allMoves.get(i).getPieceTaken();
		//				if (piece != null && piece.getValue() > highestValue) {
		//					highestValue = piece.getValue();
		//					bestMove = allMoves.get(i);
		//				}
		//			}
		//		}
		//
		//		if (highestValue > 0) {
		//			return bestMove;
		//		}
		//
		//		int size = allMoves.size();

		//		if (size > 0) {
		// Make a random move
		if (allMoves.size() > 0) {
			int choice = rand.nextInt(allMoves.size());

			bestMove = allMoves.get(choice);
		} 
		//		}
		return bestMove;

	}

	public String getColour() {
		return this.colour;
	}

	public String getOppositeColour() {
		if (this.colour == "Black") {
			return "White";
		}
		return "Black";
	}
}
