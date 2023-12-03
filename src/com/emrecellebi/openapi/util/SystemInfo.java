package com.emrecellebi.openapi.util;

import java.io.File;

import com.emrecellebi.openapi.util.text.Strings;

public final class SystemInfo
{
	public static final String OS_NAME = SystemInfoRt.OS_NAME;
	public static final String OS_VERSION = SystemInfoRt.OS_VERSION;
	public static final String OS_ARCH = System.getProperty("os.arch");
	public static final String JAVA_VERSION = System.getProperty("java.version");	
	public static final String JAVA_RUNTIME_VERSION = getRtVersion(JAVA_VERSION);
	public static final String JAVA_VENDOR = System.getProperty("java.vm.vendor", "Unknown");
	public static final boolean isWindows = SystemInfoRt.isWindows;
	public static final boolean isMac = SystemInfoRt.isMac;
	public static final boolean isLinux = SystemInfoRt.isLinux;
	public static final boolean isFreeBSD = SystemInfoRt.isFreeBSD;
	public static final boolean isSolaris = SystemInfoRt.isSolaris;
	public static final boolean isUnix = SystemInfoRt.isUnix;
	public static final boolean isChromeOS = (isLinux && isCrostini());
	public static final boolean isAppleJvm = (Strings.indexOfIgnoreCase(JAVA_VENDOR, "Apple", 0) >= 0);
	public static final boolean isOracleJvm = (Strings.indexOfIgnoreCase(JAVA_VENDOR, "Oracle", 0) >= 0);
	public static final boolean isSunJvm = (Strings.indexOfIgnoreCase(JAVA_VENDOR, "Sun", 0) >= 0 && Strings.indexOfIgnoreCase(JAVA_VENDOR, "Microsystems", 0) >= 0);
	public static final boolean isIbmJvm = (Strings.indexOfIgnoreCase(JAVA_VENDOR, "IBM", 0) >= 0);
	public static final boolean isAzulJvm = (Strings.indexOfIgnoreCase(JAVA_VENDOR, "Azul", 0) >= 0);
	public static final boolean isJetBrainsJvm = (Strings.indexOfIgnoreCase(JAVA_VENDOR, "JetBrains", 0) >= 0);
	public static final boolean IS_AT_LEAST_JAVA9 = SystemInfoRt.IS_AT_LEAST_JAVA9;
	
	
	
	
	
	private static String getRtVersion(String fallback)
	{
		String rtVersion = System.getProperty("java.runtime.version");
		return Character.isDigit(rtVersion.charAt(0)) ? rtVersion : fallback;
	}
	
	private static boolean isCrostini()
	{
		return (new File("/dev/.cros_milestone")).exists();
	}
}