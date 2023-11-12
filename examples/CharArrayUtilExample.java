package com.emrecellebi;

import com.emrecellebi.util.text.CharArrayUtil;

public class CharArrayUtilExample
{
	public static void main(String[] args)
	{
		/**
			containLineBreaks(CharSequence): boolean --> Verilen bir string içerisinde \r \n gibi karakterler olursa true değerini döner.
		**/
		System.out.println("CharArrayUtil.containLineBreaks(CharSequence): boolean -> " + CharArrayUtil.containLineBreaks((CharSequence)"Hello\n World!"));
		System.out.println("CharArrayUtil.containLineBreaks(CharSequence): boolean -> " + CharArrayUtil.containLineBreaks((CharSequence)"Hello\r World!"));
		System.out.println("CharArrayUtil.containLineBreaks(CharSequence): boolean -> " + CharArrayUtil.containLineBreaks((CharSequence)"Hello World!"));
		
		/**
			containLineBreaks(CharSequence, int, int): boolean --> Verilen bir string içerisinde \r \n gibi karakterler olursa true değerini döner.
		**/
		System.out.println("CharArrayUtil.containLineBreaks(CharSequence, int, int): boolean -> " + CharArrayUtil.containLineBreaks((CharSequence)"Hello\r World!", 0, 12));
		System.out.println("CharArrayUtil.containLineBreaks(CharSequence, int, int): boolean -> " + CharArrayUtil.containLineBreaks((CharSequence)"Hello\n World!", 0, 12));
		System.out.println("CharArrayUtil.containLineBreaks(CharSequence, int, int): boolean -> " + CharArrayUtil.containLineBreaks((CharSequence)"Hello World!", 0, 12));
		
		/**
			containsOnlyWhiteSpaces(CharSequence): boolean --> Verilen string içerisinde boşluk \r \t \n gibi karakterleri var ise true döner.
		**/
		System.out.println("CharArrayUtil.containsOnlyWhiteSpaces(CharSequence): boolean -> " + CharArrayUtil.containsOnlyWhiteSpaces((CharSequence)"Hello World!"));
		System.out.println("CharArrayUtil.containsOnlyWhiteSpaces(CharSequence): boolean -> " + CharArrayUtil.containsOnlyWhiteSpaces((CharSequence)"Hello\rWorld!"));
		System.out.println("CharArrayUtil.containsOnlyWhiteSpaces(CharSequence): boolean -> " + CharArrayUtil.containsOnlyWhiteSpaces((CharSequence)"Hello\tWorld!"));
		System.out.println("CharArrayUtil.containsOnlyWhiteSpaces(CharSequence): boolean -> " + CharArrayUtil.containsOnlyWhiteSpaces((CharSequence)"Hello\nWorld!"));
		System.out.println("CharArrayUtil.containsOnlyWhiteSpaces(CharSequence): boolean -> " + CharArrayUtil.containsOnlyWhiteSpaces((CharSequence)"HelloWorld! "));
		System.out.println("CharArrayUtil.containsOnlyWhiteSpaces(CharSequence): boolean -> " + CharArrayUtil.containsOnlyWhiteSpaces((CharSequence)" HelloWorld!"));
		System.out.println("CharArrayUtil.containsOnlyWhiteSpaces(CharSequence): boolean -> " + CharArrayUtil.containsOnlyWhiteSpaces((CharSequence)"HelloWorld!"));
		
		/**
			equals(char[], int, int, char[], int, int): boolean --> Verilen iki char arrayini karaşılaştırır.
		**/
		System.out.println("CharArrayUtil.equals(char[], int, int, char[], int, int): boolean -> " + CharArrayUtil.equals("Hello World!".toCharArray(), 0, 12, "Hello World!".toCharArray(), 0, 12));
		System.out.println("CharArrayUtil.equals(char[], int, int, char[], int, int): boolean -> " + CharArrayUtil.equals("Hello World!".toCharArray(), 0, 12, "hello World!".toCharArray(), 0, 12));
		
		/**
			fromSequence(CharSequence): char[] --> CharSequence tipindeki bir datayı char[] tipine dönüştürür.
			1) Source CharSequence değeri.
		**/
		System.out.print("CharArrayUtil.fromSequence(CharSequence): char[] -> ");
		char[] buffer4 = CharArrayUtil.fromSequence("Hello World!");
		for(int i = 0; i < buffer4.length; i++)
			System.out.print(buffer4[i]);
		System.out.println();
		
		/**
			fromSequence(CharSequence, int, int): char[] --> CharSequence tipindeki bir datayı char[] tipine dönüştürür.
			1) Source CharSequence değeri.
			2) Start değeri.
			3) End değeri.
		**/
		System.out.print("CharArrayUtil.fromSequence(CharSequence, int, int): char[] -> ");
		char[] buffer3 = CharArrayUtil.fromSequence("Hello World!", 0, 12);
		for(int i = 0; i < buffer3.length; i++)
			System.out.print(buffer3[i]);
		System.out.println();
		
		/**
			fromSequenceWithoutCopying(CharSequence): char[] --> CharSequence tipindeki bir datayı char[] tipine dönüştürür.
		**/
		System.out.print("CharArrayUtil.fromSequenceWithoutCopying(CharSequence): char[] -> ");
		char[] buffer = CharArrayUtil.fromSequenceWithoutCopying("Hello World!");
		for(int i = 0; i < buffer.length; i++)
			System.out.print(buffer[i]);
		System.out.println();
		
		/**
			getChars(CharSequence, char[], int): void --> CharSequence değerini char[] değerine aktarım yapar.
			1) Source CharSequence değeri.
			2) Destenation char[] arrayi
			3) Destenation offset değeri
		**/
		System.out.print("CharArrayUtil.getChars(CharSequence, char[], int): void -> ");
		char[] buffer6 = new char[12];
		CharArrayUtil.getChars("Hello World!", buffer6, 0);
		for(int i = 0; i < buffer6.length; i++)
			System.out.print(buffer6[i]);
		System.out.println();
		
		/**
			getChars(CharSequence, char[], int, int): void --> CharSequence değerini char[] değerine aktarım yapar.
			1) Source CharSequence değeri.
			2) Destenation char[] arrayi
			3) Destenation offset değeri
			4) Source uzunluk
		**/
		System.out.print("CharArrayUtil.getChars(CharSequence, char[], int, int): void -> ");
		char[] buffer5 = new char[12];
		CharArrayUtil.getChars("Hello World!", buffer5, 0, 12);
		for(int i = 0; i < buffer5.length; i++)
			System.out.print(buffer5[i]);
		System.out.println();
		
		/**
			getChars(CharSequence, char[], int, int, int): void --> CharSequence değerini char[] değerine aktarım yapar.
			1) Source CharSequence değeri.
			2) Destenation char[] arrayi
			3) Source offset değeri
			4) Destenation offset değeri
			5) Source uzunluk
		**/
		System.out.print("CharArrayUtil.getChars(CharSequence, char[], int, int, int): void -> ");
		char[] buffer2 = new char[12];
		CharArrayUtil.getChars("Hello World!", buffer2, 0, 0, 12);
		for(int i = 0; i < buffer2.length; i++)
			System.out.print(buffer2[i]);
		System.out.println();
		
		/**
			getIndents(CharSequence, int): TextRange --> Verelen CharSequence datası içindeki \n karakterleri kadar TextRange[] artayi döner.
			1) Source CharSequence değeri.
			2) Sağa doğru siftleme değeri
		**/
		System.out.print("CharArrayUtil.getIndents(CharSequence, int): TextRange -> ");
		TextRange[] ranges = CharArrayUtil.getIndents("H\ne\nl\nl\no\nW\no\nr\nl\nd\n!\n", 64);
		for(int i = 0; i < ranges.length; i++)
			System.out.print(ranges[i]);
		System.out.println();
		
		/**
			indexOf(CharSequence, CharSequence, int): int --> Verelen CharSequence datası içerisinde index bulunacak kelime verilir.
			1) Source CharSequence değeri.
			2) Bulunacak CharSequence değeri
			3) Başlangıc değeri
		**/
		System.out.println("CharArrayUtil.indexOf(CharSequence, CharSequence, int): int -> " + CharArrayUtil.indexOf("Hello World!", "World", 0));
		
		/**
			indexOf(CharSequence, CharSequence, int, int): int --> Verelen CharSequence datası içerisinde index bulunacak kelime verilir.
			1) Source CharSequence değeri.
			2) Bulunacak CharSequence değeri
			3) Başlangıc değeri
			4) Bitiş değeri
		**/
		System.out.println("CharArrayUtil.indexOf(CharSequence, CharSequence, int, int): int -> " + CharArrayUtil.indexOf("Hello World!", "World", 0, 12));
		
		/**
			indexOf(char[], String, int): int --> Verelen char[] datası içerisinde index bulunacak kelime verilir.
			1) Source char[] değeri.
			2) Bulunacak String değeri
			3) Başlangıc değeri
		**/
		System.out.println("CharArrayUtil.indexOf(char[], String, int): int -> " + CharArrayUtil.indexOf("Hello World!".toCharArray(), "World", 0));
		
		/**
			indexOf(char[], char, int, int): int --> Verelen char[] datası içerisinde index bulunacak karakter verilir.
			1) Source char[] değeri.
			2) Bulunacak char değeri
			3) Başlangıc değeri
			4) Bitiş değeri
		**/
		System.out.println("CharArrayUtil.indexOf(char[], char, int, int): int -> " + CharArrayUtil.indexOf("Hello World!".toCharArray(), 'W', 0, 12));
		
		/**
			isEmptyOrSpaces(CharSequence, int, int): boolean --> Verelen CharSequence datası içerisinde sadece boşluk \n \t gibi karakterler var ise true döner.
			1) Source CharSequence değeri.
			2) Başlangıc değeri
			3) Bitiş değeri
		**/
		System.out.println("CharArrayUtil.isEmptyOrSpaces(CharSequence, int, int): boolean -> " + CharArrayUtil.isEmptyOrSpaces("Hello World!", 0, 12));
		System.out.println("CharArrayUtil.isEmptyOrSpaces(CharSequence, int, int): boolean -> " + CharArrayUtil.isEmptyOrSpaces(" \n\t", 0, 2));
		
		/**
			isSuitable(String, char): boolean --> Verelen String datası içerisinde belirlenen karakteri bulur true olarak döner.
			1) Source String değeri.
			2) Aranacak char değeri
		**/
		System.out.println("CharArrayUtil.isSuitable(String, char): boolean -> " + CharArrayUtil.isSuitable("Hello World!", 'W'));
		System.out.println("CharArrayUtil.isSuitable(String, char): boolean -> " + CharArrayUtil.isSuitable("Hello World!", 'w'));
		
		/**
			lastIndexOf(CharSequence, String, int): int --> Verelen CharSequence datası içerisinde index bulunacak kelime verilir.
			1) Source CharSequence değeri.
			2) Bulunacak String değeri
			3) Source uzunluk değeri
		**/
		System.out.println("CharArrayUtil.lastIndexOf(CharSequence, String, int): int -> " + CharArrayUtil.lastIndexOf("Hello World!", "World", 12));
		
		/**
			lastIndexOf(char[], String, int): int --> Verelen char[] datası içerisinde index bulunacak kelime verilir.
			1) Source char[] değeri.
			2) Bulunacak String değeri
			3) Source uzunluk değeri
		**/
		System.out.println("CharArrayUtil.lastIndexOf(char[], String, int): int -> " + CharArrayUtil.lastIndexOf("Hello World!".toCharArray(), "World", 12));
		
		/**
			lastIndexOf(char[], char, int, int): int --> Verelen char[] datası içerisinde index bulunacak karakter verilir.
			1) Source char[] değeri.
			2) Bulunacak char değeri
			3) Başlangıc değeri
			4) Bitiş değeri
		**/
		System.out.println("CharArrayUtil.lastIndexOf(char[], char, int, int): int -> " + CharArrayUtil.lastIndexOf("Hello World!".toCharArray(), 'W', 0, 12));
		
		/**
			readerFromCharSequence(CharSequence): Reader --> Verelen CharSequence datasını Reader tipinde bir nesne olarak döner.
			1) Source CharSequence değeri.
		**/
		try
		{
			Reader rd = CharArrayUtil.readerFromCharSequence("Hello World!");
			char[] buffer7 = new char[12];
			System.out.println("rd.read(char[], int, int): int -> " + rd.read(buffer7, 0, buffer7.length));
			System.out.print("CharArrayUtil.readerFromCharSequence(CharSequence): -> ");
			for(int i = 0; i < buffer7.length; i++)
				System.out.print(buffer7[i]);
			System.out.println();
		}catch(Exception e){e.printStackTrace();}
		
		/**
			regionMatches(CharSequence, int, CharSequence): boolean --> Verelen CharSequence datası belitlnen offset kadar belirlenen CharSequence kelimesine eşleşiyor mu
			1) Source CharSequence değeri.
			2) Offset değeri.
			3) Bulunacak CharSequence değeri
		**/
		System.out.println("CharArrayUtil.regionMatches(CharSequence, int, CharSequence): boolean -> " + CharArrayUtil.regionMatches("Hello World!", 0, "Hello"));
		System.out.println("CharArrayUtil.regionMatches(CharSequence, int, CharSequence): boolean -> " + CharArrayUtil.regionMatches("Hello World!", 3, "lo Wo"));
		System.out.println("CharArrayUtil.regionMatches(CharSequence, int, CharSequence): boolean -> " + CharArrayUtil.regionMatches("Hello World!", 0, "lo Wo"));
		
		/**
			regionMatches(CharSequence, int, int, CharSequence): boolean --> Verelen CharSequence datası belitlenen offset kadar belirlenen CharSequence kelimesine eşleşiyor mu
			1) Source CharSequence değeri.
			2) Başlangıc değeri.
			3) Bitiş değeri.
			4) Bulunacak CharSequence değeri
		**/
		System.out.println("CharArrayUtil.regionMatches(CharSequence, int, int, CharSequence): boolean -> " + CharArrayUtil.regionMatches("Hello World!", 0, 12, "Hello"));
		System.out.println("CharArrayUtil.regionMatches(CharSequence, int, int, CharSequence): boolean -> " + CharArrayUtil.regionMatches("Hello World!", 3, 12, "lo Wo"));
		System.out.println("CharArrayUtil.regionMatches(CharSequence, int, int, CharSequence): boolean -> " + CharArrayUtil.regionMatches("Hello World!", 0, 12, "lo Wo"));
		
		/**
			regionMatches(CharSequence, int, int, CharSequence, int, int): boolean --> Verelen CharSequence datası belitlnen offset kadar belirlenen CharSequence kelimesine eşleşiyor mu
			1) Source CharSequence değeri.
			2) Başlangıc değeri.
			3) Bitiş değeri.
			4) Bulunacak CharSequence değeri
			5) Başlangıc değeri.
			6) Bitiş değeri.
		**/
		System.out.println("CharArrayUtil.regionMatches(CharSequence, int, int, CharSequence, int, int): boolean -> " + CharArrayUtil.regionMatches("Hello World!", 0, 5, "Hello", 0, 5));
		System.out.println("CharArrayUtil.regionMatches(CharSequence, int, int, CharSequence, int, int): boolean -> " + CharArrayUtil.regionMatches("Hello World!", 3, 8, "lo Wo", 0, 5));
		System.out.println("CharArrayUtil.regionMatches(CharSequence, int, int, CharSequence, int, int): boolean -> " + CharArrayUtil.regionMatches("Hello World!", 0, 5, "lo Wo", 0, 5));
		
		/**
			regionMatches(char[], int, int, CharSequence): boolean --> Verelen char[] datası belitlnen offset kadar belirlenen CharSequence kelimesine eşleşiyor mu
			1) Source char[] değeri.
			2) Başlangıc değeri.
			3) Bitiş değeri.
			4) Bulunacak CharSequence değeri
		**/
		System.out.println("CharArrayUtil.regionMatches(char[], int, int, CharSequence): boolean -> " + CharArrayUtil.regionMatches("Hello World!".toCharArray(), 0, 12, "Hello"));
		System.out.println("CharArrayUtil.regionMatches(char[], int, int, CharSequence): boolean -> " + CharArrayUtil.regionMatches("Hello World!".toCharArray(), 3, 12, "lo Wo"));
		System.out.println("CharArrayUtil.regionMatches(char[], int, int, CharSequence): boolean -> " + CharArrayUtil.regionMatches("Hello World!".toCharArray(), 0, 12, "lo Wo"));
		
		/**
			shiftBackward(CharSequence, int, String): int --> Verilen CharSequence datasında Geriye doğru arama yapar bulunan karakterin index döner.
			1) Source CharSequence değeri.
			2) Max offset değeri.
			3) Bulunacak String değeri
		**/
		String str = "Lorem Ipsum is simply dummy text of the printing $and typesetting industry.";
		System.out.println("CharArrayUtil.shiftBackward(CharSequence, int, String): int -> " + CharArrayUtil.shiftBackward(str, str.length() - 1, "$"));
		
		/**
			shiftBackward(CharSequence, int, int, String): int --> Verilen CharSequence datasında Geriye doğru arama yapar bulunan karakterin index döner.
			1) Source CharSequence değeri.
			2) Min offset değeri.
			3) Max offset değeri.
			4) Bulunacak String değeri
		**/
		System.out.println("CharArrayUtil.shiftBackward(CharSequence, int, int, String): int -> " + CharArrayUtil.shiftBackward(str, 0, str.length() - 1, "$"));
		
		/**
			shiftBackward(char[], int, String): int --> Verilen char[] datasında Geriye doğru arama yapar bulunan karakterin index döner.
			1) Source char[] değeri.
			2) Max offset değeri.
			3) Bulunacak String değeri
		**/
		System.out.println("CharArrayUtil.shiftBackward(char[], int, String): int -> " + CharArrayUtil.shiftBackward(str.toCharArray(), 0, "$"));
		
		/**
			shiftBackwardUntil(CharSequence, int, String): int --> Verilen CharSequence datasın Geriye doğru arama yapar bulunan karakterin index döner.
			1) Source CharSequence değeri.
			2) Max offset değeri.
			3) Bulunacak String değeri
		**/
		System.out.println("CharArrayUtil.shiftBackwardUntil(CharSequence, int, String): int -> " + CharArrayUtil.shiftBackwardUntil(str, str.length() - 1, "$"));
		
		/**
			shiftForward(CharSequence, int, String): int --> Verilen CharSequence datasın İleri doğru arama yapar bulunan karakterin index döner.
			1) Source CharSequence değeri.
			2) Max offset değeri.
			3) Bulunacak String değeri
		**/
		System.out.println("CharArrayUtil.shiftForward(CharSequence, int, String): int -> " + CharArrayUtil.shiftForward(str, 0, "$"));
		
		/**
			shiftForward(CharSequence, int, int, String): int --> Verilen CharSequence datasın İleri doğru arama yapar bulunan karakterin index döner.
			1) Source CharSequence değeri.
			2) Min offset değeri.
			3) Max offset değeri.
			4) Bulunacak String değeri
		**/
		System.out.println("CharArrayUtil.shiftForward(CharSequence, int, int, String): int -> " + CharArrayUtil.shiftForward(str, 0, str.length() - 1, "$"));
		
		/**
			shiftForward(char[], int, String): int --> Verilen char[] datasında Geriye doğru arama yapar bulunan karakterin index döner.
			1) Source char[] değeri.
			2) Max offset değeri.
			3) Bulunacak String değeri
		**/
		System.out.println("CharArrayUtil.shiftForward(char[], int, String): int -> " + CharArrayUtil.shiftForward(str.toCharArray(), 0, "$"));
		
		// shiftForwardCarefully
		
		/**
			shiftBackward(CharSequence, int, String): int --> Verilen CharSequence datasın Geriye doğru arama yapar bulunan karakterin index döner.
			1) Source CharSequence değeri.
			2) Max offset değeri.
			3) Bulunacak String değeri
		**/
		System.out.println("CharArrayUtil.shiftForwardUntil(CharSequence, int, String): int -> " + CharArrayUtil.shiftForwardUntil(str, 0, "$"));
		
	}
}