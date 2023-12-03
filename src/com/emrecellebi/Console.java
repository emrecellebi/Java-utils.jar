package com.emrecellebi;

import java.io.File;
// import java.nio.*;
// import java.nio.charset.*;
// import java.net.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
// import java.util.zip.*;

import com.emrecellebi.openapi.IDisposable;			/// Tamamlandı

import com.emrecellebi.openapi.diagnostic.IControlFlowException;	/// Tamamlandı
import com.emrecellebi.openapi.diagnostic.Logger;					/// Devam Ediyor...
import com.emrecellebi.openapi.diagnostic.LoggerRt;					/// Tamamlandı

import com.emrecellebi.openapi.util.Comparing;		/// Tamamlandı #
import com.emrecellebi.openapi.util.Conditions;		/// Devam Ediyor...
import com.emrecellebi.openapi.util.ICondition;		/// Tamamlandı
import com.emrecellebi.openapi.util.IFactory;		/// Tamamlandı
import com.emrecellebi.openapi.util.IGetter;		/// Tamamlandı
import com.emrecellebi.openapi.util.INotNullFactory;/// Tamamlandı
import com.emrecellebi.openapi.util.ISegment;		/// Tamamlandı
import com.emrecellebi.openapi.util.Pair;			/// Tamamlandı #
import com.emrecellebi.openapi.util.SystemInfo;		/// Devam Ediyor...
import com.emrecellebi.openapi.util.SystemInfoRt;	/// Tamamlandı
import com.emrecellebi.openapi.util.TextRange;		/// Tamamlandı

import com.emrecellebi.openapi.util.io.FileUtilRt;			/// Tamamlandı
import com.emrecellebi.openapi.util.io.NullAppendable;		/// Tamamlandı

import com.emrecellebi.openapi.util.text.ICharSequenceWithStringHash;	/// Tamamlandı #
import com.emrecellebi.openapi.util.text.Pluralizer;					/// Tamamlandı #
import com.emrecellebi.openapi.util.text.Strings;						/// Tamamlandı #
import com.emrecellebi.openapi.util.text.StringUtil;					/// Devam Ediyor...
import com.emrecellebi.openapi.util.text.StringUtilRt;					/// Tamamlandı #

import com.emrecellebi.reference.SoftReference;		/// Tamamlandı

import com.emrecellebi.util.ArrayUtil;				/// Tamamlandı
import com.emrecellebi.util.ArrayUtilRt;			/// Tamamlandı
import com.emrecellebi.util.IArrayFactory;			/// Tamamlandı
import com.emrecellebi.util.IConsumer;				/// Tamamlandı
import com.emrecellebi.util.IFunction;				/// Tamamlandı #
import com.emrecellebi.util.INullableFunction;		/// Tamamlandı #
import com.emrecellebi.util.IProcessor;				/// Tamamlandı
import com.emrecellebi.util.ObjectUtils;			/// Tamamlandı			--> Çok Saçma Method var
import com.emrecellebi.util.PathUtilRt;				/// Tamamlandı
import com.emrecellebi.util.SystemProperties;		/// Tamamlandı
import com.emrecellebi.util.ThreeState;				/// Tamamlandı

import com.emrecellebi.util.containers.IConvertor;	/// Tamamlandı

import com.emrecellebi.util.text.ByteArrayCharSequence;		/// Tamamlandı #
import com.emrecellebi.util.text.CharArrayCharSequence;		/// Tamamlandı #
import com.emrecellebi.util.text.CharArrayUtil;				/// Tamamlandı
import com.emrecellebi.util.text.CharSequenceReader;		/// Tamamlandı #
import com.emrecellebi.util.text.CharSequenceSubSequence;	/// Tamamlandı #
import com.emrecellebi.util.text.ICharArrayExternalizable;	/// Tamamlandı #
import com.emrecellebi.util.text.ICharSequenceBackedByArray;/// Tamamlandı #
import com.emrecellebi.util.text.ImmutableCharSequence;		/// Tamamlandı
import com.emrecellebi.util.text.ImmutableText;				/// Tamamlandı
import com.emrecellebi.util.text.MergingCharSequence;		/// Tamamlandı #
import com.emrecellebi.util.text.UnsyncCharArrayReader;		/// Tamamlandı #

import com.emrecellebi.util.io.URLUtil;				/// Tamamlandı

/** Kişisel Kendi Sınıflarım **/
import com.emrecellebi.ObjectArray;
import com.emrecellebi.ObjectFunction;
import com.emrecellebi.ObjectFactory;

