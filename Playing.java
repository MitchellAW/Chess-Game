
public class Playing {
	public Playing() {
		Position chessPos = new Position('c', 3);
		System.out.println(chessPos);
		for (int i=1; i<9; i++) {
			chessPos.moveDirection(i, 1);
			System.out.println(chessPos);
		}
	}
	public static void main(String[] args) {
		new Playing();
	}
}
