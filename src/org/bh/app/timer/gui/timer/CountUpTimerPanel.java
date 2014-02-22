package org.bh.app.timer.gui.timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * CountUpTimerPanel, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-17
 */
public class CountUpTimerPanel extends CountTimerPanel implements BHTimerPluginPane
{
	private final BHTimerPlugin PARENT;
	
	public CountUpTimerPanel(BHTimerPlugin initParent)
	{
		super(initParent);
		PARENT = initParent;
		initGUI();
	}

	JLabel display;
	private void initGUI()
	{/*
		display = new JLabel("I'm a Count-Up Timer!");
		display.setFont(display.getFont().deriveFont(30f));
		add(display);*/
		
	}

	@Override
	public BHTimerPlugin asPlugin()
	{
		return PARENT;
	}
}
