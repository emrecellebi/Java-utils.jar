package com.emrecellebi.util;

public final class SystemProperties
{
	public static boolean getBooleanProperty(String key, boolean defaultValue)
	{
		String value = System.getProperty(key);
		return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
	}
	
	public static float getFloatProperty(String key, float defaultValue)
	{
		String value = System.getProperty(key);
		if(value != null)
			try{return Float.parseFloat(value);}catch(NumberFormatException numberFormatException){}
		return defaultValue;
	}
	
	public static int getIntProperty(String key, int defaultValue)
	{
		String value = System.getProperty(key);
		if(value != null)
			try{return Integer.parseInt(value);}catch(NumberFormatException numberFormatException){}
		return defaultValue;
	}
	
	public static String getJavaHome()
	{
		return System.getProperty("java.home");
	}
	
	public static String getJavaVersion()
	{
		return SystemInfo.JAVA_VERSION;
	}
	
	public static String getJavaVmVendor()
	{
		return SystemInfo.JAVA_VENDOR;
	}
	
	public static String getLineSeparator()
	{
		return System.getProperty("line.separator");
	}
	
	public static String getOsName()
	{
		return SystemInfo.OS_NAME;
	}
	
	public static String getUserHome()
	{
		return System.getProperty("user.home");
	}
	
	public static String getUserName()
	{
		return System.getProperty("user.name");
	}
	
	public static boolean has(String key)
	{
		return (System.getProperty(key) != null);
	}
	
	public static boolean is(String key)
	{
		return getBooleanProperty(key, false);
	}
	
	public static boolean isTrueSmoothScrollingEnabled()
	{
		return getBooleanProperty("org.true.smooth.scrolling", false);
	}
}