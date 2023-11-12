package com.emrecellebi;

import com.emrecellebi.util.text.MergingCharSequence;

public class MergingCharSequenceExample
{
	public static void main(String[] args)
	{
		/**
			MergingCharSequence sınıfı CharSequence implements eden bir String birleştirme sıfıdır.
		**/
		
		MergingCharSequence mcs = new MergingCharSequence("Hello ", "World!");
		System.out.println("mcs.charAt(int): char -> " + mcs.charAt(6));
		System.out.println("mcs.length(): int -> " + mcs.length());
		System.out.println("mcs.subSequence(int, int): CharSequence -> " + mcs.subSequence(3, 8));
		System.out.println("mcs.toString(): String -> " + mcs);
		System.out.println("mcs.toString(): String -> " + mcs.toString());
	}
}