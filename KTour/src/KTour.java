
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class KTour extends JFrame
{

	private JPanel display;
	private int[][] intMap;
	private Square[][] panMatrix;
	private boolean[][] map;
	private JLabel number;
	private final int[] rMove;
	private final int[] cMove;
	private JLabel knightPiece;
	private int knightStep;
	private int knightR;
	private int knightC;
	private boolean done = false;
	private int[][] access;

	public static void main(String[] args)
	{
		KTour k = new KTour();
	}

	public KTour()
	{
		loadAccess();

		knightStep = 1;
		knightR = 0;
		knightC = 0;
		rMove = new int[]
		{ 1, 2, 2, 1, -1, -2, -2, -1, 100 };
		cMove = new int[]
		{ -2, -1, 1, 2, 2, 1, -1, -2 };
		number = new Number(0);

		knightPiece = new KnightPiece();

		panMatrix = new Square[8][8];
		intMap = new int[8][8];
		map = new boolean[8][8];
		for (int r = 0; r < 8; r++)
		{
			for (int c = 0; c < 8; c++)
			{
				map[r][c] = true;
			}
		}
		initPanMatrix();

		Controller controller = new Controller(this);
		controller.setBounds(500, 25, 275, 475);
		controller.setVisible(true);
		getContentPane().add(controller);

		display = new JPanel();
		display.setBounds(25, 25, 472, 472);
		getContentPane().add(display);
		display.setLayout(new GridLayout(8, 8, 0, 0));
		display.setVisible(true);

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setSize(806, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		repaint();
		initRCDisplay();
	}

	@SuppressWarnings("resource")
	public void loadAccess()
	{
		Scanner scan;
		scan = new Scanner(KTour.class.getResourceAsStream("/res/access.txt"));
		access = new int[8][8];
		for (int r = 0; r < 8; r++)
		{
			int[] temp = new int[8];
			for (int c = 0; c < 8; c++)
			{
				try
				{
					temp[c] = Integer.parseInt(scan.next());
				} catch (Exception e)
				{

				}
			}
			access[r] = temp;
		}

	}

	public void initPanMatrix()
	{
		for (int r = 0; r < panMatrix.length; r++)
		{
			for (int c = 0; c < panMatrix[0].length; c++)
			{
				panMatrix[r][c] = new Square();
			}
		}
		panMatrix[0][0].setKnightPlaced(true);
		map[0][0] = false;
	}

	public void initRCDisplay()
	{

		JPanel rDispPanel = new JPanel();
		rDispPanel.setBackground(Color.WHITE);
		rDispPanel.setBounds(25, 0, 475, 25);
		getContentPane().add(rDispPanel);
		rDispPanel.setLayout(new GridLayout(0, 8, 0, 0));
		JLabel[][] rRow = new JLabel[1][8];
		for (int i = 0; i < 8; i++)
		{
			rRow[0][i] = new JLabel(Integer.toString(i + 1));
			Font f = rRow[0][i].getFont();
			rRow[0][i].setFont(new Font(f.getName(), f.getStyle(), 16));
			rRow[0][i].setHorizontalAlignment(SwingConstants.CENTER);
			rRow[0][i].setBackground(Color.WHITE);
			rDispPanel.add(rRow[0][i]);
		}

		JPanel cDispPanel = new JPanel();
		cDispPanel.setBackground(Color.WHITE);
		cDispPanel.setBounds(0, 25, 25, 475);
		getContentPane().add(cDispPanel);
		cDispPanel.setLayout(new GridLayout(8, 1, 0, 0));
		JLabel[][] cRow = new JLabel[8][1];
		for (int i = 0; i < 8; i++)
		{
			cRow[i][0] = new JLabel(Integer.toString(i + 1));
			Font f = cRow[i][0].getFont();
			cRow[i][0].setFont(new Font(f.getName(), f.getStyle(), 16));
			cRow[i][0].setHorizontalAlignment(SwingConstants.CENTER);
			cRow[i][0].setBackground(Color.WHITE);
			cDispPanel.add(cRow[i][0]);
		}
	}

	public void repaint()
	{
		display.removeAll();
		for (int r = 0; r < panMatrix.length; r++)
		{
			for (int c = 0; c < panMatrix[0].length; c++)
			{
				display.add(panMatrix[r][c]);
			}
		}
	}

	public void start()
	{
		nextStep();
		repaint();
	}

	public int getKnightStep()
	{
		return knightStep;
	}

	public void nextStep()
	{
		try
		{
			int move = getMove(knightR, knightC);
			if (move == -1)
				return;
			System.out.println("index:" + move);
			try
			{

				System.out.println("old knightR = " + knightR);
				System.out.println("old knightC = " + knightC);
				panMatrix[knightR][knightC].setKnightPlaced(false);
				panMatrix[knightR][knightC].setNumber(knightStep);

				map[knightR][knightC] = false;

				knightR = (knightR + rMove[move]);
				knightC = (knightC + cMove[move]);

				subtractNeighbors(knightR, knightC);

				panMatrix[knightR][knightC].setKnightPlaced(true);

				knightStep++;

				System.out.println("knightR = " + (knightR));
				System.out.println("knightC = " + (knightC));
			} catch (ArrayIndexOutOfBoundsException e)
			{

			}
		} catch (StackOverflowError e)
		{
			done = true;
		}

	}

	public void subtractNeighbors(int r, int c)
	{
		for (int i = 0; i < 8; i++)
		{
			if ((r + rMove[i]) >= 0 && (r + rMove[i]) < 8 && (c + cMove[i]) >= 0 && (c + cMove[i]) < 8)
			{
				access[r + rMove[i]][c + cMove[i]]--;
			}
		}
	}

	public int getMove(int r, int c)
	{
		int lowestValue = 100;
		int lowestI = -1;

		for (int i = 0; i < 8; i++)
		{
			if ((r + rMove[i]) >= 0 && (r + rMove[i]) < 8 && (c + cMove[i]) >= 0 && (c + cMove[i]) < 8
					&& map[r + rMove[i]][c + cMove[i]])
			{
				if (access[r + rMove[i]][c + cMove[i]] < lowestValue)
				{
					lowestValue = access[r + rMove[i]][c + cMove[i]];
					lowestI = i;
					System.out.println("lowest reset.");
				}
			}

		}
		return lowestI;
	}

	public JPanel[][] getPanMatrix()
	{
		return panMatrix;
	}

	public boolean isDone()
	{
		return done;
	}

}
