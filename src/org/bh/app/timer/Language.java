package org.bh.app.timer;

import bht.tools.util.save.SaveableString;
import bht.tools.util.save.StateSaver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Language, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * This is a singleton class; instead of calling the constructor, you must call {@link #getInstance()}.
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-27
 */
public class Language extends StateSaver
{
	public SaveableString
		go, restart,
		pause, resume,
		stop, reset,
	
		exit,
		view,
			looksAndFeels,
		tools,
			settings,
	
		generalSettings,
			generalSettingsTip,
		miniTimerSettings,
			miniTimerSettingsTip;
	
	
	@SuppressWarnings("LoggerStringConcat")
	private Language()
	{
		super(Main.APP_NAME, "language", true);
		try
		{
			addSaveable(go      = new SaveableString("go"     , "button.go.text"));
			addSaveable(restart = new SaveableString("restart", "button.restart.text"));
			addSaveable(pause   = new SaveableString("pause"  , "button.pause.text"));
			addSaveable(resume  = new SaveableString("resume" , "button.resume.text"));
			addSaveable(stop    = new SaveableString("stop"   , "button.stop.text"));
			addSaveable(reset   = new SaveableString("reset"  , "button.reset.text"));
			
			addSaveable(exit          = new SaveableString("exit"            , "menu.exit.text"));
			addSaveable(view          = new SaveableString("view"            , "menu.view.text"));
			addSaveable(looksAndFeels = new SaveableString("looks and feels" , "menu.view.looksAndFeels.text"));
			addSaveable(tools         = new SaveableString("tools"           , "menu.tools.text"));
			addSaveable(settings      = new SaveableString("settings"        , "menu.tools.settings.text"));
			
			addSaveable(generalSettings      = new SaveableString("general" , "settings.general.text"));
			addSaveable(generalSettingsTip   = new SaveableString("settings for the general application" , "settings.general.tip"));
			addSaveable(miniTimerSettings    = new SaveableString("mini timer" , "settings.miniTimer.text"));
			addSaveable(miniTimerSettingsTip = new SaveableString("settings for the mini timer" , "settings.miniTimer.tip"));
			
			Logger.getGlobal().log(Level.CONFIG, "Initialized language save state to " + getSaveFile());
		}
		catch (IOException ex)
		{
			Logger.getGlobal().log(Level.WARNING, "Could not initialize language variables. Text might not work properly.", ex);
		}
	}
	
	/**
	 * The effective constructor of this singleton class. Use this to retrieve a new object
	 */
	public static Language getInstance()
	{
		return LanguageHolder.INSTANCE;
	}
	
	private static class LanguageHolder
	{
		private static final Language INSTANCE = new Language();
	}
 }
