package org.bh.app.timer.util;

import bht.tools.util.save.AbstractSaveable;
import bht.tools.util.save.Saveable;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SaveableColor, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-27
 */
public class SaveableColor extends AbstractSaveable<Color>
{
	public SaveableColor(Color initState, CharSequence initName)
	{
		super(initState, initName);
	}

	@Override
	public CharSequence getStringToSave()
	{
		return
			"#" +
			getHexForByte(s.getRed()) +
			getHexForByte(s.getGreen()) +
			getHexForByte(s.getBlue()) +
			getHexForByte(s.getAlpha());
	}

	@Override
	public Saveable loadFromSavedString(CharSequence savedString)
	{
		Pattern hexPattern = Pattern.compile("[0-9a-f]{2}", Pattern.CASE_INSENSITIVE); // this pattern matches pairs of hex values: http://refiddle.com/h6i
		String[] byteStrings = new String[4];
		Matcher hexMatcher = hexPattern.matcher(savedString);
		short[] bytes = new short[byteStrings.length]; // since Java doesn't have unsigned bytes
		for (int i = 0; i < byteStrings.length && hexMatcher.find(); i++)
			bytes[i] = Short.parseShort(hexMatcher.group(), 16); // split the savedString into the bytes it represents, then parse the hex strings into bytes. This inherently ignores the initial #
		s = new Color(bytes[0], bytes[1], bytes[2], bytes[3]); // set the color to the bytes represented in the string
		return this;
	}

	public static String getHexForByte(int colorByte)
	{
		return
			(colorByte < 0x10 ? "0" : "") + 
			Integer.toHexString(colorByte);
	}
}
