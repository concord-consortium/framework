package org.concord.framework.data;

public class DecorationColor
{
	public short r;
	public short g;
	public short b;

	public DecorationColor(int red, int green, int blue)
	{
		r = (short)red;
		g = (short)green;
		b = (short)blue;
	}

	public void copyInto(DecorationColor c)
	{
		c.r = r;
		c.g = g;
		c.b = b;
	}
}
