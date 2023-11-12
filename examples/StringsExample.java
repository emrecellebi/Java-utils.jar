package com.emrecellebi;

import com.emrecellebi.util.text.Strings;

public class PluralizerUtilExample
{
	public static void main(String[] args)
	{
		/**
			capitalize(String): String --> Verilen String datasınn baş harfini büyük olarak döner.
		**/
		System.out.println("Strings.capitalize(String): String -> " + Strings.capitalize("hello world!"));
		
		/**
			charsEqualIgnoreCase(char, char): boolean --> Verilen karakter ile belirtilen karacteri Büyük küçük bakmadan karşılarştır.
		**/
		System.out.println("Strings.charsEqualIgnoreCase(char, char): boolean -> " + Strings.charsEqualIgnoreCase('a', 'a'));
		System.out.println("Strings.charsEqualIgnoreCase(char, char): boolean -> " + Strings.charsEqualIgnoreCase('a', 'A'));
		
		/**
			charsMatch(char, char, boolean): boolean --> Verilen karacter ile belirtilen karacteri karşılaştır.
			1) Büyük küçükğe bakarak karşılaştırma yapar.
			2) Büyük küçüge bakmadan karşılaştırma yapar.
		**/
		System.out.println("Strings.charsMatch(char, char, boolean): boolean -> " + Strings.charsMatch('A', 'A', false));
		System.out.println("Strings.charsMatch(char, char, boolean): boolean -> " + Strings.charsMatch('A', 'a', false));
		System.out.println("Strings.charsMatch(char, char, boolean): boolean -> " + Strings.charsMatch('A', 'a', true));
		
		/**
			compare(char, char, boolean): int --> Verilen char tipindeki değerleri karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda 0 döner
			2) Soldaki değer küçük Sağdaki değer büyük olduğunda -1 veya Ascii code döner
			3) Soldaki değer küçük Sağdaki değer büyük ve case sensitive true olduğunda 0 döner 
		**/
		System.out.println("Strings.compare(char, char, boolean): int -> " + Strings.compare('a', 'a', false));
		System.out.println("Strings.compare(char, char, boolean): int -> " + Strings.compare('a', 'A', false));
		System.out.println("Strings.compare(char, char, boolean): int -> " + Strings.compare('a', 'A', true));
		
		/**
			contains(CharSequence, CharSequence): boolean --> Verilen string datasını belirtilen derğer geçiyor mu diye kontrol eder.
		**/
		System.out.println("Strings.contains(CharSequence, CharSequence): boolean -> " + Strings.contains((CharSequence)"Hello World!", (CharSequence)"World"));
		
		/**
			contains(CharSequence, int, int, char): boolean --> Verilen string datasını belirtilen başlangıc ve bitiş arasında belirlenen karakteri arar.
		**/
		System.out.println("Strings.contains(CharSequence, int, int, char): boolean -> " + Strings.contains((CharSequence)"Hello World!", 0, 12, ' '));
		
		/**
			containsAnyChar(String, String): boolean --> Verilen string datasını belirtilen derğer geçiyor mu diye kontrol eder.
		**/
		System.out.println("Strings.containsAnyChar(String, String): boolean -> " + Strings.containsAnyChar((String)"Hello World!", (String)"World!"));
		
		/**
			containsAnyChar(String, String, int, int): boolean --> Verilen string datasını belirtilen başlangıc ve bitiş arasında değer geçiyor mu diye kontrol eder.
		**/
		System.out.println("Strings.containsAnyChar(String, String, int, int): boolean -> " + Strings.containsAnyChar((String)"Hello World!", (String)"World!", 0, 12));
		
		/**
			containsChar(String, char): boolean --> Verilen string datasını belirtilen karacter geçiyor mu diye kontrol eder.
		**/
		System.out.println("Strings.containsChar(String, char): boolean -> " + Strings.containsChar((String)"Hello World!", ' '));
		
		/**
			endsWith(CharSequence, CharSequence): boolean --> Verilen data içerisinde belirlenen prefix ile son dan eşleşme yapar.
			1) Verielen data ile prefix sondan eşleşir ise true değerini döner.
			2) Verielen data ile prefix sondan eşleşmez ise false değerini döner.
			3) Verielen data veya prefix büyük ise sondan eşleşmez false değerini döner.
		**/
		System.out.println("Strings.endsWith(CharSequence, CharSequence): boolean -> " + Strings.endsWith((CharSequence)"Hello World!", (CharSequence)"World!"));
		System.out.println("Strings.endsWith(CharSequence, CharSequence): boolean -> " + Strings.endsWith((CharSequence)"Hello World!", (CharSequence)"Worrld!"));
		System.out.println("Strings.endsWith(CharSequence, CharSequence): boolean -> " + Strings.endsWith((CharSequence)"Hello World!", (CharSequence)"WORLD!"));

		/**
			endsWithChar(CharSequence, char): boolean --> Verielen data ile karekter prefix sondan eşleme yapar.
			1) Verielen data ile prefix sondan eşleşir ise true değerini döner.
			2) Verielen data ile prefix sondan eşleşmez ise false değerini döner.
			3) Verielen data veya prefix büyük ise sondan eşleşmez false değerini döner.
		**/
		System.out.println("Strings.endsWithChar(CharSequence, char): boolean -> " + Strings.endsWithChar((CharSequence)"Hello World!", '!'));
		System.out.println("Strings.endsWithChar(CharSequence, char): boolean -> " + Strings.endsWithChar((CharSequence)"Hello World!", 'l'));
		System.out.println("Strings.endsWithChar(CharSequence, char): boolean -> " + Strings.endsWithChar((CharSequence)"Hello World", 'D'));
		
		/**
			endsWithIgnoreCase(CharSequence, String): boolean --> Verilen data içerisinde belirlenen prefix ile sondan eşleşmeyi büyük küçüğe duyarlı yapar.
			1) Verielen data ile prefix sondan eşleşir ise true değerini döner.
			2) Verielen data ile prefix sondan eşleşmez ise false değerini döner.
			3) Verielen data veya prefix büyük ise sondan eşleşir true değerini döner.
		**/
		System.out.println("Strings.endsWithIgnoreCase(CharSequence, String): boolean -> " + Strings.endsWithIgnoreCase((CharSequence)"Hello WOrld!", (String)"World!"));
		System.out.println("Strings.endsWithIgnoreCase(CharSequence, String): boolean -> " + Strings.endsWithIgnoreCase((CharSequence)"Hello WOrld!", (String)"Worrld!"));
		System.out.println("Strings.endsWithIgnoreCase(CharSequence, String): boolean -> " + Strings.endsWithIgnoreCase((CharSequence)"Hello WOrld!", (String)"WORLD!"));

		/**
			indexOf(CharSequence, CharSequence): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOf(CharSequence, CharSequence): int -> " + Strings.indexOf((CharSequence)"Hello World!", (CharSequence)"World!"));
		
		/**
			indexOf(CharSequence, CharSequence, int): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOf(CharSequence, CharSequence, int): int -> " + Strings.indexOf((CharSequence)"Hello World!", (CharSequence)"World!", 0));
		
		/**
			indexOf(CharSequence, CharSequence, int, int): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOf(CharSequence, CharSequence, int, int): int -> " + Strings.indexOf((CharSequence)"Hello World!", (CharSequence)"World!", 0, 12));
		
		/**
			indexOf(CharSequence, char): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOf(CharSequence, char): int -> " + Strings.indexOf((CharSequence)"Hello World!", ' '));
		
		/**
			indexOf(CharSequence, char, int): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOf(CharSequence, char, int): int -> " + Strings.indexOf((CharSequence)"Hello World!", ' ', 0));
		
		/**
			indexOf(CharSequence, char, int, int): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOf(CharSequence, char, int, int): int -> " + Strings.indexOf((CharSequence)"Hello World!", ' ', 0, 12));
		
		/**
			indexOf(CharSequence, char, int, int, boolean): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOf(CharSequence, char, int, int, boolean): int -> " + Strings.indexOf("Hello World!", 'o', 0, 12, false));
		System.out.println("Strings.indexOf(CharSequence, char, int, int, boolean): int -> " + Strings.indexOf("Hello World!", 'O', 0, 12, true));
		
		/**
			indexOf(char[], char, int, int, boolean): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOf(char[], char, int, int, boolean): int -> " + Strings.indexOf("Hello World!".toCharArray(), 'o', 0, 12, false));
		System.out.println("Strings.indexOf(char[], char, int, int, boolean): int -> " + Strings.indexOf("Hello World!".toCharArray(), 'O', 0, 12, true));
		
		/**
			indexOfAny(CharSequence, String): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOfAny(CharSequence, String): int -> " + Strings.indexOfAny((CharSequence)"Hello World!", " "));
		
		/**
			indexOfAny(CharSequence, String, int, int): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOfAny(CharSequence, String, int, int): int -> " + Strings.indexOfAny((CharSequence)"Hello World!", " ", 0, 12));
		
		/**
			indexOfAny(String, String): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOfAny(String, String): int -> " + Strings.indexOfAny((String)"Hello World!", (String)" "));
		
		/**
			indexOfAny(String, String, int, int): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOfAny(String, String, int, int): int -> " + Strings.indexOfAny("Hello World!", "World!", 0, 12));
		
		/**
			indexOfIgnoreCase(CharSequence, CharSequence, int): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOfIgnoreCase(CharSequence, CharSequence, int): int -> " + Strings.indexOfIgnoreCase((CharSequence)"Hello World!", (CharSequence)"World!", 0));
		System.out.println("Strings.indexOfIgnoreCase(CharSequence, CharSequence, int): int -> " + Strings.indexOfIgnoreCase((CharSequence)"Hello World!", (CharSequence)"WOrld!", 0));
		
		/**
			indexOfIgnoreCase(String, String, int): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOfIgnoreCase(String, String, int): int -> " + Strings.indexOfIgnoreCase((String)"Hello World!", (String)"World!", 0));
		System.out.println("Strings.indexOfIgnoreCase(String, String, int): int -> " + Strings.indexOfIgnoreCase((String)"Hello World!", (String)"WOrld!", 0));
		
		/**
			indexOfIgnoreCase(String, char, int): int --> Verielen String datasının içerisinde belirlenen değeri bulur index döner.
		**/
		System.out.println("Strings.indexOfIgnoreCase(String, char, int): int -> " + Strings.indexOfIgnoreCase("Hello World!", ' ', 0));
		
		/**
			isAscii(char): boolean --> Verielen karacteri ascii olup olmadığını döner.
		**/
		System.out.println("Strings.isAscii(char): boolean -> " + Strings.isAscii('A'));
		
		/**
			isCapitalized(String): boolean --> Verilen metinin Baş harfinin büyük olup olmadığını döner.
		**/
		System.out.println("Strings.isCapitalized(String): boolean -> " + Strings.isCapitalized("Hello World"));
		System.out.println("Strings.isCapitalized(String): boolean -> " + Strings.isCapitalized("hello World"));
		
		/**
			isEmpty(CharSequence): boolean --> Verilen datanın boş olup olmadığını kontrol eder.
			1) String boş olduğunda true değerini döner.
			2) String dolu olduğunda false değerini döner.
		**/
		System.out.println("Strings.isEmpty(CharSequence): boolean -> " + Strings.isEmpty("Hello World"));
		System.out.println("Strings.isEmpty(CharSequence): boolean -> " + Strings.isEmpty(""));
		
		/**
			isEmpty(String): boolean --> Verilen datanın boş olup olmadığını kontrol eder.
			1) String boş olduğunda true değerini döner.
			2) String dolu olduğunda false değerini döner.
		**/
		System.out.println("Strings.isEmpty(String): boolean -> " + Strings.isEmpty("Hello World"));
		System.out.println("Strings.isEmpty(String): boolean -> " + Strings.isEmpty(""));
		
		/**
			isEmptyOrSpaces(CharSequence): boolean --> Verielen data boş veya boşluk durumunu kontrol eder.
			1) String boş olduğunda true değerini döner.
			2) String dolu olduğunda false değerini döner.
			2) String boşluk karacteri olduğunda false değerini döner.
		**/
		System.out.println("Strings.isEmptyOrSpaces(CharSequence): boolean -> " + StringUtilRt.isEmptyOrSpaces(""));
		System.out.println("Strings.isEmptyOrSpaces(CharSequence): boolean -> " + StringUtilRt.isEmptyOrSpaces("Hello World!"));
		System.out.println("Strings.isEmptyOrSpaces(CharSequence): boolean -> " + StringUtilRt.isEmptyOrSpaces(" "));
		
		/**
			isNotEmpty(String): boolean --> Verilen datanın boş olmadığı durumu kontrol eder.
			1) String boş olmadığında true değerini döner.
			2) String dolu olduğunda false değerini döner.
		**/
		System.out.println("Strings.isNotEmpty(String): boolean -> " + Strings.isNotEmpty("Hello World"));
		System.out.println("Strings.isNotEmpty(String): boolean -> " + Strings.isNotEmpty(""));
		
		/**
			notNullize(String): String --> Verilen String data eğer null ise default olarak boş bir string ataması yapar.
			1) Verilen data null ise default olarka boş atanır.
			2) Verilen data tanımlı ise datayı geri döner.
		**/
		System.out.println("Strings.notNullize(String): String -> " + Strings.notNullize(null));
		System.out.println("Strings.notNullize(String): String -> " + Strings.notNullize("Hello Worrld!"));
		
		/**
			notNullize(String, String): String --> Verilen String data eğer null ise default olarak belirlenen değeri döner.
			1) Verilen data null ise default belirlenen değeri döner.
			2) Verilen data tanımlı ise datayı geri döner.
		**/
		System.out.println("Strings.notNullize(String, String): String -> " + Strings.notNullize(null, "default"));
		System.out.println("Strings.notNullize(String, String): String -> " + Strings.notNullize("Hello World!", "default"));
		
		/**
			nullize(String): String --> Bir stringin null kontorlünü yapar
			1) String null ise default olarak boş değer atanır.
			2) String null değil ise tanımlı değeri döner.
		**/
		System.out.println("Strings.nullize(String): String -> " + Strings.nullize("Hello World"));
		System.out.println("Strings.nullize(String): String -> " + Strings.nullize(null));
		
		/**
			nullize(String, String): String --> Bir stringin null kontorlünü yapar
			1) String null ise default değeri atanır.
			2) String null değil ise tanımlı değeri döner.
		**/
		System.out.println("Strings.nullize(String, String): String -> " + Strings.nullize("Hello World", "default"));
		System.out.println("Strings.nullize(String, String): String -> " + Strings.nullize(null, "default"));
		
		/**
			nullize(String, boolean): String --> Bir stringin null kontorlünü yapar
		**/
		System.out.println("Strings.nullize(String, boolean): String -> " + Strings.nullize("Hello World", false));
		System.out.println("Strings.nullize(String, boolean): String -> " + Strings.nullize(" ", false));
		System.out.println("Strings.nullize(String, boolean): String -> " + Strings.nullize(" ", true));
		System.out.println("Strings.nullize(String, boolean): String -> " + Strings.nullize("", true));
		
		/**
			startsWith(CharSequence, int, CharSequence): String --> Verilen data içerisinde belirlenen prefix ile baştan dan eşleşme yapar.
		**/
		System.out.println("Strings.startsWith(CharSequence, int, CharSequence): boolean -> " + Strings.startsWith((CharSequence)"Hello World!", 0, (CharSequence)"Hello"));
		
		/**
			stringHashCode(CharSequence, int, int): int --> Verilen String datasını belirlenen başlangıç ve bitiş olarak hash değerini döner.
			1) Verilen String datasını belirtilen başlangıc ve bitiş ile bir hash üretir.
		**/
		System.out.println("Strings.stringHashCode(CharSequence, int, int): int -> " + Strings.stringHashCode((CharSequence)"Hello World!", 0, 12));
		
		/**
			stringHashCode(CharSequence, int, int, int): int --> Verilen String datasını belirlenen başlangıç ve bitiş olarak hash değerini döner.
			1) Verilen String datasını belirtilen başlangıc ve bitiş ile bir hash üretir.
			2) Verilen String datasını belirtilen başlangıc, bitiş ve prefix kadar bir hash üretir.
		**/
		System.out.println("Strings.stringHashCode(CharSequence, int, int, int): int -> " + Strings.stringHashCode((CharSequence)"Hello World!", 0, 12, 0));
		System.out.println("Strings.stringHashCode(CharSequence, int, int, int): int -> " + Strings.stringHashCode((CharSequence)"Hello World!", 0, 12, 64));
		
		/**
			stringHashCode(char[], int, int): int --> Verilen char datasını belirlenen başlangıç ve bitiş olarak hash değerini döner.
			1) Verilen char datasını belirtilen başlangıc ve bitiş ile bir hash üretir.
		**/
		System.out.println("Strings.stringHashCode(char[], int, int): int -> " + Strings.stringHashCode("Hello World!".toCharArray(), 0, 12));
		
		/**
			stringHashCodeInsensitive(CharSequence): int --> Verilen String datasını küçük harfler olarak hash değerini döner.
		**/
		System.out.println("Strings.stringHashCodeInsensitive(CharSequence): int -> " + Strings.stringHashCodeInsensitive("Hello World!"));
		
		/**
			stringHashCodeInsensitive(CharSequence, int, int): int --> Verilen String datasını belirlenen başlangıç ve bitiş ile küçük harfler olarak hash değerini döner.
		**/
		System.out.println("Strings.stringHashCodeInsensitive(CharSequence, int, int): int -> " + Strings.stringHashCodeInsensitive("Hello World!", 0, 12));
		
		/**
			stringHashCodeInsensitive(CharSequence, int, int, int): int --> Verilen String datasını belirlenen başlangıç ve bitiş ile küçük harfler olarak hash değerini döner.
			1) Verilen String datasını belirtilen başlangıc ve bitiş ile bir hash üretir.
			2) Verilen String datasını belirtilen başlangıc, bitiş ve prefix kadar bir hash üretir.
		**/
		System.out.println("Strings.stringHashCodeInsensitive(CharSequence, int, int, int): int -> " + Strings.stringHashCodeInsensitive("Hello World!", 0, 12, 0));
		System.out.println("Strings.stringHashCodeInsensitive(CharSequence, int, int, int): int -> " + Strings.stringHashCodeInsensitive("Hello World!", 0, 12, 64));
		
		/**
			stringHashCodeInsensitive(char[], int, int): int --> Verilen String datasını belirlenen başlangıç ve bitiş ile küçük harfler olarak hash değerini döner.
		**/
		System.out.println("Strings.stringHashCodeInsensitive(char[], int, int): int -> " + Strings.stringHashCodeInsensitive("Hello World!".toCharArray(), 0, 12));
		
		/**
			toLowerCase(String): String --> Verilen String datasını küçük olarak döner.
		**/
		System.out.println("Strings.toLowerCase(String): String -> " + Strings.toLowerCase("Hello World!"));
		
		/**
			toLowerCase(char): char --> Verilen karacter değerini küçük harf ile döner.
		**/
		System.out.println("Strings.toLowerCase(char): char -> " + Strings.toLowerCase('S'));
		
		/**
			toUpperCase(String): String --> Verilen String datasını büyük olarak döner.
		**/
		System.out.println("Strings.toUpperCase(String): String -> " + Strings.toUpperCase("Hello World!"));
		
		/**
			toUpperCase(char): char --> Verilen karacter değerini büyük harf ile döner.
		**/
		System.out.println("Strings.toUpperCase(char): char -> " + Strings.toUpperCase('s'));
		
		/**
			trim(String): String --> Verilen string datasının sağındaki solundaki tüm boşlukları temizler.
		**/
		System.out.println("Strings.trim(String): String -> " + Strings.trim(" Hello World! "));
		
		/**
			trimEnd(String, String, boolean): String --> Verilen string datasının sondan verilen prefix değerini temiler.
			1) boolean olarak true verilirse büyük küçüğe bakmadan temizler.
		**/
		System.out.println("Strings.trimEnd(String, String, boolean): String -> " + Strings.trimEnd("Hello World!Aa", "a", false));
		System.out.println("Strings.trimEnd(String, String, boolean): String -> " + Strings.trimEnd("Hello World!A", "a", true));
		
		/**
			trimEnd(String, String): String --> Verilen string datasının sondan verilen prefix değerini temiler.
		**/
		System.out.println("Strings.trimEnd(String, String): String -> " + Strings.trimEnd("Hello World!a", "a"));
		
		/**
			trimEnd(String, char): String --> Verilen string datasının sondan verilen prefix değerini temiler.
		**/
		System.out.println("Strings.trimEnd(String, char): String -> " + Strings.trimEnd("Hello World!a", 'a'));
		
		/**
			pluralize(String): String --> 
		**/
		String str = Strings.pluralize("Hello World");
		System.out.println("Strings.pluralize(String): String -> " + str);
		
		/**
			unpluralize(String): String --> 
		**/
		System.out.println("Strings.unpluralize(String): String -> " + Strings.unpluralize(str));
	}
}