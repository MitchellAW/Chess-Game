
public class Playing {
	public Playing() {
		Board board = new Board();
		board.reset();
		System.out.println(board);
	}
	public static void main(String[] args) {
		new Playing();
	}
}
