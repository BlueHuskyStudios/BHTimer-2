package org.bh.app.timer.delegation;

import org.bh.app.timer.BHTimerPlugin;
import org.bh.app.timer.evt.TimerPluginListener;
import java.awt.Component;
import org.bh.app.timer.evt.PluginListener;
import org.bh.app.timer.gui.timer.CountUpTimerPanel;
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
	private long startTime = -1, end = -1, offset = 0;
	private boolean paused = false, going = false;
	private byte resolution = Time.RES_DEFAULT;
	private String name = "Count-Up";
	
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
		return name;
	}

//	long START = 0;
	@Override
	public long getCurrentTime()
	{
		if (isGoing() && !isPaused())
		{
			end = System.currentTimeMillis();
			return ((end - startTime) - offset) * (long)Time.MILLISECOND_IN_NANOSECONDS;
		}
		return
				(startTime < 0
					? 0
					:
						(end < startTime
							? pauseTime - startTime
							: ((end - startTime) - offset)
						) * (long)Time.MILLISECOND_IN_NANOSECONDS
				)
		;
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
		startTime = System.currentTimeMillis();
		end = startTime;
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
		startTime = -1;
	}

	@Override
	public boolean isReset()
	{
		return going == false && startTime <= 0;
	}

	@Override
	public long getStartTime()
	{
		return startTime;
	}

	@Override
	public long getOffset()
	{
		return offset;// + (paused ? System.currentTimeMillis() - pauseTime : 0);
	}

	@Override
	public long getPauseTime()
	{
		return pauseTime;
	}

	@Override
	public void addPluginListener(PluginListener pluginListener)
	{
		
	}

	@Override
	public void setStartTime(long newStartTime)
	{
		startTime = newStartTime;
	}

	@Override
	public void setOffset(long newOffset)
	{
		offset = newOffset;
//		pauseTime = startTime + offset;
		System.out.println(
			"Offset set to " + Time.toString((long)(offset * Time.MILLISECOND_IN_NANOSECONDS), Time.RES_DEFAULT) + ";\r\n" + 
			"\tpauseTime is " + Time.toString(pauseTime, Time.RES_DEFAULT) + ";\r\n" +
			"\tstartTime is " + Time.toString(startTime, Time.RES_DEFAULT));
	}

	@Override
	public void setPauseTime(long newPauseTime)
	{
		pauseTime = newPauseTime;
	}

	@Override
	public void setGoing(boolean newGoing)
	{
		going = newGoing;
	}

	@Override
	public void setPaused(boolean newPaused)
	{
		paused = newPaused;
	}

	@Override
	public void setReset(boolean newReset)
	{
		if (newReset)
			reset();
	}
}
