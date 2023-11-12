package com.emrecellebi;

import com.emrecellebi.util.text.CaseInsensitiveStringHashingStrategy;

public class CaseInsensitiveStringHashingStrategyExample
{
	public static void main(String[] args)
	{
		CaseInsensitiveStringHashingStrategy strategy = CaseInsensitiveStringHashingStrategy.INSTANCE;
		
		/**
			computeHashCode(String): int --> HashingStrategy interface override edilerek yapılan hashCode methodu. Küçük harf olarak hash üretir.
		**/
		System.out.println("HashingStrategy.computeHashCode(String): int -> " + strategy.computeHashCode("Hello World!"));
		
		/**
			equals(String, String): boolean --> HashingStrategy interface override edilerek yapılan equals methodu. Küçük harf olarak karşılaştırır.
		**/
		System.out.println("HashingStrategy.equals(String, String): boolean -> " + strategy.equals("Hello World!", "Hello World!"));
	}
}