

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller extends JPanel implements ActionListener
{
	private KTour game;
	private Timer timer;
	private JSpinner spinner;

	public Controller(KTour g)
	{
		setLayout(null);
		game = g;

		JLabel lblMPS = new JLabel("Moves per second:");
		lblMPS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMPS.setBounds(33, 275, 144, 33);
		add(lblMPS);
		
		JLabel lblMoveNumVal = new JLabel("0");
		lblMoveNumVal.setBounds(155, 101, 46, 14);
		add(lblMoveNumVal);
		setVisible(true);

		spinner = new JSpinner();
		spinner.setValue(new Integer(1));
		JButton btnStart = new JButton("Start at " + (int) (spinner.getValue()) + " MPS");
		spinner.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent arg0)
			{
				btnStart.setText("Start at " + (int) (spinner.getValue()) + " MPS");
				timer.setDelay(1000 / (int) (spinner.getValue()));
			}
		});
		spinner.setBounds(187, 272, 38, 36);
		add(spinner);

		btnStart.addActionListener(this);
		btnStart.setBounds(50, 193, 175, 53);
		add(btnStart);
		setSize(275, 475);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				game.dispose();
				KTour k = new KTour();
			}
		});
		btnReset.setBounds(50, 411, 175, 53);
		add(btnReset);

		timer = new Timer(1000 / (int) (spinner.getValue()), new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				game.start();
				lblMoveNumVal.setText("" + game.getKnightStep());
				if (game.isDone())
					timer.stop();
			}
		});
		timer.setRepeats(true);

		setBackground(Color.WHITE);
		
		JLabel lblMoveNumber = new JLabel("Move Number:");
		lblMoveNumber.setBounds(33, 101, 100, 14);
		add(lblMoveNumber);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		timer.start();

	}
}
