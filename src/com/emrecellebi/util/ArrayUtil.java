package com.emrecellebi.util;

// import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

// import gnu.trove.Equality;

import com.emrecellebi.IArrayFactory;

public final class ArrayUtil
{
	public static <T> T[] append(T[] src, T element)
	{
		return append(src, element, getComponentType(src));
	}
	
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
	
	public static double[] append(double[] array, double value)
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
	
	public static <T> void copy(Collection<? extends T> src, T[] dst, int dstOffset)
	{
		int i = dstOffset;
		for(T t : src) dst[i++] = t;
	}
	
	public static <T> T[] copyOf(T[] original)
	{
		return Arrays.copyOf(original, original.length);
	}
	
	public static boolean[] copyOf(boolean[] original)
	{
		return (original.length == 0) ? new boolean[0] : Arrays.copyOf(original, original.length);
	}
	
	public static byte[] copyOf(byte[] original)
	{
		return (original.length == 0) ? new byte[0] : Arrays.copyOf(original, original.length);
	}
	
	public static char[] copyOf(char[] original)
	{
		return (original.length == 0) ? new char[0] : Arrays.copyOf(original, original.length);
	}
	
	public static double[] copyOf(double[] original)
	{
		return (original.length == 0) ? new double[0] : Arrays.copyOf(original, original.length);
	}
	
	public static float[] copyOf(float[] original)
	{
		return (original.length == 0) ? new float[0] : Arrays.copyOf(original, original.length);
	}
	
	public static int[] copyOf(int[] original)
	{
		return (original.length == 0) ? new int[0] : Arrays.copyOf(original, original.length);
	}
	
	public static long[] copyOf(long[] original)
	{
		return (original.length == 0) ? new long[0] : Arrays.copyOf(original, original.length);
	}
	
	public static short[] copyOf(short[] original)
	{
		return (original.length == 0) ? new short[0] : Arrays.copyOf(original, original.length);
	}
	
	public static <E> E[] ensureExactSize(int count, E[] sample)
	{
		if(count == sample.length) return sample; 
		return newArray(getComponentType(sample), count);
	}
	
	public static <T> boolean equals(T[] a1, T[] a2, Comparator<? super T> comparator)
	{
		if(a1 == a2) return true; 
		int length = a2.length;
		if(a1.length != length) return false; 
		for(int i = 0; i < length; i++)
			if(comparator.compare(a1[i], a2[i]) != 0) return false; 
		return true;
	}
	
	/* public static <T> boolean equals(T[] a1, T[] a2, Equality<? super T> comparator)
	{
		if(a1 == a2) return true; 
		int length = a2.length;
		if(a1.length != length) return false; 
		for(int i = 0; i < length; i++)
			if(!comparator.equals(a1[i], a2[i])) return false; 
		return true;
	} */
  
	public static <T> int find(T[] src, T obj)
	{
		return ArrayUtilRt.find((Object[])src, obj);
	}
	
	public static int find(boolean[] src, boolean obj)
	{
		return indexOf(src, obj);
	}
	
	public static int find(byte[] src, byte obj)
	{
		return indexOf(src, obj);
	}
	
	public static int find(int[] src, int obj)
	{
		return indexOf(src, obj);
	}
	
	public static int find(long[] src, long obj)
	{
		return indexOf(src, obj);
	}
	
	public static int find(char[] src, char obj)
	{
		return indexOf(src, obj);
	}
	
	public static int find(short[] src, short obj)
	{
		return indexOf(src, obj);
	}
	
	public static int find(float[] src, float obj)
	{
		return indexOf(src, obj);
	}
	
	public static int find(double[] src, double obj)
	{
		return indexOf(src, obj);
	}
	
	public static <T> Class<T> getComponentType(T[] collection)
	{
		return (Class)collection.getClass().getComponentType();
	}
	
	public static <T> T getFirstElement(T[] array)
	{
		return (array != null && array.length > 0) ? array[0] : null;
	}
	
	public static boolean getFirstElement(boolean[] array)
	{
		return (array != null && array.length > 0) ? array[0] : null;
	}
	
	public static byte getFirstElement(byte[] array)
	{
		return (array != null && array.length > 0) ? array[0] : null;
	}
	
	public static int getFirstElement(int[] array)
	{
		return (array != null && array.length > 0) ? array[0] : null;
	}
	
	public static long getFirstElement(long[] array)
	{
		return (array != null && array.length > 0) ? array[0] : null;
	}
	
