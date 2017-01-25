package org.bh.app.timer.delegation;

import bht.tools.util.ArrayPP;
import bht.tools.util.save.StateSaverAdapter;
import bht.tools.util.save.StateSaverEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bh.app.timer.gui.BHTimerFrame;
import org.bh.app.timer.BHTimerManager;
import org.bh.app.timer.BHTimerPlugin;
import org.bh.app.timer.evt.MenuActionListener;
import org.bh.app.timer.util.SaveableRectangle;

/**
 * BHTimerDelegate, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-06
 */
public class BHTimerDelegate extends JFrameDelegate<BHTimerFrame>
{
	private BHTimerManager manager;
	private SaveableRectangle boundsSaveable;
	
	public BHTimerDelegate(BHTimerManager initManager)
	{
		basis = new BHTimerFrame(this);
		manager = initManager;
		boundsSaveable = new SaveableRectangle(getBounds(), "bigTimer.location");
		try
		{
			manager.getSettings().addSaveable(boundsSaveable);
			manager.getSettings().putState(boundsSaveable);
			setBounds(boundsSaveable.getState());
			manager.getSettings().addStateSaverListener(
				new StateSaverAdapter()
				{
					@Override
					public void stateLoaded(StateSaverEvent evt)
					{
						manager.getSettings().putState(boundsSaveable);
						setBounds(boundsSaveable.getState());
					}

					@Override
					public void stateSaving(StateSaverEvent evt)
					{
						boundsSaveable.setState(getBounds());
						
					}

				}
			);
		}
		catch (Throwable ex)
		{
			Logger.getGlobal().log(Level.SEVERE, "Could not attach big timer save settings", ex);
		}
	}
	
	public BHTimerManager getManager()
	{
		return manager;
	}

	public BHTimerDelegate registerPlugin(BHTimerPlugin newPlugin)
	{
		basis.registerPlugin(newPlugin);
		return this;
	}
	
	public BHTimerPlugin getCurrentTimerPlugin()
	{
		return basis.getCurrentTimerPlugin();
	}
	
	public BHTimerDelegate addMenuActionListener(MenuActionListener mal)
	{
//		menuActionListeners.add(mal);
		basis.addMenuActionListener(mal);
		return this;
	}
}
