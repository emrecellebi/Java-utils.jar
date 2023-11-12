package com.emrecellebi;

import com.emrecellebi.util.text.CharArrayCharSequence;

public class CharArrayCharSequenceExample
{
	public static void main(String[] args)
	{
		/**
			CharArrayCharSequence(char[], int, int) --> Verilen char[] ile bir CharArrayCharSequence nennesi yaratır.
			1) Source char[] değeri.
		**/
		new CharArrayCharSequence("Hello World!".toCharArray());
		
		/**
			CharArrayCharSequence(char[], int, int) --> Verilen char[] ile bir CharArrayCharSequence nennesi yaratır.
			1) Source char[] değeri.
			2) Source start değeri.
			3) Source end değeri.
		**/
		CharArrayCharSequence ch = new CharArrayCharSequence("Hello World!".toCharArray(), 0, 12);
		
		/**
			charAt(int): char --> Verilen index derğerini char olarak geri döner.
			1) Index number
		**/
		System.out.println("ch.charAt(int): char -> " + ch.charAt(7));
		
		/**
			equals(Object): boolean --> Verilen nesne ile karşılaştırma yapar
		**/
		System.out.println("ch.equals(Object): boolean -> " + ch.equals(new CharArrayCharSequence("Hello World!".toCharArray())));
		
		/**
			getChars(): char[] --> Verilen nesne içerisindekini char[] olarak geri döner.
		**/
		char[] buffer9 = new char[0];
		System.out.print("ch.getChars(): char[] -> ");
		buffer9 = ch.getChars();
		for(int i = 0; i < buffer9.length; i++)
			System.out.print(buffer9[i]);
		System.out.println();
		
		/**
			getChars(char[], int): void --> Verilen char[] arrayine belirlenene offset kadar içeriğini doldurur.
			1) char[] destenation
			2) offset değeri
		**/
		char[] buffer8 = new char[12];
		System.out.print("ch.getChars(char[], int): void -> ");
		ch.getChars(buffer8, 0);
		for(int i = 0; i < buffer8.length; i++)
			System.out.print(buffer8[i]);
		System.out.println();
		
		/**
			hashCode(): int --> Hashcode üretir.
		**/
		System.out.println("ch.hashCode(): int -> " + ch.hashCode());
		
		/**
			length(): int --> Uzunluk
		**/
		System.out.println("ch.length(): int -> " + ch.length());
		
		/**
			readCharsTo(int, char[], int, int): int --> char[] arrayi olarak okuma yapar.
			1) Okunacak başlangıc
			2) char[] buffer arrayi
			3) buffer offset
			4) buffer uzunluk
		**/
		char[] buffer10 = new char[12];
		System.out.print("ch.readCharsTo(int, char[], int, int): int -> " + ch.readCharsTo(0, buffer10, 0, 12) + " --> ");
		for(int i = 0; i < buffer10.length; i++)
			System.out.print(buffer10[i]);
		System.out.println();
		
		/**
			subSequence(int, int): CharSequence --> Verilen değerler kadar kısmı döner.
		**/
		System.out.println("ch.subSequence(int, int): CharSequence -> " + ch.subSequence(2, 9));
		
		/**
			toString(): String --> Oluşturulan nesneyi String olarak geri döner.
		**/
		System.out.println("ch.toString(): String -> " + ch);
		System.out.println("ch.toString(): String -> " + ch.toString());
	}
}