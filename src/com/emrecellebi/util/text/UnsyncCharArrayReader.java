package com.emrecellebi.util.text;

import java.io.Reader;

public class UnsyncCharArrayReader extends Reader
{
	private final char[] myText;
	private final int myLength;
	private int myCurPos;
	
	public UnsyncCharArrayReader(String str)
	{
		this(str.toCharArray(), 0, str.length());
	}
	
	public UnsyncCharArrayReader(char[] text, int offset, int length)
	{
		this.myText = text;
		this.myLength = length;
		this.myCurPos = offset;
	}
	
	@Override
	public void close(){}
	
	@Override
	public int read()
	{
		if(this.myCurPos >= this.myLength) return -1; 
		return this.myText[this.myCurPos++];
	}
	
	@Override
	public int read(char[] cbuf, int off, int len)
	{
		if(off < 0 || off > cbuf.length || len < 0 || off + len > cbuf.length || off + len < 0) throw new IndexOutOfBoundsException();
		if(len == 0) return 0; 
		int charsToCopy = Math.min(len, this.myLength - this.myCurPos);
		if(charsToCopy <= 0) return -1;
		System.arraycopy(this.myText, this.myCurPos, cbuf, off, charsToCopy);
		this.myCurPos += charsToCopy;
		return charsToCopy;
	}
}