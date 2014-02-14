package org.bh.app.timer;

import bht.tools.comps.BHCompUtilities;
import bht.tools.fx.LookAndFeelChanger;
import bht.tools.misc.Argument;
import bht.tools.util.StringPP;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.bh.app.timer.gui.BHTimerManager;
import org.bh.app.timer.log.DialogHandler;

/**
 * Main, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2013
 *
 * @author Supuhstar of Blue Husky Programming
 * @version 1.0.0
 * @since 2013-08-08
 */
public class Main
{
	/**
	 * Represents the argument <CODE>{@value}</CODE>. By default, it activates developer mode. However, it can be followed by positive or negative keyword to forve developer mode on or off.
	 * @see #setDeveloperMode(boolean) 
	 */
	public static final String ARG_DEV = "-dev";
	public static final String APP_NAME = "Blue Husky's Timer 2";
	public static final String APP_ABBR = new StringPP(APP_NAME).toAbbreviation(true)+"";
	public static final String VERSION = "2.0.0\u03BB";
	
	private static final Logger LOGGER = Logger.getLogger(Main.class.getCanonicalName());
	private static boolean devMode = false;
	
	static
	{
		LookAndFeelChanger.setLookAndFeel(LookAndFeelChanger.SYSTEM);
		Logger.getGlobal().setLevel(Level.ALL);
		Logger.getGlobal().setFilter(new Filter(){@Override public boolean isLoggable(LogRecord record){return true;}});
		{
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setFilter(new Filter()
			{
				@Override
				public boolean isLoggable(LogRecord record)
				{
					return true;
				}
			});
			Logger.getGlobal().addHandler(consoleHandler);
		}
		{
			DialogHandler dialogHandler = new DialogHandler(APP_NAME);
			dialogHandler.setFilter(new Filter()
			{
				@Override public boolean isLoggable(LogRecord record)
				{
					return record.getLevel().intValue() >= Level.INFO.intValue();
				}
			});
			Logger.getGlobal().addHandler(dialogHandler);
		}
		try
		{
			FileHandler fileHandler = new FileHandler("log");
			fileHandler.setFilter(new Filter()
			{
				@Override
				public boolean isLoggable(LogRecord record)
				{
					return record.getLevel().intValue() >= Level.FINE.intValue();
				}
			});
			Logger.getGlobal().addHandler(fileHandler);
		}
		catch (Throwable ex)
		{
			Logger.getGlobal().log(Level.SEVERE, "Could not initialize log file!", ex);
		}
		
		BHCompUtilities.setUsesOSMenuBar(true, APP_NAME);
	}

	/**
	 * @param args the command line arguments.<BR/>
	 * <B>Accepts:</B>
	 * <UL>
	 *	<LI><B>{@code -dev}</B> - see {@link #ARG_DEV}
	 * </UL>
	 */
	public static void main(String[] args)
	{
		parseArgs(args);
		BHTimerManager bhTimer = new BHTimerManager();
		bhTimer.setMainWindowVisible(true);
	}

	private static void parseArgs(CharSequence[] args)
	{
		for (int arg = 0; arg < args.length; arg++)
		{
			if (args[arg] != null && ARG_DEV.equalsIgnoreCase(args[arg].toString()))
			{
				if (arg < args.length - 1 && Argument.DefaultArg.BOOLEAN.matches(args[++arg]))
					setDeveloperMode(Argument.DefaultArg.POSITIVE.matches(args[arg]) ? true : !Argument.DefaultArg.NEGATIVE.matches(args[arg]));
			}
		}
	}

	public static void setDeveloperMode(boolean b)
	{
		devMode = b;
		
		LOGGER.setLevel(devMode ? Level.ALL : Level.WARNING);
		LOGGER.log(Level.INFO, "{0}ctivated development mode", (b ? "A" : "Dea"));
	}
}
