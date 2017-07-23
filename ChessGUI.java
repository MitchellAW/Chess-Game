import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ChessGUI extends JFrame {

	private static final long serialVersionUID = 7731593523032576580L;

	int moves = 0;

	Board board = new Board();

	// Various font and colours used
	Color darkBg = new Color(209, 139, 71);
	Color lightBg = new Color(255, 206, 158);
	Color highlight = new Color(255, 128, 97);
	Color highlightDark = new Color(255, 102, 71);
	Font arialMS = new Font("Arial Unicode MS", Font.BOLD, 64);

	Opponent computer = new Opponent("Black");
	Position pos = new Position('a', 1);

	// Chess Board
	JButton[][] boardButtons;
	JFrame frame = new JFrame("Mitch's Chess");

	// Menu options
	JButton undo = new JButton("Undo");
	JMenuItem credits = new JMenuItem("Credits");
	JMenuItem exit = new JMenuItem("Exit");
	JButton reset = new JButton("Reset");
	JMenuItem easyOption = new JMenuItem("Easy");
	JMenuItem mediumOption = new JMenuItem("Medium");
	JMenuItem hardOption = new JMenuItem("Hard");
	JTextArea moveDisplay = new JTextArea(16, 32);

	public ChessGUI() {
		frame.setJMenuBar(createMenuBar());
		frame.setSize(1250, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(createBoard());
		frame.setVisible(true);
		frame.setResizable(false);

	}

	public JPanel createBoard() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel gameBoard = new JPanel(new GridLayout(8, 8));
		JPanel sidePanel = new JPanel();

		boardButtons = new JButton[8][8];

		JLabel title = new JLabel("Mitch's Chess", SwingConstants.CENTER);
		JLabel field = new JLabel("Move History", SwingConstants.CENTER);
		title.setFont(new Font("Arial Unicode MS", Font.BOLD, 48));
		field.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));

		moveDisplay.setEditable(false);
		moveDisplay.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		JScrollPane scroll = new JScrollPane(moveDisplay);
		scroll.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		gameBoard.setPreferredSize(new Dimension(850, 850));
		mainPanel.add(gameBoard, BorderLayout.WEST);
		mainPanel.add(sidePanel, BorderLayout.CENTER);

		sidePanel.add(title);
		sidePanel.add(field);
		sidePanel.add(scroll);
		sidePanel.add(undo);
		sidePanel.add(reset);

		undo.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
		reset.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));

		undo.addActionListener(new MyActionListener());

		// Initialise all the buttons
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boardButtons[i][j] = new JButton();
				boardButtons[i][j].setVisible(true);

				if (i % 2 == 0) {
					if (j % 2 != 0) {
						boardButtons[i][j].setBackground(darkBg);
					} else {
						boardButtons[i][j].setBackground(lightBg);
					}
				} else {
					if (j % 2 != 0) {
						boardButtons[i][j].setBackground(lightBg);
					} else {
						boardButtons[i][j].setBackground(darkBg);
					}
				}

				boardButtons[i][j].setForeground(Color.BLACK);
				boardButtons[i][j].setText((board.getPieceAt(i, j).toString()));
				boardButtons[i][j].setFont(arialMS);

				gameBoard.add(boardButtons[i][j]);
				boardButtons[i][j].addActionListener(new MyActionListener());
			}
		}
		return mainPanel;
	}

	public JMenuBar createMenuBar() {
		// Create difficulties and listen for clicks
		JMenu difficulty = new JMenu("Change Difficulty");
		difficulty.add(easyOption);
		easyOption.addActionListener(new MyActionListener());
		difficulty.add(mediumOption);
		mediumOption.addActionListener(new MyActionListener());
		difficulty.add(hardOption);
		hardOption.addActionListener(new MyActionListener());

		// Listen for clicks on reset button
		reset.addActionListener(new MyActionListener());

		// Creating menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");

		// Creating menu items
		credits.addActionListener(new MyActionListener());
		exit.addActionListener(new MyActionListener());

		// Adding Menu Items
		menu.add(difficulty);
		// menu.add(undo);
		// menu.add(reset);
		menu.add(credits);
		menu.add(exit);

		// Adding menu to menu bar
		menuBar.add(menu);
		// menuBar.add(undo);

		return menuBar;
	}

	// Reset all the colours of the board
	public void resetColours() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0) {
					if (j % 2 != 0) {
						boardButtons[i][j].setBackground(darkBg);
					} else {
						boardButtons[i][j].setBackground(lightBg);
					}
				} else {
					if (j % 2 != 0) {
						boardButtons[i][j].setBackground(lightBg);
					} else {
						boardButtons[i][j].setBackground(darkBg);
					}
				}
				boardButtons[i][j].setForeground(Color.BLACK);
			}
		}
	}

	// Update all the pieces displayed in the GUI
	public void updatePieces() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boardButtons[i][j].setText(board.getPieceAt(i, j).toString());
			}
		}
	}

	// Display all moves available at position
	public void showMoves(Position position) {
		if (board.getPieceAt(position) instanceof Piece) {
			Piece piece = ((Piece) board.getPieceAt(position));
			List<Position> moves = piece.getMoves(board);
			for (int i = 0; i < moves.size(); i++) {
				int[] points = moves.get(i).getIndexes();

				boardButtons[points[0]][points[1]].setBackground(highlight);
				boardButtons[points[0]][points[1]]
						.setForeground(Color.RED.darker());
			}
		}
	}

	public void updateMoveHistory() {
		List<Object[]> moveHistory = board.getMoveHistory();
		String captured;
		moveDisplay.setText("");

		for (int i = 0; i < moveHistory.size(); i++) {
			if (moveHistory.get(i)[1].equals("")) {
				captured = "";
			} else {
				captured = "x";
			}
			moveDisplay.append(((i + 1) + ". "
					+ moveHistory.get(i)[0].toString() + captured
					+ moveHistory.get(i)[3].toString() + "\n"));
		}
	}

	// Computer will make it's move
	public void computerMove() {
		// Make computer's move
		if (board.isCheckmate("Black") == false) {
			Position[] computerMove = computer.getRandomMove(board);
			int[] move;

			board.move(computerMove[0], computerMove[1]);
			updatePieces();
			updateMoveHistory();

			// Highlight opponent's move for clarity
			move = computerMove[1].getIndexes();
			boardButtons[move[0]][move[1]].setForeground(Color.RED.darker());

			move = computerMove[0].getIndexes();
			boardButtons[move[0]][move[1]].setBackground(highlightDark);
			moves++;

			if (board.isCheckmate("White") == true) {
				JOptionPane.showMessageDialog(frame, "Checkmate. You Lose.",
						"Checkmate", JOptionPane.INFORMATION_MESSAGE);
			} else if (board.isCheck("White")) {
				JOptionPane.showMessageDialog(frame, "You are in check.",
						"Check", JOptionPane.INFORMATION_MESSAGE);

			}
		} else {
			JOptionPane.showMessageDialog(frame, "Checkmate. You Win.",
					"Checkmate", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == reset) {
				board.reset();
				updatePieces();
				resetColours();
			}
			if (e.getSource() == undo) {
				board.undo();
				board.undo();
				moves -= 2;
				updatePieces();
				resetColours();
			}
			if (e.getSource() == credits) {
				JOptionPane.showMessageDialog(frame,
						"Copyright © 2017 Mitchell Woollatt", "Credits",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (e.getSource() == exit) {
				System.exit(0);
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					// Show the available moves if white piece is clicked
					if (e.getSource() == boardButtons[i][j]
							&& boardButtons[i][j]
									.getBackground() != highlight) {

						// Highlight available moves and set the piece position
						if (board.getColourAt(new Position(i, j)) == "White") {
							resetColours();
							pos = ((Piece) board.getPieceAt(i, j))
									.getPosition();
							showMoves(pos);

							// If a white piece isn't clicked, clear the
							// highlighted moves
						} else {
							resetColours();
						}
						// Player's move
					} else if (e.getSource() == boardButtons[i][j]
							&& moves % 2 == 0
							&& board.getColourAt(pos) == "White") {

						if (board.isCheckmate("White") == false) {
							// Move to the selected position
							board.move(pos, new Position(i, j));
							if (board.isCheck("White") == false) {
								resetColours();
								updateMoveHistory();
								updatePieces();
								moves++;

								computerMove();
							} else {
								board.undo();
							}
						} else {
							JOptionPane.showMessageDialog(frame,
									"Checkmate. You Lose.", "Checkmate",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}

				}
			}
			updateMoveHistory();
			updatePieces();
		}
	}

	public static void main(String[] args) {
		new ChessGUI();
	}
}
