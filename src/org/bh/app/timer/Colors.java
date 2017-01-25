package org.bh.app.timer;

import bht.tools.util.save.StateSaver;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bh.app.timer.util.SaveableColor;

/**
 * Colors, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * This is a singleton class; instead of calling the constructor, you must call {@link #getInstance()}.
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-27
 */
public class Colors extends StateSaver
{
	public SaveableColor go, pause, stop;
	private Colors()
	{
		super(Main.APP_NAME, "colors", true);
		try
		{
			addSaveable(go    = new SaveableColor(new Color(34, 187, 119), "button.go.color"));
			addSaveable(pause = new SaveableColor(new Color(34, 119, 187), "button.pause.color"));
			addSaveable(stop  = new SaveableColor(new Color(187, 34, 34),  "button.stop.color"));
		}
		catch (IOException ex)
		{
			Logger.getGlobal().log(Level.WARNING, "Could not initialize color variables. Items may render plainly or inconsistently.", ex);
		}
	}
	
	/**
	 * The effective constructor of this singleton class. Use this to retrieve a new object
	 */
	public static Colors getInstance()
	{
		return ColorsHolder.INSTANCE;
	}
	
	private static class ColorsHolder
	{
		private static final Colors INSTANCE = new Colors();
	}
 }
