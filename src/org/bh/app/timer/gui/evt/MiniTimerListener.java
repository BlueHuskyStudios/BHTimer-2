package org.bh.app.timer.gui.evt;

import java.util.EventListener;

/**
 * MiniTimerListener, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-13
 */
public interface MiniTimerListener extends EventListener
{
	public void expandButtonPressed(MiniTimerEvent evt);
	public void goButtonPressed(MiniTimerEvent evt);
	public void pauseButtonPressed(MiniTimerEvent evt);
	public void stopButtonPressed(MiniTimerEvent evt);
}
