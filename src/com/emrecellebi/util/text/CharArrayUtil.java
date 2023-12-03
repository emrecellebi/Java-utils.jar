package com.emrecellebi.util.text;

import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.emrecellebi.openapi.util.TextRange;
import com.emrecellebi.util.text.CharArrayCharSequence;
import com.emrecellebi.util.text.CharSequenceReader;
import com.emrecellebi.util.text.ImmutableCharSequence;
import com.emrecellebi.util.text.ImmutableText;
import com.emrecellebi.util.text.UnsyncCharArrayReader;

public final class CharArrayUtil
{
	private static final int GET_CHARS_THRESHOLD = 10;
	
	public static boolean containLineBreaks(CharSequence seq)
	{
		return containLineBreaks(seq, 0, seq.length());
	}
	
	public static boolean containLineBreaks(CharSequence seq, int fromOffset, int endOffset)
	{
		if(seq == null) return false; 
		for(int i = fromOffset; i < endOffset; i++)
		{
			char c = seq.charAt(i);
			if(c == '\n' || c == '\r') return true;
		}
		return false;
	}
	
	public static boolean containsOnlyWhiteSpaces(CharSequence chars)
	{
		if(chars == null) return false; 
		for(int i = 0; i < chars.length(); i++)
		{
			char c = chars.charAt(i);
			if(c == ' ' || c == '\t' || c == '\n' || c == '\r')
				return true;
		}
		return false;
	}

	public static ImmutableCharSequence createImmutableCharSequence(CharSequence sequence)
	{
		return ImmutableText.valueOf(sequence);
	}
	
	public static boolean equals(char[] buffer1, int start1, int end1, char[] buffer2, int start2, int end2)
	{
		if(end1 - start1 != end2 - start2) return false;
		for(int i = start1; i < end1; i++)
			if(buffer1[i] != buffer2[i - start1 + start2]) return false;
		return true;
	}
	
	public static char[] fromSequence(CharSequence seq)
	{
		char[] underlying = fromSequenceWithoutCopying(seq);
		return (underlying != null) ? Arrays.copyOf(underlying, underlying.length) : fromSequence(seq, 0, seq.length());
	}
	
	public static char[] fromSequence(CharSequence seq, int start, int end)
	{
		char[] result = new char[end - start];
		getChars(seq, result, start, 0, end - start);
		return result;
	}

	public static char[] fromSequenceWithoutCopying(CharSequence seq)
	{		
		CharBuffer buffer = CharBuffer.allocate(seq.length());
		buffer.put((String)seq);
		buffer.flip();
		if(buffer.hasArray() && !buffer.isReadOnly() && buffer.arrayOffset() == 0 && buffer.position() == 0)
			return buffer.array();
		return null;
	}
	
	public static void getChars(CharSequence src, char[] dst, int dstOffset)
	{
		getChars(src, dst, dstOffset, src.length());
	}
	
	public static void getChars(CharSequence src, char[] dst, int dstOffset, int len)
	{
		getChars(src, dst, 0, dstOffset, len);
	}
	
	public static void getChars(CharSequence src, char[] dst, int srcOffset, int dstOffset, int len)
	{
		for(int i = 0, j = srcOffset, max = srcOffset + len; j < max && i < dst.length; i++, j++)
			dst[i + dstOffset] = src.charAt(j);
	}
	
	public static TextRange[] getIndents(CharSequence charsSequence, int shift)
	{
		List<TextRange> result = new ArrayList<>();
		int whitespaceEnd = -1;
		int lastTextFound = 0;
		for(int i = charsSequence.length() - 1; i >= 0; i--)
		{
			char charAt = charsSequence.charAt(i);
			boolean isWhitespace = Character.isWhitespace(charAt);
			if(charAt == '\n')
			{
				result.add((new TextRange(i, ((whitespaceEnd >= 0) ? whitespaceEnd : i) + 1)).shiftRight(shift));
				whitespaceEnd = -1;
			}
			else if(whitespaceEnd >= 0)
			{
				if (!isWhitespace)
				{
					lastTextFound = result.size();
					whitespaceEnd = -1;
				}
			}
			else if(isWhitespace) whitespaceEnd = i; else lastTextFound = result.size(); 
		}
		if(whitespaceEnd > 0) result.add((new TextRange(0, whitespaceEnd + 1)).shiftRight(shift)); 
		result = (lastTextFound >= result.size()) ? result : result.subList(0, lastTextFound); 
		return result.toArray(new TextRange[0]);
	}
	
