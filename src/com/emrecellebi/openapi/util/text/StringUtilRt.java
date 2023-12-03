package com.emrecellebi.openapi.util.text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public final class StringUtilRt
{
	public static boolean charsEqualIgnoreCase(char a, char b)
	{
		return (a == b || toUpperCase(a) == toUpperCase(b) || toLowerCase(a) == toLowerCase(b));
	}
	
	public static CharSequence convertLineSeparators(CharSequence text, String newSeparator)
	{
		return unifyLineSeparators(text, newSeparator, null, false);
	}
	
	public static String convertLineSeparators(String text)
	{
		return convertLineSeparators(text, false);
	}
	
	public static String convertLineSeparators(String text, String newSeparator)
	{
		return convertLineSeparators(text, newSeparator, null);
	}
	
	public static String convertLineSeparators(String text, String newSeparator, int[] offsetsToKeep)
	{
		return convertLineSeparators(text, newSeparator, offsetsToKeep, false);
	}
	
	public static String convertLineSeparators(String text, String newSeparator, int[] offsetsToKeep, boolean keepCarriageReturn)
	{
		return unifyLineSeparators(text, newSeparator, offsetsToKeep, keepCarriageReturn).toString();
	}
	
	public static String convertLineSeparators(String text, boolean keepCarriageReturn)
	{
		return convertLineSeparators(text, "\n", null, keepCarriageReturn);
	}
	
	public static boolean endsWith(CharSequence text, CharSequence suffix)
	{
		int l1 = text.length();
		int l2 = suffix.length();
		if(l1 < l2) return false; 
		for(int i = l1 - 1; i >= l1 - l2; i--)
			if (text.charAt(i) != suffix.charAt(i + l2 - l1)) return false; 
		return true;
	}
	
	public static boolean endsWithChar(CharSequence s, char suffix)
	{
		return (s != null && s.length() != 0 && s.charAt(s.length() - 1) == suffix);
	}
	
	public static boolean endsWithIgnoreCase(CharSequence text, CharSequence suffix)
	{
		int l1 = text.length();
		int l2 = suffix.length();
		if(l1 < l2) return false; 
		for(int i = l1 - 1; i >= l1 - l2; i--)
			if(!charsEqualIgnoreCase(text.charAt(i), suffix.charAt(i + l2 - l1))) return false; 
		return true;
	}
	
	public static boolean equal(CharSequence s1, CharSequence s2, boolean caseSensitive)
	{
		if(s1 == s2) return true; 
		if(s1 == null || s2 == null) return false; 
		if(s1.length() != s2.length()) return false; 
		if(caseSensitive)
		{
			for (int i = 0; i < s1.length(); i++)
				if(s1.charAt(i) != s2.charAt(i)) return false;
		}
		else
		{
			for(int i = 0; i < s1.length(); i++)
				if(!charsEqualIgnoreCase(s1.charAt(i), s2.charAt(i))) return false;
		}
		return true;
	}
	
	public static String formatFileSize(long fileSize)
	{
		return formatFileSize(fileSize, " ");
	}
	
	public static String formatFileSize(long fileSize, String unitSeparator)
	{
		if(fileSize < 0L) throw new IllegalArgumentException("Invalid value: " + fileSize); 
		if(fileSize == 0L) return "0" + unitSeparator + "B";
		int rank = (int)((Math.log10(fileSize) + 2.1714778384307465E-6D) / 3.0D);
		double value = fileSize / Math.pow(1000.0D, rank);
		String[] units = { "B", "kB", "MB", "GB", "TB", "PB", "EB" };
		return (new DecimalFormat("0.##")).format(value) + unitSeparator + units[rank];
	}
	
	public static String getShortName(Class<?> aClass)
	{
		return getShortName(aClass.getName());
	}
	
	public static String getShortName(String fqName)
	{
		return getShortName(fqName, '.');
	}
	
	public static String getShortName(String fqName, char separator)
	{
		int lastPointIdx = fqName.lastIndexOf(separator);
		if(lastPointIdx >= 0)
			return fqName.substring(lastPointIdx + 1);
		return fqName;
	}
	
	public static boolean isEmpty(CharSequence cs)
	{
		return (cs == null || cs.length() == 0);
	}
	
	public static boolean isEmptyOrSpaces(CharSequence s)
	{
		if(isEmpty(s)) return true; 
		for(int i = 0; i < s.length(); i++)
			if(s.charAt(i) > ' ') return false;
		return true;
	}
	
	public static boolean isQuotedString(String s)
	{
		return (s.length() > 1 && (s.charAt(0) == '\'' || s.charAt(0) == '"') && s.charAt(0) == s.charAt(s.length() - 1));
	}
	
	public static int lastIndexOf(CharSequence s, char c, int start, int end)
	{ 
		start = Math.max(start, 0);
		for(int i = Math.min(end, s.length()) - 1; i >= start; i--)
			if(s.charAt(i) == c) return i;
		return -1;
	}
	
	public static String notNullize(String s)
	{
		return notNullize(s, "");
	}
	
	public static String notNullize(String s, String defaultValue)
	{
		return (s == null) ? defaultValue : s;
	}
	
	public static double parseDouble(String string, double defaultValue)
	{
		if(string != null) try{return Double.parseDouble(string);}catch(NumberFormatException numberFormatException){}
		return defaultValue;
	}
	
	public static int parseInt(String string, int defaultValue)
	{
		if(string != null) try{return Integer.parseInt(string);}catch(NumberFormatException numberFormatException){}
		return defaultValue;
	}
	
	public static long parseLong(String string, long defaultValue)
	{
		if(string != null) try{return Long.parseLong(string);}catch(NumberFormatException numberFormatException){}
		return defaultValue;
	}
	
	public static List<String> splitHonorQuotes(String s, char separator)
	{
		List<String> result = new ArrayList<String>();
		StringBuilder builder = new StringBuilder(s.length());
		boolean inQuotes = false;
		for(int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if(c == separator && !inQuotes)
			{
				if(builder.length() > 0)
				{
					result.add(builder.toString());
					builder.setLength(0);
				} 
			}
			else
			{
				if((c == '"' || c == '\'') && (i <= 0 || s.charAt(i - 1) != '\\')) inQuotes = !inQuotes; 
				builder.append(c);
			}
		}
		if(builder.length() > 0) result.add(builder.toString());
		return result;
	}
	
	public static boolean startsWith(CharSequence text, CharSequence prefix)
	{
		int l1 = text.length();
		int l2 = prefix.length();
		if(l1 < l2) return false; 
		for (int i = 0; i < l2; i++)
			if(text.charAt(i) != prefix.charAt(i)) return false;
		return true;
	}
	
	public static boolean startsWithIgnoreCase(String str, String prefix)
	{
		return startsWithIgnoreCase(str, 0, prefix);
	}
	
	public static boolean startsWithIgnoreCase(String str, int startOffset, String prefix)
	{
		int stringLength = str.length();
		int prefixLength = prefix.length();
		return (stringLength >= prefixLength && str.regionMatches(true, startOffset, prefix, 0, prefixLength));
	}
	
	public static int stringHashCodeInsensitive(CharSequence chars)
	{
		return stringHashCodeInsensitive(chars, 0, chars.length());
	}
	
	public static int stringHashCodeInsensitive(CharSequence chars, int from, int to)
	{
		return stringHashCodeInsensitive(chars, from, to, 0);
	}
	
	public static int stringHashCodeInsensitive(CharSequence chars, int from, int to, int prefixHash)
	{
		int h = prefixHash;
		for(int off = from; off < to; off++)
			h = 31 * h + toLowerCase(chars.charAt(off)); 
		return h;
	}
	
	public static char toLowerCase(char a)
	{
		if(a <= 'z') return (a >= 'A' && a <= 'Z') ? (char)(a + 32) : a; 
		return Character.toLowerCase(a);
	}
	
	public static char toUpperCase(char a)
	{
		if(a < 'a') return a;
		if(a <= 'z') return (char)(a + -32);
		return Character.toUpperCase(a);
	}
	
	public static String unquoteString(String s)
	{
		return isQuotedString(s) ? s.substring(1, s.length() - 1) : s;
	}
	
	public static String unquoteString(String s, char quotationChar)
	{
		boolean quoted = (s.length() > 1 && quotationChar == s.charAt(0) && quotationChar == s.charAt(s.length() - 1));
		return quoted ? s.substring(1, s.length() - 1) : s;
	}
	
	static <E extends Enum<E>> E parseEnum(String string, E defaultValue, Class<E> clazz)
	{
		try{return Enum.valueOf(clazz, string);}catch(Exception e) {return defaultValue;} 
	}
	
	private static CharSequence unifyLineSeparators(CharSequence text, String newSeparator, int[] offsetsToKeep, boolean keepCarriageReturn)
	{
		StringBuilder buffer = null;
		int intactLength = 0;
		boolean newSeparatorIsSlashN = "\n".equals(newSeparator);
		for(int i = 0; i < text.length(); i++)
		{
			char c = text.charAt(i);
			if(c == '\n')
			{
				if(!newSeparatorIsSlashN)
				{
					if(buffer == null)
					{
						buffer = new StringBuilder(text.length());
						buffer.append(text, 0, intactLength);
					}
					buffer.append(newSeparator);
					shiftOffsets(offsetsToKeep, buffer.length(), 1, newSeparator.length());
				}
				else if(buffer == null) intactLength++; else buffer.append('\n');
			}
			else if(c == '\r')
			{
				boolean followedByLineFeed = (i < text.length() - 1 && text.charAt(i + 1) == '\n');
				if(!followedByLineFeed && keepCarriageReturn)
				{
					if(buffer == null) intactLength++; else buffer.append('\r');
				} 
				else
				{
					if(buffer == null)
					{
						buffer = new StringBuilder(text.length());
						buffer.append(text, 0, intactLength);
					}
					buffer.append(newSeparator);
					if(followedByLineFeed) 
					{
						i++;
						shiftOffsets(offsetsToKeep, buffer.length(), 2, newSeparator.length());
					} else shiftOffsets(offsetsToKeep, buffer.length(), 1, newSeparator.length());
				}
			}
			else if(buffer == null) intactLength++; else buffer.append(c);
		}
		return (buffer == null) ? text : buffer;
	}
	
	private static void shiftOffsets(int[] offsets, int changeOffset, int oldLength, int newLength)
	{
		if(offsets == null) return;
		int shift = newLength - oldLength;
		
		if(shift == 0) return; 
		
		for(int i = 0; i < offsets.length; i++)
		{
			int offset = offsets[i];
			if(offset >= changeOffset + oldLength)
				offsets[i] = offsets[i] + shift; 
		}
	}
}