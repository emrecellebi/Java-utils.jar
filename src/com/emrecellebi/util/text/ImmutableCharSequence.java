package com.emrecellebi.util.text;

public abstract class ImmutableCharSequence implements CharSequence
{
	public static CharSequence asImmutable(CharSequence cs)
	{
		return isImmutable(cs) ? cs : cs.toString();
	}

	private static boolean isImmutable(CharSequence cs)
	{
		return (cs instanceof ImmutableCharSequence || (cs instanceof CharSequenceSubSequence && isImmutable(((CharSequenceSubSequence)cs).getBaseSequence())));
	}
	
	public abstract ImmutableCharSequence concat(CharSequence paramCharSequence);
	public abstract ImmutableCharSequence insert(int paramInt, CharSequence paramCharSequence);
	public abstract ImmutableCharSequence delete(int paramInt1, int paramInt2);
	public abstract ImmutableCharSequence subtext(int paramInt1, int paramInt2);
	public abstract String toString();
}