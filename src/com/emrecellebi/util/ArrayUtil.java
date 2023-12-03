package com.emrecellebi.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

import com.emrecellebi.openapi.util.Comparing;

public final class ArrayUtil
{
	public static final IArrayFactory<String> STRING_ARRAY_FACTORY = ArrayUtil::newStringArray;
	public static final IArrayFactory<Object> OBJECT_ARRAY_FACTORY = ArrayUtil::newObjectArray;
	
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
	
	public static <T> int indexOfIdentity(T[] list, T element)
	{
		for(int i = 0; i < list.length; i++)
			if(list[i] == element) return i; 
		return -1;
	}
	
	public static <T> T[] insert(T[] array, int index, T value)
	{
		T[] result = newArray(getComponentType(array), array.length + 1);
		System.arraycopy(array, 0, result, 0, index);
		result[index] = value;
		System.arraycopy(array, index, result, index + 1, array.length - index);
		return result;
	}
	
	public static boolean[] insert(boolean[] array, int index, boolean value)
	{
		boolean[] result = new boolean[array.length + 1];
		System.arraycopy(array, 0, result, 0, index);
		result[index] = value;
		System.arraycopy(array, index, result, index + 1, array.length - index);
		return result;
	}
	
	public static byte[] insert(byte[] array, int index, byte value)
	{
		byte[] result = new byte[array.length + 1];
		System.arraycopy(array, 0, result, 0, index);
		result[index] = value;
		System.arraycopy(array, index, result, index + 1, array.length - index);
		return result;
	}
	
	public static int[] insert(int[] array, int index, int value)
	{
		int[] result = new int[array.length + 1];
		System.arraycopy(array, 0, result, 0, index);
		result[index] = value;
		System.arraycopy(array, index, result, index + 1, array.length - index);
		return result;
	}
	
	public static long[] insert(long[] array, int index, long value)
	{
		long[] result = new long[array.length + 1];
		System.arraycopy(array, 0, result, 0, index);
		result[index] = value;
		System.arraycopy(array, index, result, index + 1, array.length - index);
		return result;
	}
	
	public static char[] insert(char[] array, int index, char value)
	{
		char[] result = new char[array.length + 1];
		System.arraycopy(array, 0, result, 0, index);
		result[index] = value;
		System.arraycopy(array, index, result, index + 1, array.length - index);
		return result;
	}
	
	public static short[] insert(short[] array, int index, short value)
	{
		short[] result = new short[array.length + 1];
		System.arraycopy(array, 0, result, 0, index);
		result[index] = value;
		System.arraycopy(array, index, result, index + 1, array.length - index);
		return result;
	}
	
	public static float[] insert(float[] array, int index, float value)
	{
		float[] result = new float[array.length + 1];
		System.arraycopy(array, 0, result, 0, index);
		result[index] = value;
		System.arraycopy(array, index, result, index + 1, array.length - index);
		return result;
	}
	
	public static double[] insert(double[] array, int index, double value)
	{
		double[] result = new double[array.length + 1];
		System.arraycopy(array, 0, result, 0, index);
		result[index] = value;
		System.arraycopy(array, index, result, index + 1, array.length - index);
		return result;
	}
	
	public static <T> boolean isEmpty(T[] array)
	{
		return (array == null || array.length == 0);
	}
	
	public static <T> int lastIndexOf(List<? extends T> src, T obj, BiPredicate<? super T, ? super T> comparator)
	{
		for (int i = src.size() - 1; i >= 0; i--)
		{
			T o = src.get(i);
			if(comparator.test(obj, o)) return i; 
		}
		return -1;
	}
	
