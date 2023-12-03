package com.emrecellebi.util.text;

import com.emrecellebi.openapi.util.text.Strings;
import com.emrecellebi.openapi.util.text.ICharSequenceWithStringHash;
import com.emrecellebi.util.text.ICharArrayExternalizable;
import com.emrecellebi.util.text.CharArrayUtil;

public class CharSequenceSubSequence implements ICharArrayExternalizable, ICharSequenceWithStringHash
{
	private final CharSequence myChars;
	private final int myStart;
	private final int myEnd;
	private transient int hash;
	
	public CharSequenceSubSequence(CharSequence chars)
	{
		this(chars, 0, chars.length());
	}
	
	public CharSequenceSubSequence(CharSequence chars, int start, int end)
	{
		if(start < 0 || end > chars.length() || start > end)
			throw new IndexOutOfBoundsException("chars sequence.length:" + chars.length() + ", start:" + start + ", end:" + end); 
		this.myChars = chars;
		this.myStart = start;
		this.myEnd = end;
	}
	
	CharSequence getBaseSequence()
	{
		return this.myChars;
	}
	
	@Override
	public final char charAt(int index)
	{
		return this.myChars.charAt(index + this.myStart);
	}
	
	@Override
	public void getChars(int start, int end, char[] dest, int destPos)
	{
		assert end - start <= this.myEnd - this.myStart;
		CharArrayUtil.getChars(this.myChars, dest, start + this.myStart, destPos, end - start);
	}
	
	@Override
	public int hashCode()
	{
		int h = this.hash;
		if(h == 0) this.hash = h = Strings.stringHashCode(this, 0, length()); 
		return h;
	}
	
	@Override
	public int length()
	{
		return this.myEnd - this.myStart;
	}
	
	@Override
	public CharSequence subSequence(int start, int end)
	{
		if(start == this.myStart && end == this.myEnd) return this;
		return new CharSequenceSubSequence(this.myChars, this.myStart + start, this.myStart + end);
	}
	
	@Override
	public String toString()
	{
		return new String(CharArrayUtil.fromSequence(this.myChars, this.myStart, this.myEnd));
	}
}