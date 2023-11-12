package com.emrecellebi.text;

import com.emrecellebi.ICharSequenceBackedByArray;
import com.emrecellebi.ICharSequenceWithStringHash;
import com.emrecellebi.util.CharArrayUtil;
import com.emrecellebi.util.Strings;

public class CharArrayCharSequence implements ICharSequenceBackedByArray, ICharSequenceWithStringHash
{
	protected final char[] myChars;
	protected final int myStart;
	protected final int myEnd;
	private transient int hash;
	
	public CharArrayCharSequence(char... chars)
	{
		this(chars, 0, chars.length);
	}
	
	public CharArrayCharSequence(char[] chars, int start, int end)
	{
		if(start < 0 || end > chars.length || start > end)
			throw new IndexOutOfBoundsException("chars.length:" + chars.length + ", start:" + start + ", end:" + end); 
		this.myChars = chars;
		this.myStart = start;
		this.myEnd = end;
	}
	
	@Override
	public final char charAt(int index)
	{
		return this.myChars[index + this.myStart];
	}
	
	public boolean equals(Object anObject)
	{
		if(this == anObject) return true;
		if(anObject == null || getClass() != anObject.getClass() || length() != ((CharSequence)anObject).length()) return false; 
		return CharArrayUtil.regionMatches(this.myChars, this.myStart, this.myEnd, (CharSequence)anObject);
	}
	
	@Override
	public char[] getChars()
	{
		if(this.myStart == 0) return this.myChars;
		char[] chars = new char[length()];
		getChars(chars, 0);
		return chars;
	}
	
	@Override
	public void getChars(char[] dst, int dstOffset)
	{
		System.arraycopy(this.myChars, this.myStart, dst, dstOffset, length());
	}
	
	@Override
	public int hashCode()
	{
		int h = this.hash;
		if(h == 0) this.hash = h = Strings.stringHashCode((CharSequence)this, 0, length()); 
		return h;
	}
	
	@Override
	public final int length()
	{
		return this.myEnd - this.myStart;
	}
	
	public int readCharsTo(int start, char[] cbuf, int off, int len)
	{
		int readChars = Math.min(len, length() - start);
		if(readChars <= 0) return -1; 
		System.arraycopy(this.myChars, this.myStart + start, cbuf, off, readChars);
		return readChars;
	}
	
	@Override
	public CharSequence subSequence(int start, int end)
	{
		return (start == 0 && end == length()) ? (CharSequence)this : (CharSequence)new CharArrayCharSequence(this.myChars, this.myStart + start, this.myStart + end);
	}
	
	@Override
	public String toString()
	{
		return new String(this.myChars, this.myStart, this.myEnd - this.myStart);
	}
}