package org.bh.app.timer.gui;

import org.bh.app.timer.BHTimerManager;
import org.bh.app.timer.gui.delegation.MiniTimerDelegate;
import org.bh.app.timer.gui.delegation.BHTimerDelegate;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import org.bh.app.timer.Main;
import org.bh.app.timer.gui.timer.BHTimerPlugin;

/**
 * BHTimerFrame, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-06
 */
public class BHTimerFrame extends JFrame implements WindowListener
{
	private static enum ON_CLOSE {EXIT, ACTIVATE_MINI_TIMER, HIDE, DO_NOTHING}
	private static enum ON_MINIMIZE {ACTIVATE_MINI_TIMER, HIDE, DO_NOTHING}
	
	private BHTimerDelegate delegate;
	private BHTimerApplet content;
	public BHTimerFrame(BHTimerDelegate initDelegate) throws HeadlessException
	{
		delegate = initDelegate;
		initGUI();
	}
	
	private void initGUI()
	{
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		setTitle("");
		content = new BHTimerApplet(this);
		setContentPane(content);
		
		pack();
	}
	
	public BHTimerDelegate getDelegate()
	{
		return delegate;
	}

	@Override
	public void setTitle(String title)
	{
		super.setTitle((title == null || title.isEmpty() ? "" : title + " - ") + Main.APP_NAME + " (" + Main.VERSION + ")"); //To change body of generated methods, choose Tools | Templates.
	}

	//<editor-fold defaultstate="collapsed" desc="WindowListener overrides">
	@Override public void windowOpened(WindowEvent e){}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		switch(getCloseOperation())
		{
			case EXIT:
				System.exit(0);
				break;
			case ACTIVATE_MINI_TIMER:
				delegate.getManager().setDisplayState(BHTimerManager.STATE_ONLY_MINI_TIMER);
				break;
			case HIDE:
			default:
				setVisible(false);
				break;
		}
	}
	
	@Override public void windowClosed(WindowEvent e){}
	@Override public void windowIconified(WindowEvent e)
	{
		switch (getMinimizeOperation())
		{
			case ACTIVATE_MINI_TIMER:
				setState(e.getOldState()); // cancel the minimization
				requestFocus();            // bring it back to the foreground
				delegate.getManager().setDisplayState(BHTimerManager.STATE_ONLY_MINI_TIMER);
				break;
			case DO_NOTHING:
				setState(e.getOldState()); // cancel the minimization
				requestFocus();            // bring it back to the foreground
				break;
			case HIDE:
			default:
				setVisible(false);
		}
	}
	@Override public void windowDeiconified(WindowEvent e){}
	@Override public void windowActivated(WindowEvent e){}
	@Override public void windowDeactivated(WindowEvent e){}
//</editor-fold>

	private byte getMiniTimerUseCase()
	{
		return delegate.getManager().getMiniTimerUseCase();
	}

	private ON_CLOSE getCloseOperation()
	{
		if (!delegate.getManager().isMiniTimerActive())
			return ON_CLOSE.EXIT;
		if ((getMiniTimerUseCase() & MiniTimerDelegate.USE_ON_CLOSE) != 0)
			return ON_CLOSE.ACTIVATE_MINI_TIMER;
		if (delegate.getManager().isMiniTimerActive())
			return ON_CLOSE.HIDE;
		return ON_CLOSE.DO_NOTHING;
	}
	
	private ON_MINIMIZE getMinimizeOperation()
	{
		if ((getMiniTimerUseCase() & MiniTimerDelegate.USE_ON_MINIMIZE) != 0)
			return ON_MINIMIZE.ACTIVATE_MINI_TIMER;
		if (delegate.getManager().isMiniTimerActive())
			return ON_MINIMIZE.HIDE;
		return ON_MINIMIZE.DO_NOTHING;
	}

	@Override
	public String toString()
	{
		return "BigTimer";
	}

	public BHTimerApplet getContent()
	{
		return content;
	}
	
	public BHTimerFrame registerPlugin(BHTimerPlugin newPlugin)
	{
		content.registerPlugin(newPlugin);
		return this;
	}

	public BHTimerPlugin getCurrentTimerPlugin()
	{
		return content.getCurrentTimerPlugin();
	}
}