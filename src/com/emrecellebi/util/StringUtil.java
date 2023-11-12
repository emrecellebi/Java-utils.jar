package com.emrecellebi.util;

import java.beans.Introspector;
import java.util.StringTokenizer;

import com.emrecellebi.IFunction;

public final class StringUtil ///extends StringUtilRt
{
	public static void assertValidSeparators(CharSequence s)
	{
		char[] chars = CharArrayUtil.fromSequenceWithoutCopying(s);
		int slashRIndex = -1;
		if(chars != null)
		{
			for (int i = 0, len = s.length(); i < len; i++)
			{
				if(chars[i] == '\r')
				{
					slashRIndex = i;
					break;
				}
			} 
		}
		else
		{
			for(int i = 0, len = s.length(); i < len; i++)
			{
				if(s.charAt(i) == '\r')
				{
					slashRIndex = i;
					break;
				} 
			} 
		} 
		if(slashRIndex != -1)
		{
			String context = String.valueOf(last(s.subSequence(0, slashRIndex), 10, true)) + first(s.subSequence(slashRIndex, s.length()), 10, true);
			context = escapeStringCharacters(context);
			throw new AssertionError("Wrong line separators: '" + context + "' at offset " + slashRIndex);
		} 
	}
	
	public static String capitalize(String s)
	{
		return Strings.capitalize(s);
	}
	
	public static String capitalizeWithJavaBeanConvention(String s)
	{
		if(s.length() > 1 && Character.isUpperCase(s.charAt(1))) return s;
		return capitalize(s);
	}
	
	public static String capitalizeWords(String text, String tokenizerDelim, boolean allWords, boolean leaveOriginalDelims)
	{
		StringTokenizer tokenizer = new StringTokenizer(text, tokenizerDelim, leaveOriginalDelims);
		StringBuilder out = new StringBuilder(text.length());
		boolean toCapitalize = true;
		while(tokenizer.hasMoreTokens())
		{
			String word = tokenizer.nextToken();
			if(!leaveOriginalDelims && out.length() > 0) out.append(' '); 
			out.append(toCapitalize ? capitalize(word) : word);
			if(!allWords) toCapitalize = false; 
		} 
		return out.toString();
	}
	
	public static String capitalizeWords(String text, boolean allWords)
	{
		return capitalizeWords(text, " \t\n\r\f([<", allWords, true);
	}
	
	public static boolean charsEqualIgnoreCase(char a, char b)
	{
		return Strings.charsEqualIgnoreCase(a, b);
	}
	
	public static String collapseWhiteSpace(CharSequence s)
	{
		StringBuilder result = new StringBuilder();
		boolean space = false;
		for(int i = 0, length = s.length(); i < length; i++)
		{
			char ch = s.charAt(i);
			if(isWhiteSpace(ch))
				if(!space) space = true; 
			else
			{
				if(space && result.length() > 0) result.append(' '); 
				result.append(ch);
				space = false;
			} 
		} 
		return result.toString();
	}
	
	public static String commonPrefix(String s1, String s2)
	{
		return s1.substring(0, commonPrefixLength(s1, s2));
	}
	
	public static int commonPrefixLength(CharSequence s1, CharSequence s2)
	{
		return commonPrefixLength(s1, s2, false);
	}
	
	public static int commonPrefixLength(CharSequence s1, CharSequence s2, boolean ignoreCase)
	{
		int minLength = Math.min(s1.length(), s2.length());
		int i;
		for(i = 0; i < minLength && Strings.charsMatch(s1.charAt(i), s2.charAt(i), ignoreCase); i++);
		return i;
	}
	
	public static String commonSuffix(String s1, String s2)
	{
		return s1.substring(s1.length() - commonSuffixLength(s1, s2));
	}
	
	public static int commonSuffixLength(CharSequence s1, CharSequence s2)
	{
		int s1Length = s1.length();
		int s2Length = s2.length();
		if(s1Length == 0 || s2Length == 0) return 0; 
		int i;
		for(i = 0; i < s1Length && i < s2Length && s1.charAt(s1Length - i - 1) == s2.charAt(s2Length - i - 1); i++);
		return i;
	}
	
	public static int compare(CharSequence s1, CharSequence s2, boolean ignoreCase)
	{
		if(s1 == s2) return 0; 
		if(s1 == null) return -1; 
		if(s2 == null) return 1; 
		int length1 = s1.length();
		int length2 = s2.length();
		int i = 0;
		for(; i < length1 && i < length2; i++) 
		{
			int diff = Strings.compare(s1.charAt(i), s2.charAt(i), ignoreCase);
			if(diff != 0) return diff; 
		} 
		return length1 - length2;
	}
	
