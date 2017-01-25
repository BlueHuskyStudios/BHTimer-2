package org.bh.app.timer.gui.timer;

import org.bh.app.timer.BHTimerPlugin;
import bht.tools.util.StringPP;
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
import org.bh.app.timer.Colors;
import org.bh.app.timer.Language;

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
	private Language language = Language.getInstance();
	private Colors colors = Colors.getInstance();
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
//		goButton.setBorder(new LineBorder(colors.go.getState()));
		goButton.setBackground(colors.go.getState());
		goButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (basis.isPaused())
					basis.resume();
				basis.go();
				validate();
			}
		});
		gbc.weightx = 0;
		gbc.weighty = 3;
		add(goButton, gbc);
		
		pauseButton = new JButton("Pause");
		pauseButton.setBackground(colors.pause.getState());
		pauseButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (basis.isPaused())
					basis.resume();
				else
					basis.pause();
				validate();
			}
		});
		gbc.weighty = 1;
		gbc.gridy++;
		add(pauseButton, gbc);
		
		stopButton = new JButton("Stop");
		stopButton.setBackground(colors.stop.getState());
		stopButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (basis.isGoing())
					basis.stop();
				else
					basis.reset();
				basis.resume();
				validate();
			}
		});
		gbc.gridy++;
		gbc.weighty = 0;
		add(stopButton, gbc);
		
		display = new JLabel();
		display.setFont(getFont().deriveFont(48f));
		display.setHorizontalAlignment(CENTER);
		display.setHorizontalTextPosition(CENTER);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = REMAINDER;
		gbc.gridx++;
		gbc.gridy = RELATIVE;
		gbc.weightx = 1;
		add(display, gbc);
		
		validate();
	}
	

	public void setDisplay(String newDisplay)
	{
		display.setText(newDisplay);
	}

	@Override
	public void validate()
	{
		super.validate();
		boolean going = basis.isGoing();
		boolean paused = basis.isPaused();
		boolean reset = basis.isReset();
		stopButton.setEnabled(!reset);
		pauseButton.setEnabled(going);
		
		goButton   .setText(new StringPP(going || !reset ? language.restart.getState() : language.go.getState()   ).toTitleCase()+"");
		stopButton .setText(new StringPP(going || reset  ? language.stop.getState()    : language.reset.getState()).toTitleCase()+"");
		pauseButton.setText(new StringPP(paused          ? language.resume.getState()  : language.pause.getState()).toTitleCase()+"");
		
		if (!going && reset)
			setDisplay("Click \"" + goButton.getText() + "\" to start!");
	}
}
