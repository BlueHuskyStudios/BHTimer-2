package org.bh.app.timer.gui.timer;

import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.RELATIVE;
import static java.awt.GridBagConstraints.REMAINDER;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.bh.app.timer.util.Fonts;

/**
 * CountTimerPanel, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-20
 */
public class CountTimerPanel extends JPanel
{
	private BHTimerPlugin basis;
	public CountTimerPanel(BHTimerPlugin initBasis)
	{
		basis = initBasis;
		initGUI();
	}

	private JLabel display;
	private JButton goButton, pauseButton, stopButton;
	private void initGUI()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(1, 1, 1, 1, 1, 1, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0);
		
		goButton = new JButton("Go");
		goButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				basis.go();
			}
		});
		gbc.weightx = 0;
		gbc.weighty = 3;
		add(goButton, gbc);
		
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					
					if (basis.isPaused())
						basis.resume();
					else
						basis.pause();
				}
			});
		gbc.weighty = 1;
		gbc.gridy++;
		add(pauseButton, gbc);
		
		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (basis.isGoing())
						basis.stop();
					else
						basis.reset();
				}
			});
		gbc.gridy++;
		gbc.weighty = 0;
		add(stopButton, gbc);
		
		display = new JLabel("Loading...");
		display.setFont(getFont().deriveFont(48f));
		display.setHorizontalAlignment(CENTER);
		display.setHorizontalTextPosition(CENTER);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = REMAINDER;
		gbc.gridx++;
		gbc.gridy = RELATIVE;
		gbc.weightx = 1;
		add(display, gbc);
	}
	

	public void setDisplay(String newDisplay)
	{
		display.setText(newDisplay);
	}
}
