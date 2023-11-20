package com.emrecellebi;

import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.charset.*;

import com.emrecellebi.logging.Logger;				/// Devam Ediyor...
import com.emrecellebi.logging.LoggerRt;			/// Tamamlandı

import com.emrecellebi.ICharArrayExternalizable;	/// Tamamlandı
import com.emrecellebi.ICharSequenceBackedByArray;	/// Tamamlandı
import com.emrecellebi.ICharSequenceWithStringHash;	/// Tamamlandı
import com.emrecellebi.IFunction;					/// Tamamlandı
import com.emrecellebi.INullableFunction;			/// Tamamlandı
import com.emrecellebi.IProcessor;					/// Tamamlandı
import com.emrecellebi.ISegment;					/// Tamamlandı
import com.emrecellebi.NullAppendable;				/// Tamamlandı
import com.emrecellebi.ThreeState;					/// Tamamlandı

import com.emrecellebi.util.ArrayUtilRt;			/// Tamamlandı
import com.emrecellebi.util.CharArrayUtil;			/// Tamamlandı
import com.emrecellebi.util.Comparing;				/// Tamamlandı #
import com.emrecellebi.util.FileUtilRt;				/// Tamamlandı
import com.emrecellebi.util.PathUtilRt;				/// Tamamlandı
import com.emrecellebi.util.Strings;				/// Tamamlandı #
import com.emrecellebi.util.StringUtil;				/// Devam Ediyor...
import com.emrecellebi.util.StringUtilRt;			/// Tamamlandı #
import com.emrecellebi.util.SystemInfo;				/// Devam Ediyor...
import com.emrecellebi.util.SystemInfoRt;			/// Tamamlandı
import com.emrecellebi.util.URLUtil;				/// Devam Ediyor...

import com.emrecellebi.text.ByteArrayCharSequence;		/// Tamamlandı
import com.emrecellebi.text.CharArrayCharSequence;		/// Tamamlandı
import com.emrecellebi.text.CharSequenceReader;			/// Tamamlandı
import com.emrecellebi.text.CharSequenceSubSequence;	/// Tamamlandı
import com.emrecellebi.text.ImmutableCharSequence;		/// Tamamlandı
import com.emrecellebi.text.ImmutableText;				/// Tamamlandı
import com.emrecellebi.text.MergingCharSequence;		/// Tamamlandı
import com.emrecellebi.text.Pair;						/// Tamamlandı
import com.emrecellebi.text.Pluralizer;					/// Tamamlandı #
import com.emrecellebi.text.TextRange;					/// Tamamlandı
import com.emrecellebi.text.UnsyncCharArrayReader;		/// Tamamlandı

/** Kişisel Kendi Sınıflarım **/
import com.emrecellebi.util.ObjectFactory;

public class Console
{
	public static <T> void instance(T seq)
	{
		if(seq instanceof String) System.out.println("String");
		if(seq instanceof StringBuilder) System.out.println("StringBuilder");
		if(seq instanceof StringBuffer) System.out.println("StringBuffer");
		if(seq instanceof CharBuffer) System.out.println("CharBuffer");
		if(seq instanceof CharSequence) System.out.println("CharSequence");
	}
	
