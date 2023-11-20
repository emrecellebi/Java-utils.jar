package com.emrecellebi;

import com.emrecellebi.util.SystemProperties;

public class SystemPropertiesExample
{
	public static void main(String[] args)
	{
		/// Bir property bilgisi döner. Değer null ise default değeri döner.
		System.out.println("SystemProperties.getBooleanProperty(String, boolean): boolean -> " + SystemProperties.getBooleanProperty("com.data.is", false));
		
		/// Bir property bilgisi döner. Değer null ise default değeri döner.
		System.out.println("SystemProperties.getFloatProperty(String, float): float -> " + SystemProperties.getFloatProperty("com.data.float", 0.0f));
		
		/// Bir property bilgisi döner. Değer null ise default değeri döner.
		System.out.println("SystemProperties.getIntProperty(String, int): int -> " + SystemProperties.getIntProperty("com.data.int", 0));
		
		/// Java home path döner.
		System.out.println("SystemProperties.getJavaHome(): String -> " + SystemProperties.getJavaHome());
		
		/// Java version döner.
		System.out.println("SystemProperties.getJavaVersion(): String -> " + SystemProperties.getJavaVersion());
		
		/// Java vendör değerini döner.
		System.out.println("SystemProperties.getJavaVmVendor(): String -> " + SystemProperties.getJavaVmVendor());
		
		/// line.seperator property bilgisini döner.
		System.out.println("SystemProperties.getLineSeparator(): String -> " + SystemProperties.getLineSeparator());
		
		/// Sistem bilgisi döner.
		System.out.println("SystemProperties.getOsName(): String -> " + SystemProperties.getOsName());
		
		/// User home bilgisini döner.
		System.out.println("SystemProperties.getUserHome(): String -> " + SystemProperties.getUserHome());
		
		/// User Name bilgisini döner.
		System.out.println("SystemProperties.getUserName(): String -> " + SystemProperties.getUserName());
		
		/// Bir property tanımlımı değil mi kontrolü yapar tanımli ise true değil ise false döner.
		System.out.println("SystemProperties.has(String): boolean -> " + SystemProperties.has("line.seperator"));
		
		/// Bir property bilgisi döner.
		System.out.println("SystemProperties.is(String): boolean -> " + SystemProperties.is("com.data.is"));
		
		/// org.true.smooth.scrolling bu property bilgisini döner. Default olarak false döner.
		System.out.println("SystemProperties.isTrueSmoothScrollingEnabled(): boolean -> " + SystemProperties.isTrueSmoothScrollingEnabled());
		
	}
}