package com.emrecellebi;

import com.emrecellebi.util.IFunction;

public class IFunctionExample
{
	public static void main(String[] args)
	{
		/**
			IFunction interface bir nesne oluşturlduğunda onu override edilir.
		**/
		IFunction<Integer, String> func = new IFunction<Integer, String>()
		{
			@Override
			public String fun(Integer num)
			{
				return "Number: " + num;
			}
		};
		System.out.println("IFunction.fun(Object): Object -> " + func.fun(25));
		System.out.println("IFunction.NULL -> " + func.NULL);
		System.out.println("IFunction.TO_STRING -> " + func.TO_STRING);
		System.out.println("IFunction.ID -> " + func.ID);
		
		/**
			IFunction interface içerisinde de bulanan sınıfdan nesne türetilir.
		**/
		IFunction.First<Integer> funf = new IFunction.First<Integer>();
		Integer[] array = {1, 2, 3, 4};
		System.out.println("IFunction.fun(Object[]): Object -> " + funf.fun(array));
		
		/**
			IFunction interface içerisinde de bulanan sınıfdan nesne türetilir.
		**/
		IFunction.FirstCollection<Integer> funcoll = new IFunction.FirstCollection<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++) list.add(i + 1);
		System.out.println("IFunction.fun(Collection): Object -> " + funcoll.fun(list));
		
		/**
			IFunction interface içerisinde de bulanan sınıfdan nesne türetilir.
		**/
		String str1 = "Hello World!";
		String str2 = null;
		IFunction.InstanceOf<String, String> funIn = new IFunction.InstanceOf<String, String>(String.class);
		System.out.println("IFunction.fun(Class): Object -> " + funIn.fun(str1));
		System.out.println("IFunction.fun(Class): Object -> " + funIn.fun(str2));
	}
}