	public static void main(String[] args)
	{
		String uri = "http://78.135.80.229:32764/orders/n11/update?companyCode=Fr-5500175&number=205818585916";
		
		/// Bir URL de (mailto: :// www.) geçiyor ise true döner.
		System.out.println("URLUtil.canContainUrl(String): boolean -> " + URLUtil.canContainUrl(uri));
		
		/// Bir URL de (://) geçiyor ise true döner.
		System.out.println("URLUtil.containsScheme(String): boolean -> " + URLUtil.containsScheme(uri));
		
		/// Bir URL de %20 gibi değerleri decode eder.
		System.out.println("URLUtil.decode(String): String -> " + URLUtil.decode(uri));
		
		/// Bir URL encode eder.
		System.out.println("URLUtil.encodeURIComponent(String): String -> " + URLUtil.encodeURIComponent(uri));
		
		/// Bir html veya her hangi data bilgisindeki URL bulur ve TextRange olarak geri döner.
		System.out.println("URLUtil.findUrl(CharSequence, int, int): TextRange -> " + URLUtil.findUrl(uri, 0, uri.length()));
		
		byte[] bytes = new byte[0];
		System.out.print("URLUtil.getBytesFromDataUri(String): byte[] -> ");
		// bytes = URLUtil.getBytesFromDataUri(url);
		for(int i = 0; i < bytes.length; i++)
			System.out.print(bytes[i]);
		System.out.println();
		
		// URL url = null;
		// try{url = URLUtil.getJarEntryURL(new File("../utils.jar"), "com/emrecellebi/util/URLUtil");}catch(Exception e){}
		// System.out.println("URLUtil.getJarEntryURL(File, String): URL -> " + url);
		
		// System.out.println("URLUtil.isDataUri(String): boolean -> " + URLUtil.isDataUri("data:" + uri));
		// System.out.println("URLUtil.isDataUri(String): boolean -> " + URLUtil.isDataUri(uri));
		// System.out.println("URLUtil.openJarStream(URL): InputStream -> " + URLUtil.openJarStream());
		
		
		// try{Thread.sleep(10000);}catch(Exception e){}
		
		
		
		
		

		
		// Logger log = Logger.getInstance(Console.class);
		
		
		
		
		
		
		
		
		
		/**
			Verieln string içerisinde \r karakter var ise bir AssertionError döner. 
		**/
		// System.out.println("StringUtil.assertValidSeparators(CharSequence): void");
		// StringUtil.assertValidSeparators("Lorem Ipsum is simply dummy text of the printing and type setting industry.");
		
		// System.out.println("StringUtil.capitalize(String): String -> " + StringUtil.capitalize("hello world!"));
		
		// System.out.println("StringUtil.capitalizeWithJavaBeanConvention(String): String -> " + StringUtil.capitalizeWithJavaBeanConvention("hello world!"));
		
		// System.out.println("StringUtil.capitalizeWords(String, String, boolean, boolean): String -> " + StringUtil.capitalizeWords("hello world!", " ", true, true));
		// System.out.println("StringUtil.capitalizeWords(String, boolean): String -> " + StringUtil.capitalizeWords("hello world!", true));
		
		// System.out.println("StringUtil.charsEqualIgnoreCase(char, char): boolean -> " + StringUtil.charsEqualIgnoreCase('a', 'a'));
		// System.out.println("StringUtil.charsEqualIgnoreCase(char, char): boolean -> " + StringUtil.charsEqualIgnoreCase('a', 'A'));
		
		// System.out.println("StringUtil.collapseWhiteSpace(CharSequence): String -> " + StringUtil.collapseWhiteSpace("Hello World!"));
		
		// System.out.println("StringUtil.commonPrefix(String, String): String -> " + StringUtil.commonPrefix("Hello World!", "Hello"));
		
		// System.out.println("StringUtil.commonPrefixLength(CharSequence, CharSequence): int -> " + StringUtil.commonPrefixLength("Hello World!", "hello world!"));
		// System.out.println("StringUtil.commonPrefixLength(CharSequence, CharSequence, boolean): int -> " + StringUtil.commonPrefixLength("Hello World!", "hello world!", true));
		
		// System.out.println("StringUtil.commonSuffix(String, String): String -> " + StringUtil.commonSuffix("Hello World!", "Hello"));
		
		// System.out.println("StringUtil.commonSuffixLength(CharSequence, CharSequence): int -> " + StringUtil.commonSuffixLength("Hello World!", "hello world!"));
		
		// System.out.println("StringUtil.compare(CharSequence, CharSequence, boolean): int -> " + StringUtil.compare("Hello World!", "hello world!", false));
		// System.out.println("StringUtil.compare(CharSequence, CharSequence, boolean): int -> " + StringUtil.compare("Hello World!", "hello world!", true));
		
		// System.out.println("StringUtil.compare(String, String, boolean): int -> " + StringUtil.compare("Hello World!", "hello world!", false));
		// System.out.println("StringUtil.compare(String, String, boolean): int -> " + StringUtil.compare("Hello World!", "hello world!", true));
		
		// System.out.println("StringUtil.compare(char, char, boolean): int -> " + StringUtil.compare('a', 'a', false));
		// System.out.println("StringUtil.compare(char, char, boolean): int -> " + StringUtil.compare('a', 'A', true));
		
		// System.out.println("StringUtil.comparePairs(String, String, String, String, boolean): int -> " + StringUtil.comparePairs("Hello World!", "Hello World!", "Hello World!", "Hello World!", true));
		
		// System.out.println("StringUtil.compareVersionNumbers(String, String): int -> " + StringUtil.compareVersionNumbers("1.0", "1.0"));
		// System.out.println("StringUtil.compareVersionNumbers(String, String): int -> " + StringUtil.compareVersionNumbers("1.0", "1.1"));
		
		// System.out.println("StringUtil.contains(CharSequence, CharSequence): boolean -> " + StringUtil.contains("Hello World!", "!"));
		// System.out.println("StringUtil.contains(CharSequence, int, int, char): boolean -> " + StringUtil.contains("Hello World!", 0, 12, '!'));
		
		// System.out.println("StringUtil.containsAlphaCharacters(String): boolean -> " + StringUtil.containsAlphaCharacters("Hello World!"));
		
		// System.out.println("StringUtil.containsAnyChar(String, String): boolean -> " + StringUtil.containsAnyChar("Hello World!", "Hello World!"));
		// System.out.println("StringUtil.containsAnyChar(String, String, int, int): boolean -> " + StringUtil.containsAnyChar("Hello World!", "Hello World!", 0, 12));
		
		// System.out.println("StringUtil.containsChar(String, char): boolean -> " + StringUtil.containsChar("Hello World!", 'W'));
		
		// System.out.println("StringUtil.containsIgnoreCase(String, String): boolean -> " + StringUtil.containsIgnoreCase("Hello World!", "world"));
		
		// System.out.println("StringUtil.containsLineBreak(CharSequence): boolean -> " + StringUtil.containsLineBreak("Hello \nWorld!"));
		// System.out.println("StringUtil.containsLineBreak(CharSequence): boolean -> " + StringUtil.containsLineBreak("Hello \rWorld!"));
		// System.out.println("StringUtil.containsLineBreak(CharSequence): boolean -> " + StringUtil.containsLineBreak("Hello World!"));
		
		// System.out.println("StringUtil.containsWhitespaces(CharSequence): boolean -> " + StringUtil.containsWhitespaces("Hello World!"));
		// System.out.println("StringUtil.containsWhitespaces(CharSequence): boolean -> " + StringUtil.containsWhitespaces("HelloWorld!"));
		
		// System.out.println("StringUtil.convertLineSeparators(String): String -> " + StringUtil.convertLineSeparators("Hello World!"));
		// System.out.println("StringUtil.convertLineSeparators(String, String): String -> " + StringUtil.convertLineSeparators("Hello World!", "-"));
		// System.out.println("StringUtil.convertLineSeparators(String, String, int[]): String -> " + StringUtil.convertLineSeparators("Hello World!", "-", null));
		// System.out.println("StringUtil.convertLineSeparators(String, boolean): String -> " + StringUtil.convertLineSeparators("Hello World!", true));
		
		// System.out.println("StringUtil.countChars(CharSequence, char): int -> " + StringUtil.countChars("Hello World!", 'o'));
		// System.out.println("StringUtil.countChars(CharSequence, char, int, boolean): int -> " + StringUtil.countChars("Hello World!", 'o', 0, false));
		// System.out.println("StringUtil.countChars(CharSequence, char, int, int, boolean): int -> " + StringUtil.countChars("Hello World!", 'o', 0, 12, false));
		
		// System.out.println("StringUtil.countNewLines(CharSequence): int -> " + StringUtil.countNewLines("Hello\nWorld!"));
		
		// IFunction<String, String> func = StringUtil.createToStringFunction(String.class);
		// System.out.println("StringUtil.createToStringFunction(Class<T>): int -> " + func.fun("Hello World!"));
		
		// System.out.println("StringUtil.decapitalize(String): String -> " + StringUtil.decapitalize("Hello world!"));
		
		// System.out.println("StringUtil.defaultIfEmpty(String, String): String -> " + StringUtil.defaultIfEmpty("Hello world!", "default"));
		// System.out.println("StringUtil.defaultIfEmpty(String, String): String -> " + StringUtil.defaultIfEmpty("", "default"));
		
		
		
		
		
		
		/**
			Verilen bir string datasında verieln karakterlerin \ ile atlatma seçeneğini verir.
		**/
		// String str = "Lorem Ipsum is simply dummy text of the printing and type setting industry.";
		// System.out.println("StringUtil.escapeStringCharacters(String): String -> " + StringUtil.escapeStringCharacters(str));
		// System.out.println("StringUtil.escapeStringCharacters(int, String, String, StringBuilder): StringBuilder -> " + StringUtil.escapeStringCharacters(str.length(), str, "ry", new StringBuilder()));
		// System.out.println("StringUtil.escapeStringCharacters(int, String, String, boolean, StringBuilder): StringBuilder -> " + StringUtil.escapeStringCharacters(str.length(), str, "ry", false, new StringBuilder()));
		// System.out.println("StringUtil.escapeStringCharacters(int, String, String, boolean, boolean, StringBuilder): StringBuilder -> " + StringUtil.escapeStringCharacters(str.length(), str, "ry", false, false, new StringBuilder()));
		
		// System.out.print("StringUtil.escapeStringCharacters(int, String, StringBuilder): void -> ");
		// StringBuilder sb = new StringBuilder();
		// StringUtil.escapeStringCharacters(str.length(), str, "ry", sb);
		// System.out.println(sb);
		
		// System.out.println("StringUtil.first(CharSequence, int, boolean): CharSequence -> " + StringUtil.first((CharSequence)"Hello World!", 5, false));
		// System.out.println("StringUtil.first(CharSequence, int, boolean): CharSequence -> " + StringUtil.first((CharSequence)"Hello World!", 5, true));
		
		// System.out.println("StringUtil.first(String, int, boolean): String -> " + StringUtil.first((String)"Hello World!", 5, false));
		// System.out.println("StringUtil.first(String, int, boolean): String -> " + StringUtil.first((String)"Hello World!", 5, true));
		
		// System.out.println("StringUtil.last(CharSequence, int, boolean): CharSequence -> " + StringUtil.last((CharSequence)"Hello World!", 5, false));
		// System.out.println("StringUtil.last(CharSequence, int, boolean): CharSequence -> " + StringUtil.last((CharSequence)"Hello World!", 5, true));
		
		// System.out.println("StringUtil.isPrintableUnicode(char): boolean -> " + StringUtil.isPrintableUnicode('a'));
		
		// System.out.println("StringUtil.toUpperCase(String): String -> " + StringUtil.toUpperCase("Hello World!"));
		
		// System.out.println("StringUtil.toUpperCase(char): char -> " + StringUtil.toUpperCase('a'));
		
		
		
		
		
		
		
		
		
		
		// System.out.println("SystemInfo.OS_NAME: String -> " + SystemInfo.OS_NAME);
		// System.out.println("SystemInfo.OS_VERSION: String -> " + SystemInfo.OS_VERSION);
		// System.out.println("SystemInfo.JAVA_VERSION: String -> " + SystemInfo.JAVA_VERSION);
		// System.out.println("SystemInfo.JAVA_RUNTIME_VERSION: String -> " + SystemInfo.JAVA_RUNTIME_VERSION);
		// System.out.println("SystemInfo.JAVA_VENDOR: String -> " + SystemInfo.JAVA_VENDOR);
		// System.out.println("SystemInfo.isWindows: boolean -> " + SystemInfo.isWindows);
		// System.out.println("SystemInfo.isMac: boolean -> " + SystemInfo.isMac);
		// System.out.println("SystemInfo.isLinux: boolean -> " + SystemInfo.isLinux);
		// System.out.println("SystemInfo.isFreeBSD: boolean -> " + SystemInfo.isFreeBSD);
		// System.out.println("SystemInfo.isSolaris: boolean -> " + SystemInfo.isSolaris);
		// System.out.println("SystemInfo.isUnix: boolean -> " + SystemInfo.isUnix);
		// System.out.println("SystemInfo.isChromeOS: boolean -> " + SystemInfo.isChromeOS);
		// System.out.println("SystemInfo.isAppleJvm: boolean -> " + SystemInfo.isAppleJvm);
		// System.out.println("SystemInfo.isOracleJvm: boolean -> " + SystemInfo.isOracleJvm);
		// System.out.println("SystemInfo.isSunJvm: boolean -> " + SystemInfo.isSunJvm);
		// System.out.println("SystemInfo.isIbmJvm: boolean -> " + SystemInfo.isIbmJvm);
		// System.out.println("SystemInfo.isAzulJvm: boolean -> " + SystemInfo.isAzulJvm);
		// System.out.println("SystemInfo.isJetBrainsJvm: boolean -> " + SystemInfo.isJetBrainsJvm);
		// System.out.println("SystemInfo.IS_AT_LEAST_JAVA9: boolean -> " + SystemInfo.IS_AT_LEAST_JAVA9);
		
		
		
	
		
		
		// String str = "Lorem Ipsum is simply dummy text of the printing and type setting industry.";
		// int len = str.length();
		
		// int ch = CharArrayUtil.shiftForwardCarefully(str, 0, "m t");
		// System.out.println("Length: " + len + " int: " + ch + " char: " + str.charAt(ch));
		
		
		
		// instance(new String("Hello World!"));
		// instance(new StringBuilder("Hello World!"));
		// instance(new StringBuffer("Hello World!"));
		// instance((CharSequence)"Hello World!");
		
		
		// CharArrayUtil
		// ImmutableCharSequence
		// ImmutableText
		// CharSequenceSubSequence
		
		
		// JavaVersion ver = JavaVersion.current();
		// System.out.println("Feature: " + ver.feature + " Minor: " + ver.minor + " Update: " + ver.update + " Build: " + ver.build + " EA: " + ver.ea);
		// System.out.println("current(): JavaVersion -> " + JavaVersion.current());
		// System.out.println("compareTo(JavaVersion): int -> " + JavaVersion.current().compareTo(JavaVersion.compose(8, 0, 271, 9, false)));
		// System.out.println("compose(int): JavaVersion -> " + JavaVersion.compose(8));
		// System.out.println("compose(int, int, int, int, boolean): JavaVersion -> " + JavaVersion.compose(8, 0, 12, 0, false));
		// System.out.println("toString(): String -> " + ver.toString());
		// System.out.println("toFeatureString(): String -> " + ver.toFeatureString());
		// System.out.println("toFeatureMinorUpdateString(): String -> " + ver.toFeatureMinorUpdateString());
		// System.out.println("parse(String): JavaVersion -> " + JavaVersion.parse(System.getProperty("java.version")));
		// System.out.println("tryParse(String): JavaVersion -> " + JavaVersion.tryParse(System.getProperty("java.version")));
		// System.out.println("equals(Object): boolean -> " + ver.equals(JavaVersion.current()));
		// System.out.println("hashCode(): int -> " + ver.hashCode());
		// System.out.println("isAtLeast(int): boolean -> " + ver.isAtLeast(5));
		
		
		
		
		
		
		
		
		
		// ResourceBundle bundle = ResourceBundle.getBundle("messages", new UTF8ResourceControl());
		// System.out.println(bundle.getString("a1"));
		
		/**
			s -> Verilen String data
			c -> Aranacak karakter
			start -> Arama başlangıcı
			end -> Arama sonu
			
			Verilen String içerisinde verilen karakteri arar ve index numarasını döner.
		**/
		// String str1 = "abcd%20deded";
		// System.out.println(StringUtil.indexOf(str1, '%', 0, str1.length()));
		
		/**
			s -> Verilen URL bilgisi
			form -> Arama başlangıcı
			end -> Arama sonu
			
			Verilen URL bilgisini decode eder. 
		**/
		// String str2 = "%20%21%22%23%24%25%26%27%28%29%2A%2B%2C%2D%2E%2F";
		// System.out.println(URLUtil.unescapePercentSequences(str2, 0, str2.length()));
	
		
		/**
			ThreadLocal her iş parçacığı kendi özel verisini saklamasına olanak tanır. Diğer iş parçacıklarından bu verilere 
			erişimi engeller.
			
			Abstract ve Interface sınıflardan nesne türetilemez
			
			Abstract Reader sınıfını A adındaki class extends ederse bu sınıfın içindeki abstract methodları Override edilir.
			Eğer A adında bir nesne oluşturulup tipini Reader olarak convert yapılırsa buraki abstract olan read methodu çağrıldığında A sınıfındaki
			Override edilen read methodu çağrılır.
		**/
		
		// https://play.apktruck.com/Spotify%20v8.8.80.599_apktruck.com.apk
	}
}