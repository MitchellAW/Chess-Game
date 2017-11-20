package testing;

import org.junit.Assert;
import org.junit.Test;

import game.*;
import pieces.*;

public class GameOverTest {

	@Test
	public void stalemateTest1() {
		Board testBoard = new Board();
		testBoard.clear();
		
		Position pos1 = new Position('h', 8);
		Position pos2 = new Position('f', 7);
		Position pos3 = new Position('g', 6);
		
		testBoard.newPiece(pos1, new King(pos1, "Black"));
		testBoard.newPiece(pos2, new King(pos2, "White"));
		testBoard.newPiece(pos3, new Queen(pos3, "White"));
		
		Assert.assertTrue(testBoard.isStalemate());
	}
	
	@Test
	public void stalemateTest2() {
		Board testBoard = new Board();
		testBoard.clear();
		
		Position pos1 = new Position('f', 8);
		Position pos2 = new Position('f', 7);
		Position pos3 = new Position('f', 6);
		
		testBoard.newPiece(pos1, new King(pos1, "Black"));
		testBoard.newPiece(pos2, new Pawn(pos2, "White"));
		testBoard.newPiece(pos3, new King(pos3, "White"));
		
		Assert.assertTrue(testBoard.isStalemate());
	}
	
	@Test
	public void stalemateTest3() {
		Board testBoard = new Board();
		testBoard.clear();
		
		Position pos1 = new Position('a', 8);
		Position pos2 = new Position('b', 8);
		Position pos3 = new Position('h', 8);
		Position pos4 = new Position('b', 6);
		
		testBoard.newPiece(pos1, new King(pos1, "Black"));
		testBoard.newPiece(pos2, new Bishop(pos2, "Black"));
		testBoard.newPiece(pos3, new Rook(pos3, "White"));
		testBoard.newPiece(pos4, new King(pos4, "White"));
		
		Assert.assertTrue(testBoard.isStalemate());
	}
	
	@Test
	public void stalemateTest4() {
		Board testBoard = new Board();
		testBoard.clear();
		
		Position pos1 = new Position('a', 1);
		Position pos2 = new Position('b', 2);
		Position pos3 = new Position('c', 3);
		
		testBoard.newPiece(pos1, new King(pos1, "Black"));
		testBoard.newPiece(pos2, new Rook(pos2, "White"));
		testBoard.newPiece(pos3, new King(pos3, "White"));
		
		Assert.assertTrue(testBoard.isStalemate());
	}
	
	@Test
	public void stalemateTest5() {
		Board testBoard = new Board();
		testBoard.clear();
		
		Position pos1 = new Position('a', 1);
		Position pos2 = new Position('a', 2);
		Position pos3 = new Position('b', 3);
		Position pos4 = new Position('g', 5);
		
		testBoard.newPiece(pos1, new King(pos1, "Black"));
		testBoard.newPiece(pos2, new Pawn(pos2, "Black"));
		testBoard.newPiece(pos3, new Queen(pos3, "White"));
		testBoard.newPiece(pos4, new King(pos4, "White"));
		
		Assert.assertTrue(testBoard.isStalemate());
	}
	
	@Test
	public void stalemateTest6() {
		Board testBoard = new Board();
		testBoard.clear();
		
		Position pos1 = new Position('a', 8);
		Position pos2 = new Position('a', 7);
		Position pos3 = new Position('a', 6);
		Position pos4 = new Position('f', 4);
		
		testBoard.newPiece(pos1, new King(pos1, "Black"));
		testBoard.newPiece(pos2, new Pawn(pos2, "White"));
		testBoard.newPiece(pos3, new King(pos3, "White"));
		testBoard.newPiece(pos4, new Bishop(pos4, "White"));
		
		Assert.assertTrue(testBoard.isStalemate());
	}
	
	@Test
	public void checkmateTest1() {
		Board testBoard = new Board();
		testBoard.clear();
		
		Position pos1 = new Position('h', 5);
		Position pos2 = new Position('h', 3);
		Position pos3 = new Position('f', 2);
		
		testBoard.newPiece(pos1, new Queen(pos1, "White"));
		testBoard.newPiece(pos2, new King(pos2, "Black"));
		testBoard.newPiece(pos3, new King(pos3, "White"));
				
		Assert.assertTrue(testBoard.isCheckmate("Black"));
	}
	
	@Test
	public void checkmateTest2() {
		Board testBoard = new Board();
		testBoard.clear();
		
		Position pos1 = new Position('a', 6);
		Position pos2 = new Position('b', 6);
		Position pos3 = new Position('c', 6);
		
		testBoard.newPiece(pos1, new King(pos1, "Black"));
		testBoard.newPiece(pos2, new Queen(pos2, "White"));
		testBoard.newPiece(pos3, new King(pos3, "White"));
				
		Assert.assertTrue(testBoard.isCheckmate("Black"));
	}
	
	@Test
	public void checkmateTest3() {
		Board testBoard = new Board();
		testBoard.clear();
		
		Position pos1 = new Position('a', 6);
		Position pos2 = new Position('b', 6);
		Position pos3 = new Position('c', 6);
		
		testBoard.newPiece(pos1, new King(pos1, "Black"));
		testBoard.newPiece(pos2, new Queen(pos2, "White"));
		testBoard.newPiece(pos3, new King(pos3, "White"));
				
		Assert.assertTrue(testBoard.isCheckmate("Black"));
	}
	
	@Test
	public void checkmateTest4() {
		Board testBoard = new Board();
		
		Position pos1Before = new Position('e', 7);
		Position pos1After = new Position('e', 5);
		Position pos2Before = new Position('g', 2);
		Position pos2After = new Position('g', 4);
		Position pos3Before = new Position('d', 8);
		Position pos3After = new Position('h', 4);
		Position pos4Before = new Position('f', 2);
		Position pos4After = new Position('f', 3);

		testBoard.move(pos1Before, pos1After);
		testBoard.move(pos2Before, pos2After);
		testBoard.move(pos3Before, pos3After);
		testBoard.move(pos4Before, pos4After);
	
		Assert.assertTrue(testBoard.isCheckmate("White"));
	}
}
