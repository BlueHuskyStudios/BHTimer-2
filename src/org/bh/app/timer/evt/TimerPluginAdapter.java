package org.bh.app.timer.evt;

import org.bh.app.timer.evt.TimerPluginListener;

/**
 * TimerPluginAdapter, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * A naked implementation of {@link TimerPluginListener}
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-17
 */
public abstract class TimerPluginAdapter implements TimerPluginListener
{

	@Override
	public void timeChanged(long newTime){}
	
}
