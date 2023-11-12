package com.emrecellebi;

import com.emrecellebi.util.SystemInfoRt;

public class PairExample
{
	public static void main(String[] args)
	{
		/**
			OS_NAME: String --> OS system adını döner.
		**/
		System.out.println("SystemInfoRt.OS_NAME: -> " + SystemInfoRt.OS_NAME);
		
		/**
			OS_VERSION: String --> OS system versiyonu döner.
		**/
		System.out.println("SystemInfoRt.OS_VERSION: " + SystemInfoRt.OS_VERSION);
		
		/**
			isWindows: boolean --> System windows ise true değerini döner.
		**/
		System.out.println("SystemInfoRt.isWindows: " + SystemInfoRt.isWindows);
		
		/**
			isMac: boolean --> System mac ise true değerini döner.
		**/
		System.out.println("SystemInfoRt.isMac: " + SystemInfoRt.isMac);
		
		/**
			isLinux: boolean --> System linux ise true değerini döner.
		**/
		System.out.println("SystemInfoRt.isLinux: " + SystemInfoRt.isLinux);
		
		/**
			isFreeBSD: boolean --> System freeBSD ise true değerini döner.
		**/
		System.out.println("SystemInfoRt.isFreeBSD: " + SystemInfoRt.isFreeBSD);
		
		/**
			isSolaris: boolean --> System solaris ise true değerini döner.
		**/
		System.out.println("SystemInfoRt.isSolaris: " + SystemInfoRt.isSolaris);
		
		/**
			isUnix: boolean --> System unix ise true değerini döner.
		**/
		System.out.println("SystemInfoRt.isUnix: " + SystemInfoRt.isUnix);
		
		/**
			isXWindow: boolean --> System xwindows ise true değerini döner.
		**/
		System.out.println("SystemInfoRt.isXWindow: " + SystemInfoRt.isXWindow);
		
		/**
			is32Bit: boolean --> System 32bit ise true değerini döner.
		**/
		System.out.println("SystemInfoRt.is32Bit: " + SystemInfoRt.is32Bit);
		
		/**
			is64Bit: boolean --> System 64bit ise true değerini döner.
		**/
		System.out.println("SystemInfoRt.is64Bit: " + SystemInfoRt.is64Bit);
		
		/**
			isFileSystemCaseSensitive: boolean --> System File Case Sensitive ise true değerini döner.
			Note: org.case.sensitive.fs = Boolean 	# File System Case Sensitive kullanılır.
		**/
		System.out.println("SystemInfoRt.isFileSystemCaseSensitive: " + SystemInfoRt.isFileSystemCaseSensitive);
		
		/**
			IS_AT_LEAST_JAVA9: boolean --> Java 9 dan büyük ise true değerini döner.
		**/
		System.out.println("SystemInfoRt.IS_AT_LEAST_JAVA9: " + SystemInfoRt.IS_AT_LEAST_JAVA9);
	}
}