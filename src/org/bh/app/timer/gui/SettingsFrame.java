package org.bh.app.timer.gui;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 * SettingsFrame, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 *
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-03-03
 */
public class SettingsFrame extends JFrame
{

	public SettingsFrame() throws HeadlessException
	{
		setContentPane(new SettingsApplet());
		pack();
	}

}
