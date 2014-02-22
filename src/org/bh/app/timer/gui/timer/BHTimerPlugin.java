package org.bh.app.timer.gui.timer;

import org.bh.app.timer.gui.evt.TimerPluginListener;
import java.awt.Component;
import java.awt.PopupMenu;

/**
 * BHTimerPlugin, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-17
 */
public interface BHTimerPlugin extends TimerPluginListener
{
	/**
	 * Returns the name of this timer. <B>Example:</B> {@code Count-Up}
	 * @return the name of this timer
	 */
	public CharSequence getName();
	/**
	 * Returns this timer as a component, to be displayed in a large timer display frame
	 * @return this timer as a component
	 */
	public Component asComponent();
	/**
	 * Returns the current time of this timer, as nanoseconds from the 0 time. {@link Long#MIN_VALUE} means the timer is not currently active, and any other negative value means the timer has gone over the intended time.
	 * @return the current time of this timer
	 */
	public long getCurrentTime();

	/*
	 * Adds the given {@link TimerPluginListener} to the list of listeners to be fired when relevant
	 * @param timerPluginAdapter The {@link TimerPluginListener} to add
	 /
	public void addTimerPluginListener(TimerPluginListener timerPluginListener);*/

	/**
	 * Tells this timer that it's time to update.
	 */
	public void update();
	
	/**
	 * Commands the timer to begin.
	 */
	public void go();
	
	/**
	 * Commands the timer to stop.
	 */
	public void stop();
	
	/**
	 * Signifies whether this timer is going and needs updating.
	 * @return {@code true} iff the timer is going
	 */
	public boolean isGoing();
	
	/**
	 * Commands the timer to pause.
	 */
	public void pause();
	
	/**
	 * Commands the timer to resume normally from the last recorded time
	 */
	public void resume();
	
	/**
	 * Signifies whether the timer is paused. This should return {@code false} if {@link #isGoing()} returns {@code false}.
	 * @return {@code true} iff the timer is paused
	 */
	public boolean isPaused();
	
	/**
	 * Commands the timer to reset. After reset, it should be ready to begin again at a moment's notice.
	 */
	public void reset();
}
