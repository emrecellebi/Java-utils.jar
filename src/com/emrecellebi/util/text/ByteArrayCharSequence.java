package com.emrecellebi.util.text;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.emrecellebi.openapi.util.text.ICharSequenceWithStringHash;
import com.emrecellebi.openapi.util.text.Strings;

public class ByteArrayCharSequence implements ICharSequenceWithStringHash
{
	private final int myStart;
	private final int myEnd;
	private transient int hash;
	private final byte[] myChars;

	public ByteArrayCharSequence(byte[] chars)
	{
		this(chars, 0, chars.length);
	}

	public ByteArrayCharSequence(byte[] chars, int start, int end)
	{
		this.myChars = chars;
		this.myStart = start;
		this.myEnd = end;
	}
	
	@Override
	public final char charAt(int index)
	{
		return (char)(this.myChars[index + this.myStart] & 0xFF);
	}
	
	public static CharSequence convertToBytesIfAsciiString(CharSequence string)
	{
		if(string.length() == 0) return "";
		byte[] bytes = toBytesIfPossible(string);
		return(bytes == null) ? string : (CharSequence)new ByteArrayCharSequence(bytes);
	}

	public byte[] getBytes()
	{
		return (this.myStart == 0 && this.myEnd == this.myChars.length) ? this.myChars : Arrays.copyOfRange(this.myChars, this.myStart, this.myEnd);
	}
	
	@Override
	public int hashCode()
	{
		int h = this.hash;
		if(h == 0) this.hash = h = Strings.stringHashCode((CharSequence)this, this.myStart, this.myEnd); 
		return h;
	}
	
	@Override
	public final int length()
	{
		return this.myEnd - this.myStart;
	}
	
	@Override
	public CharSequence subSequence(int start, int end)
	{
		return (start == 0 && end == length()) ? (CharSequence)this : new CharSequenceSubSequence((CharSequence)this, start, end);
	}
	
	public static byte[] toBytesIfPossible(CharSequence seq)
	{
		byte[] bytes = new byte[seq.length()];
		char[] chars = CharArrayUtil.fromSequenceWithoutCopying(seq);
		for(int i = 0; i < bytes.length; i++)
		{
			char c = (chars == null) ? seq.charAt(i) : chars[i];
			if((c & 0xFF00) != 0) return null; 
			bytes[i] = (byte)c;
		}
		return bytes;
	}
	
	@Override
	public String toString()
	{
		return new String(this.myChars, this.myStart, length(), StandardCharsets.ISO_8859_1);
	}
}