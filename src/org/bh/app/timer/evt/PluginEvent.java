package org.bh.app.timer.evt;

import java.util.EventObject;
import org.bh.app.timer.BHTimerPlugin;

/**
 * PluginEvent, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-27
 */
public class PluginEvent extends EventObject
{
	public PluginEvent(BHTimerPlugin source)
	{
		super(source);
	}

	@Override
	public BHTimerPlugin getSource()
	{
		return (BHTimerPlugin)super.getSource();
	}
}
