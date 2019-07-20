

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Number extends JLabel
{
	
	public Number(int x)
	{
		Font f = getFont();
		setFont(new Font(f.getName(), f.getStyle(), 24));
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		setText("" + x);
		setVisible(true);

	}
	
	public void setInt(int x)
	{
		setText("" + x);
	}

}