	public static int indexOf(CharSequence buffer, CharSequence pattern, int fromIndex)
	{
		return indexOf(buffer, pattern, fromIndex, buffer.length());
	}
	
	public static int indexOf(CharSequence buffer, CharSequence pattern, int fromIndex, int toIndex)
	{
		int patternLength = pattern.length();
		if(fromIndex < 0) fromIndex = 0; 
		int limit = toIndex - patternLength + 1;
		for(int i = fromIndex; i < limit; i++)
		{
			int j = 0;
			while (true)
			{
				if(j < patternLength)
				{
					if(pattern.charAt(j) != buffer.charAt(i + j)) break; 
					j++;
					continue;
				}
				return i;
			}
		}
		return -1;
	}
	
	public static int indexOf(char[] buffer, String pattern, int fromIndex)
	{
		char[] chars = pattern.toCharArray();
		int limit = buffer.length - chars.length + 1;
		if(fromIndex < 0) fromIndex = 0; 
		for(int i = fromIndex; i < limit; i++)
		{
			int j = 0;
			while(true)
			{
				if(j < chars.length)
				{
					if(chars[j] != buffer[i + j]) break; 
					j++;
					continue;
				} 
				return i;
			} 
		} 
		return -1;
	}
	
	public static int indexOf(char[] buffer, char symbol, int fromIndex, int toIndex)
	{
		if(fromIndex < 0) fromIndex = 0; 
		for(int i = fromIndex; i < toIndex; i++)
			if(buffer[i] == symbol) return i; 
		return -1;
	}
	
	public static boolean isEmptyOrSpaces(CharSequence text, int start, int end)
	{
		for(int i = start; i < end; i++)
		{
			char c = text.charAt(i);
			if(c != ' ' && c != '\t' && c != '\n') return false; 
		}
		return true;
	}
	
	public static boolean isSuitable(String chars, char c)
	{
		for(int i = 0; i < chars.length(); i++)
			if(c == chars.charAt(i)) return true; 
		return false;
	}
	
	public static int lastIndexOf(CharSequence buffer, String pattern, int maxIndex)
	{
		char[] chars = pattern.toCharArray();
		int end = buffer.length() - chars.length;
		if(maxIndex > end) maxIndex = end; 
		for(int i = maxIndex; i >= 0; i--)
		{
			int j = 0;
			while(true)
			{
				if(j < chars.length)
				{
					if(chars[j] != buffer.charAt(i + j)) break; 
					j++;
					continue;
				} 
				return i;
			} 
		} 
		return -1;
	}
	
	public static int lastIndexOf(char[] buffer, String pattern, int maxIndex)
	{
		char[] chars = pattern.toCharArray();
		int end = buffer.length - chars.length;
		if(maxIndex > end) maxIndex = end; 
		for(int i = maxIndex; i >= 0; i--)
		{
			int j = 0;
			while(true)
			{
				if(j < chars.length)
				{
					if(chars[j] != buffer[i + j]) break; 
					j++;
					continue;
				} 
				return i;
			} 
		} 
		return -1;
	}
	
	public static int lastIndexOf(char[] buffer, char symbol, int fromIndex, int toIndex)
	{
		if(fromIndex < 0) fromIndex = 0; 
		for(int i = toIndex - 1; i >= fromIndex; i--)
			if(buffer[i] == symbol) return i;
		return -1;
	}
	
	public static Reader readerFromCharSequence(CharSequence text)
	{
		char[] chars = fromSequenceWithoutCopying(text);
		return (chars == null) ? new CharSequenceReader(text) : new UnsyncCharArrayReader(chars, 0, text.length());
	}
	
