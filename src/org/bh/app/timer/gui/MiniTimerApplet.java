package org.bh.app.timer.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import org.bh.app.timer.gui.evt.MiniTimerEvent;
import org.bh.app.timer.gui.evt.MiniTimerListener;

/**
 * MiniTimerApplet, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-06
 */
public class MiniTimerApplet extends JApplet
{
	private ArrayList<MiniTimerListener> miniTimerListeners;
	
	public MiniTimerApplet() throws HeadlessException
	{
		miniTimerListeners = new ArrayList<>();
		initGUI();
	}

	private JButton expandButton;
	private void initGUI()
	{
		expandButton = new JButton(new AbstractAction("Expand")
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
		add(expandButton);
	}
	
	public MiniTimerApplet addMiniTimerListener(MiniTimerListener newMiniTimerListener)
	{
		miniTimerListeners.add(newMiniTimerListener);
		return this;
	}
}
