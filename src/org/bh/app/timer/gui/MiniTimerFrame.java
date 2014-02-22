package org.bh.app.timer.gui;

import org.bh.app.timer.gui.delegation.MiniTimerDelegate;
import javax.swing.JWindow;
import org.bh.app.timer.gui.evt.MiniTimerListener;
import org.bh.app.timer.gui.timer.BHTimerPlugin;

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
	private final MiniTimerApplet CONTENT;
	public MiniTimerFrame(MiniTimerDelegate initDelegate)
	{
		delegate = initDelegate;
		CONTENT = new MiniTimerApplet();
		setContentPane(CONTENT);
		/*setSize(new Dimension(256, 128));*/
		pack();
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
		CONTENT.addMiniTimerListener(newMiniTimerListener);
	}
	
	

	@Override
	public String toString()
	{
		return "miniTimer";
	}

	@Override
	public void validate()
	{
		super.validate();
		CONTENT.validate();
	}

	public void setCurrentTimerPlugin(BHTimerPlugin newTimerPlugin)
	{
		CONTENT.setCurrentTimerPlugin(newTimerPlugin);
	}

	public BHTimerPlugin getCurrentTimerPlugin()
	{
		return CONTENT.getCurrentTimerPlugin();
	}
}
