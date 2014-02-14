package org.bh.app.timer.util;

import bht.tools.util.save.AbstractSaveable;
import bht.tools.util.save.Saveable;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * SaveableRectangle, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-13
 */
public class SaveableRectangle extends AbstractSaveable<Rectangle>
{

	public SaveableRectangle(Rectangle initState, CharSequence initName)
	{
		super(initState, initName);
	}

	@Override
	public CharSequence getStringToSave()
	{
		return s.x + " " + s.y + " " + s.width + " " + s.height;
	}

	@Override
	public Saveable loadFromSavedString(CharSequence savedString)
	{
		String[] digits = Pattern.compile("\\s+").split(savedString);
		s.x = Integer.parseInt(digits[0]);
		s.y = Integer.parseInt(digits[1]);
		s.width = Integer.parseInt(digits[2]);
		s.height = Integer.parseInt(digits[3]);
		return null;
	}
	
	public static void main(String... s)
	{
		System.out.println(Arrays.toString(Pattern.compile("\\s+").split("5 20 512 512")));
	}
}
