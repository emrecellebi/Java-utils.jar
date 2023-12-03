package com.emrecellebi.util.text;

import java.io.Reader;

public class CharSequenceReader extends Reader
{
	private final CharSequence myText;
	private int myCurPos;
	
	public CharSequenceReader(CharSequence text)
	{
		this.myText = text;
	}
	
	@Override
	public void close(){}
	
	@Override
	public int read()
	{
		if(this.myCurPos >= this.myText.length()) return -1; 
		return this.myText.charAt(this.myCurPos++);
	}
	
	@Override
	public int read(char[] cbuf, int off, int len)
	{
		if(off < 0 || off > cbuf.length || len < 0 || off + len > cbuf.length || off + len < 0)
			throw new IndexOutOfBoundsException("cbuf.length=" + cbuf.length + "; off=" + off + "; len=" + len); 
		if(len == 0) return 0; 
		
		/** CharSequence implement eden her sınıf kulanıla bilinir. **/
		if(this.myText instanceof CharArrayCharSequence)
		{
			int readChars = ((CharArrayCharSequence)this.myText).readCharsTo(this.myCurPos, cbuf, off, len);
			if(readChars < 0) return -1; 
			this.myCurPos += readChars;
			return readChars;
		}
		
		int charsToCopy = Math.min(len, this.myText.length() - this.myCurPos);
		if(charsToCopy <= 0) return -1; 
		for(int n = 0; n < charsToCopy; n++)
			cbuf[n + off] = this.myText.charAt(n + this.myCurPos); 
		this.myCurPos += charsToCopy;
		return charsToCopy;
	}
}