	public static char getFirstElement(char[] array)
	{
		return (array != null && array.length > 0) ? array[0] : null;
	}
	
	public static short getFirstElement(short[] array)
	{
		return (array != null && array.length > 0) ? array[0] : null;
	}
	
	public static float getFirstElement(float[] array)
	{
		return (array != null && array.length > 0) ? array[0] : null;
	}
	
	public static double getFirstElement(double[] array)
	{
		return (array != null && array.length > 0) ? array[0] : null;
	}
	
	public static <T> T getFirstElement(T[] array, T defaultValue)
	{
		return (array != null && array.length > 0) ? array[0] : defaultValue;
	}
	
	public static boolean getFirstElement(boolean[] array, boolean defaultValue)
	{
		return (array != null && array.length > 0) ? array[0] : defaultValue;
	}
	
	public static byte getFirstElement(byte[] array, byte defaultValue)
	{
		return (array != null && array.length > 0) ? array[0] : defaultValue;
	}
	
	public static int getFirstElement(int[] array, int defaultValue)
	{
		return (array != null && array.length > 0) ? array[0] : defaultValue;
	}
	
	public static long getFirstElement(long[] array, long defaultValue)
	{
		return (array != null && array.length > 0) ? array[0] : defaultValue;
	}
	
	public static char getFirstElement(char[] array, char defaultValue)
	{
		return (array != null && array.length > 0) ? array[0] : defaultValue;
	}
	
	public static short getFirstElement(short[] array, short defaultValue)
	{
		return (array != null && array.length > 0) ? array[0] : defaultValue;
	}
	
	public static float getFirstElement(float[] array, float defaultValue)
	{
		return (array != null && array.length > 0) ? array[0] : defaultValue;
	}
	
	public static double getFirstElement(double[] array, double defaultValue)
	{
		return (array != null && array.length > 0) ? array[0] : defaultValue;
	}
	
