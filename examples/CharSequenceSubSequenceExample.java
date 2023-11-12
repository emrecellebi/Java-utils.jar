package com.emrecellebi;

import com.emrecellebi.util.text.CharArrayCharSequence;

public class CharSequenceSubSequenceExample
{
	public static void main(String[] args)
	{
		new CharSequenceSubSequence("Hello World!");
		CharSequenceSubSequence cs = new CharSequenceSubSequence("Hello World!", 0, 12);
		
		System.out.print("cs.getChars(int, int, char[], int): void -> ");
		char[] ch3 = new char[12];
		cs.getChars(0, 12, ch3, 0);
		for(int i = 0; i < ch3.length; i++)
			System.out.print(ch3[i]);
		System.out.println();
		
		System.out.println("cs.hashCode(): int -> " + cs.hashCode());
		
		System.out.println("cs.length(): int -> " + cs.length());
		
		System.out.println("cs.subSequence(int, int): int -> " + cs.subSequence(3, 8));
		
		System.out.println("cs.charAt(int): int -> " + cs.charAt(6));
		
		System.out.println("cs.toString(): String -> " + cs);
		System.out.println("cs.toString(): String -> " + cs.toString());
	}
}