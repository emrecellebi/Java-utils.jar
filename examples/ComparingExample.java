package com.emrecellebi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.emrecellebi.util.Comparing;

public class ComparingExample
{
	public static void main(String[] args)
	{
		/**
			compare(T<Comparable>, T<Comparable>): int --> Verilen Comparable tipindeki değerleri karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda 0 döner
			2) Soldaki değer küçük Sağdaki değer büyük olduğunda -1 veya Ascii code döner
			3) Soldaki değer büyük Sağdaki değer küçük olduğunda 1 veya Ascii code döner
		**/
		System.out.println("Comparing.compare(T<Comparable>, T<Comparable>): " + Comparing.compare("A", "A"));
		System.out.println("Comparing.compare(T<Comparable>, T<Comparable>): " + Comparing.compare("a", "A"));
		System.out.println("Comparing.compare(T<Comparable>, T<Comparable>): " + Comparing.compare("A", "a"));
		
		/**
			compare(Object, Object): int --> Verilen Object tipindeki değerleri karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda 0 döner
			2) Soldaki değer küçük Sağdaki değer büyük olduğunda -1 döner
			3) Soldaki değer büyük Sağdaki değer küçük olduğunda 1 döner
		**/
		Comparator<Integer> intComp = Comparator.naturalOrder();
		System.out.println("Comparing.compare(Object, Object): " + Comparing.compare(25, 25, intComp));
		System.out.println("Comparing.compare(Object, Object): " + Comparing.compare(0, 25, intComp));
		System.out.println("Comparing.compare(Object, Object): " + Comparing.compare(25, 0, intComp));

		/**
			compare(boolean, boolean): int --> Verilen boolean tipindeki değerleri karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda 0 döner
			2) Soldaki değer küçük Sağdaki değer büyük olduğunda -1 döner
			3) Soldaki değer büyük Sağdaki değer küçük olduğunda 1 döner
		**/
		System.out.println("Comparing.compare(boolean, boolean): " + Comparing.compare(true, true));
		System.out.println("Comparing.compare(boolean, boolean): " + Comparing.compare(false, true));
		System.out.println("Comparing.compare(boolean, boolean): " + Comparing.compare(true, false));
		
		/**
			compare(byte, byte): int --> Verilen byte tipindeki değerleri karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda 0 döner
			2) Soldaki değer küçük Sağdaki değer büyük olduğunda -1 döner
			3) Soldaki değer büyük Sağdaki değer küçük olduğunda 1 döner
		**/
		System.out.println("Comparing.compare(byte, byte): " + Comparing.compare(25, 25));
		System.out.println("Comparing.compare(byte, byte): " + Comparing.compare(0, 25));
		System.out.println("Comparing.compare(byte, byte): " + Comparing.compare(25, 0));
		
		/**
			compare(byte[], byte[]): int --> Verilen byte[] tipindeki değerleri karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda 0 döner
			2) Soldaki değer küçük Sağdaki değer büyük olduğunda -1 döner
			3) Soldaki değer büyük Sağdaki değer küçük olduğunda 1 döner
		**/
		System.out.println("Comparing.compare(byte[], byte[]): " + Comparing.compare(new byte[]{1,2,3,4}, new byte[]{1,2,3,4}));
		System.out.println("Comparing.compare(byte[], byte[]): " + Comparing.compare(new byte[]{1,2,3,4}, new byte[]{4,3,2,1}));
		System.out.println("Comparing.compare(byte[], byte[]): " + Comparing.compare(new byte[]{4,3,2,1}, new byte[]{1,2,3,4}));
		
		/**
			compare(double, double): int --> Verilen double tipindeki değerleri karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda 0 döner
			2) Soldaki değer küçük Sağdaki değer büyük olduğunda -1 döner
			3) Soldaki değer büyük Sağdaki değer küçük olduğunda 1 döner
		**/
		System.out.println("Comparing.compare(double, double): " + Comparing.compare(25.0, 25.0));
		System.out.println("Comparing.compare(double, double): " + Comparing.compare(0.0, 25.0));
		System.out.println("Comparing.compare(double, double): " + Comparing.compare(25.0, 0.0));
		
		/**
			compare(int, int): int --> Verilen int tipindeki değerleri karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda 0 döner
			2) Soldaki değer küçük Sağdaki değer büyük olduğunda -1 döner
			3) Soldaki değer büyük Sağdaki değer küçük olduğunda 1 döner
		**/
		System.out.println("Comparing.compare(int, int): " + Comparing.compare(25, 25));
		System.out.println("Comparing.compare(int, int): " + Comparing.compare(0, 25));
		System.out.println("Comparing.compare(int, int): " + Comparing.compare(25, 0));
		
		/**
			compare(long, long): int --> Verilen long tipindeki değerleri karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda 0 döner
			2) Soldaki değer küçük Sağdaki değer büyük olduğunda -1 döner
			3) Soldaki değer büyük Sağdaki değer küçük olduğunda 1 döner
		**/
		System.out.println("Comparing.compare(long, long): " + Comparing.compare(25L, 25L));
		System.out.println("Comparing.compare(long, long): " + Comparing.compare(0L, 25L));
		System.out.println("Comparing.compare(long, long): " + Comparing.compare(25L, 0L));
		
		/**
			equal(CharSequence, CharSequence): boolean --> Verilen CharSequence tipindeki değerleri eşitlik olarak karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda true döner
			2) Her iki değerden biri faklı olduğunda false döner
		**/
		System.out.println("Comparing.equal(CharSequence, CharSequence): " + Comparing.equal((CharSequence)"Hello World!", (CharSequence)"Hello World!"));
		System.out.println("Comparing.equal(CharSequence, CharSequence): " + Comparing.equal((CharSequence)"Hello World!", (CharSequence)"Hello WORLD!"));
		
		/**
			equal(CharSequence, CharSequence, boolean): boolean --> Verilen CharSequence tipindeki değerleri eşitlik olarak karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda true döner
			2) Her iki değerden biri ile faklı olduğunda ve caseSensitive false ise false döner.
			3) Her iki değerden biri ile faklı olduğunda ve caseSensitive true ise true döner
		**/
		System.out.println("Comparing.equal(CharSequence, CharSequence, boolean): " + Comparing.equal((CharSequence)"Hello World!", (CharSequence)"Hello World!", true));
		System.out.println("Comparing.equal(CharSequence, CharSequence, boolean): " + Comparing.equal((CharSequence)"Hello World!", (CharSequence)"Hello WORLD!", true));
		System.out.println("Comparing.equal(CharSequence, CharSequence, boolean): " + Comparing.equal((CharSequence)"Hello World!", (CharSequence)"Hello WORLD!", false));
		
		/**
			equal(Object, Object): boolean --> Verilen Object tipindeki değerleri eşitlik olarak karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda true döner
			2) Her iki değerden biri ile faklı olduğunda ise false döner.
		**/
		System.out.println("Comparing.equal(Object, Object): " + Comparing.equal(new String("Hello World!"), new String("Hello World!")));
		System.out.println("Comparing.equal(Object, Object): " + Comparing.equal(new String("Hello World!"), new String("Hello WORLD!")));
		
		/**
			equal(Object[], Object[]): boolean --> Verilen Object[] tipindeki değerleri eşitlik olarak karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda true döner
			2) Her iki değerden biri ile faklı olduğunda ise false döner.
		**/
		System.out.println("Comparing.equal(Object[], Object[]): " + Comparing.equal(new String[]{"Hello World!"}, new String[]{"Hello World!"}));
		System.out.println("Comparing.equal(Object[], Object[]): " + Comparing.equal(new String[]{"Hello World!"}, new String[]{"Hello WORLD!"}));
		
		/**
			equal(String, String): boolean --> Verilen String tipindeki değerleri eşitlik olarak karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda true döner
			2) Her iki değerden biri ile faklı olduğunda ise false döner.
		**/
		System.out.println("Comparing.equal(String, String): " + Comparing.equal("Hello World!", "Hello World!"));
		System.out.println("Comparing.equal(String, String): " + Comparing.equal("Hello World!", "Hello WORLD!"));
		
		/**
			equal(String, String, boolean): boolean --> Verilen String tipindeki değerleri eşitlik olarak karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda true döner
			2) Her iki değerden biri ile faklı olduğunda ve caseSensitive false ise false döner.
			3) Her iki değerden biri ile faklı olduğunda ve caseSensitive true ise true döner
		**/
		System.out.println("Comparing.equal(String, String, boolean): " + Comparing.equal("Hello World!", "Hello World!", true));
		System.out.println("Comparing.equal(String, String, boolean): " + Comparing.equal("Hello World!", "Hello WORLD!", true));
		System.out.println("Comparing.equal(String, String, boolean): " + Comparing.equal("Hello World!", "Hello WORLD!", false));
		
		/**
			hashcode(Object): int --> Verilen Object tipindeki değerinden bir hashcodu üretir.
		**/
		System.out.println("Comparing.hashcode(Object): " + Comparing.hashcode(new String("Hello World!")));
		
		/**
			hashcode(Object, Object): int --> Verilen Object tipindeki değerinden bir hashcodu üretir.
			1) Her iki Object değerini bitwise olarak karşılaştırılır aynı durumunda 0 döner.
			2) Her iki Object değerini bitwise olarak karşılaştırılır farklı durumunda ortak bir hashcode döner.
		**/
		System.out.println("Comparing.hashcode(Object, Object): " + Comparing.hashcode(new String("Hello World!"), new String("Hello World!")));
		System.out.println("Comparing.hashcode(Object, Object): " + Comparing.hashcode(new String("Hello World!"), new String("Hello WORLD!")));
		
		/**
			haveEqualElements(Collection, Collection): boolean --> Verilen iki Collection tipindeki değerleri karşılaştırır.
			1) Her iki Collection değerleri karşılaştırılır aynı durumunda true döner.
			2) Her iki Collection değerleri karşılaştırılır faklı durumunda false döner.
		**/
		List<Integer> list1 = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++) list1.add(i + 1);
		List<Integer> list2 = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++) list2.add(i + 1);
		List<Integer> list3 = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++) list3.add((i + 1) * 2);
		System.out.println("Comparing.haveEqualElements(Collection, Collection): " + Comparing.haveEqualElements(list1, list2));
		System.out.println("Comparing.haveEqualElements(Collection, Collection): " + Comparing.haveEqualElements(list1, list3));
		
		/**
			haveEqualElements(Object[], Object[]): boolean --> Verilen iki Object[] tipindeki değerleri karşılaştırır.
			1) Her iki Object[] değerleri karşılaştırılır aynı durumunda true döner.
			2) Her iki Object[] değerleri karşılaştırılır faklı durumunda false döner.
		**/
		Integer[] i1 = new Integer[5];
		for(int i = 0; i < 5; i++) i1[i] = i + 1;
		Integer[] i2 = new Integer[5];
		for(int i = 0; i < 5; i++) i2[i] = i + 1;
		Integer[] i3 = new Integer[5];
		for(int i = 0; i < 5; i++) i3[i] = (i + 1) * 2;
		System.out.println("Comparing.haveEqualElements(Object[], Object[]): " + Comparing.haveEqualElements(i1, i2));
		System.out.println("Comparing.haveEqualElements(Object[], Object[]): " + Comparing.haveEqualElements(i1, i3));
		
		/**
			strEqual(String, String, boolean): boolean --> Verilen String tipindeki değerleri eşitlik olarak karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda true döner
			2) Her iki değerden biri ile faklı olduğunda ve caseSensitive false ise false döner.
			3) Her iki değerden biri ile faklı olduğunda ve caseSensitive true ise true döner
		**/
		System.out.println("Comparing.strEqual(String, String, boolean): " + Comparing.strEqual("Hello World!", "Hello World!", true));
		System.out.println("Comparing.strEqual(String, String, boolean): " + Comparing.strEqual("Hello World!", "Hello WORLD!", true));
		System.out.println("Comparing.strEqual(String, String, boolean): " + Comparing.strEqual("Hello World!", "Hello WORLD!", false));
		
		/**
			strEqual(String, String): boolean --> Verilen String tipindeki değerleri eşitlik olarak karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda true döner
			2) Her iki değerden biri ile faklı olduğunda ise false döner.
		**/
		System.out.println("Comparing.strEqual(String, String): " + Comparing.strEqual("Hello World!", "Hello World!"));
		System.out.println("Comparing.strEqual(String, String): " + Comparing.strEqual("Hello World!", "Hello WORLD!"));
		
		/**
			unorderedHashcode(Collection): int --> Verilen Collection tipindeki değerinden düzensiz bir hashcod üretir.
		**/
		List<Integer> list4 = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++) list4.add((i + 1) * 2);
		System.out.println("Comparing.unorderedHashcode(Collection): " + Comparing.unorderedHashcode(list4));
	}
}