public class Console
{
	public static void main(String[] args)
	{
		System.out.println("alwaysFalse: " + Conditions.alwaysFalse());
		System.out.println("*********************************************************************************************");
		
		System.out.println("alwaysTrue: " + Conditions.alwaysTrue());
		System.out.println("*********************************************************************************************");
		
		System.out.println("and: " + Conditions.and((ICondition<String>)str -> str.length() < 5, (ICondition<String>)str -> str.startsWith("Hello")).value("Hello World!"));
		System.out.println("*********************************************************************************************");
		
		System.out.println("and2: " + Conditions.and2((ICondition<String>)str -> str.length() > 5, (ICondition<String>)str -> str.startsWith("Hello")).value("Hello World!"));
		System.out.println("*********************************************************************************************");
		
		System.out.println("assignableTo: " + Conditions.assignableTo(Object.class).value(String.class));
		System.out.println("assignableTo: " + Conditions.assignableTo(String.class).value(Integer.class));
		System.out.println("*********************************************************************************************");
		
		System.out.println("cached: " + Conditions.cached((ICondition<String>)str -> str.length() > 5).value("Hello World!"));
		System.out.println("*********************************************************************************************");
		
		System.out.println("compose: " + Conditions.compose((IFunction<Integer, String>)num -> String.valueOf(num * 2), (ICondition<String>)str -> str.length() > 5).value(3));
		System.out.println("*********************************************************************************************");
		
		System.out.println("constant: " + Conditions.constant(true));
		System.out.println("constant: " + Conditions.constant(false));
		System.out.println("*********************************************************************************************");
		
		System.out.println("equalTo: " + Conditions.equalTo("Hi").value("Hi"));
		System.out.println("equalTo: " + Conditions.equalTo("Hi").value("HI"));
		System.out.println("*********************************************************************************************");
		
		System.out.println("instanceOf: " + Conditions.instanceOf(String.class).value("Hi"));
		System.out.println("*********************************************************************************************");
		
		System.out.println("instanceOf: " + Conditions.instanceOf(Integer.class, Long.class, String.class).value("Hi"));
		System.out.println("*********************************************************************************************");
		
		System.out.println("is: " + Conditions.is("Hi").value("Hi"));
		System.out.println("is: " + Conditions.is("Hi").value("HI"));
		System.out.println("*********************************************************************************************");
		
		
		// not
		
/**
	------------------> public static <T> ICondition<T> alwaysFalse() <------------------
	Her zaman False şartını döner.
	
	*********************************************************************************************
	
	------------------> public static <T> ICondition<T> alwaysTrue() <------------------
	Her zaman True şartını döner.
	
	*********************************************************************************************
	
	------------------> public static <T> ICondition<T> and(ICondition<? super T> c1, ICondition<? super T> c2) <------------------
	Verilen her iki şart doğru ise true döner.
	
	*********************************************************************************************
	
	------------------> public static <T> ICondition<T> and2(ICondition<? super T> c1, ICondition<? super T> c2) <------------------
	Verilen her iki şart doğru ise true döner.
	
	*********************************************************************************************
	
	------------------> public static ICondition<Class<?>> assignableTo(final Class<?> clazz) <------------------
	Verilen değer ve belirlenen value değeri ile atanabilir olup olmadığını kontrol eder.
	
	*********************************************************************************************
	
	------------------> public static <T> ICondition<T> cached(ICondition<? super T> c) <------------------
	Verilen her koşulu cacheler ve istenidiği zaman çağrır kullanır.
	
	*********************************************************************************************
	
	------------------> public static <T> ICondition<T> constant(boolean value) <------------------
	Sabit tanımlı olan boolean değerlerini döner.
	
	*********************************************************************************************
	
	------------------> public static <T> Condition<T> equalTo(final Object option) <------------------
	Verilen object değerini belirlenen value ile eşitlik kontrolü yapar.
	
	*********************************************************************************************
	
	------------------> public static <T> ICondition<T> instanceOf(final Class<?> clazz) <------------------
	Verilen sınıf tipinde bir instance olup olmadığını döner.
	
	------------------> public static <T> ICondition<T> instanceOf(Class<?>... clazz) <------------------
	Verilen sınıf tipinde bir array instance olup olmadığını döner.
	
	*********************************************************************************************
	
	------------------> public static <T> ICondition<T> is(T option) <------------------
	Verilen object değerini belirlenen value ile eşitlik kontrolü yapar.
	
	
	
	
	*********************************************************************************************
	------------------>  <------------------
**/
		

		
		/**
			FileUtil
				import com.intellij.UtilBundle;
				import com.intellij.openapi.diagnostic.Logger;
				import com.intellij.openapi.util.NlsSafe;
				import com.intellij.openapi.util.SystemInfo;
				import com.intellij.openapi.util.text.StringUtil;
				import com.intellij.util.concurrency.AppExecutorUtil;
				import com.intellij.util.containers.ContainerUtil;
					import com.intellij.openapi.util.Conditions;			--> Sıra Bunda
					import com.intellij.openapi.util.Couple;
					import com.intellij.openapi.util.Disposer;
					import com.intellij.util.DeprecatedMethodException;
					import com.intellij.util.PairConsumer;
					import com.intellij.util.SmartList;
				import com.intellij.util.containers.JBIterable;
				import com.intellij.util.containers.JBTreeTraverser;
				import com.intellij.util.text.FilePathHashingStrategy;
		**/
		
		// boolean, byte, int, long, char, short, float, double
		
		// SoftReference bununla ilgili örnekler yapılıcak
		
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
			ThreadLocal her iş parçacığı kendi özel verisini saklamasına olanak tanır. Diğer iş parçacıklarından bu verilere 
			erişimi engeller.
			
			Abstract ve Interface sınıflardan nesne türetilemez
			
			Abstract Reader sınıfını A adındaki class extends ederse bu sınıfın içindeki abstract methodları Override edilir.
			Eğer A adında bir nesne oluşturulup tipini Reader olarak convert yapılırsa buraki abstract olan read methodu çağrıldığında A sınıfındaki
			Override edilen read methodu çağrılır.
		**/
	}
}