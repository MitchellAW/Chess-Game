import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ChessGUI extends JFrame {

	private static final long serialVersionUID = 7731593523032576580L;

	Board board = new Board();

	Color darkBg = new Color(209, 139, 71);
	Color lightBg = new Color(255, 206, 158);
	Color highlight = new Color(255, 128, 97);

	JButton[][] boardButtons;
	// JButton resetButton = new JButton("Reset");
	JFrame frame = new JFrame("Mitch's Chess");

	JMenu reset = new JMenu("Reset");
	JMenuItem easyOption = new JMenuItem("Easy");
	JMenuItem mediumOption = new JMenuItem("Medium");
	JMenuItem hardOption = new JMenuItem("Hard");

	public ChessGUI() {
		frame.setJMenuBar(createMenuBar());
		frame.setSize(900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setContentPane(createBoard());

	}

	public JPanel createBoard() {
		JPanel newJPanel = new JPanel();

		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel gameBoard = new JPanel(new GridLayout(8, 8));

		boardButtons = new JButton[8][8];

		gameBoard.setPreferredSize(new Dimension(850, 850));

		mainPanel.add(gameBoard);
		// mainPanel.add(resetButton);

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
				boardButtons[i][j].setText((board.getBoard()[i][j].toString()));

				gameBoard.add(boardButtons[i][j]);
				boardButtons[i][j].addActionListener(new MyActionListener());
				boardButtons[i][j]
						.setFont(new Font("Arial Unicode MS", Font.BOLD, 64));
			}
		}

		return mainPanel;
	}

	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu, submenu;

		// Initalise the menu bar
		menuBar = new JMenuBar();
		menu = new JMenu("File");

		frame.add(menu);
		frame.add(reset);

		menuBar.add(menu);
		menuBar.add(reset);

		// Submenu of difficulties
		submenu = new JMenu("Change Difficulty");

		submenu.add(easyOption);
		easyOption.addActionListener(new MyActionListener());

		submenu.add(mediumOption);
		mediumOption.addActionListener(new MyActionListener());

		submenu.add(hardOption);
		hardOption.addActionListener(new MyActionListener());

		reset.addActionListener(new MyActionListener());

		menu.add(submenu);

		return menuBar;
	}

	public void resetColours() {
		// Initialise all the buttons
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
			}
		}
	}

	public void showMoves(Position position) {
		Position[] moves = ((Piece)board.getPieceAt(position)).getMoves();
		for (int i = 0; i < moves.length; i++) {
			int[] points = moves[i].getIndexes();
			boardButtons[points[0]][points[1]].setBackground(highlight);
		}
	}

	public class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resetColours();
			if (e.getSource() == reset) {
				board.reset();
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						boardButtons[i][j].setText((board.getBoard()[i][j].toString()));
					}
				}
			} else {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (e.getSource() == boardButtons[i][j]) {
							if (board.getPieceAt(i, j) instanceof Piece) {
								Position pos = ((Piece)board.getPieceAt(i, j)).getPosition();
								showMoves(pos);
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		new ChessGUI();

	}

}
