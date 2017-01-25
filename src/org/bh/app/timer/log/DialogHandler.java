package org.bh.app.timer.log;

import bht.tools.util.StringPP;
import java.util.logging.Handler;
import java.util.logging.Level;
import static java.util.logging.Level.*;
import java.util.logging.LogRecord;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

/**
 * DialogHandler, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-13
 */
public class DialogHandler extends Handler
{
	private CharSequence appName;
	
	public DialogHandler(CharSequence initAppName)
	{
		appName = initAppName;
	}
	@Override
	public void publish(LogRecord record)
	{
		if (!getFilter().isLoggable(record))
			return;
		
		StringPP errorName = new StringPP(record.getThrown() == null ? null : record.getThrown().getClass().getSimpleName());
		
		if (record.getLevel().intValue() >= SEVERE.intValue())
		{
			if (errorName == null) errorName = new StringPP("Error");
			
			if (JOptionPane.showOptionDialog(
					null,
					record.getMessage() + "\r\n\r\n*** Quit " + appName + " for safety?",
					"A" + (errorName.startsWithVowel() ? 'n' : "") + " occurred! - " + appName,
					ERROR_MESSAGE,
					YES_NO_OPTION,
					null,
					new Object[]{"Quit " + appName + " for safety", "Continue using " + appName},
					0) == 0)
				System.exit(1);
		}
		else if (record.getLevel().intValue() >= WARNING.intValue())
		{
			if (errorName == null || errorName.isEmpty()) errorName = new StringPP("Warning");
			
			JOptionPane.showMessageDialog(null, record.getMessage(), errorName + " - " + appName, WARNING_MESSAGE);
		}
		else if (record.getLevel().intValue() >= INFO.intValue())
		{
			if (errorName == null || errorName.isEmpty()) errorName = new StringPP("Info");
			
			JOptionPane.showMessageDialog(null, record.getMessage(), errorName + " - " + appName, INFORMATION_MESSAGE);
		}
	}

	@Override
	public void flush()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void close() throws SecurityException
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
