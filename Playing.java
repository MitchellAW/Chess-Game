
public class Playing {
	public Playing() {
		Pawn test1 = new Pawn(new Position('d', 2), "White");
		Bishop test2 = new Bishop(new Position('d', 5), "Black");
		King test3 = new King(new Position('d', 5), "Black");
		Queen test4 = new Queen(new Position('d', 5), "Black");
		Rook test5 = new Rook(new Position('d', 5), "Black");

		Position[] moves1 = test1.getMoves();
		System.out.println();
		System.out.println("Pawn:");
		for (int i=0; i<moves1.length; i++) {
			System.out.print(moves1[i] + ", ");
		}
		moves1 = test2.getMoves();
		System.out.println();
		System.out.println("Bishop");
		for (int i=0; i<moves1.length; i++) {
			System.out.print(moves1[i] + ", ");
		}
		moves1 = test3.getMoves();
		System.out.println();
		System.out.println("King");
		for (int i=0; i<moves1.length; i++) {
			System.out.print(moves1[i] + ", ");
		}
		moves1 = test4.getMoves();
		System.out.println();
		System.out.println("Queen");
		for (int i=0; i<moves1.length; i++) {
			System.out.print(moves1[i] + ", ");
		}
		moves1 = test5.getMoves();
		System.out.println();
		System.out.println("Rook");
		for (int i=0; i<moves1.length; i++) {
			System.out.print(moves1[i] + ", ");
		}
	}
	public static void main(String[] args) {
		new Playing();
	}
}