	public static int compare(String s1, String s2, boolean ignoreCase)
	{
		if(s1 == s2) return 0; 
		if(s1 == null) return -1; 
		if(s2 == null) return 1; 
		return ignoreCase ? s1.compareToIgnoreCase(s2) : s1.compareTo(s2);
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
	
	public static int comparePairs(String s1, String t1, String s2, String t2, boolean ignoreCase)
	{
		int compare = compare(s1, s2, ignoreCase);
		return (compare != 0) ? compare : compare(t1, t2, ignoreCase);
	}
	
	public static int compareVersionNumbers(String v1, String v2)
	{
		if(v1 == null && v2 == null) return 0; 
		if(v1 == null) return -1; 
		if(v2 == null) return 1; 
		String[] part1 = v1.split("[._\\-]");
		String[] part2 = v2.split("[._\\-]");
		int idx = 0;
		for(; idx < part1.length && idx < part2.length; idx++)
		{
			int cmp;
			String p1 = part1[idx];
			String p2 = part2[idx];
			if(p1.matches("\\d+") && p2.matches("\\d+"))
				cmp = Integer.valueOf(p1).compareTo(Integer.valueOf(p2));
			else
				cmp = part1[idx].compareTo(part2[idx]);
			if(cmp != 0) return cmp; 
		} 
		if(part1.length != part2.length)
		{
			boolean left = (part1.length > idx);
			String[] parts = left ? part1 : part2;
			for(; idx < parts.length; idx++)
			{
				int cmp;
				String p = parts[idx];
				if(p.matches("\\d+"))
					cmp = Integer.valueOf(p).compareTo(Integer.valueOf(0));
				else
					cmp = 1;
				if(cmp != 0) return left ? cmp : -cmp; 
			} 
		} 
		return 0;
	}
	
	public static boolean contains(CharSequence sequence, CharSequence infix)
	{
		return Strings.contains(sequence, infix);
	}
	
	public static boolean contains(CharSequence s, int start, int end, char c)
	{
		return Strings.contains(s, start, end, c);
	}
	
	public static boolean containsAlphaCharacters(String value)
	{
		for(int i = 0; i < value.length(); i++)
			if(Character.isLetter(value.charAt(i))) return true; 
		return false;
	}
	
	public static boolean containsAnyChar(String value, String chars)
	{
		return Strings.containsAnyChar(value, chars);
	}
	
	public static boolean containsAnyChar(String value, String chars, int start, int end)
	{
		return Strings.containsAnyChar(value, chars, start, end);
	}
	
	public static boolean containsChar(String value, char ch)
	{
		return Strings.containsChar(value, ch);
	}
	
	public static boolean containsIgnoreCase(String where, String what)
	{
		return (indexOfIgnoreCase(where, what, 0) >= 0);
	}
	
	public static boolean containsLineBreak(CharSequence text)
	{
		for(int i = 0; i < text.length(); i++)
		{
			char c = text.charAt(i);
			if(isLineBreak(c)) return true; 
		} 
		return false;
	}
	
	public static boolean containsWhitespaces(CharSequence s)
	{
		if(s == null) return false; 
		for(int i = 0; i < s.length(); i++)
			if(Character.isWhitespace(s.charAt(i))) return true; 
		return false;
	}
	
	public static String convertLineSeparators(String text)
	{
		return StringUtilRt.convertLineSeparators(text);
	}
	
	public static String convertLineSeparators(String text, String newSeparator)
	{
		return StringUtilRt.convertLineSeparators(text, newSeparator);
	}
	
	public static String convertLineSeparators(String text, String newSeparator, int[] offsetsToKeep)
	{
		return StringUtilRt.convertLineSeparators(text, newSeparator, offsetsToKeep);
	}
	
	public static String convertLineSeparators(String text, boolean keepCarriageReturn)
	{
		return StringUtilRt.convertLineSeparators(text, keepCarriageReturn);
	}
	
	public static int countChars(CharSequence text, char c)
	{
		return countChars(text, c, 0, false);
	}
	
	public static int countChars(CharSequence text, char c, int offset, boolean stopAtOtherChar)
	{
		return countChars(text, c, offset, text.length(), stopAtOtherChar);
	}
	
	public static int countChars(CharSequence text, char c, int start, int end, boolean stopAtOtherChar)
	{
		boolean forward = (start <= end);
		start = forward ? Math.max(0, start) : Math.min(text.length(), start);
		end = forward ? Math.min(text.length(), end) : Math.max(0, end);
		int count = 0;
		int i;
		for(i = forward ? start : (start - 1); forward == ((i < end)); i += forward ? 1 : -1)
		{
			if (text.charAt(i) == c) count++; else if (stopAtOtherChar) break;
		} 
		return count;
	}
	
	public static int countNewLines(CharSequence text)
	{
		return countChars(text, '\n');
	}
	
	public static <T> IFunction<T, String> createToStringFunction(Class<T> cls)
	{
		return Object::toString;
	}
	
	public static String decapitalize(String s)
	{
		return Introspector.decapitalize(s);
	}
	
	public static String defaultIfEmpty(String value, String defaultValue)
	{
		return isEmpty(value) ? defaultValue : value;
	}
	
	/// detectSeparators ---> Devam Edilicek
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean isWhiteSpace(char c)
	{
		return (c == '\n' || c == '\t' || c == ' ');
	}
	
	
	public static String escapeStringCharacters(String s)
	{
		StringBuilder buffer = new StringBuilder(s.length());
		escapeStringCharacters(s.length(), s, "\"", buffer);
		return buffer.toString();
	}
	
	public static int indexOfIgnoreCase(String where, String what, int fromIndex)
	{
		return Strings.indexOfIgnoreCase(where, what, fromIndex);
	}
	
	public static StringBuilder escapeStringCharacters(int length, String str, String additionalChars, StringBuilder buffer)
	{
		return escapeStringCharacters(length, str, additionalChars, true, buffer);
	}
	
	public static StringBuilder escapeStringCharacters(int length, String str, String additionalChars, boolean escapeSlash, StringBuilder buffer)
	{
		return escapeStringCharacters(length, str, additionalChars, escapeSlash, true, buffer);
	}
	
	public static boolean isLineBreak(char c)
	{
		return (c == '\n' || c == '\r');
	}

	public static boolean isEmpty(String s)
	{
		return Strings.isEmpty(s);
	}
	
	public static StringBuilder escapeStringCharacters(int length, String str, String additionalChars, boolean escapeSlash, boolean escapeUnicode, StringBuilder buffer)
	{
		char prev = Character.MIN_VALUE;
		for(int idx = 0; idx < length; idx++)
		{
			char ch = str.charAt(idx);
			switch(ch)
			{
				case '\b':
					buffer.append("\\b");
				break;
				
				case '\t':
					buffer.append("\\t");
				break;
				
				case '\n':
					buffer.append("\\n");
				break;
				
				case '\f':
					buffer.append("\\f");
				break;
				
				case '\r':
					buffer.append("\\r");
				break;
				
				default:
				if(escapeSlash && ch == '\\')
				{
					buffer.append("\\\\");
					break;
				}
				
				if(additionalChars != null && additionalChars.indexOf(ch) > -1 && (escapeSlash || prev != '\\'))
				{
					buffer.append("\\").append(ch);
					break;
				}
				
				if(escapeUnicode && !isPrintableUnicode(ch))
				{
					CharSequence hexCode = toUpperCase(Integer.toHexString(ch));
					buffer.append("\\u");
					int paddingCount = 4 - hexCode.length();
					while(paddingCount-- > 0) buffer.append(0); 
					buffer.append(hexCode);
					break;
				}
				buffer.append(ch);
				break;
			}
			prev = ch;
		} 
		return buffer;
	}
	
	public static void escapeStringCharacters(int length, String str, StringBuilder buffer)
	{
		escapeStringCharacters(length, str, "\"", buffer);
	}
	
	public static CharSequence first(CharSequence text, int length, boolean appendEllipsis)
	{
		if(text.length() <= length) return text;
		if(appendEllipsis) return text.subSequence(0, length) + "...";
		return text.subSequence(0, length);
	}
	
	public static String first(String text, int maxLength, boolean appendEllipsis)
	{
		return (text.length() > maxLength) ? (text.substring(0, maxLength) + (appendEllipsis ? "..." : "")) : text;
	}
	
	public static CharSequence last(CharSequence text, int length, boolean prependEllipsis)
	{
		if(text.length() <= length) return text;
		if(prependEllipsis) return "..." + text.subSequence(text.length() - length, text.length());
		return text.subSequence(text.length() - length, text.length());
	}
	
	public static int indexOf(CharSequence s, char c, int start, int end)
	{
		return Strings.indexOf(s, c, start, end);
	}
	
	public static boolean isPrintableUnicode(char c)
	{
		int t = Character.getType(c);
		return (t != 0 && t != 13 && t != 14 && t != 15 && t != 16 && t != 18 && t != 19);
	}
	
	public static String toUpperCase(String s)
	{
		return Strings.toUpperCase(s);
	}
	
	public static char toUpperCase(char a)
	{
		return Strings.toUpperCase(a);
	}
}