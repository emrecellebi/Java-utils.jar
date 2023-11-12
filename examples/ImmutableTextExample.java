package com.emrecellebi;

import com.emrecellebi.util.text.CharArrayUtil;
import com.emrecellebi.util.text.ImmutableText;

public class ImmutableTextExample
{
	public static void main(String[] args)
	{
		ImmutableText it = ImmutableText.valueOf("Hello World!");
		
		System.out.print("it.getChars(int, int, char[], int): void -> ");
		char[] ch = new char[12];
		it.getChars(0, 12, ch, 0);
		for(int i = 0; i < ch.length; i++)
			System.out.print(ch[i]);
		System.out.println();
		
		System.out.println("it.subtext(int, int): ImmutableText -> " + it.subtext(3, 6));
		System.out.println("it.subSequence(int, int): CharSequence -> " + it.subSequence(3, 6));
		System.out.println("it.length(): int -> " + it.length());
		System.out.println("it.insert(int, CharSequence): ImmutableText -> " + it.insert(12, " Data"));
		System.out.println("it.hashCode(): int -> " + it.hashCode());
		System.out.println("it.equals(Object): boolean -> " + it.equals(it));
		System.out.println("it.delete(int, int): ImmutableText -> " + it.delete(6, 12));
		System.out.println("it.concat(CharSequence): ImmutableText -> " + it.concat(" Data"));
		System.out.println("it.charAt(): char -> " + it.charAt(6));
		System.out.println("it.toString(): String -> " + it);
		System.out.println("it.toString(): String -> " + it.toString());
	}
}