
public class Playing {
	public Playing() {
		Board board = new Board();
		
//		Knight test = new Knight(new Position('a', 8),"Black");
//		Position[] moves = test.getMoves();
//		for (int i = 0; i < moves.length; i++) {
//			System.out.println(moves[i]);
//		}
		
		board.showMoves(new Position('b', 2));
	}

	public static void main(String[] args) {
		new Playing();
	}
}
