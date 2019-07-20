

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class KnightPiece extends JLabel
{
	
	public KnightPiece()
	{
		ImageIcon pic = new ImageIcon(KnightPiece.class.getResource("/res/KnightPiece.png"));
		setIcon(pic);
		setVisible(true);
	}

}
