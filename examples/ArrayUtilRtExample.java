package com.emrecellebi;

import com.emrecellebi.util.text.ArrayUtilRt;

public class ArrayUtilRtExample
{
	public static void main(String[] args)
	{
		Integer[] indis = {5, 8, 9, 7, 3, 4};
		List<String> list = new ArrayList<String>();
		for(int i = 0; i <= 10; i++) list.add((i * 2) + "");
		
		System.out.println("ArrayUtilRt.find(Object<T>[], Object<T>): int -> " + ArrayUtilRt.find(indis, 9));
		System.out.println("ArrayUtilRt.indexOf(Object<T>[], Object<T>, int, int): int -> " + ArrayUtilRt.indexOf(indis, 9, 0, 6));
		System.out.print("ArrayUtilRt.toStringArray(Collection<String>): String[] -> ");
		String[] arr = ArrayUtilRt.toStringArray(list);
		for(int i = 0; i < arr.length; i++) 
			System.out.print(arr[i] + ",");
	}
}