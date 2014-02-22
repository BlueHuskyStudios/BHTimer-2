package org.bh.app.timer.util;

import bht.tools.util.math.Numbers;

/**
 * Time, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-17
 */
public class Time
{
	/** Signifies that leading {@code 0} times should be trimmed. */
	public static final byte TRIM        = (byte) 0b1000_0000;
	/** Signifies that the number of years should be displayed. */
	public static final byte YEAR        = 0b0100_0000;
	/** Signifies that the number of days should be displayed. */
	public static final byte DAY         = 0b0010_0000;
	/** Signifies that the number of hours should be displayed. */
	public static final byte HOUR        = 0b0001_0000;
	/** Signifies that the number of minutes should be displayed. */
	public static final byte MINUTE      = 0b0000_1000;
	/** Signifies that the number of seconds should be displayed. */
	public static final byte SECOND      = 0b0000_0100;
	/** Signifies that the number of milliseconds should be displayed. */
	public static final byte MILLISECOND = 0b0000_0010;
	/** Signifies that the number of nanoseconds should be displayed. */
	public static final byte NANOSECOND  = 0b0000_0001;
	
	/** The default set of resolution flags. This signifies that The time should be trimmed and that all but nanoseconds will be displayed. It's the equivalent of {@code Time.TRIM | Time.RES_ALL & ~Time.NANOSECOND} */
	public static final byte RES_DEFAULT = (byte)(Time.TRIM | Time.RES_ALL & ~Time.NANOSECOND);
	/** represents that all resolutions should be used. This is the equivalent of {@code YEAR | DAY | HOUR | MINUTE | SECOND | MILLISECOND | NANOSECOND} */
	public static final byte RES_ALL     = (byte)(YEAR | DAY | HOUR | MINUTE | SECOND | MILLISECOND | NANOSECOND);
	
	public static final double
		YEAR_IN_DAYS = 365.24219,
		DAY_IN_HOURS = 24,
		HOUR_IN_MINUTES = 60,
		MINUTE_IN_SECONDS = 60,
		SECOND_IN_MILLISECONDS = 1000,
		
		MILLISECOND_IN_NANOSECONDS = 1_000_000,
		SECOND_IN_NANOSECONDS = SECOND_IN_MILLISECONDS * MILLISECOND_IN_NANOSECONDS,
		MINUTE_IN_NANOSECONDS = MINUTE_IN_SECONDS * SECOND_IN_NANOSECONDS,
		HOUR_IN_NANOSECONDS = HOUR_IN_MINUTES * MINUTE_IN_NANOSECONDS,
		DAY_IN_NANOSECONDS = DAY_IN_HOURS * HOUR_IN_NANOSECONDS,
		YEAR_IN_NANOSECONDS = YEAR_IN_DAYS * DAY_IN_NANOSECONDS;
	
