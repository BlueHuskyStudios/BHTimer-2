package org.bh.app.timer.evt;

import java.util.EventListener;

/**
 * TimerPluginListener, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * This is a barebones listener to help ensure it can be called hundreds of times a second without lagging the timer.
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-17
 */
public interface TimerPluginListener extends EventListener
{
	/**
	 * Called when the time has changed in the timer plugin. This is a barebones method to help ensure it can be called hundreds of times a second without lagging the timer.
	 * @param newTime the new time in the timer plugin
	 */
	public void timeChanged(long newTime);
}
