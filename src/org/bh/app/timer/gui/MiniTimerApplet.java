package org.bh.app.timer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.border.AbstractBorder;
import static org.bh.app.timer.gui.Colors.*;
import org.bh.app.timer.gui.evt.MiniTimerEvent;
import org.bh.app.timer.gui.evt.MiniTimerListener;
import org.bh.app.timer.gui.timer.BHTimerPlugin;
import org.bh.app.timer.util.Time;

/**
 * MiniTimerApplet, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-06
 */
public class MiniTimerApplet extends JApplet
{
	private ArrayList<MiniTimerListener> miniTimerListeners = new ArrayList<>();
	private BHTimerPlugin timerPlugin;
	
	public MiniTimerApplet() throws HeadlessException
	{
		initGUI();
	}

	private JButton expandButton, greenButton, blueButton, redButton;
	private JProgressBar progressBar;
	private void initGUI()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		//<editor-fold defaultstate="collapsed" desc="expandButton">
		{
			expandButton = new JButton(new AbstractAction("^")
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					MiniTimerEvent evt = new MiniTimerEvent(e, expandButton);
					for (MiniTimerListener miniTimerListener : miniTimerListeners)
					{
						miniTimerListener.expandButtonPressed(evt);
					}
				}
			});
			gbc.gridheight = 2;
			gbc.weightx = 0;
			add(expandButton, gbc);
		}
		//</editor-fold>
		//<editor-fold defaultstate="collapsed" desc="greenButton">
		{
			greenButton = new JButton();
			greenButton.setBackground(GREEN);
//			greenButton.setBorder(new AbstractBorder(){});
			greenButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					getCurrentTimerPlugin().go();
				}
			});
			gbc.gridx++;
			gbc.gridheight = 1;
			gbc.weightx = 3;
			add(greenButton, gbc);
		}
		//</editor-fold>
		//<editor-fold defaultstate="collapsed" desc="blueButton">
		{
			blueButton = new JButton();
			blueButton.setBackground(BLUE);
			blueButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (getCurrentTimerPlugin().isPaused())
						getCurrentTimerPlugin().resume();
					else
						getCurrentTimerPlugin().pause();
				}
			});
			gbc.gridx++;
			gbc.weightx = 1;
			add(blueButton, gbc);
		}
		//</editor-fold>
		//<editor-fold defaultstate="collapsed" desc="redButton">
		{
			redButton = new JButton();
			redButton.setBackground(RED);
			redButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (getCurrentTimerPlugin().isGoing())
						getCurrentTimerPlugin().stop();
					else
						getCurrentTimerPlugin().reset();
				}
			});
			gbc.gridx++;
			add(redButton, gbc);
		}
		//</editor-fold>
		//<editor-fold defaultstate="collapsed" desc="progressBar">
		{
			progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
			progressBar.setString("00:00:00.00");
			gbc.gridy++;
			gbc.gridx = GridBagConstraints.RELATIVE;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weighty = 1;
			add(progressBar, gbc);
		}
		//</editor-fold>
	}
	
	public MiniTimerApplet addMiniTimerListener(MiniTimerListener newMiniTimerListener)
	{
		miniTimerListeners.add(newMiniTimerListener);
		return this;
	}
	
	public MiniTimerApplet setCurrentTimerPlugin(BHTimerPlugin newCurrentPlugin)
	{
		timerPlugin = newCurrentPlugin;
		validate();
		return this;
	}

	@Override
	public void validate()
	{
		super.validate();
		if (progressBar != null && timerPlugin != null)
			progressBar.setString(Time.toString(timerPlugin.getCurrentTime(), Time.RES_DEFAULT));
	}

	public BHTimerPlugin getCurrentTimerPlugin()
	{
		return timerPlugin;
	}
}
