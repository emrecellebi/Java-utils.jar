package com.emrecellebi;

import com.emrecellebi.util.PathUtilRt;

public class PathUtilRtExample
{
	public static void main(String[] args)
	{
		System.out.println("PathUtilRt.Platform: enum -> " + PathUtilRt.Platform.CURRENT);
		System.out.println("PathUtilRt.fsCharset(): Charset -> " + PathUtilRt.fsCharset());
		System.out.println("PathUtilRt.getEnd(String): int -> " + PathUtilRt.getEnd("D:/Cizimler/01/01.txt"));
		System.out.println("PathUtilRt.getFileExtension(String): String -> " + PathUtilRt.getFileExtension("D:/Cizimler/01/01.txt"));
		System.out.println("PathUtilRt.getFileName(String): String -> " + PathUtilRt.getFileName("D:/Cizimler/01/01.txt"));
		System.out.println("PathUtilRt.getLastIndexOfPathSeparator(CharSequence, int): int -> " + PathUtilRt.getLastIndexOfPathSeparator("D:/Cizimler/01/01.txt", 21));
		System.out.println("PathUtilRt.getParentPath(String): String -> " + PathUtilRt.getParentPath("D:/Cizimler/01/01.txt"));
		System.out.println("PathUtilRt.isValidFileName(String, Platform, boolean, Charset): boolean -> " + PathUtilRt.isValidFileName("COM1", PathUtilRt.Platform.CURRENT, false, null));
		System.out.println("PathUtilRt.isValidFileName(String, Platform, boolean, Charset): boolean -> " + PathUtilRt.isValidFileName("data", PathUtilRt.Platform.CURRENT, false, null));
		System.out.println("PathUtilRt.isValidFileName(String, boolean): boolean -> " + PathUtilRt.isValidFileName("data", false));
		System.out.println("PathUtilRt.isValidFileName(String, boolean): boolean -> " + PathUtilRt.isValidFileName("COM1", false));
		System.out.println("PathUtilRt.isValidFileNameChar(char, Platform, boolean): boolean -> " + PathUtilRt.isValidFileNameChar('a', PathUtilRt.Platform.CURRENT, false));
		System.out.println("PathUtilRt.isValidFileNameChar(char, Platform, boolean): boolean -> " + PathUtilRt.isValidFileNameChar('*', PathUtilRt.Platform.CURRENT, false));
		System.out.println("PathUtilRt.isWindowsUNCRoot(CharSequence, int): boolean -> " + PathUtilRt.isWindowsUNCRoot("D:/Cizimler/01/01.txt", 21));
		System.out.println("PathUtilRt.suggestFileName(String): String -> " + PathUtilRt.suggestFileName("D:/Cizimler/01/01.txt"));
		System.out.println("PathUtilRt.suggestFileName(String, boolean, boolean): String -> " + PathUtilRt.suggestFileName("D:/Cizimler/01/01.txt", false, false));
		
	}
}