	/**
	 * Converts the given number of nanoseconds from timer 0 to a String in the format
	 * {@code YYY:DDD:HH:MM:SS:mmm:nnnnnn}
	 * where all values have leading zeroes. You can choose which values are used with the resFlags, which can be combined bitwise.
	 * @param timeStamp the number of <B>nano</B>seconds away from timer 0
	 * @param resFlags the flags
	 * @return a {@link String} representing the given timestamp at the given resolutions
	 */
	public static String toString(long timeStamp, byte resFlags)
	{
		int temp;
		byte trimmed = (byte)~resFlags; // start by declaring that all unused resolutions have been trimmed
		StringBuilder sb = new StringBuilder();
		if ((resFlags & YEAR) != 0)
		{
			temp = (int) (timeStamp / YEAR_IN_NANOSECONDS);
			timeStamp -= temp * YEAR_IN_NANOSECONDS;
			if ((resFlags & TRIM) != 0 && temp == 0)
			{
				trimmed |= YEAR;
			}
			else
			{
				sb.append(temp);
				if ((resFlags & ~YEAR) != 0)
					sb.append(':');
			}
		}
		if ((resFlags & DAY) != 0)
		{
			temp = (int) (timeStamp / DAY_IN_NANOSECONDS);
			timeStamp -= temp * DAY_IN_NANOSECONDS;
			if ((resFlags & TRIM) != 0 && ((trimmed & YEAR) != 0) && temp == 0)
			{
				trimmed |= DAY;
			}
			else
			{
				sb.append((resFlags & YEAR) != 0 ? Numbers.lenFmt(temp, 3) : temp);
				if ((resFlags & ~(YEAR | DAY)) != 0)
					sb.append(':');
			}
		}
		if ((resFlags & HOUR) != 0)
		{
			temp = (int) (timeStamp / HOUR_IN_NANOSECONDS);
			timeStamp -= temp * HOUR_IN_NANOSECONDS;
			if ((resFlags & TRIM) != 0 && ((trimmed & DAY) != 0) && temp == 0)
			{
				trimmed |= HOUR;
			}
			else
			{
				sb.append((resFlags & (YEAR | DAY)) != 0 ? Numbers.lenFmt(temp, 2) : temp);
				if ((resFlags & ~(YEAR | DAY | HOUR)) != 0)
					sb.append(':');
			}
		}
		if ((resFlags & MINUTE) != 0)
		{
			temp = (int) (timeStamp / MINUTE_IN_NANOSECONDS);
			timeStamp -= temp * MINUTE_IN_NANOSECONDS;
			if ((resFlags & TRIM) != 0 && ((trimmed & HOUR) != 0) && temp == 0)
			{
				trimmed |= MINUTE;
			}
			else
			{
				sb.append((resFlags & (YEAR | DAY | HOUR)) != 0 ? Numbers.lenFmt(temp, 2) : temp);
				if ((resFlags & (SECOND | MILLISECOND | NANOSECOND)) != 0)
					sb.append(':');
			}
		}
		if ((resFlags & SECOND) != 0)
		{
			temp = (int) (timeStamp / SECOND_IN_NANOSECONDS);
			timeStamp -= temp * SECOND_IN_NANOSECONDS;
			if ((resFlags & TRIM) != 0 && ((trimmed & MINUTE) != 0) && temp == 0)
			{
				trimmed |= SECOND;
			}
			else
			{
				sb.append((resFlags & ~(SECOND | MILLISECOND | NANOSECOND)) != 0 ? Numbers.lenFmt(temp, 2) : temp);
				if ((resFlags & (MILLISECOND | NANOSECOND)) != 0)
					sb.append(':');
			}
		}
		if ((resFlags & MILLISECOND) != 0)
		{
			temp = (int) (timeStamp / MILLISECOND_IN_NANOSECONDS);
			timeStamp -= temp * MILLISECOND_IN_NANOSECONDS;
			if ((resFlags & TRIM) != 0 && ((trimmed & SECOND) != 0) && temp == 0)
			{
				trimmed |= MILLISECOND;
			}
			else
			{
				sb.append((resFlags & ~(MILLISECOND | NANOSECOND)) != 0 ? Numbers.lenFmt(temp, 3) : temp);
				if ((resFlags & NANOSECOND) != 0)
					sb.append(':');
			}
		}
		if ((resFlags & NANOSECOND) != 0)
		{
			sb.append((resFlags & ~(NANOSECOND)) != 0 ? Numbers.lenFmt(timeStamp, 6) : timeStamp);
		}
		return sb.toString();

		
		
		/* Archived 2014-02-19 (1.0.0) by Kyli Rouge - Old, beautiul, and dysfunctional
		return ""+
			((resFlags & YEAR) != 0
				? (long)(timeStamp / YEAR_IN_NANOSECONDS) + "y"
					+ ((resFlags & ~YEAR) != 0
						? ":"
						: "")
				: "")
			+ ((resFlags & DAY) != 0
				? (long)(timeStamp % DAY_IN_NANOSECONDS) + "d"
					+ ((resFlags & ~(YEAR | DAY)) != 0
						? ":"
						: "")
				: "")
			+ ((resFlags & HOUR) != 0
				? (long)(timeStamp % HOUR_IN_NANOSECONDS) + "h"
					+ ((resFlags & ~(YEAR | DAY | HOUR)) != 0
						? ":"
						: "")
				: "")
			+ ((resFlags & MINUTE) != 0
				? (long)(timeStamp % MINUTE_IN_NANOSECONDS) + "M"
					+ ((resFlags & ~(YEAR | DAY | HOUR | MINUTE)) != 0
						? ":"
						: "")
				: "")
			+ ((resFlags & SECOND) != 0
				? (long)(timeStamp % SECOND_IN_NANOSECONDS) + "s"
					+ ((resFlags & (MILLISECOND | NANOSECOND)) != 0
						? ":"
						: "")
				: "")
			+ ((resFlags & MILLISECOND) != 0
				? (long)(timeStamp % MILLISECOND_IN_NANOSECONDS) + "m"
					+ ((resFlags & NANOSECOND) != 0
						? ":"
						: "")
				: "")
			+ ((resFlags & NANOSECOND) != 0
				? extract(timeStamp, NANOSECOND) + "n"
				: "")
			;*/
	}

	/**
	 * Creates a timestamp with the given resolutions
	 * @param years the number of years
	 * @param days the number of days
	 * @param hours the number of hours
	 * @param minutes the number of minutes
	 * @param seconds the number of seconds
	 * @param milliseconds the number of milliseconds
	 * @param nanoseconds the number of nanoseconds
	 * @return the number of nanoseconds form 0 represented by the given resolutions
	 */
	public static long getTimestamp(int years, short days, byte hours, byte minutes, byte seconds, short milliseconds, int nanoseconds)
	{
		return (long)(
			years * YEAR_IN_NANOSECONDS +
			days * DAY_IN_NANOSECONDS +
			hours * HOUR_IN_NANOSECONDS +
			minutes * MINUTE_IN_NANOSECONDS +
			seconds * SECOND_IN_NANOSECONDS +
			milliseconds * MILLISECOND_IN_NANOSECONDS +
			nanoseconds
		);
	}
}
