package game;

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

import pieces.Piece;

public class ChessGUI extends JFrame {

	private static final long serialVersionUID = 7731593523032576580L;
	private int moves = 0;

	Board board = new Board();

	// Various font and colours used
	Color defaultThemeDark = new Color(209, 139, 71);
	Color defaultThemeLight = new Color(255, 206, 158);

	Color blueThemeDark = new Color(153, 225, 229);
	Color blueThemeLight = new Color(255, 255, 255);

	Color aussieThemeDark = new Color(33, 165, 66);
	Color aussieThemeLight = new Color(255, 205, 0);

	Color darkBg = defaultThemeDark;
	Color lightBg = defaultThemeLight;

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

	JMenuItem defaultOption = new JMenuItem("Default");
	JMenuItem whiteOption = new JMenuItem("White/Blue");
	JMenuItem greenOption = new JMenuItem("Green/Gold");

	JTextArea moveDisplay = new JTextArea(14, 21);
	JTextArea capturedBlack = new JTextArea(3, 16);
	JTextArea capturedWhite = new JTextArea(3, 16);

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

		JTextArea black = new JTextArea("Black", 1, 12);
		JTextArea white = new JTextArea("\nWhite", 2, 12);

		black.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
		white.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));

		black.setBackground(new JPanel().getBackground());
		black.setEditable(false);
		white.setBackground(new JPanel().getBackground());
		white.setEditable(false);

		capturedBlack.setFont(new Font("Arial Unicode MS", Font.PLAIN, 24));
		capturedBlack.setBackground(new JPanel().getBackground());
		capturedBlack.setEditable(false);
		capturedWhite.setFont(new Font("Arial Unicode MS", Font.PLAIN, 24));
		capturedWhite.setBackground(new JPanel().getBackground());
		capturedWhite.setEditable(false);

		JLabel gameTitle = new JLabel("Mitch's Chess", SwingConstants.CENTER);
		JLabel moveHistoryTitle = new JLabel("Move History",
				SwingConstants.CENTER);
		gameTitle.setFont(new Font("Arial Unicode MS", Font.BOLD, 48));
		moveHistoryTitle.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));

		moveDisplay.setEditable(false);
		moveDisplay.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
		JScrollPane scroll = new JScrollPane(moveDisplay);
		scroll.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		gameBoard.setPreferredSize(new Dimension(850, 850));
		mainPanel.add(gameBoard, BorderLayout.WEST);
		mainPanel.add(sidePanel, BorderLayout.CENTER);

		sidePanel.add(gameTitle);
		sidePanel.add(white);
		sidePanel.add(capturedWhite);
		sidePanel.add(black);
		sidePanel.add(capturedBlack);
		sidePanel.add(moveHistoryTitle);
		sidePanel.add(scroll);
		sidePanel.add(undo);
		sidePanel.add(reset);

		undo.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
		reset.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));

		undo.addActionListener(new MyActionListener());

		// Initialise all the buttons
		for (int row = 0; row < Board.ROWS; row++) {
			for (int col = 0; col < Board.COLS; col++) {
				boardButtons[row][col] = new JButton();
				JButton currentButton = boardButtons[row][col];

				currentButton.setVisible(true);
				currentButton.setOpaque(true);

				// Draw the pieces on the board
				currentButton.setForeground(Color.BLACK);
				Piece currentPiece = board.getPieceAt(row, col);
				String pieceText;
				if (currentPiece != null) {
					pieceText = currentPiece.toString();

				} else {
					pieceText = "";
				}
				currentButton.setText(pieceText);
				currentButton.setFont(arialMS);

				gameBoard.add(boardButtons[row][col]);
				currentButton.addActionListener(new MyActionListener());
			}
		}
		resetColours();
		updateCaptured();

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

		JMenu theme = new JMenu("Theme");
		theme.add(defaultOption);
		defaultOption.addActionListener(new MyActionListener());
		theme.add(whiteOption);
		whiteOption.addActionListener(new MyActionListener());
		theme.add(greenOption);
		greenOption.addActionListener(new MyActionListener());

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
		menu.add(theme);
		menu.add(credits);
		menu.add(exit);

		// Adding menu to menu bar
		menuBar.add(menu);

		return menuBar;
	}

	// Reset all the colours of the board
	public void resetColours() {
		for (int row = 0; row < Board.ROWS; row++) {
			for (int col = 0; col < Board.COLS; col++) {
				JButton currentButton = boardButtons[row][col];
				if (row % 2 == 0) {
					if (col % 2 != 0) {
						currentButton.setBackground(darkBg);
					} else {
						currentButton.setBackground(lightBg);
					}
				} else if (col % 2 != 0) {
					currentButton.setBackground(lightBg);
				} else {
					currentButton.setBackground(darkBg);
				}
				currentButton.setForeground(Color.BLACK);
			}
		}
	}

	// Update all the pieces displayed in the GUI
	public void updatePieces() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				JButton currentButton = boardButtons[row][col];
				Piece currentPiece = board.getPieceAt(row, col);
				if (currentPiece != null) {
					currentButton
							.setText(board.getPieceAt(row, col).toString());
				} else {
					currentButton.setText("");
				}
			}
		}
	}

	public void checkBoard() {
		if (board.isStalemate()) {
			JOptionPane.showMessageDialog(frame, "Stalemate. Draw.",
					"Stalemate", JOptionPane.INFORMATION_MESSAGE);
		} else if (board.isCheckmate("White")) {
			JOptionPane.showMessageDialog(frame, "Checkmate. You Lose.",
					"Checkmate", JOptionPane.INFORMATION_MESSAGE);
		} else if (board.isCheckmate("Black")) {
			JOptionPane.showMessageDialog(frame, "Checkmate. You Win.",
					"Checkmate", JOptionPane.INFORMATION_MESSAGE);
		} else if (board.isCheck("White")) {
			JOptionPane.showMessageDialog(frame, "You are in check.", "Check",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Display all moves available at position
	public void showMoves(Position position) {
		if (board.getPieceAt(position) instanceof Piece) {
			Piece piece = ((Piece) board.getPieceAt(position));
			List<Move> moves = piece.getMoves(board);
			for (int i = 0; i < moves.size(); i++) {
				int[] points = moves.get(i).getEndPosition().getIndexes();

				JButton currentButton = boardButtons[points[0]][points[1]];

				currentButton.setBackground(highlight);
				currentButton.setForeground(Color.RED.darker());
			}
		}
	}

	public void updateMoveHistory() {
		List<Move> moveHistory = board.getMoveHistory();
		moveDisplay.setText("");

		for (int i = 0; i < moveHistory.size(); i++) {

			moveDisplay.append(" " + (i + 1) + "." + moveHistory.get(i) + "\n");
		}
	}

	public void updateCaptured() {
		int[] whiteCount = board.countPieces("White");
		int[] blackCount = board.countPieces("Black");

		char[] whitePieces = { '♙', '♖', '♘', '♗', '♕', '♔' };
		char[] blackPieces = { '♟', '♜', '♞', '♝', '♛', '♚' };

		capturedWhite.setText("");
		capturedBlack.setText("");

		// Add counts for white pieces
		String capText = "";
		for (int i = 0; i < whiteCount.length; i++) {
			if (whiteCount[i] > 0) {
				capText += String.format("%" + whiteCount[i] + "s", "")
						.replace(' ', whitePieces[i]);
			}

			if (i == 0) {
				capText += "\n";
			}
		}
		capturedWhite.setText(capText);

		// Add counts for black pieces
		capText = "";
		for (int i = 0; i < blackCount.length; i++) {
			if (blackCount[i] > 0) {
				capText += String.format("%" + blackCount[i] + "s", "")
						.replace(' ', blackPieces[i]);
			}

			if (i == 0) {
				capText += "\n";
			}
		}
		capturedBlack.setText(capText);
	}

	// Computer will make it's move
	public void computerMove() {
		// Make computer's move
		if (!board.isCheckmate("Black")) {
			Move computerMove = computer.getMove(board);
			int[] move;

			if (computerMove != null) {

				board.move(computerMove);
				updatePieces();
				updateMoveHistory();

				// Highlight opponent's move for clarity
				move = computerMove.getStartPosition().getIndexes();
				boardButtons[move[0]][move[1]]
						.setForeground(Color.RED.darker());

				move = computerMove.getEndPosition().getIndexes();
				boardButtons[move[0]][move[1]].setBackground(highlightDark);
				moves++;

				checkBoard();
			}
		}
	}

	public class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == reset) {
				board.reset();
				updatePieces();
				resetColours();
				moves = 0;
			} else if (e.getSource() == undo) {
				board.undo();
				board.undo();
				moves -= 2;
				updatePieces();
				resetColours();
			} else if (e.getSource() == defaultOption) {
				darkBg = defaultThemeDark;
				lightBg = defaultThemeLight;
				resetColours();
			} else if (e.getSource() == whiteOption) {
				darkBg = blueThemeDark;
				lightBg = blueThemeLight;
				resetColours();
			} else if (e.getSource() == greenOption) {
				darkBg = aussieThemeDark;
				lightBg = aussieThemeLight;
				resetColours();
			} else if (e.getSource() == credits) {
				JOptionPane.showMessageDialog(frame,
						"Copyright © 2017 Mitchell Woollatt", "Credits",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (e.getSource() == exit) {
				System.exit(0);
			}
			for (int row = 0; row < Board.ROWS; row++) {
				for (int col = 0; col < Board.COLS; col++) {
					JButton currentButton = boardButtons[row][col];
					Position currentPos = new Position(row, col);
					Object currentPiece = board.getPieceAt(row, col);

					// Show the available moves if white piece is clicked
					if (e.getSource() == currentButton
							&& currentButton.getBackground() != highlight) {

						// Highlight available moves and set the piece position
						if (board.getColourAt(currentPos) == "White") {
							resetColours();
							pos = ((Piece) currentPiece).getPosition();
							showMoves(pos);

							// If a white piece isn't clicked, clear the
							// highlighted moves
						} else {
							resetColours();
						}
						// Player's move
					} else if (e.getSource() == currentButton && moves % 2 == 0
							&& board.getColourAt(pos) == "White") {

						if (board.isCheckmate("White") == false) {
							// Move to the selected position
							board.move(pos, currentPos);
							if (board.isCheck("White") == false) {
								resetColours();
								updateMoveHistory();
								updateCaptured();
								updatePieces();
								checkBoard();
								moves++;

								computerMove();
							} else {
								board.undo();
							}
						}
					}
				}
			}
			board.updatePieces();
			updateCaptured();
			updateMoveHistory();
			updatePieces();
		}
	}

	public static void main(String[] args) {
		new ChessGUI();
	}
}
