package com.emrecellebi.util;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.emrecellebi.logging.LoggerRt;

public final class PathUtilRt
{
	/** Enum **/
	public enum Platform
	{
		UNIX, WINDOWS;

		public static final Platform CURRENT = SystemInfoRt.isWindows ? WINDOWS : UNIX;

		static{}
	}
	
	private static String[] rn = new String[] {"CON", "PRN", "AUX", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"};
	private static final Charset FS_CHARSET = fsCharset();
	private static final Set<String> WINDOWS_RESERVED_NAMES = new HashSet<String>(Arrays.asList(rn));
	
	public static Charset fsCharset()
	{
		if(!SystemInfoRt.isWindows && !SystemInfoRt.isMac)
		{
			String property = System.getProperty("sun.jnu.encoding");
			System.out.println(property);
			if(property != null)
			{
				try
				{
					return Charset.forName(property);
				}
				catch(Exception e)
				{
					LoggerRt.getInstance(PathUtilRt.class).warn("unknown JNU charset: " + property, e);
				}
			}
		}
		return null;
	}
	
	public static int getEnd(String path)
	{
		for(int index = path.length() - 1; index >= 0; index--)
		{
			char c = path.charAt(index);
			if(c != '/' && c != '\\') return index + 1; 
		} 
		return path.length() - 1;
	}
	
	public static String getFileExtension(String path)
	{
		if(StringUtilRt.isEmpty(path)) return null; 
		int end = getEnd(path);
		int start = getLastIndexOfPathSeparator(path, end) + 1;
		int index = StringUtilRt.lastIndexOf(path, '.', Math.max(start, 0), end);
		return (index < 0) ? null : path.substring(index + 1, end);
	}
	
	public static String getFileName(String path)
	{
		if(StringUtilRt.isEmpty(path)) return "";
		int end = getEnd(path);
		int start = getLastIndexOfPathSeparator(path, end);
		if(isWindowsUNCRoot(path, start)) start = -1;
		return path.substring(start + 1, end);
	}
	
	public static int getLastIndexOfPathSeparator(CharSequence path, int end)
	{
		return Math.max(StringUtilRt.lastIndexOf(path, '/', 0, end - 1), StringUtilRt.lastIndexOf(path, '\\', 0, end - 1));
	}
	
	public static String getParentPath(String path)
	{
		if(path.isEmpty()) return ""; 
		int end = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));
		if(end == path.length() - 1) end = getLastIndexOfPathSeparator(path, end); 
		if(end == -1 || end == 0) return ""; 
		if(isWindowsUNCRoot(path, end)) return ""; 
		char prev = path.charAt(end - 1);
		if(prev == '/' || prev == '\\') end--;
		return path.substring(0, end);
	}
	
	public static boolean isValidFileName(String name, Platform os, boolean strict, Charset cs)
	{
		if(name.isEmpty() || name.equals(".") || name.equals("..")) return false; 
		for(int i = 0; i < name.length(); i++)
			if(!isValidFileNameChar(name.charAt(i), os, strict)) return false; 
		if(os == Platform.WINDOWS && name.length() >= 3 && name.length() <= 4 && WINDOWS_RESERVED_NAMES.contains(name.toUpperCase(Locale.ENGLISH))) return false; 
		return (cs == null || (cs.canEncode() && cs.newEncoder().canEncode(name)));
	}
	
	public static boolean isValidFileName(String fileName, boolean strict)
	{ 
		return isValidFileName(fileName, Platform.CURRENT, strict, FS_CHARSET);
	}
	
	public static boolean isValidFileNameChar(char c, Platform os, boolean strict)
	{
		if(c == '/' || c == '\\') return false; 
		if((strict || os == Platform.WINDOWS) && (c < ' ' || "<>:\"|?*".indexOf(c) >= 0)) return false; 
		return(!strict || c != ';');
	}
	
	public static boolean isWindowsUNCRoot(CharSequence path, int lastPathSeparatorPosition)
	{
		return(Platform.CURRENT == Platform.WINDOWS && (StringUtilRt.startsWith(path, "//") || StringUtilRt.startsWith(path, "\\\\")) && getLastIndexOfPathSeparator(path, lastPathSeparatorPosition) == 1);
	}
	
	public static String suggestFileName(String text)
	{
		return suggestFileName(text, false, false);
	}
	
	public static String suggestFileName(String text, boolean allowDots, boolean allowSpaces)
	{
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < text.length(); i++)
		{
			char c = text.charAt(i);
			if(!isValidFileNameChar(c, Platform.CURRENT, true) || (!allowDots && c == '.') || (!allowSpaces && Character.isWhitespace(c)))
				result.append('_');
			else
				result.append(c);
		}
		return result.toString();
	}
}