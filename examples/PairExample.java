package com.emrecellebi;

import com.emrecellebi.util.Pair;

public class PairExample
{
	public static void main(String[] args)
	{
		/**
			Pair.NonNull(Object, Object) -> Yeni bir Pair.NonNull nesnesi yaratır.
		**/
		Pair.NonNull<String, String> pairN = new Pair.NonNull<>("NonNullFirst", "NonNullSecond");
		System.out.println("Pair.NonNull(Object, Object) -> " + pairN);
		
		/**
			Pair(Object, Object) -> Verilen Object değerlerinde bir Pair nesnesi yaratır.
		**/
		Pair<String, String> pair1 = new Pair<>("First", "Second");
		Pair<String, String> pair2 = new Pair<>("First", "Second");
		Pair<String, String> pair3 = new Pair<>("First3", "Second3");
		
		/**
			create(Object, Object): Pair<A, B> --> Yeni bir Pair nesnsi yaratır.
		**/
		System.out.println("Pair.create(Object, Object): Pair<A, B> -> " + Pair.create("First", "Second"));
		
		/**
			createIFunction(Object): IFunction --> Yeni bir IFunction Pair tipinde nenesi yaratır.
			Note: iç içe bir class dan oluşturulmuş nesne içinde nesne oluşturamazsın.
		**/
		IFunction<String, Pair<String, String>> spfun = Pair.createIFunction("default value");
		System.out.println("createIFunction(Object): IFunction.NULL -> " + spfun.NULL);
		System.out.println("createIFunction(Object): IFunction.TO_STRING -> " + spfun.TO_STRING);
		System.out.println("createIFunction(Object): IFunction.fun(Object) -> " + spfun.fun("Hello World!"));
		
		/**
			createNonNull(Object, Object): Pair.NonNull --> Yeni bir NonNull tipinde bir Pair nesnesi yaratır.
			Note: Tipi Pair veya NonNull olabilir her ikisinde de Pair methodlarını kullana bilir.
		**/
		Pair.NonNull<String, String> npair = Pair.createNonNull("NFirst", "NSecond");
		System.out.println("Pair.createNonNull(Object, Object): Pair.NonNull -> " + npair);
		
		/**
			empty(): Pair --> Boş bir Pair nesnesi oluşturur.
		**/
		System.out.println("Pair.empty(): Pair -> " + Pair.empty());
		
		/**
			equals(Object): boolean --> Verilen Object tipindeki değeri Pair nesnesi ile eşitlik olarak karşılaştır.
			1) Her iki değer bir biri ile aynı durumunda true döner.
			2) Her iki değerden biri ile faklı olduğunda ise false döner
		**/
		System.out.println("pair.equals(Object): " + pair1.equals(pair2));
		System.out.println("pair.equals(Object): " + pair1.equals(pair3));
		
		/**
			getFirst(): Object --> Pair nesnesindeki first değerini döner.
		**/
		System.out.println("pair.getFirst(): " + pair1.getFirst());
		
		/**
			getFirst(Pair): Object --> Pair nesnesindeki first değerini döner.
		**/
		System.out.println("Pair.getFirst(Pair): " + Pair.getFirst(pair1));
		
		/**
			getSecond(): Object --> Pair nesnesindeki second değerini döner.
		**/
		System.out.println("pair.getSecond(): " + pair1.getSecond());
		
		/**
			getSecond(Pair): Object --> Pair nesnesindeki second değerini döner.
		**/
		System.out.println("Pair.getSecond(Pair): " + Pair.getSecond(pair1));
		
		/**
			hashCode(): int --> Pair nesnesinden bir hashCode üretir.
		**/
		System.out.println("pair.hashCode(): " + pair1.hashCode());
		
		/**
			pair(Object, Object): Pair --> Yeni bir Pair nesnesi yaratır.
		**/
		System.out.println("Pair.pair(Object, Object): " + Pair.pair("First", "Second"));
		
		/**
			toString(): String --> Oluşturulan Pair nesnesini String olarak döner.
		**/
		System.out.println("pair: String -> " + pair1);
		System.out.println("pair.first: Object -> " + pair1.first);
		System.out.println("pair.second: Object -> " + pair1.second);
		System.out.println("pair.toString(): String -> " + pair1.toString());
	}
}