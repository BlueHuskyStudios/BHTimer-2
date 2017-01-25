package org.bh.app.timer.evt;

/**
 * PluginAdapter, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-27
 */
public class PluginAdapter implements PluginListener
{
	@Override public void timerStarted(PluginEvent evt){}
	@Override public void timerRestarted(PluginEvent evt){}
	@Override public void timerPaused(PluginEvent evt){}
	@Override public void timerResumed(PluginEvent evt){}
	@Override public void timerStopped(PluginEvent evt){}
	@Override public void timerReset(PluginEvent evt){}
	}
