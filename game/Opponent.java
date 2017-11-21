package game;

import java.util.List;

/**
 * An opponent for playing against the user of the chess game.
 * @author Mitchell
 *
 */
public class Opponent {
	
	/**
	 * The alliance colour of the opponent.
	 */
	private String colour;

	/**
	 * Creates an opponent with an alliance colour.
	 * @param colour The alliance colour of the opponent.
	 */
	public Opponent(String colour) {
		this.colour = colour;
	}

	/**
	 * Gets the best move for the board.
	 * @param board The board to evaluate and choose the best move in.
	 * @return The best move.
	 */
	public Move getMove(Board board) {
		Move bestMove = minimax(board, 3);
		return bestMove;
	}

	/**
	 * Minimax algorithm for determining the best move within a board given a 
	 * particular depth.
	 * @param board The board to check for the best move.
	 * @param depth The depth for determining the best move.
	 * @return The best move
	 */
	public Move minimax(Board board, int depth) {
		Move bestMove = null;
		board = board.copy();
		int highestSeenValue = Integer.MIN_VALUE;
		int lowestSeenValue = Integer.MAX_VALUE;
		int currentValue;
		List<Move> allMoves = board.getAllLegalMoves(board.getCurrentPlayer());

		for (int i = 0; i < allMoves.size(); i++) {
			currentValue = board.getCurrentPlayer().equals("Black")
					? min(board, depth - 1) : max(board, depth - 1);
			if (board.getCurrentPlayer().equals("Black")
					&& currentValue >= highestSeenValue) {
				highestSeenValue = currentValue;
				bestMove = allMoves.get(i);
			} else if (board.getCurrentPlayer().equals("White")
					&& currentValue <= lowestSeenValue) {
				lowestSeenValue = currentValue;
				bestMove = allMoves.get(i);
			}
		}
		System.out.println(allMoves.size());
		return bestMove;
	}

	/**
	 * Gets the minimum score.
	 * @param board The board to evaluate.
	 * @param depth The depth to continue evaluating
	 * @return The minimum score.
	 */
	public int min(Board board, int depth) {
		if (depth == 0 || board.isGameOver()) {
			return board.score();
		}

		int lowestSeenValue = Integer.MAX_VALUE;
		List<Move> allMoves = board.getAllLegalMoves(board.getCurrentPlayer());
		for (int i = 0; i < allMoves.size(); i++) {
			board.move(allMoves.get(i));
			int currentValue = max(board, depth - 1);

			if (currentValue <= lowestSeenValue) {
				lowestSeenValue = currentValue;
			}
			board.undo();
		}
		return lowestSeenValue;
	}

	/**
	 * Gets the maximum score.
	 * @param board The board to evaluate.
	 * @param depth The depth to continue evaluating
	 * @return The maximum score.
	 */
	public int max(Board board, int depth) {
		if (depth == 0 || board.isGameOver()) {
			return board.score();
		}

		int highestSeenValue = Integer.MIN_VALUE;
		List<Move> allMoves = board.getAllLegalMoves(board.getCurrentPlayer());
		for (int i = 0; i < allMoves.size(); i++) {
			board.move(allMoves.get(i));
			int currentValue = min(board, depth - 1);
			if (currentValue >= highestSeenValue) {
				highestSeenValue = currentValue;
			}
			board.undo();
		}
		return highestSeenValue;
	}

	/**
	 * Gets the alliance colour of the opponent.
	 * @return The alliance colour of the opponent.
	 */
	public String getColour() {
		return this.colour;
	}
}
