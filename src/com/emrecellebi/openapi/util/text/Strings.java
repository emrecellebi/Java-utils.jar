package com.emrecellebi.openapi.util.text;

import java.util.Locale;
import java.util.Objects;

public final class Strings
{
	public static String capitalize(String s)
	{
		if(s.isEmpty()) return s;
		if(s.length() == 1) return toUpperCase(s);
		if(Character.isUpperCase(s.charAt(0))) return s;
		return toUpperCase(s.charAt(0)) + s.substring(1);
	}
	
	public static boolean charsEqualIgnoreCase(char a, char b)
	{
		return charsMatch(a, b, true);
	}
	
	public static boolean charsMatch(char c1, char c2, boolean ignoreCase)
	{
		return (compare(c1, c2, ignoreCase) == 0);
	}
	
	public static int compare(char c1, char c2, boolean ignoreCase)
	{
		int d = c1 - c2;
		if(d == 0 || !ignoreCase) return d; 
		char u1 = StringUtilRt.toUpperCase(c1);
		char u2 = StringUtilRt.toUpperCase(c2);
		d = u1 - u2;
		if(d != 0) d = StringUtilRt.toLowerCase(u1) - StringUtilRt.toLowerCase(u2); 
		return d;
	}
	
	public static boolean contains(CharSequence sequence, CharSequence infix)
	{
		return (indexOf(sequence, infix) >= 0);
	}
	
	public static boolean contains(CharSequence s, int start, int end, char c)
	{
		return (indexOf(s, c, start, end) >= 0);
	}
	
	public static boolean containsAnyChar(String value, String chars)
	{
		return (chars.length() > value.length()) ? containsAnyChar(value, chars, 0, value.length()) : containsAnyChar(chars, value, 0, chars.length());
	}
	
	public static boolean containsAnyChar(String value, String chars, int start, int end)
	{
		for(int i = start; i < end; i++)
			if(chars.indexOf(value.charAt(i)) >= 0) return true; 
		return false;
	}
	
	public static boolean containsChar(String value, char ch)
	{
		return (value.indexOf(ch) >= 0);
	}
	
	public static boolean endsWith(CharSequence text, CharSequence suffix)
	{
		return StringUtilRt.endsWith(text, suffix);
	}
	
	public static boolean endsWithChar(CharSequence s, char suffix)
	{
		return StringUtilRt.endsWithChar(s, suffix);
	}
	
	public static boolean endsWithIgnoreCase(CharSequence str, String suffix)
	{
		return StringUtilRt.endsWithIgnoreCase(str, suffix);
	}
	
	public static int indexOf(CharSequence sequence, CharSequence infix)
	{
		return indexOf(sequence, infix, 0);
	}
	
	public static int indexOf(CharSequence sequence, CharSequence infix, int start)
	{
		return indexOf(sequence, infix, start, sequence.length());
	}
	
	public static int indexOf(CharSequence sequence, CharSequence infix, int start, int end)
	{
		for(int i = start; i <= end - infix.length(); i++)
			if(startsWith(sequence, i, infix)) return i; 
		return -1;
	}
	
	public static int indexOf(CharSequence s, char c)
	{
		return indexOf(s, c, 0, s.length());
	}
	
	public static int indexOf(CharSequence s, char c, int start)
	{
		return indexOf(s, c, start, s.length());
	}
	
	public static int indexOf(CharSequence s, char c, int start, int end)
	{
		end = Math.min(end, s.length());
		for (int i = Math.max(start, 0); i < end; i++)
			if(s.charAt(i) == c) return i; 
		return -1;
	}
	
	public static int indexOf(CharSequence s, char c, int start, int end, boolean caseSensitive)
	{
		end = Math.min(end, s.length());
		for (int i = Math.max(start, 0); i < end; i++)
			if(charsMatch(s.charAt(i), c, !caseSensitive)) return i; 
		return -1;
	}
	
	public static int indexOf(char[] s, char c, int start, int end, boolean caseSensitive)
	{
		end = Math.min(end, s.length);
		for(int i = Math.max(start, 0); i < end; i++)
		{
			boolean ignoreCase = !caseSensitive;
			if(charsMatch(s[i], c, ignoreCase)) return i; 
		}
		return -1;
	}
	
	public static int indexOfAny(CharSequence s, String chars)
	{
		return indexOfAny(s, chars, 0, s.length());
	}
	
	public static int indexOfAny(CharSequence s, String chars, int start, int end)
	{
		if(chars.length() == 0) return -1; 
		end = Math.min(end, s.length());
		for(int i = Math.max(start, 0); i < end; i++)
			if(containsChar(chars, s.charAt(i))) return i;
		return -1;
	}
	
	public static int indexOfAny(String s, String chars)
	{
		return indexOfAny(s, chars, 0, s.length());
	}
	
	public static int indexOfAny(String s, String chars, int start, int end)
	{
		return indexOfAny((CharSequence)s, chars, start, end);
	}
	
	public static int indexOfIgnoreCase(CharSequence where, CharSequence what, int fromIndex)
	{
		int targetCount = what.length();
		int sourceCount = where.length();
		if(fromIndex >= sourceCount) return (targetCount == 0) ? sourceCount : -1; 
		if(fromIndex < 0) fromIndex = 0;
		if(targetCount == 0) return fromIndex; 
		char first = what.charAt(0);
		int max = sourceCount - targetCount;
		for(int i = fromIndex; i <= max; i++)
		{
			if(!charsEqualIgnoreCase(where.charAt(i), first))
				while (++i <= max && !charsEqualIgnoreCase(where.charAt(i), first)); 
			if(i <= max)
			{
				int j = i + 1;
				int end = j + targetCount - 1;
				for(int k = 1; j < end && charsEqualIgnoreCase(where.charAt(j), what.charAt(k)); )
				{
					j++;
					k++;
				}
				if(j == end) return i; 
			}
		} 
		return -1;
	}
	
