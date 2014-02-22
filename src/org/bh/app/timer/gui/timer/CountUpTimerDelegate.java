package org.bh.app.timer.gui.timer;

import org.bh.app.timer.gui.evt.TimerPluginListener;
import bht.tools.util.ArrayPP;
import java.awt.Component;
import org.bh.app.timer.util.Time;

/**
 * CountUpTimerDelegate, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * A default plugin for BH Timer 2, allowing the user to track the amount of time that has passed.
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-17
 */
public class CountUpTimerDelegate implements BHTimerPlugin, TimerPluginListener
{
	private long start = -1, end = -1, offset = 0;
	private boolean paused = false, going = false;
	private byte resolution = Time.RES_DEFAULT;
	
	private final CountUpTimerPanel basis;
	public CountUpTimerDelegate()
	{
		basis = new CountUpTimerPanel(this);
	}

	@Override
	public Component asComponent()
	{
		return basis;
	}

	@Override
	public CharSequence getName()
	{
		return "Count-Up";
	}

	long START = 0;
	@Override
	public long getCurrentTime()
	{
		if (isGoing() && !isPaused())
		{
			end = System.currentTimeMillis();
			return ((end - start) - offset) * 1_000_000;
		}
		return start < 0 ? 0 : ((end - start) - offset) * 1_000_000;
	}

	@Override
	public void timeChanged(long newTime)
	{
//		basis.setDisplay(Time.toString(newTime, Time.RES_ALL));
		update();
	}

	@Override
	public void update()
	{
		basis.setDisplay(Time.toString(getCurrentTime(), resolution));
	}

	@Override
	public boolean isGoing()
	{
		return going;
	}

	@Override
	public void go()
	{
		going = true;
		start = System.currentTimeMillis();
		end = start;
		offset = 0;
	}

	@Override
	public void stop()
	{
		going = false;
	}

	private long pauseTime;
	@Override
	public void pause()
	{
		paused = true;
		pauseTime = System.currentTimeMillis();
	}

	@Override
	public void resume()
	{
		paused = false;
		offset += System.currentTimeMillis() - pauseTime;
	}

	@Override
	public boolean isPaused()
	{
		return paused;
	}

	@Override
	public void reset()
	{
		going = false;
		start = -1;
	}
}
