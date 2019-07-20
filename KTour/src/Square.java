

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Square extends JPanel
{
	private KnightPiece knightPiece;
	private Number number;
	
	public Square()
	{
		knightPiece = new KnightPiece();
		knightPiece.setBounds(0, 0, 59, 59);
		knightPiece.setVisible(false);
		
		number = new Number(0);
		number.setBounds(1, 1, 59, 59);
		number.setVisible(false);
		
		
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setSize(59, 59);
		setVisible(true);
		setLayout(null);
		add(knightPiece);
		add(number);
		
		
	}
	
	public void setKnightPlaced(boolean k)
	{
		knightPiece.setVisible(k);
		number.setVisible(false);
	}
	
	
	public void setNumber(boolean t)
	{
		number.setVisible(t);
		knightPiece.setVisible(!t);
	}
	
	public void setNumber(int i){
		number.setVisible(true);
		number.setText("" + i);
		knightPiece.setVisible(false);
	}
}
