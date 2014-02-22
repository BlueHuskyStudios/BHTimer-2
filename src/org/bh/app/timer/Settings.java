package org.bh.app.timer;

import bht.tools.util.save.SaveableByte;
import bht.tools.util.save.SaveableDouble;
import bht.tools.util.save.StateSaver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bh.app.timer.BHTimerManager.*;
import static org.bh.app.timer.gui.delegation.MiniTimerDelegate.*;

/**
 * Settings, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 *
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-06
 */
public class Settings extends StateSaver
{
	public SaveableByte
		miniTimerUseCase,
		miniTimerPosition,
		displayState;
	public SaveableDouble
		animationDuration,
		animationFPS;

	@SuppressWarnings("LoggerStringConcat")
	public Settings()
	{
		super(Main.APP_NAME, false);
		try
		{
			addSaveable(miniTimerUseCase = new SaveableByte(USE_ON_CLOSE_OR_MINIMIZE, "minitimer.useCase"));
			addSaveable(miniTimerPosition = new SaveableByte(POS_BOTTOM_RIGHT, "minitimer.position"));
			addSaveable(displayState = new SaveableByte(STATE_ONLY_BIG_TIMER, "manager.displayState"));
			addSaveable(animationDuration = new SaveableDouble(1d, "animation.duration"));
			addSaveable(animationFPS = new SaveableDouble(30d, "animation.fps"));
			loadState();
			super.putAllStates();
			setShouldAutoSaveLoad(true);
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						saveState();
					}
					catch (Throwable ex)
					{
						Logger.getGlobal().log(Level.SEVERE, "Could not save the file!", ex);
					}
				}
			}, "State Saver shutdown handler"));
			Logger.getGlobal().log(Level.CONFIG, "Settings successfully initialized to " + getSaveFile());
		}
		catch (Throwable ex)
		{
			Logger.getGlobal().log(Level.SEVERE, "Could not save/load settings!", ex);
		}
	}

	@Override
	public void saveState() throws IOException
	{
		super.saveState();
		Logger.getGlobal().log(Level.CONFIG, "Settings successfully saved to " + getSaveFile());
	}
	
}
