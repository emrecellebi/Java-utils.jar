package com.emrecellebi;

import com.emrecellebi.util.ObjectFactory;

public class ObjectFactoryExample
{
	public static void main(String[] args)
	{
		/**
			getConstructor(Class<T>, Class<?>[], Object...): Object<T> --> Verilen sıfın erilemeyen Constructor dan bir nesne döner.
			1) Özel Constructor erişilicek sınıf bilgisi.
			2) Constructor veri tipi bilgisi.
			3) Constructor veri bilgisi.
		**/
		Class[] constTypes = new Class[]{char[].class, boolean.class};
		Object[] constParams = new Object[]{"Hello World".toCharArray(), true};
		System.out.println("ObjectFactory.getConstructor(Class<T>, Class<?>[], Object...): Object<T> -> " + ObjectFactory.getConstructor(String.class, constTypes, constParams));
		
		/**
			getConstructors(Class<T>): void --> Constructor listesini döner.
		**/
		ObjectFactory.getConstructors(String.class);
		
		/**
			getMethod(Class<T>, String, Class<?>[], Object...): Object --> Verilen sıfın erilemeyen Constructor dan bir nesne döner.
			1) Özel Method erişilicek sınıf bilgisi.
			2) Erişilicek methodun adı.
			3) Method veri tipi bilgisi.
			4) Method veri bilgisi.
		**/
		Class[] mTypes = new Class[]{boolean.class};
		Object[] mParams = new Object[]{true};
		System.out.println("ObjectFactory.getMethod(Class<T>, String, Class<?>[], Object...): Object -> " ObjectFactory.getMethod(String.class, "valueOf", mTypes, mParams));
		
		/**
			getMethods(Class<T>): void --> Method listesini döner.
		**/
		ObjectFactory.getMethods(String.class);
	}
}