	public static int indexOfIgnoreCase(String where, String what, int fromIndex)
	{
		return indexOfIgnoreCase((CharSequence)where, (CharSequence)what, fromIndex);
	}
	
	public static int indexOfIgnoreCase(String where, char what, int fromIndex)
	{
		int sourceCount = where.length();
		for(int i = Math.max(fromIndex, 0); i < sourceCount; i++)
			if(charsEqualIgnoreCase(where.charAt(i), what)) return i; 
		return -1;
	}
	
	public static boolean isAscii(char ch)
	{
		int asciiValue = (int)ch;
        return asciiValue >= 0 && asciiValue <= 127;
	}
	
	public static boolean isCapitalized(String s)
	{
		return (s != null && !s.isEmpty() && Character.isUpperCase(s.charAt(0)));
	}
	
	public static boolean isEmpty(CharSequence cs)
	{
		return StringUtilRt.isEmpty(cs);
	}
	
	public static boolean isEmpty(String s)
	{
		return (s == null || s.isEmpty());
	}
	
	public static boolean isEmptyOrSpaces(CharSequence s)
	{
		return StringUtilRt.isEmptyOrSpaces(s);
	}
	
	public static boolean isNotEmpty(String s)
	{
		return !isEmpty(s);
	}
	
	public static String notNullize(String s)
	{
		return StringUtilRt.notNullize(s);
	}
	
	public static String notNullize(String s, String defaultValue)
	{
		return StringUtilRt.notNullize(s, defaultValue);
	}
	
	public static String nullize(String s)
	{
		return nullize(s, false);
	}
	
	public static String nullize(String s, String defaultValue)
	{
		boolean empty = (isEmpty(s) || Objects.equals(s, defaultValue));
		return empty ? null : s;
	}
	
	public static String nullize(String s, boolean nullizeSpaces)
	{
		boolean empty = nullizeSpaces ? isEmptyOrSpaces(s) : isEmpty(s);
		return empty ? null : s;
	}
	
	public static String pluralize(String word)
	{
		String plural = Pluralizer.PLURALIZER.plural(word);
		if(plural != null) return plural;
		if(word.endsWith("s")) return Pluralizer.restoreCase(word, word + "es");
		return Pluralizer.restoreCase(word, word + "s");
	}
	
	public static boolean startsWith(CharSequence text, int startIndex, CharSequence prefix)
	{
		int tl = text.length();
		if(startIndex < 0 || startIndex > tl)
			throw new IllegalArgumentException("Index is out of bounds: " + startIndex + ", length: " + tl);
		int l1 = tl - startIndex;
		int l2 = prefix.length();
		if(l1 < l2) return false; 
		for(int i = 0; i < l2; i++)
			if(text.charAt(i + startIndex) != prefix.charAt(i)) return false; 
		return true;
	}
	
	public static int stringHashCode(CharSequence chars, int from, int to)
	{
		return stringHashCode(chars, from, to, 0);
	}
	
	public static int stringHashCode(CharSequence chars, int from, int to, int prefixHash)
	{
		int h = prefixHash;
		for(int off = from; off < to; off++)
			h = 31 * h + chars.charAt(off); 
		return h;
	}
	
	public static int stringHashCode(char[] chars, int from, int to)
	{
		int h = 0;
		for(int off = from; off < to; off++)
			h = 31 * h + chars[off]; 
		return h;
	}
	
	public static int stringHashCodeInsensitive(CharSequence chars)
	{
		return StringUtilRt.stringHashCodeInsensitive(chars);
	}
	
	public static int stringHashCodeInsensitive(CharSequence chars, int from, int to)
	{
		return StringUtilRt.stringHashCodeInsensitive(chars, from, to);
	}
	
	public static int stringHashCodeInsensitive(CharSequence chars, int from, int to, int prefixHash)
	{
		return StringUtilRt.stringHashCodeInsensitive(chars, from, to, prefixHash);
	}
	
	public static int stringHashCodeInsensitive(char[] chars, int from, int to)
	{
		int h = 0;
		for(int off = from; off < to; off++)
			h = 31 * h + toLowerCase(chars[off]); 
		return h;
	}
	
	public static String toLowerCase(String str)
	{
		return (str == null) ? null : str.toLowerCase(Locale.ENGLISH);
	}
	
	public static char toLowerCase(char a)
	{
		return StringUtilRt.toLowerCase(a);
	}
	
	public static String toUpperCase(String s)
	{
		return (s == null) ? null : s.toUpperCase(Locale.ENGLISH);
	}
	
	public static char toUpperCase(char a)
	{
		return StringUtilRt.toUpperCase(a);
	}
	
	public static String trim(String s)
	{
		return (s == null) ? null : s.trim();
	}
	
	public static String trimEnd(String s, String suffix)
	{
		return trimEnd(s, suffix, false);
	}
	
	public static String trimEnd(String s, String suffix, boolean ignoreCase)
	{
		boolean endsWith = ignoreCase ? endsWithIgnoreCase(s, suffix) : s.endsWith(suffix);
		if(endsWith) return s.substring(0, s.length() - suffix.length());
		return s;
	}
	
	public static String trimEnd(String s, char suffix)
	{
		if(endsWithChar(s, suffix))
			return s.substring(0, s.length() - 1);
		return s;
	}
	
	public static String unpluralize(String word)
	{
		String singular = Pluralizer.PLURALIZER.singular(word);
		if(singular != null) return singular; 
		if(word.endsWith("es")) return nullize(trimEnd(word, "es", true)); 
		if(word.endsWith("s")) return nullize(trimEnd(word, "s", true)); 
		return null;
	}
}