package org.bh.app.timer;

import org.bh.app.timer.evt.TimerPluginListener;
import java.awt.Component;
import java.awt.PopupMenu;
import org.bh.app.timer.evt.PluginListener;

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
	 * Returns the current time of this timer, as nanoseconds from the 0 time. A negative value means the timer is not
	 * currently active, and any other negative value means the timer has gone over the intended time.
	 * @return the current time of this timer
	 */
	public long getCurrentTime();
	
	/**
	 * Returns the time this timer was started, as milliseconds from the Unix Epoch.
	 * @return the time this timer was started
	 */
	public long getStartTime();
	
	/**
	 * Returns the amount of offset caused by pausing the timer, as nanoseconds from timer start. A negative value signifies
	 * this timer has not been paused
	 * @return the offset for this timer
	 */
	public long getOffset();
	
	/**
	 * Returns the time at which the timer was last paused, as milliseconds from Unix Epoch. A negative value signifies
	 * this timer has not been paused
	 * @return the time this timer was last paused
	 */
	public long getPauseTime();

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

	/**
	 * Signifies whether the timer has been reset to its initial state, ready to be run again
	 * @return {@code true} iff the timer is reset
	 */
	public boolean isReset();

	public void addPluginListener(PluginListener pluginListener);

	/**
	 * <strong>This should be used solely for state saving and loading.</strong> This directly changes the start time.
	 * @param newStartTime the new start time
	 * @deprecated This should be used solely for state saving and loading.
	 */
	public void setStartTime(long newStartTime);

	/**
	 * <strong>This should be used solely for state saving and loading.</strong> This directly changes the offset.
	 * @param newPauseTime the new offset
	 * @deprecated This should be used solely for state saving and loading.
	 */
	public void setOffset(long newOffset);

	/**
	 * <strong>This should be used solely for state saving and loading.</strong> This directly changes the pause timr.
	 * @param newPauseTime the new pause time
	 * @deprecated This should be used solely for state saving and loading.
	 */
	public void setPauseTime(long newPauseTime);

	/**
	 * <strong>This should be used solely for state saving and loading.</strong> This directly changes the going state.
	 * @param newGoing the new going state
	 * @deprecated This should be used solely for state saving and loading.
	 */
	public void setGoing(boolean newGoing);

	/**
	 * <strong>This should be used solely for state saving and loading.</strong> This directly changes the pause state.
	 * @param newPaused the new pause state
	 * @deprecated This should be used solely for state saving and loading.
	 */
	public void setPaused(boolean newPaused);

	/**
	 * <strong>This should be used solely for state saving and loading.</strong> This directly changes the reset state.
	 * @param newReset the new reset state
	 * @deprecated This should be used solely for state saving and loading.
	 */
	public void setReset(boolean newReset);
}
