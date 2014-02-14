package org.bh.app.timer.gui.delegation;

import bht.tools.util.save.SaveableByte;
import java.awt.Rectangle;
import org.bh.app.timer.gui.BHTimerManager;
import org.bh.app.timer.gui.MiniTimerApplet;
import org.bh.app.timer.gui.MiniTimerFrame;
import org.bh.app.timer.gui.evt.MiniTimerAdapter;
import org.bh.app.timer.gui.evt.MiniTimerListener;

/**
 * MiniTimerDelegate, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-06
 */
public class MiniTimerDelegate extends WindowDelegate<MiniTimerFrame>
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
}
