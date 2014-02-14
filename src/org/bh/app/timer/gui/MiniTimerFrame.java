package org.bh.app.timer.gui;

import java.awt.Dimension;
import org.bh.app.timer.gui.delegation.MiniTimerDelegate;
import javax.swing.JWindow;
import org.bh.app.timer.gui.evt.MiniTimerListener;

/**
 * MiniTimerFrame, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-06
 */
public class MiniTimerFrame extends JWindow
{
	private MiniTimerDelegate delegate;
	private MiniTimerApplet content;
	public MiniTimerFrame(MiniTimerDelegate initDelegate)
	{
		delegate = initDelegate;
		content = new MiniTimerApplet();
		setContentPane(content);
		setSize(new Dimension(256, 128));
		setAlwaysOnTop(true);
	}

	public MiniTimerDelegate getDelegate()
	{
		return delegate;
	}

	public void setDelegate(MiniTimerDelegate newDelegate)
	{
		delegate = newDelegate;
	}

	public void addMiniTimerListener(MiniTimerListener newMiniTimerListener)
	{
		content.addMiniTimerListener(newMiniTimerListener);
	}
	
	

	@Override
	public String toString()
	{
		return "miniTimer";
	}
}
