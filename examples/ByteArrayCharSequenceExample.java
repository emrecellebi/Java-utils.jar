package com.emrecellebi;

import com.emrecellebi.util.text.ByteArrayCharSequence;

public class ByteArrayCharSequenceExample
{
	public static void main(String[] args)
	{
		/**
			ByteArrayCharSequence(byte[]) --> Verilen byte array ile bir nesne yaratır.
			1) Source byte[] değeri
		**/
		new ByteArrayCharSequence("Hello World!".getBytes(), 0, 1);
		
		/**
			ByteArrayCharSequence(byte[], int, int) --> Verilen byte array ile bir nesne yaratır.
			1) Source byte[] değeri
			2) Başlangıc değeri
			3) Bitiş değeri
		**/
		ByteArrayCharSequence br = new ByteArrayCharSequence("Hello World!".getBytes(), 0, 12);
		
		/**
			charAt(int): char --> Verilen index değerini char olarak döner.
		**/
		System.out.println("br.charAt(int): char -> " + br.charAt(6));
		
		/**
			convertToBytesIfAsciiString(CharSequence): CharSequence --> Verilen CharSequence değerini CharSequence değeri olarak döner.
		**/
		System.out.println("ByteArrayCharSequence.convertToBytesIfAsciiString(CharSequence): CharSequence -> " + ByteArrayCharSequence.convertToBytesIfAsciiString("Hello World!"));
		
		/**
			getBytes(): byte[] --> Verilen CharSequence değerini CharSequence değeri olarak döner.
		**/
		byte[] ch2 = br.getBytes();
		System.out.print("br.getBytes(): byte[] -> ");
		for(int i = 0; i < ch2.length; i++)
			System.out.print(ch2[i]);
		System.out.println();
		
		/**
			hashCode(): int --> HashCode döner.
		**/
		System.out.println("br.hashCode(): int -> " + br.hashCode());
		
		/**
			length(): int --> Uzunluk döner.
		**/
		System.out.println("br.length(): int -> " + br.length());
		
		/**
			subSequence(3, 8): int --> Başlangıç ve bitiş verilerek bir kesit döner.
		**/
		System.out.println("br.subSequence(3, 8): int -> " + br.subSequence(3, 8));
		
		/**
			toBytesIfPossible(CharSequence): byte[] --> Verilen CharSequence değerini byte[] değeri olarak döner.
		**/
		byte[] ch = ByteArrayCharSequence.toBytesIfPossible("Hello World!");
		System.out.print("ByteArrayCharSequence.toBytesIfPossible(CharSequence): byte[] -> ");
		for(int i = 0; i < ch.length; i++)
			System.out.print(ch[i]);
		System.out.println();
		
		System.out.println("br.toString(): String -> " + br);
		System.out.println("br.toString(): String -> " + br.toString());
	}
}