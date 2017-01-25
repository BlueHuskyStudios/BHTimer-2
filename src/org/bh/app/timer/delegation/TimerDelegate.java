package org.bh.app.timer.delegation;

import org.bh.app.timer.BHTimerPlugin;
import java.awt.Component;
import org.bh.app.timer.evt.PluginListener;
import org.bh.app.timer.evt.TimerPluginListener;

/**
 * TimerDelegate, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-27
 */
public class TimerDelegate implements BHTimerPlugin, TimerPluginListener
{
	private BHTimerPlugin basis;

	public TimerDelegate(BHTimerPlugin basis)
	{
		this.basis = basis;
	}
	
	@Override
	public CharSequence getName()
	{
		return basis.getName();
	}

	@Override
	public Component asComponent()
	{
		return basis.asComponent();
	}

	@Override
	public long getCurrentTime()
	{
		return basis.getCurrentTime();
	}

	@Override
	public long getStartTime()
	{
		return basis.getStartTime();
	}

	@Override
	public long getOffset()
	{
		return basis.getOffset();
	}

	@Override
	public void update()
	{
		basis.update();
	}

	@Override
	public void go()
	{
		basis.go();
	}

	@Override
	public void stop()
	{
		basis.stop();
	}

	@Override
	public boolean isGoing()
	{
		return basis.isGoing();
	}

	@Override
	public void pause()
	{
		basis.pause();
	}

	@Override
	public void resume()
	{
		basis.resume();
	}

	@Override
	public boolean isPaused()
	{
		return basis.isPaused();
	}

	@Override
	public void reset()
	{
		basis.reset();
	}

	@Override
	public boolean isReset()
	{
		return basis.isReset();
	}

	@Override
	public void timeChanged(long newTime)
	{
		basis.timeChanged(newTime);
	}

	@Override
	public void addPluginListener(PluginListener pluginListener)
	{
		// TODO: Create plugin listener array
	}

	@Override
	public void setStartTime(long newStartTime)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setOffset(long newPauseTime)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setGoing(boolean newGoing)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setPaused(boolean newPaused)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setReset(boolean newReset)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public long getPauseTime()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setPauseTime(long newPauseTime)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
