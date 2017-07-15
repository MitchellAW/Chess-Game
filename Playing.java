
public class Playing {
	public Playing() {
		Board board = new Board();
		board.reset();
		//System.out.println(board);

		Position chessPos = new Position('d',4);
		chessPos.moveDirection(8, 3);
		System.out.println(chessPos);
	}
	public static void main(String[] args) {
		new Playing();
	}
}