	public static <T> int lastIndexOf(T[] src, T obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			T o = src[i];
			if(o == null)
				if(obj == null) return i; 
			else if(o.equals(obj))
				return i;
		} 
		return -1;
	}
	
	public static <T> int lastIndexOf(T[] src, T obj, BiPredicate<? super T, ? super T> predicate)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			T o = src[i];
			if(predicate.test(obj, o)) return i; 
		}
		return -1;
	}
	
	public static int lastIndexOf(boolean[] src, boolean obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			boolean o = src[i];
			if(o == obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOf(byte[] src, byte obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			byte o = src[i];
			if(o == obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOf(int[] src, int obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			int o = src[i];
			if(o == obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOf(long[] src, long obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			long o = src[i];
			if(o == obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOf(char[] src, char obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			char o = src[i];
			if(o == obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOf(short[] src, short obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			short o = src[i];
			if(o == obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOf(float[] src, float obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			float o = src[i];
			if(o == obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOf(double[] src, double obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			double o = src[i];
			if(o == obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOfNot(boolean[] src, boolean obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			boolean o = src[i];
			if(o != obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOfNot(byte[] src, byte obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			byte o = src[i];
			if(o != obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOfNot(int[] src, int obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			int o = src[i];
			if(o != obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOfNot(long[] src, long obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			long o = src[i];
			if(o != obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOfNot(char[] src, char obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			char o = src[i];
			if(o != obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOfNot(short[] src, short obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			short o = src[i];
			if(o != obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOfNot(float[] src, float obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			float o = src[i];
			if(o != obj) return i; 
		} 
		return -1;
	}
	
	public static int lastIndexOfNot(double[] src, double obj)
	{
		for(int i = src.length - 1; i >= 0; i--)
		{
			double o = src[i];
			if(o != obj) return i; 
		} 
		return -1;
	}
	
	public static <T> int lexicographicCompare(T[] obj1, T[] obj2)
	{
		for(int i = 0; i < Math.max(obj1.length, obj2.length); i++)
		{
			T o1 = (i < obj1.length) ? obj1[i] : null;
			T o2 = (i < obj2.length) ? obj2[i] : null;
			if(o1 == null) return -1; 
			if(o2 == null) return 1; 
			int res = ((Comparable<T>)o1).compareTo(o2);
			if(res != 0) return res; 
		}
		return 0;
	}
	
	public static int lexicographicCompare(String[] obj1, String[] obj2)
	{
		for(int i = 0; i < Math.max(obj1.length, obj2.length); i++)
		{
			String o1 = (i < obj1.length) ? obj1[i] : null;
			String o2 = (i < obj2.length) ? obj2[i] : null;
			if(o1 == null) return -1; 
			if(o2 == null) return 1; 
			int res = o1.compareToIgnoreCase(o2);
			if(res != 0) return res; 
		} 
		return 0;
	}
	
	public static int lexicographicCompare(int[] obj1, int[] obj2)
	{
		for(int i = 0; i < Math.min(obj1.length, obj2.length); i++)
		{
			int res = Integer.compare(obj1[i], obj2[i]);
			if(res != 0) return res; 
		} 
		return Integer.compare(obj1.length, obj2.length);
	}
	
	public static int lexicographicCompare(boolean[] obj1, boolean[] obj2)
	{
		for(int i = 0; i < Math.min(obj1.length, obj2.length); i++)
		{
			int res = Boolean.compare(obj1[i], obj2[i]);
			if(res != 0) return res; 
		} 
		return Integer.compare(obj1.length, obj2.length);
	}
	
	public static int lexicographicCompare(long[] obj1, long[] obj2)
	{
		for(int i = 0; i < Math.min(obj1.length, obj2.length); i++)
		{
			int res = Long.compare(obj1[i], obj2[i]);
			if(res != 0) return res; 
		} 
		return Integer.compare(obj1.length, obj2.length);
	}
	
	public static int lexicographicCompare(char[] obj1, char[] obj2)
	{
		for(int i = 0; i < Math.min(obj1.length, obj2.length); i++)
		{
			int res = Character.compare(obj1[i], obj2[i]);
			if(res != 0) return res; 
		} 
		return Integer.compare(obj1.length, obj2.length);
	}
	
	public static int lexicographicCompare(short[] obj1, short[] obj2)
	{
		for(int i = 0; i < Math.min(obj1.length, obj2.length); i++)
		{
			int res = Short.compare(obj1[i], obj2[i]);
			if(res != 0) return res; 
		} 
		return Integer.compare(obj1.length, obj2.length);
	}
	
	public static int lexicographicCompare(float[] obj1, float[] obj2)
	{
		for(int i = 0; i < Math.min(obj1.length, obj2.length); i++)
		{
			int res = Float.compare(obj1[i], obj2[i]);
			if(res != 0) return res; 
		} 
		return Integer.compare(obj1.length, obj2.length);
	}
	
	public static int lexicographicCompare(double[] obj1, double[] obj2)
	{
		for(int i = 0; i < Math.min(obj1.length, obj2.length); i++)
		{
			int res = Double.compare(obj1[i], obj2[i]);
			if(res != 0) return res; 
		} 
		return Integer.compare(obj1.length, obj2.length);
	}
	
	public static int lexicographicCompare(byte[] obj1, byte[] obj2)
	{
		for(int i = 0; i < Math.min(obj1.length, obj2.length); i++)
		{
			int res = Byte.compare(obj1[i], obj2[i]);
			if(res != 0) return res; 
		} 
		return Integer.compare(obj1.length, obj2.length);
	}
	
	public static int max(int[] values)
	{
		int max = Integer.MIN_VALUE;
		for(int value : values)
			if(value > max) max = value; 
		return max;
	}
	
	public static <T> T[] mergeArrayAndCollection(T[] array, Collection<? extends T> collection, IArrayFactory<? extends T> factory)
	{
		T[] array2;
		if(collection.isEmpty()) return array;
		try
		{
			T[] a = (T[])factory.create(collection.size());
			array2 = collection.toArray(a);
		}catch(ArrayStoreException e){throw new RuntimeException("Bad elements in collection: " + collection, e);} 
		if(array.length == 0) return array2;
		T[] result = (T[])factory.create(array.length + collection.size());
		System.arraycopy(array, 0, result, 0, array.length);
		System.arraycopy(array2, 0, result, array.length, array2.length);
		return result;
	}
	
	public static <T> T[] mergeArrays(T[] a1, T[] a2)
	{
		if(a1.length == 0) return a2;
		if(a2.length == 0) return a1;
		Class<T> class1 = getComponentType(a1);
		Class<T> class2 = getComponentType(a2);
		Class<T> aClass = class1.isAssignableFrom(class2) ? class1 : class2;
		T[] result = newArray(aClass, a1.length + a2.length);
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}
	
	public static <T> T[] mergeArrays(T[] a1, T[] a2, IArrayFactory<? extends T> factory)
	{
		if(a1.length == 0) return a2;
		if(a2.length == 0) return a1;
		T[] result = (T[])factory.create(a1.length + a2.length);
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}
	
	public static String[] mergeArrays(String[] a1, String... a2)
	{
		return mergeArrays(a1, a2, STRING_ARRAY_FACTORY);
	}
	
	public static byte[] mergeArrays(byte[] a1, byte[] a2)
	{
		if(a1.length == 0) return a2;
		if(a2.length == 0) return a1;
		byte[] result = new byte[a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}
	
	public static int[] mergeArrays(int[] a1, int[] a2)
	{
		if(a1.length == 0) return a2;
		if(a2.length == 0) return a1;
		int[] result = new int[a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}
	
	public static boolean[] mergeArrays(boolean[] a1, boolean[] a2)
	{
		if(a1.length == 0) return a2;
		if(a2.length == 0) return a1;
		boolean[] result = new boolean[a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}
	
	public static long[] mergeArrays(long[] a1, long[] a2)
	{
		if(a1.length == 0) return a2;
		if(a2.length == 0) return a1;
		long[] result = new long[a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}
	
	public static char[] mergeArrays(char[] a1, char[] a2)
	{
		if(a1.length == 0) return a2;
		if(a2.length == 0) return a1;
		char[] result = new char[a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}
	
	public static short[] mergeArrays(short[] a1, short[] a2)
	{
		if(a1.length == 0) return a2;
		if(a2.length == 0) return a1;
		short[] result = new short[a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}
	
	public static float[] mergeArrays(float[] a1, float[] a2)
	{
		if(a1.length == 0) return a2;
		if(a2.length == 0) return a1;
		float[] result = new float[a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}
	
	public static double[] mergeArrays(double[] a1, double[] a2)
	{
		if(a1.length == 0) return a2;
		if(a2.length == 0) return a1;
		double[] result = new double[a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}
	
	public static <T> T[] mergeCollections(Collection<? extends T> c1, Collection<? extends T> c2, IArrayFactory<? extends T> factory)
	{
		T[] res = (T[])factory.create(c1.size() + c2.size());
		int i = 0;
		for(T t : c1) res[i++] = t; 
		for(T t : c2) res[i++] = t; 
		return res;
	}
	
	public static int[] mergeSortedArrays(int[] a1, int[] a2, boolean mergeEqualItems)
	{
		int newSize = a1.length + a2.length;
		if(newSize == 0) return new int[0]; 
		int[] r = new int[newSize];
		int o = 0;
		int index1 = 0;
		int index2 = 0;
		while(index1 < a1.length || index2 < a2.length)
		{
			int e;
			if(index1 >= a1.length)
				e = a2[index2++];
			else if(index2 >= a2.length)
				e = a1[index1++];
			else
			{
				int element1 = a1[index1];
				int element2 = a2[index2];
				if(element1 == element2)
				{
					index1++;
					index2++;
					if(mergeEqualItems)
						e = element1;
					else
					{
						r[o++] = element1;
						e = element2;
					} 
				}
				else if(element1 < element2)
				{
					e = element1;
					index1++;
				}
				else
				{
					e = element2;
					index2++;
				} 
			} 
			r[o++] = e;
		} 
		return (o == newSize) ? r : Arrays.copyOf(r, o);
	}
	
	public static int min(int[] values)
	{
		int min = Integer.MAX_VALUE;
		for(int value : values)
			if(value < min) min = value;
		return min;
	}
	
	public static <T> T[] newArray(Class<T> type, int length)
	{
		return (T[])Array.newInstance(type, length);
	}
	
	public static int[] newIntArray(int count)
	{
		return (count == 0) ? new int[0] : new int[count];
	}

	public static long[] newLongArray(int count)
	{
		return (count == 0) ? new long[0] : new long[count];
	}
	
	public static boolean[] newBooleanArray(int count)
	{
		return (count == 0) ? new boolean[0] : new boolean[count];
	}
	
	public static byte[] newByteArray(int count)
	{
		return (count == 0) ? new byte[0] : new byte[count];
	}
	
	public static char[] newCharArray(int count)
	{
		return (count == 0) ? new char[0] : new char[count];
	}
	
	public static short[] newShortArray(int count)
	{
		return (count == 0) ? new short[0] : new short[count];
	}
	
	public static float[] newFloatArray(int count)
	{
		return (count == 0) ? new float[0] : new float[count];
	}
	
	public static double[] newDoubleArray(int count)
	{
		return (count == 0) ? new double[0] : new double[count];
	}

	public static String[] newStringArray(int count)
	{
		return (count == 0) ? new String[0] : new String[count];
	}

	public static Object[] newObjectArray(int count)
	{
		return (count == 0) ? new Object[0] : new Object[count];
	}
	
	public static <T> T[] prepend(T element, T[] array)
	{
		return prepend(element, array, getComponentType(array));
	}
	
	public static <T> T[] prepend(T element, T[] src, IArrayFactory<? extends T> factory)
	{
		int length = src.length;
		T[] result = (T[])factory.create(length + 1);
		System.arraycopy(src, 0, result, 1, length);
		result[0] = element;
		return result;
	}
	
	public static <T> T[] prepend(T element, T[] array, Class<T> type)
	{
		int length = array.length;
		T[] result = newArray(type, length + 1);
		System.arraycopy(array, 0, result, 1, length);
		result[0] = element;
		return result;
	}
	
	public static byte[] prepend(byte element, byte[] array)
	{
		int length = array.length;
		byte[] result = new byte[length + 1];
		result[0] = element;
		System.arraycopy(array, 0, result, 1, length);
		return result;
	}
	
	public static boolean[] prepend(boolean element, boolean[] array)
	{
		int length = array.length;
		boolean[] result = new boolean[length + 1];
		result[0] = element;
		System.arraycopy(array, 0, result, 1, length);
		return result;
	}
	
	public static int[] prepend(int element, int[] array)
	{
		int length = array.length;
		int[] result = new int[length + 1];
		result[0] = element;
		System.arraycopy(array, 0, result, 1, length);
		return result;
	}
	
	public static long[] prepend(long element, long[] array)
	{
		int length = array.length;
		long[] result = new long[length + 1];
		result[0] = element;
		System.arraycopy(array, 0, result, 1, length);
		return result;
	}
	
	public static char[] prepend(char element, char[] array)
	{
		int length = array.length;
		char[] result = new char[length + 1];
		result[0] = element;
		System.arraycopy(array, 0, result, 1, length);
		return result;
	}
	
	public static short[] prepend(short element, short[] array)
	{
		int length = array.length;
		short[] result = new short[length + 1];
		result[0] = element;
		System.arraycopy(array, 0, result, 1, length);
		return result;
	}
	
	public static float[] prepend(float element, float[] array)
	{
		int length = array.length;
		float[] result = new float[length + 1];
		result[0] = element;
		System.arraycopy(array, 0, result, 1, length);
		return result;
	}
	
	public static double[] prepend(double element, double[] array)
	{
		int length = array.length;
		double[] result = new double[length + 1];
		result[0] = element;
		System.arraycopy(array, 0, result, 1, length);
		return result;
	}
	
	public static <T> T[] realloc(T[] array, int newSize, IArrayFactory<? extends T> factory)
	{
		int oldSize = array.length;
		if(oldSize == newSize) return array;
		T[] result = (T[])factory.create(newSize);
		if(newSize == 0) return result;
		System.arraycopy(array, 0, result, 0, Math.min(oldSize, newSize));
		return result;
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
	
	public static <T> T[] remove(T[] src, T element)
	{ 
		int index = ArrayUtilRt.find((Object[])src, element);
		return (index == -1) ? src : remove(src, index);
	}
	
	public static <T> T[] remove(T[] src, T element, IArrayFactory<? extends T> factory)
	{
		int idx = find(src, element);
		if(idx == -1) return src;
		return remove(src, idx, factory);
	}
	
	public static <T> T[] remove(T[] src, int idx)
	{
		int length = src.length;
		if(idx < 0 || idx >= length)
			throw new IllegalArgumentException("invalid index: " + idx); 
		Class<T> type = getComponentType(src);
		T[] result = newArray(type, length - 1);
		System.arraycopy(src, 0, result, 0, idx);
		System.arraycopy(src, idx + 1, result, idx, length - idx - 1);
		return result;
	}
	
	public static <T> T[] remove(T[] src, int idx, IArrayFactory<? extends T> factory)
	{
		int length = src.length;
		if(idx < 0 || idx >= length)
			throw new IllegalArgumentException("invalid index: " + idx); 
		T[] result = (T[])factory.create(length - 1);
		System.arraycopy(src, 0, result, 0, idx);
		System.arraycopy(src, idx + 1, result, idx, length - idx - 1);
		return result;
	}
	
	public static int[] remove(int[] src, int idx)
	{
		int length = src.length;
		if(idx < 0 || idx >= length)
			throw new IllegalArgumentException("invalid index: " + idx); 
		int[] result = newIntArray(src.length - 1);
		System.arraycopy(src, 0, result, 0, idx);
		System.arraycopy(src, idx + 1, result, idx, length - idx - 1);
		return result;
	}
	
	public static short[] remove(short[] src, int idx)
	{
		int length = src.length;
		if(idx < 0 || idx >= length)
			throw new IllegalArgumentException("invalid index: " + idx); 
		short[] result = (src.length == 1) ? new short[0] : new short[src.length - 1];
		System.arraycopy(src, 0, result, 0, idx);
		System.arraycopy(src, idx + 1, result, idx, length - idx - 1);
		return result;
	}
	
	public static boolean[] remove(boolean[] src, int idx)
	{
		int length = src.length;
		if(idx < 0 || idx >= length)
			throw new IllegalArgumentException("invalid index: " + idx); 
		boolean[] result = (src.length == 1) ? new boolean[0] : new boolean[src.length - 1];
		System.arraycopy(src, 0, result, 0, idx);
		System.arraycopy(src, idx + 1, result, idx, length - idx - 1);
		return result;
	}
	
	public static byte[] remove(byte[] src, int idx)
	{
		int length = src.length;
		if(idx < 0 || idx >= length)
			throw new IllegalArgumentException("invalid index: " + idx); 
		byte[] result = (src.length == 1) ? new byte[0] : new byte[src.length - 1];
		System.arraycopy(src, 0, result, 0, idx);
		System.arraycopy(src, idx + 1, result, idx, length - idx - 1);
		return result;
	}
	
	public static long[] remove(long[] src, int idx)
	{
		int length = src.length;
		if(idx < 0 || idx >= length)
			throw new IllegalArgumentException("invalid index: " + idx); 
		long[] result = (src.length == 1) ? new long[0] : new long[src.length - 1];
		System.arraycopy(src, 0, result, 0, idx);
		System.arraycopy(src, idx + 1, result, idx, length - idx - 1);
		return result;
	}
	
	public static char[] remove(char[] src, int idx)
	{
		int length = src.length;
		if(idx < 0 || idx >= length)
			throw new IllegalArgumentException("invalid index: " + idx); 
		char[] result = (src.length == 1) ? new char[0] : new char[src.length - 1];
		System.arraycopy(src, 0, result, 0, idx);
		System.arraycopy(src, idx + 1, result, idx, length - idx - 1);
		return result;
	}
	
	public static float[] remove(float[] src, int idx)
	{
		int length = src.length;
		if(idx < 0 || idx >= length)
			throw new IllegalArgumentException("invalid index: " + idx); 
		float[] result = (src.length == 1) ? new float[0] : new float[src.length - 1];
		System.arraycopy(src, 0, result, 0, idx);
		System.arraycopy(src, idx + 1, result, idx, length - idx - 1);
		return result;
	}
	
	public static double[] remove(double[] src, int idx)
	{
		int length = src.length;
		if(idx < 0 || idx >= length)
			throw new IllegalArgumentException("invalid index: " + idx); 
		double[] result = (src.length == 1) ? new double[0] : new double[src.length - 1];
		System.arraycopy(src, 0, result, 0, idx);
		System.arraycopy(src, idx + 1, result, idx, length - idx - 1);
		return result;
	}
	
	public static <T> T[] reverseArray(T[] array)
	{
		T[] newArray = (T[])array.clone();
		for(int i = 0; i < array.length; i++)
			newArray[array.length - i - 1] = array[i]; 
		return newArray;
	}
	
	public static int[] reverseArray(int[] array)
	{
		int[] newArray = (int[])array.clone();
		for(int i = 0; i < array.length; i++)
			newArray[array.length - i - 1] = array[i]; 
		return newArray;
	}
	
	public static boolean[] reverseArray(boolean[] array)
	{
		boolean[] newArray = (boolean[])array.clone();
		for(int i = 0; i < array.length; i++)
			newArray[array.length - i - 1] = array[i]; 
		return newArray;
	}
	
	public static byte[] reverseArray(byte[] array)
	{
		byte[] newArray = (byte[])array.clone();
		for(int i = 0; i < array.length; i++)
			newArray[array.length - i - 1] = array[i]; 
		return newArray;
	}
	
	public static long[] reverseArray(long[] array)
	{
		long[] newArray = (long[])array.clone();
		for(int i = 0; i < array.length; i++)
			newArray[array.length - i - 1] = array[i]; 
		return newArray;
	}
	
	public static char[] reverseArray(char[] array)
	{
		char[] newArray = (char[])array.clone();
		for(int i = 0; i < array.length; i++)
			newArray[array.length - i - 1] = array[i]; 
		return newArray;
	}
	
	public static short[] reverseArray(short[] array)
	{
		short[] newArray = (short[])array.clone();
		for(int i = 0; i < array.length; i++)
			newArray[array.length - i - 1] = array[i]; 
		return newArray;
	}
	
	public static float[] reverseArray(float[] array)
	{
		float[] newArray = (float[])array.clone();
		for(int i = 0; i < array.length; i++)
			newArray[array.length - i - 1] = array[i]; 
		return newArray;
	}
	
	public static double[] reverseArray(double[] array)
	{
		double[] newArray = (double[])array.clone();
		for(int i = 0; i < array.length; i++)
			newArray[array.length - i - 1] = array[i]; 
		return newArray;
	}
	
	public static <T> void rotateLeft(T[] array, int i1, int i2)
	{
		T t = array[i1];
		System.arraycopy(array, i1 + 1, array, i1, i2 - i1);
		array[i2] = t;
	}
	
	public static void rotateLeft(boolean[] array, int i1, int i2)
	{
		boolean t = array[i1];
		System.arraycopy(array, i1 + 1, array, i1, i2 - i1);
		array[i2] = t;
	}
	
	public static void rotateLeft(byte[] array, int i1, int i2)
	{
		byte t = array[i1];
		System.arraycopy(array, i1 + 1, array, i1, i2 - i1);
		array[i2] = t;
	}
	
	public static void rotateLeft(int[] array, int i1, int i2)
	{
		int t = array[i1];
		System.arraycopy(array, i1 + 1, array, i1, i2 - i1);
		array[i2] = t;
	}
	
	public static void rotateLeft(long[] array, int i1, int i2)
	{
		long t = array[i1];
		System.arraycopy(array, i1 + 1, array, i1, i2 - i1);
		array[i2] = t;
	}
	
	public static void rotateLeft(char[] array, int i1, int i2)
	{
		char t = array[i1];
		System.arraycopy(array, i1 + 1, array, i1, i2 - i1);
		array[i2] = t;
	}
	
	public static void rotateLeft(short[] array, int i1, int i2)
	{
		short t = array[i1];
		System.arraycopy(array, i1 + 1, array, i1, i2 - i1);
		array[i2] = t;
	}
	
	public static void rotateLeft(float[] array, int i1, int i2)
	{
		float t = array[i1];
		System.arraycopy(array, i1 + 1, array, i1, i2 - i1);
		array[i2] = t;
	}
	
	public static void rotateLeft(double[] array, int i1, int i2)
	{
		double t = array[i1];
		System.arraycopy(array, i1 + 1, array, i1, i2 - i1);
		array[i2] = t;
	}
	
	public static <T> void rotateRight(T[] array, int i1, int i2)
	{
		T t = array[i2];
		System.arraycopy(array, i1, array, i1 + 1, i2 - i1);
		array[i1] = t;
	}
	
	public static void rotateRight(boolean[] array, int i1, int i2)
	{
		boolean t = array[i2];
		System.arraycopy(array, i1, array, i1 + 1, i2 - i1);
		array[i1] = t;
	}
	
	public static void rotateRight(byte[] array, int i1, int i2)
	{
		byte t = array[i2];
		System.arraycopy(array, i1, array, i1 + 1, i2 - i1);
		array[i1] = t;
	}
	
	public static void rotateRight(int[] array, int i1, int i2)
	{
		int t = array[i2];
		System.arraycopy(array, i1, array, i1 + 1, i2 - i1);
		array[i1] = t;
	}
	
	public static void rotateRight(long[] array, int i1, int i2)
	{
		long t = array[i2];
		System.arraycopy(array, i1, array, i1 + 1, i2 - i1);
		array[i1] = t;
	}
	
	public static void rotateRight(char[] array, int i1, int i2)
	{
		char t = array[i2];
		System.arraycopy(array, i1, array, i1 + 1, i2 - i1);
		array[i1] = t;
	}
	
	public static void rotateRight(short[] array, int i1, int i2)
	{
		short t = array[i2];
		System.arraycopy(array, i1, array, i1 + 1, i2 - i1);
		array[i1] = t;
	}
	
	public static void rotateRight(float[] array, int i1, int i2)
	{
		float t = array[i2];
		System.arraycopy(array, i1, array, i1 + 1, i2 - i1);
		array[i1] = t;
	}
	
	public static void rotateRight(double[] array, int i1, int i2)
	{
		double t = array[i2];
		System.arraycopy(array, i1, array, i1 + 1, i2 - i1);
		array[i1] = t;
	}
	
	public static <E> boolean startsWith(E[] array, E[] subArray)
	{
		if(array == subArray) return true; 
		int length = subArray.length;
		if(array.length < length) return false; 
		for(int i = 0; i < length; i++)
			if(!Comparing.equal(array[i], subArray[i])) return false;
		return true;
	}
	
	public static boolean startsWith(boolean[] array, boolean[] prefix)
	{
		if(array == prefix) return true; 
		int length = prefix.length;
		if(array.length < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[i] != prefix[i]) return false; 
		return true;
	}
	
	public static boolean startsWith(byte[] array, byte[] prefix)
	{
		if(array == prefix) return true; 
		int length = prefix.length;
		if(array.length < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[i] != prefix[i]) return false; 
		return true;
	}
	
	public static boolean startsWith(int[] array, int[] prefix)
	{
		if(array == prefix) return true; 
		int length = prefix.length;
		if(array.length < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[i] != prefix[i]) return false; 
		return true;
	}
	
	public static boolean startsWith(long[] array, long[] prefix)
	{
		if(array == prefix) return true; 
		int length = prefix.length;
		if(array.length < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[i] != prefix[i]) return false; 
		return true;
	}
	
	public static boolean startsWith(char[] array, char[] prefix)
	{
		if(array == prefix) return true; 
		int length = prefix.length;
		if(array.length < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[i] != prefix[i]) return false; 
		return true;
	}
	
	public static boolean startsWith(short[] array, short[] prefix)
	{
		if(array == prefix) return true; 
		int length = prefix.length;
		if(array.length < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[i] != prefix[i]) return false; 
		return true;
	}
	
	public static boolean startsWith(float[] array, float[] prefix)
	{
		if(array == prefix) return true; 
		int length = prefix.length;
		if(array.length < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[i] != prefix[i]) return false; 
		return true;
	}
	
	public static boolean startsWith(double[] array, double[] prefix)
	{
		if(array == prefix) return true; 
		int length = prefix.length;
		if(array.length < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[i] != prefix[i]) return false; 
		return true;
	}
	
	public static <E> boolean startsWith(E[] array, int start, E[] subArray)
	{
		int length = subArray.length;
		if(array.length - start < length) return false; 
		for(int i = 0; i < length; i++)
			if(array[start + i] != subArray[i]) return false;
		return true;
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
	
	public static <T> T[] stripTrailingNulls(T[] array)
	{
		return (array.length != 0 && array[array.length - 1] == null) ? Arrays.<T>copyOf(array, trailingNullsIndex(array)) : array;
	}
	
	public static <T> void swap(T[] array, int i1, int i2)
	{
		T t = array[i1];
		array[i1] = array[i2];
		array[i2] = t;
	}
	
	public static void swap(boolean[] array, int i1, int i2)
	{
		boolean t = array[i1];
		array[i1] = array[i2];
		array[i2] = t;
	}
	
	public static void swap(char[] array, int i1, int i2)
	{
		char t = array[i1];
		array[i1] = array[i2];
		array[i2] = t;
	}
	
	public static void swap(int[] array, int i1, int i2)
	{
		int t = array[i1];
		array[i1] = array[i2];
		array[i2] = t;
	}
	
	public static void swap(byte[] array, int i1, int i2)
	{
		byte t = array[i1];
		array[i1] = array[i2];
		array[i2] = t;
	}
	
	public static void swap(long[] array, int i1, int i2)
	{
		long t = array[i1];
		array[i1] = array[i2];
		array[i2] = t;
	}
	
	public static void swap(short[] array, int i1, int i2)
	{
		short t = array[i1];
		array[i1] = array[i2];
		array[i2] = t;
	}
	
	public static void swap(float[] array, int i1, int i2)
	{
		float t = array[i1];
		array[i1] = array[i2];
		array[i2] = t;
	}
	
	public static void swap(double[] array, int i1, int i2)
	{
		double t = array[i1];
		array[i1] = array[i2];
		array[i2] = t;
	}
	
	public static int[] toIntArray(Collection<Integer> list)
	{
		int[] ret = newIntArray(list.size());
		int i = 0;
		for(Integer e : list)
			ret[i++] = e.intValue(); 
		return ret;
	}
	
	public static <T> T[] toObjectArray(Class<T> aClass, Object... source)
	{
		T[] array = newArray(aClass, source.length);
		System.arraycopy(source, 0, array, 0, array.length);
		return array;
	}
	
	public static Object[] toObjectArray(Collection<?> collection)
	{
		return collection.toArray(new Object[0]);
	}
	
	public static <T> T[] toObjectArray(Collection<? extends T> collection, Class<T> aClass)
	{
		T[] array = newArray(aClass, collection.size());
		return collection.toArray(array);
	}
	
	public static String[] toStringArray(Collection<String> collection)
	{
		return ArrayUtilRt.toStringArray(collection);
	}
	
	private static <T> int trailingNullsIndex(T[] array)
	{
		for(int i = array.length - 1; i >= 0; i--)
			if(array[i] != null) return i + 1; 
		return 0;
	}
}