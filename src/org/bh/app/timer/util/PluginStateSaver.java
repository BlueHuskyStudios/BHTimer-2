package org.bh.app.timer.util;

import bht.tools.util.save.SaveableBoolean;
import bht.tools.util.save.SaveableLong;
import bht.tools.util.save.SaveableString;
import bht.tools.util.save.StateSaver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bh.app.timer.Main;
import org.bh.app.timer.BHTimerPlugin;

/**
 * PluginStateSaver, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 *
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-27
 */
public class PluginStateSaver extends StateSaver
{
	public SaveableLong
		start,
		pauseTime,
		offset;
	public SaveableBoolean
		going,
		paused,
		reset;
	public BHTimerPlugin plugin;
	
	public PluginStateSaver(BHTimerPlugin initPlugin)
	{
		super(Main.APP_NAME, "/plugins/" + initPlugin.getName(), true);
		plugin = initPlugin;
		CharSequence name = plugin.getName();
		try
		{
			addSaveable(start     = new SaveableLong(initPlugin.getStartTime(), "plugin." + name + ".start"));
			addSaveable(pauseTime = new SaveableLong(initPlugin.getPauseTime(), "plugin." + name + ".pauseTime"));
			addSaveable(offset    = new SaveableLong(initPlugin.getOffset(), "plugin." + name + ".offset"));
			
			addSaveable(going     = new SaveableBoolean(initPlugin.isGoing(),   "plugin." + name + ".going"));
			addSaveable(paused    = new SaveableBoolean(initPlugin.isPaused(),  "plugin." + name + ".paused"));
			addSaveable(reset     = new SaveableBoolean(initPlugin.isReset(),   "plugin." + name + ".reset"));
		}
		catch (IOException ex)
		{
			Logger.getGlobal().log(Level.WARNING, "Could not load the savestate of " + name, ex);
		}
	}

	public void prepareStatesForSaving()
	{
		start.setState(plugin.getStartTime());
		pauseTime.setState(plugin.getPauseTime());
		offset.setState(plugin.getOffset());
		System.out.println(offset);
		
		going.setState(plugin.isGoing());
		paused.setState(plugin.isPaused());
		reset.setState(plugin.isReset());
	}

	public void putPluginStates()
	{
		putAllStates();
		plugin.setStartTime(start.getState());
		plugin.setPauseTime(pauseTime.getState());
		plugin.setOffset(offset.getState());
		
		plugin.setGoing(going.getState());
		plugin.setPaused(paused.getState());
		plugin.setReset(reset.getState());
		plugin.update();
//		plugin.asComponent().validate();
	}
}
