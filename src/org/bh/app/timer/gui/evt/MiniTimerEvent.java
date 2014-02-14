package org.bh.app.timer.gui.evt;

import java.awt.event.ActionEvent;
import java.util.EventObject;

/**
 * MiniTimerEvent, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-13
 */
public class MiniTimerEvent extends EventObject
{
	private ActionEvent invoker;

	public MiniTimerEvent(ActionEvent invoker, Object source)
	{
		super(source);
		this.invoker = invoker;
	}

	public ActionEvent getInvoker()
	{
		return invoker;
	}
}