	public static boolean regionMatches(CharSequence buffer, int offset, CharSequence s)
	{
		if(offset + s.length() > buffer.length()) return false; 
		if(offset < 0) return false; 
		for(int i = 0; i < s.length(); i++)
			if(buffer.charAt(offset + i) != s.charAt(i)) return false; 
		return true;
	}
	
	public static boolean regionMatches(CharSequence buffer, int start, int end, CharSequence s)
	{
		int len = s.length();
		if(start + len > end) return false; 
		if(start < 0) return false; 
		for(int i = 0; i < len; i++)
			if(buffer.charAt(start + i) != s.charAt(i)) return false; 
		return true;
	}
	
	public static boolean regionMatches(CharSequence s1, int start1, int end1, CharSequence s2, int start2, int end2)
	{
		if(end1 - start1 != end2 - start2) return false; 
		for(int i = start1, j = start2; i < end1; i++, j++)
			if(s1.charAt(i) != s2.charAt(j)) return false; 
		return true;
	}
	
	public static boolean regionMatches(char[] buffer, int start, int end, CharSequence s)
	{
		int len = s.length();
		if(start + len > end) return false; 
		if(start < 0) return false; 
		for(int i = 0; i < len; i++)
			if(buffer[start + i] != s.charAt(i)) return false;
		return true;
	}
	
	public static int shiftBackward(CharSequence buffer, int offset, String chars)
	{
		return shiftBackward(buffer, 0, offset, chars);
	}
	
	public static int shiftBackward(CharSequence buffer, int minOffset, int maxOffset, String chars)
	{
		if(maxOffset >= buffer.length()) return maxOffset; 
		while(maxOffset >= minOffset)
		{
			char c = buffer.charAt(maxOffset);
			int i;
			for(i = 0; i < chars.length() && c != chars.charAt(i); i++);
			if(i < chars.length()) break; 
			maxOffset--;
		} 
		return maxOffset;
	}
	
	public static int shiftBackward(char[] buffer, int offset, String chars)
	{
		return shiftBackward((CharSequence)new CharArrayCharSequence(buffer), offset, chars);
	}
	
	public static int shiftBackwardUntil(CharSequence buffer, int offset, String chars)
	{
		if(offset >= buffer.length()) return offset; 
		while(offset >= 0)
		{
			char c = buffer.charAt(offset);
			int i;
			for(i = 0; i < chars.length() && c != chars.charAt(i); i++);
			if(i < chars.length()) break; 
			offset--;
		}
		return offset;
	}
	
	public static int shiftForward(CharSequence buffer, int offset, String chars)
	{
		return shiftForward(buffer, offset, buffer.length(), chars);
	}
	
	public static int shiftForward(CharSequence buffer, int startOffset, int endOffset, String chars)
	{
		for(int offset = startOffset, limit = Math.min(endOffset, buffer.length()); offset < limit; offset++)
		{
			char c = buffer.charAt(offset);
			int i;
			for(i = 0; i < chars.length() && c != chars.charAt(i); i++);
			if(i < chars.length()) return offset; 
		}
		return endOffset;
	}
	
	public static int shiftForward(char[] buffer, int offset, String chars)
	{
		return shiftForward((CharSequence)new CharArrayCharSequence(buffer), offset, chars);
	}
	
	public static int shiftForwardCarefully(CharSequence buffer, int offset, String chars)
	{
		if(offset + 1 >= buffer.length()) return offset;
		if(!isSuitable(chars, buffer.charAt(offset))) return offset; 
		offset++;
		while(true)
		{
			if(offset >= buffer.length()) return offset - 1; 
			char c = buffer.charAt(offset);
			if(!isSuitable(chars, c)) return offset - 1; 
			offset++;
		} 
	}
	
	public static int shiftForwardUntil(CharSequence buffer, int offset, String chars)
	{
		while(offset < buffer.length())
		{
			char c = buffer.charAt(offset);
			int i;
			for(i = 0; i < chars.length() && c != chars.charAt(i); i++);
			if(i < chars.length()) break; 
			offset++;
		}
		return offset;
	}
}