	public static <T> T getLastElement(T[] array)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : null;
	}
	
	public static boolean getLastElement(boolean[] array)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : null;
	}
	
	public static byte getLastElement(byte[] array)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : null;
	}
	
	public static int getLastElement(int[] array)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : null;
	}
	
	public static long getLastElement(long[] array)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : null;
	}
	
	public static char getLastElement(char[] array)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : null;
	}
	
	public static short getLastElement(short[] array)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : null;
	}
	
	public static float getLastElement(float[] array)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : null;
	}
	
	public static double getLastElement(double[] array)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : null;
	}
	
	public static <T> T getLastElement(T[] array, T defaultValue)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : defaultValue;
	}
	
	public static boolean getLastElement(boolean[] array, boolean defaultValue)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : defaultValue;
	}
	
	public static byte getLastElement(byte[] array, byte defaultValue)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : defaultValue;
	}
	
	public static int getLastElement(int[] array, int defaultValue)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : defaultValue;
	}
	
	public static long getLastElement(long[] array, long defaultValue)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : defaultValue;
	}
	
	public static char getLastElement(char[] array, char defaultValue)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : defaultValue;
	}
	
	public static short getLastElement(short[] array, short defaultValue)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : defaultValue;
	}
	
	public static float getLastElement(float[] array, float defaultValue)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : defaultValue;
	}
	
	public static double getLastElement(double[] array, double defaultValue)
	{
		return (array != null && array.length > 0) ? array[array.length - 1] : defaultValue;
	}
	
	public static <T> int indexOf(List<? extends T> objects, T object, BiPredicate<? super T, ? super T> predicate)
	{
		for(int i = 0; i < objects.size(); i++)
			if(predicate.test(objects.get(i), object)) return i; 
		return -1;
	}
	
	public static int indexOf(Object[] objects, Object object)
	{
		return ArrayUtilRt.indexOf(objects, object, 0, objects.length);
	}
	
	public static <T> int indexOf(T[] objects, T object, BiPredicate<? super T, ? super T> comparator)
	{
		for(int i = 0; i < objects.length; i++)
			if(comparator.test(objects[i], object)) return i;
		return -1;
	}
	
	public static int indexOf(boolean[] array, boolean[] pattern, int startIndex)
	{
		for(int i = startIndex; i <= array.length - pattern.length; i++)
			if(startsWith(array, i, pattern)) return i; 
		return -1;
	}
	
	public static int indexOf(byte[] array, byte[] pattern, int startIndex)
	{
		for(int i = startIndex; i <= array.length - pattern.length; i++)
			if(startsWith(array, i, pattern)) return i; 
		return -1;
	}
	
	public static int indexOf(int[] array, int[] pattern, int startIndex)
	{
		for(int i = startIndex; i <= array.length - pattern.length; i++)
			if(startsWith(array, i, pattern)) return i; 
		return -1;
	}
	
	public static int indexOf(long[] array, long[] pattern, int startIndex)
	{
		for(int i = startIndex; i <= array.length - pattern.length; i++)
			if(startsWith(array, i, pattern)) return i; 
		return -1;
	}
	
	public static int indexOf(char[] array, char[] pattern, int startIndex)
	{
		for(int i = startIndex; i <= array.length - pattern.length; i++)
			if(startsWith(array, i, pattern)) return i; 
		return -1;
	}
	
	public static int indexOf(short[] array, short[] pattern, int startIndex)
	{
		for(int i = startIndex; i <= array.length - pattern.length; i++)
			if(startsWith(array, i, pattern)) return i; 
		return -1;
	}
	
	public static int indexOf(float[] array, float[] pattern, int startIndex)
	{
		for(int i = startIndex; i <= array.length - pattern.length; i++)
			if(startsWith(array, i, pattern)) return i; 
		return -1;
	}
	
	public static int indexOf(double[] array, double[] pattern, int startIndex)
	{
		for(int i = startIndex; i <= array.length - pattern.length; i++)
			if(startsWith(array, i, pattern)) return i; 
		return -1;
	}
	
	public static int indexOf(boolean[] ints, boolean value)
	{
		for(int i = 0; i < ints.length; i++)
			if(ints[i] == value) return i;
		return -1;
	}
	
	public static int indexOf(byte[] ints, byte value)
	{
		for(int i = 0; i < ints.length; i++)
			if(ints[i] == value) return i;
		return -1;
	}
	
	public static int indexOf(int[] ints, int value)
	{
		for(int i = 0; i < ints.length; i++)
			if(ints[i] == value) return i;
		return -1;
	}
	
	public static int indexOf(long[] ints, long value)
	{
		for(int i = 0; i < ints.length; i++)
			if(ints[i] == value) return i;
		return -1;
	}
	
	public static int indexOf(char[] ints, char value)
	{
		for(int i = 0; i < ints.length; i++)
			if(ints[i] == value) return i;
		return -1;
	}
	
	public static int indexOf(short[] ints, short value)
	{
		for(int i = 0; i < ints.length; i++)
			if(ints[i] == value) return i;
		return -1;
	}
	
	public static int indexOf(float[] ints, float value)
	{
		for(int i = 0; i < ints.length; i++)
			if(ints[i] == value) return i;
		return -1;
	}
	
	public static int indexOf(double[] ints, double value)
	{
		for(int i = 0; i < ints.length; i++)
			if(ints[i] == value) return i;
		return -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// boolean, byte, int, long, char, short, float, double
  
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
	
	public static <T> T[] newArray(Class<T> type, int length)
	{
		return (T[])Array.newInstance(type, length);
	}
	
	public static boolean startsWith(boolean[] array, int start, boolean[] subArray)
	{
		int length = subArray.length;
		if(array.length - start < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[start + i] != subArray[i]) return false;
		return true;
	}
	
	public static boolean startsWith(byte[] array, int start, byte[] subArray)
	{
		int length = subArray.length;
		if(array.length - start < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[start + i] != subArray[i]) return false;
		return true;
	}
	
	public static boolean startsWith(int[] array, int start, int[] subArray)
	{
		int length = subArray.length;
		if(array.length - start < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[start + i] != subArray[i]) return false;
		return true;
	}
	
	public static boolean startsWith(long[] array, int start, long[] subArray)
	{
		int length = subArray.length;
		if(array.length - start < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[start + i] != subArray[i]) return false;
		return true;
	}
	
	public static boolean startsWith(char[] array, int start, char[] subArray)
	{
		int length = subArray.length;
		if(array.length - start < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[start + i] != subArray[i]) return false;
		return true;
	}
	
	public static boolean startsWith(short[] array, int start, short[] subArray)
	{
		int length = subArray.length;
		if(array.length - start < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[start + i] != subArray[i]) return false;
		return true;
	}
	
	public static boolean startsWith(float[] array, int start, float[] subArray)
	{
		int length = subArray.length;
		if(array.length - start < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[start + i] != subArray[i]) return false;
		return true;
	}
	
	public static boolean startsWith(double[] array, int start, double[] subArray)
	{
		int length = subArray.length;
		if(array.length - start < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[start + i] != subArray[i]) return false;
		return true;
	}
}