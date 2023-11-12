package com.emrecellebi;

import com.emrecellebi.util.text.StringUtilRt;

public class StringUtilRtExample
{
	public static void main(String[] args)
	{
		
		
		/**
			isEmpty(CharSequence): boolean --> Verilen datanın boş olup olmadığını kontrol eder.
			1) String boş olduğunda true değerini döner.
			2) String dolu olduğunda false değerini döner.
		**/
		System.out.println("StringUtilRt.isEmpty(CharSequence): boolean -> " + StringUtilRt.isEmpty(""));
		System.out.println("StringUtilRt.isEmpty(CharSequence): boolean -> " + StringUtilRt.isEmpty("Hello World!"));
		
		/**
			isEmptyOrSpaces(CharSequence): boolean --> Verielen data boş veya boşluk durumunu kontrol eder.
			1) String boş olduğunda true değerini döner.
			2) String dolu olduğunda false değerini döner.
			2) String boşluk karacteri olduğunda false değerini döner.
		**/
		System.out.println("StringUtilRt.isEmptyOrSpaces(CharSequence): boolean -> " + StringUtilRt.isEmptyOrSpaces(""));
		System.out.println("StringUtilRt.isEmptyOrSpaces(CharSequence): boolean -> " + StringUtilRt.isEmptyOrSpaces("Hello World!"));
		System.out.println("StringUtilRt.isEmptyOrSpaces(CharSequence): boolean -> " + StringUtilRt.isEmptyOrSpaces(" "));
		
		/**
			isQuotedString(String): boolean --> Verilen data içerisinde ' ve " karacterini kontrol eder.
			1) String ' ' arasında olduğunda true değerini döner.
			2) String " " arasında olduğunda true değerini döner.
			2) String ' veya " başında veya sonun olduğunda olduğunda false değerini döner.
		**/
		System.out.println("StringUtilRt.isQuotedString(String): boolean -> " + StringUtilRt.isQuotedString("'Hello World!'"));
		System.out.println("StringUtilRt.isQuotedString(String): boolean -> " + StringUtilRt.isQuotedString("\"Hello World!\""));
		System.out.println("StringUtilRt.isQuotedString(String): boolean -> " + StringUtilRt.isQuotedString("'Hello' World!"));
		
		/**
			lastIndexOf(CharSequence, char, int, int): int --> Verilen data içerisinde belirlenen prefix karacterinin sondan bulucak şekilde index numarsını döner.
			1) Verielen data içerisinde belirlenen prefix karacterini sondan bulduğunda index değerini döner.
			2) Verielen data içerisinde belirlenen prefix karacterini belirleyeceğimiz başlangıç ve bitiş arasında sondan bulduğunda index değerini döner.
			2) Verilen data içerisinde belirlenen prefix karacterini bulamz ise -1 değerini döner.
		**/
		System.out.println("StringUtilRt.lastIndexOf(CharSequence, char, int, int): int -> " + StringUtilRt.lastIndexOf("Hello World!", '!', 0, 12));
		System.out.println("StringUtilRt.lastIndexOf(CharSequence, char, int, int): int -> " + StringUtilRt.lastIndexOf("Hello World!", '!', 10, 12));
		System.out.println("StringUtilRt.lastIndexOf(CharSequence, char, int, int): int -> " + StringUtilRt.lastIndexOf("Hello World!", 'x', 0, 12));
		
		/**
			notNullize(String): String --> Verilen String data eğer null ise default olarak boş bir string ataması yapar.
			1) Verilen data null ise default olarka boş atanır.
			2) Verilen data tanımlı ise datayı geri döner.
		**/
		System.out.println("StringUtilRt.notNullize(String): String -> " + StringUtilRt.notNullize(null));
		System.out.println("StringUtilRt.notNullize(String): String -> " + StringUtilRt.notNullize("Hello Worrld!"));
		
		/**
			notNullize(String, String): String --> Verilen String data eğer null ise default olarak belirlenen değeri döner.
			1) Verilen data null ise default belirlenen değeri döner.
			2) Verilen data tanımlı ise datayı geri döner.
		**/
		System.out.println("StringUtilRt.notNullize(String, String): String -> " + StringUtilRt.notNullize(null, "default"));
		System.out.println("StringUtilRt.notNullize(String, String): String -> " + StringUtilRt.notNullize("Hello World!", "default"));
		
		/**
			parseDouble(String, double): double --> Verielen String datasını double tipine dönüştürür.
			1) Verilen String bir double değeri ise dönüştürür.
			2) Verilen String bir null değeri ise default değeri döner.
		**/
		System.out.println("StringUtilRt.parseDouble(String, double): double -> " + StringUtilRt.parseDouble("25.25", 0.0));
		System.out.println("StringUtilRt.parseDouble(String, double): double -> " + StringUtilRt.parseDouble(null, 0.0));
		
		/**
			parseInt(String, int): int --> Verielen String datasını int tipine dönüştürür.
			1) Verilen String bir int değeri ise dönüştürür.
			2) Verilen String bir null değeri ise default değeri döner.
		**/
		System.out.println("StringUtilRt.parseInt(String, int): int -> " + StringUtilRt.parseInt("25", 0));
		System.out.println("StringUtilRt.parseInt(String, int): int -> " + StringUtilRt.parseInt(null, 0));
		
		/**
			parseLong(String, long): long --> Verielen String datasını int tipine dönüştürür.
			1) Verilen String bir long değeri ise dönüştürür.
			2) Verilen String bir null değeri ise default değeri döner.
		**/
		System.out.println("StringUtilRt.parseLong(String, long): long -> " + StringUtilRt.parseLong("25", 0L));
		System.out.println("StringUtilRt.parseLong(String, long): long -> " + StringUtilRt.parseLong(null, 0L));
		
		/**
			splitHonorQuotes(String, char): List<String> --> Verile bir string içerisinde aytılmış bir datayı listeye dönüştürür.
			1) Verielen String içerisinde belirlenen ayatç ile list işlemi yapar.
		**/
		System.out.println("StringUtilRt.splitHonorQuotes(String, char): List<String> -> " + StringUtilRt.splitHonorQuotes("'1',2,3,\"4\",5", ','));
		
		/**
			startsWith(CharSequence, CharSequence): boolean --> Verilen data içerisinde belirlenen prefix ile baştan dan eşleşme yapar.
			1) Verielen data ile prefix baştan eşleşir ise true değerini döner.
			2) Verielen data ile prefix baştan eşleşmez ise false değerini döner.
			3) Verielen data veya prefix büyük ise baştan eşleşmez false değerini döner.
		**/
		System.out.println("StringUtilRt.startsWith(CharSequence, CharSequence): boolean -> " + StringUtilRt.startsWith("Hello World!", "Hello"));
		System.out.println("StringUtilRt.startsWith(CharSequence, CharSequence): boolean -> " + StringUtilRt.startsWith("Heello World!", "Hello"));
		System.out.println("StringUtilRt.startsWith(CharSequence, CharSequence): boolean -> " + StringUtilRt.startsWith("HELLO World!", "Hello"));
		
		/**
			startsWithIgnoreCase(String, String): boolean --> Verilen data içerisinde belirlenen prefix ile baştan eşleşmeyi büyük küçüğe duyarlı yapar.
			1) Verielen data ile prefix baştan eşleşir ise true değerini döner.
			2) Verielen data ile prefix baştan eşleşmez ise false değerini döner.
			3) Verielen data veya prefix büyük ise baştan eşleşir true değerini döner.
		**/
		System.out.println("StringUtilRt.startsWithIgnoreCase(String, String): boolean -> " + StringUtilRt.startsWithIgnoreCase("Hello World!", "Hello"));
		System.out.println("StringUtilRt.startsWithIgnoreCase(String, String): boolean -> " + StringUtilRt.startsWithIgnoreCase("Heello World!", "Hello"));
		System.out.println("StringUtilRt.startsWithIgnoreCase(String, String): boolean -> " + StringUtilRt.startsWithIgnoreCase("HELLO World!", "Hello"));
		
		/**
			startsWithIgnoreCase(String, int, String): boolean --> Verilen data içerisinde belirlenen prefix ve offset ile baştan eşleşmeyi büyük küçüğe duyarlı yapar.
			1) Verielen data ile prefix baştan eşleşir ise true değerini döner.
			2) Verielen data ile prefix baştan eşleşmez ise false değerini döner.
			3) Verielen data veya prefix büyük ise baştan eşleşir true değerini döner.
		**/
		System.out.println("StringUtilRt.startsWithIgnoreCase(String, int, String): boolean -> " + StringUtilRt.startsWithIgnoreCase("Hello World!", 0, "Hello"));
		System.out.println("StringUtilRt.startsWithIgnoreCase(String, int, String): boolean -> " + StringUtilRt.startsWithIgnoreCase("Heello World!", 0, "Hello"));
		System.out.println("StringUtilRt.startsWithIgnoreCase(String, int, String): boolean -> " + StringUtilRt.startsWithIgnoreCase("HELLO World!", 0, "Hello"));
		
		/**
			stringHashCodeInsensitive(CharSequence): int --> Verilen String datasını küçük harfler olarak hash değerini döner.
		**/
		System.out.println("StringUtilRt.stringHashCodeInsensitive(CharSequence): int -> " + StringUtilRt.stringHashCodeInsensitive("Hello World!"));
		
		/**
			stringHashCodeInsensitive(CharSequence, int, int): int --> Verilen String datasını belirlenen başlangıç ve bitiş ile küçük harfler olarak hash değerini döner.
		**/
		System.out.println("StringUtilRt.stringHashCodeInsensitive(CharSequence, int, int): int -> " + StringUtilRt.stringHashCodeInsensitive("Hello World!", 0, 12));
		
		/**
			stringHashCodeInsensitive(CharSequence, int, int, int): int --> Verilen String datasını belirlenen başlangıç ve bitiş ile küçük harfler olarak hash değerini döner.
			1) Verilen String datasını belirtilen başlangıc ve bitiş ile bir hash üretir.
			2) Verilen String datasını belirtilen başlangıc, bitiş ve prefix kadar bir hash üretir.
		**/
		System.out.println("StringUtilRt.stringHashCodeInsensitive(CharSequence, int, int, int): int -> " + StringUtilRt.stringHashCodeInsensitive("Hello World!", 0, 12, 0));
		System.out.println("StringUtilRt.stringHashCodeInsensitive(CharSequence, int, int, int): int -> " + StringUtilRt.stringHashCodeInsensitive("Hello World!", 0, 12, 64));
		
		/**
			toLowerCase(char): char --> Verilen karacter değerini küçük harf ile döner.
		**/
		System.out.println("StringUtilRt.toLowerCase(char): char -> " + StringUtilRt.toLowerCase('S'));

		/**
			toUpperCase(char): char --> Verilen karacter değerini büyük harf ile döner.
		**/
		System.out.println("StringUtilRt.toUpperCase(char): char -> " + StringUtilRt.toUpperCase('s'));
		
		/**
			unquoteString(String): String --> Verielen String data içerisnde ' veya " karaktei arasında ise bunları siler.
			1) String ' ' arasında olduğunda temizler.
			2) String " " arasında olduğunda temizler.
		**/
		System.out.println("StringUtilRt.unquoteString(String): String -> " + StringUtilRt.unquoteString("'Hello World!'"));
		System.out.println("StringUtilRt.unquoteString(String): String -> " + StringUtilRt.unquoteString("\"Hello World!\""));
		
		/**
			unquoteString(String, char): String --> Verielen String data içerisnde belirlenen karakeri temizler.
			1) String -- arasında olduğunda temizler.
		**/
		System.out.println("StringUtilRt.unquoteString(String, char): String -> " + StringUtilRt.unquoteString("-Hello World!-", '-'));
	}
}