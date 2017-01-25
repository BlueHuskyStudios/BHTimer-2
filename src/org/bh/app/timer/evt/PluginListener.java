package org.bh.app.timer.evt;

import java.util.EventListener;

/**
 * PluginListener, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-27
 */
public interface PluginListener extends EventListener
{
	public void timerStarted(PluginEvent evt);
	public void timerRestarted(PluginEvent evt);
	public void timerPaused(PluginEvent evt);
	public void timerResumed(PluginEvent evt);
	public void timerStopped(PluginEvent evt);
	public void timerReset(PluginEvent evt);
}
