package com.emrecellebi.util;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

import com.emrecellebi.IArrayFactory;

public final class ArrayUtil
{
	public static <T> T[] append(T[] src, T element, IArrayFactory<? extends T> factory)
	{
		int length = src.length;
		T[] result = (T[])factory.create(length + 1);
		System.arraycopy(src, 0, result, 0, length);
		result[length] = element;
		return result;
	}
	
	public static <T> T[] append(T[] src, T element, Class<T> componentType)
	{
		int length = src.length;
		T[] result = newArray(componentType, length + 1);
		System.arraycopy(src, 0, result, 0, length);
		result[length] = element;
		return result;
	}
	
	public static boolean[] append(boolean[] array, boolean value)
	{
		array = realloc(array, array.length + 1);
		array[array.length - 1] = value;
		return array;
	}
	
	public static byte[] append(byte[] array, byte value)
	{
		array = realloc(array, array.length + 1);
		array[array.length - 1] = value;
		return array;
	}
	
	public static int[] append(int[] array, int value)
	{
		array = realloc(array, array.length + 1);
		array[array.length - 1] = value;
		return array;
	}
	
	public static long[] append(long[] array, long value)
	{
		array = realloc(array, array.length + 1);
		array[array.length - 1] = value;
		return array;
	}
	
	public static float[] append(float[] array, float value)
	{
		array = realloc(array, array.length + 1);
		array[array.length - 1] = value;
		return array;
	}
	
	public static char[] append(char[] array, char value)
	{
		array = realloc(array, array.length + 1);
		array[array.length - 1] = value;
		return array;
	}
	
	public static short[] append(short[] array, short value)
	{
		array = realloc(array, array.length + 1);
		array[array.length - 1] = value;
		return array;
	}
	
	public static long averageAmongMedians(int[] time, int part)
	{ 
		assert part >= 1;
		int n = time.length;
		Arrays.sort(time);
		long total = 0L;
		int start = n / 2 - n / part / 2;
		int end = n / 2 + n / part / 2;
		for(int i = start; i < end; i++)
			total += time[i]; 
		int middlePartLength = end - start;
		return (middlePartLength == 0) ? 0L : (total / middlePartLength);
	}
	
	public static long averageAmongMedians(long[] time, int part)
	{
		assert part >= 1;
		int n = time.length;
		Arrays.sort(time);
		long total = 0L;
		int start = n / 2 - n / part / 2;
		int end = n / 2 + n / part / 2;
		for(int i = start; i < end; i++)
			total += time[i]; 
		int middlePartLength = end - start;
		return (middlePartLength == 0) ? 0L : (total / middlePartLength);
	}
	
	public static <T> boolean contains(T o, T... objects)
	{
		return (indexOf((Object[])objects, o) >= 0);
	}
	
	public static boolean contains(String s, String... strings)
	{
		return (indexOf((Object[])strings, s) >= 0);
	}
	
	
  
  
  
  
	public static boolean[] realloc(boolean[] array, int newSize)
	{
		if(newSize == 0) return new boolean[0];
		int oldSize = array.length;
		return (oldSize == newSize) ? array : Arrays.copyOf(array, newSize);
	}
	
	public static byte[] realloc(byte[] array, int newSize)
	{
		if(newSize == 0) return new byte[0];
		int oldSize = array.length;
		return (oldSize == newSize) ? array : Arrays.copyOf(array, newSize);
	}
	
	public static char[] realloc(char[] array, int newSize)
	{
		if(newSize == 0) return new char[0];
		int oldSize = array.length;
		return (oldSize == newSize) ? array : Arrays.copyOf(array, newSize);
	}
	
	public static int[] realloc(int[] array, int newSize)
	{
		if(newSize == 0) return new int[0];
		int oldSize = array.length;
		return (oldSize == newSize) ? array : Arrays.copyOf(array, newSize);
	}
	
	public static long[] realloc(long[] array, int newSize)
	{
		if(newSize == 0) return new long[0];
		int oldSize = array.length;
		return (oldSize == newSize) ? array : Arrays.copyOf(array, newSize);
	}
	
	public static short[] realloc(short[] array, int newSize)
	{
		if(newSize == 0) return new short[0];
		int oldSize = array.length;
		return (oldSize == newSize) ? array : Arrays.copyOf(array, newSize);
	}
	
	public static float[] realloc(float[] array, int newSize)
	{
		if(newSize == 0) return new float[0];
		int oldSize = array.length;
		return (oldSize == newSize) ? array : Arrays.copyOf(array, newSize);
	}
	
	public static double[] realloc(double[] array, int newSize)
	{
		if(newSize == 0) return new double[0];
		int oldSize = array.length;
		return (oldSize == newSize) ? array : Arrays.copyOf(array, newSize);
	}
	
	public static int indexOf(Object[] objects, Object object)
	{
		return ArrayUtilRt.indexOf(objects, object, 0, objects.length);
	}
	
	public static <T> T[] newArray(Class<T> type, int length)
	{
		return (T[])Array.newInstance(type, length);
	}
}