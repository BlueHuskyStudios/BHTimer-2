package org.bh.app.timer.delegation;

import java.awt.Component;
import org.bh.app.timer.BHTimerManager;
import org.bh.app.timer.gui.MiniTimerFrame;
import org.bh.app.timer.evt.MiniTimerListener;
import org.bh.app.timer.evt.PluginListener;
import org.bh.app.timer.evt.TimerPluginListener;
import org.bh.app.timer.BHTimerPlugin;

/**
 * MiniTimerDelegate, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-06
 */
public class MiniTimerDelegate extends WindowDelegate<MiniTimerFrame> implements BHTimerPlugin
{
	public static final byte USE_ON_CLOSE             = 0b0000_0001,
	                         USE_ON_MINIMIZE          = 0b0000_0010,
	                         USE_ON_CLOSE_OR_MINIMIZE = USE_ON_CLOSE | USE_ON_MINIMIZE,
	                         USE_NEVER                = 0b0000_0000,
							 
							 POS_TOP_LEFT     = 0b0000_0000,
							 POS_TOP_RIGHT    = 0b0000_0001,
							 POS_BOTTOM_RIGHT = 0b0000_0010,
							 POS_BOTTOM_LEFT  = 0b0000_0011;
	private BHTimerManager manager;
	
	public MiniTimerDelegate(BHTimerDelegate parent, BHTimerManager initManager)
	{
		basis = new MiniTimerFrame(this);
		manager = initManager;
	}

	public BHTimerManager getManager()
	{
		return manager;
	}

	public void addMiniTimerListener(MiniTimerListener newMiniTimerListener)
	{
		basis.addMiniTimerListener(newMiniTimerListener);
	}

	public void setCurrentTimerPlugin(BHTimerPlugin newTimerPlugin)
	{
		basis.setCurrentTimerPlugin(newTimerPlugin);
	}

	public BHTimerPlugin getCurrentTimerPlugin()
	{
		return basis.getCurrentTimerPlugin();
	}
	
	@Override
	public CharSequence getName()
	{
		return "MiniTimer";
	}

	@Override
	public Component asComponent()
	{
		return getComponent();
	}

	@Override
	public long getCurrentTime()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		return plugin != null ? plugin.getCurrentTime(): -1;
	}

	@Override
	public void update()
	{
		validate();
	}

	@Override
	public boolean isGoing()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		return plugin != null && plugin.isGoing();
	}

	@Override
	public void timeChanged(long newTime)
	{
		update();
	}

	@Override
	public void go()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.go();
	}

	@Override
	public void stop()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.stop();
	}

	@Override
	public void pause()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.pause();
	}

	@Override
	public void resume()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.resume();
	}

	@Override
	public boolean isPaused()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		return plugin == null || plugin.isPaused();
	}

	@Override
	public void reset()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.reset();
	}

	@Override
	public boolean isReset()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		return plugin == null || plugin.isReset();
	}

	@Override
	public long getStartTime()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		return plugin == null ? -1 : plugin.getStartTime();
	}

	@Override
	public long getOffset()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		return plugin == null ? -1 : plugin.getOffset();
	}

	@Override
	public long getPauseTime()
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		return plugin == null ? -1 : plugin.getOffset();
	}

	@Override
	public void addPluginListener(PluginListener pluginListener)
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.addPluginListener(pluginListener);
	}

	@Override
	public void setStartTime(long newStartTime)
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.setStartTime(newStartTime);
	}

	@Override
	public void setOffset(long newOffset)
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.setOffset(newOffset);
	}

	@Override
	public void setPauseTime(long newPauseTime)
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.setPauseTime(newPauseTime);
	}

	@Override
	public void setGoing(boolean newGoing)
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.setGoing(newGoing);
	}

	@Override
	public void setPaused(boolean newPaused)
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.setPaused(newPaused);
	}

	@Override
	public void setReset(boolean newReset)
	{
		BHTimerPlugin plugin = getCurrentTimerPlugin();
		if (plugin != null) plugin.setReset(newReset);
	}
}