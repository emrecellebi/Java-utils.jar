package com.emrecellebi;

import java.util.*;
import java.util.zip.*;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.charset.*;

import com.emrecellebi.logging.Logger;				/// Devam Ediyor...
import com.emrecellebi.logging.LoggerRt;			/// Tamamlandı

import com.emrecellebi.IArrayFactory;				/// Tamamlandı
import com.emrecellebi.ICharArrayExternalizable;	/// Tamamlandı
import com.emrecellebi.ICharSequenceBackedByArray;	/// Tamamlandı
import com.emrecellebi.ICharSequenceWithStringHash;	/// Tamamlandı
import com.emrecellebi.IFactory;					/// Tamamlandı
import com.emrecellebi.IFunction;					/// Tamamlandı #
import com.emrecellebi.INotNullFactory;				/// Tamamlandı
import com.emrecellebi.INullableFunction;			/// Tamamlandı #
import com.emrecellebi.IProcessor;					/// Tamamlandı
import com.emrecellebi.ISegment;					/// Tamamlandı
import com.emrecellebi.NullAppendable;				/// Tamamlandı
import com.emrecellebi.ThreeState;					/// Tamamlandı

import com.emrecellebi.util.ArrayUtil;				/// Devam Ediyor...
import com.emrecellebi.util.ArrayUtilRt;			/// Tamamlandı
import com.emrecellebi.util.CharArrayUtil;			/// Tamamlandı
import com.emrecellebi.util.Comparing;				/// Tamamlandı #
import com.emrecellebi.util.FileUtilRt;				/// Tamamlandı
import com.emrecellebi.util.PathUtilRt;				/// Tamamlandı
import com.emrecellebi.util.Strings;				/// Tamamlandı #
import com.emrecellebi.util.StringUtil;				/// Devam Ediyor...
import com.emrecellebi.util.StringUtilRt;			/// Tamamlandı #
import com.emrecellebi.util.SystemInfo;				/// Devam Ediyor...
import com.emrecellebi.util.SystemInfoRt;			/// Tamamlandı
import com.emrecellebi.util.SystemProperties;		/// Tamamlandı
import com.emrecellebi.util.URLUtil;				/// Tamamlandı

import com.emrecellebi.text.ByteArrayCharSequence;		/// Tamamlandı
import com.emrecellebi.text.CharArrayCharSequence;		/// Tamamlandı
import com.emrecellebi.text.CharSequenceReader;			/// Tamamlandı
import com.emrecellebi.text.CharSequenceSubSequence;	/// Tamamlandı
import com.emrecellebi.text.ImmutableCharSequence;		/// Tamamlandı
import com.emrecellebi.text.ImmutableText;				/// Tamamlandı
import com.emrecellebi.text.MergingCharSequence;		/// Tamamlandı
import com.emrecellebi.text.Pair;						/// Tamamlandı #
import com.emrecellebi.text.Pluralizer;					/// Tamamlandı #
import com.emrecellebi.text.TextRange;					/// Tamamlandı
import com.emrecellebi.text.UnsyncCharArrayReader;		/// Tamamlandı

/** Kişisel Kendi Sınıflarım **/
import com.emrecellebi.ObjectArray;
import com.emrecellebi.ObjectFunction;
import com.emrecellebi.util.ObjectFactory;

public class Console
{
	public static void main(String[] args)
	{
		Object[] src = {"Hello World!", 25, 3.14f, 25.25, false, true, new String("Object String")};
		Object[] newObj3 = ArrayUtil.append(src, 125.5f);
		System.out.print("append: ");
		for(int i = 0; i < newObj3.length; i++)
			System.out.print(newObj3[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		Object[] newObj = ArrayUtil.append(src, 25, new ObjectArray());
		System.out.print("append: ");
		for(int i = 0; i < newObj.length; i++)
			System.out.print(newObj[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		Object[] newObj2 = ArrayUtil.append(src, 125.5f, Object.class);
		System.out.print("append: ");
		for(int i = 0; i < newObj2.length; i++)
			System.out.print(newObj2[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		boolean[] blns = ArrayUtil.append(new boolean[0], true);
		System.out.print("append: ");
		for(int i = 0; i < blns.length; i++)
			System.out.print(blns[i]);
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		int[] blns2 = ArrayUtil.append(new int[0], 0);
		System.out.print("append: ");
		for(int i = 0; i < blns2.length; i++)
			System.out.print(blns2[i]);
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		long[] blns3 = ArrayUtil.append(new long[0], 0L);
		System.out.print("append: ");
		for(int i = 0; i < blns3.length; i++)
			System.out.print(blns3[i]);
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		float[] blns4 = ArrayUtil.append(new float[0], 0.0f);
		System.out.print("append: ");
		for(int i = 0; i < blns4.length; i++)
			System.out.print(blns4[i]);
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		char[] blns5 = ArrayUtil.append(new char[0], 'A');
		System.out.print("append: ");
		for(int i = 0; i < blns5.length; i++)
			System.out.print(blns5[i]);
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		short[] blns6 = ArrayUtil.append(new short[0], (short)0);
		System.out.print("append: ");
		for(int i = 0; i < blns6.length; i++)
			System.out.print(blns6[i]);
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		double[] blns7 = ArrayUtil.append(new double[0], 0.0);
		System.out.print("append: ");
		for(int i = 0; i < blns7.length; i++)
			System.out.print(blns7[i]);
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		byte[] blns8 = ArrayUtil.append(new byte[0], (byte)0);
		System.out.print("append: ");
		for(int i = 0; i < blns8.length; i++)
			System.out.print(blns8[i]);
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		System.out.println("averageAmongMedians: " + ArrayUtil.averageAmongMedians(new int[]{22, 25, 35, 45, 55, 60, 80}, 3));
		System.out.println("*********************************************************************************************");
		
		System.out.println("averageAmongMedians: " + ArrayUtil.averageAmongMedians(new long[]{22, 25, 35, 45, 55, 60, 80}, 3));
		System.out.println("*********************************************************************************************");
		
		System.out.println("contains: " + ArrayUtil.contains(35, new Integer[]{22, 25, 35, 45, 55, 60, 80}));
		System.out.println("contains: " + ArrayUtil.contains(3, new Integer[]{22, 25, 35, 45, 55, 60, 80}));
		System.out.println("*********************************************************************************************");
		
		System.out.println("contains: " + ArrayUtil.contains("45", new String[]{"22", "25", "35", "45", "55", "60", "80"}));
		System.out.println("contains: " + ArrayUtil.contains("3", new String[]{"22", "25", "35", "45", "55", "60", "80"}));
		System.out.println("*********************************************************************************************");
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 25; i++) list.add((i + 1) * 2);
		System.out.print("copy: ");
		Integer[] arr = new Integer[25];
		ArrayUtil.copy(list, arr, 0);
		for(int i = 0; i < 25; i++) System.out.print(arr[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		boolean[] barr = ArrayUtil.copyOf(new boolean[]{true, false, true, false});
		System.out.print("copyOf: ");
		for(int i = 0; i < barr.length; i++) System.out.print(barr[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		byte[] byarr = ArrayUtil.copyOf(new byte[]{1, 4, 5, 8});
		System.out.print("copyOf: ");
		for(int i = 0; i < byarr.length; i++) System.out.print(byarr[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		char[] carr = ArrayUtil.copyOf(new char[]{'A', 'B', 'c', 'd'});
		System.out.print("copyOf: ");
		for(int i = 0; i < carr.length; i++) System.out.print(carr[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		double[] darr = ArrayUtil.copyOf(new double[]{1.0, 25.2, 8.9, 7.5});
		System.out.print("copyOf: ");
		for(int i = 0; i < darr.length; i++) System.out.print(darr[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		float[] farr = ArrayUtil.copyOf(new float[]{3.17f, 5.8f, 8.7f, 5.7f});
		System.out.print("copyOf: ");
		for(int i = 0; i < farr.length; i++) System.out.print(farr[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		int[] iarr = ArrayUtil.copyOf(new int[]{1, 5, 8, 9});
		System.out.print("copyOf: ");
		for(int i = 0; i < iarr.length; i++) System.out.print(iarr[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		long[] larr = ArrayUtil.copyOf(new long[]{7, 9, 5, 4});
		System.out.print("copyOf: ");
		for(int i = 0; i < larr.length; i++) System.out.print(larr[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		short[] sarr = ArrayUtil.copyOf(new short[]{8, 9, 5, 7});
		System.out.print("copyOf: ");
		for(int i = 0; i < sarr.length; i++) System.out.print(sarr[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		Object[] xobj = ArrayUtil.ensureExactSize(4, new Object[]{"Hi", 25, 458.5, true});
		System.out.print("ensureExactSize: ");
		for(int i = 0; i < xobj.length; i++) System.out.print(xobj[i] + ",");
		System.out.println();
		System.out.println("*********************************************************************************************");
		
		Comparator<Integer> intComp = Comparator.naturalOrder();
		System.out.println("equals: " + ArrayUtil.equals(new Integer[]{25, 85, 95, 15}, new Integer[]{25, 85, 95, 15}, intComp));
		System.out.println("equals: " + ArrayUtil.equals(new Integer[]{0, 85, 95, 15}, new Integer[]{25, 85, 95, 15}, intComp));
		System.out.println("equals: " + ArrayUtil.equals(new Integer[]{25, 85, 95, 15}, new Integer[]{25, 85, 0, 15}, intComp));
		System.out.println("*********************************************************************************************");
		
		System.out.println("find: " + ArrayUtil.find(new Object[]{"Hi", 25, 458.5, true}, 25));
		System.out.println("*********************************************************************************************");
		
		System.out.println("find: " + ArrayUtil.find(new boolean[]{true, true, false, true}, false));
		System.out.println("*********************************************************************************************");
		
		System.out.println("find: " + ArrayUtil.find(new byte[]{25, 15, 35, 45}, (byte)15));
		System.out.println("*********************************************************************************************");
		
		System.out.println("find: " + ArrayUtil.find(new int[]{25, 15, 35, 45}, 35));
		System.out.println("*********************************************************************************************");
		
		System.out.println("find: " + ArrayUtil.find(new long[]{25, 15, 35, 45}, 45));
		System.out.println("*********************************************************************************************");
		
		System.out.println("find: " + ArrayUtil.find(new char[]{'A', 'B', 'C', 'D'}, 'C'));
		System.out.println("*********************************************************************************************");
		
		System.out.println("find: " + ArrayUtil.find(new short[]{25, 15, 35, 45}, (short)45));
		System.out.println("*********************************************************************************************");
		
		System.out.println("find: " + ArrayUtil.find(new float[]{25.0f, 15.0f, 35.0f, 45.0f}, 15.0f));
		System.out.println("*********************************************************************************************");
		
		System.out.println("find: " + ArrayUtil.find(new double[]{25.0, 15.0, 35.0, 45.0}, 45.0));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getComponentType: " + ArrayUtil.getComponentType(new Object[]{"Hi", 25, 458.5, true}));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new Object[]{"Hi", 25, 458.5, true}));
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new Object[]{"Hi", 25, 458.5, true}, new Object()));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new boolean[]{true, true, false, true}));
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new boolean[]{true, true, false, true}, false));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new byte[]{25, 15, 35, 45}));
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new byte[]{25, 15, 35, 45}, (byte)0));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new int[]{25, 15, 35, 45}));
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new int[]{25, 15, 35, 45}, 0));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new long[]{25, 15, 35, 45}));
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new long[]{25, 15, 35, 45}, 0L));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new char[]{'A', 'B', 'C', 'D'}));
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new char[]{'A', 'B', 'C', 'D'}, '-'));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new short[]{25, 15, 35, 45}));
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new short[]{25, 15, 35, 45}, (short)0));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new float[]{25.0f, 15.0f, 35.0f, 45.0f}));
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new float[]{25.0f, 15.0f, 35.0f, 45.0f}, 0.0f));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new double[]{25.0, 15.0, 35.0, 45.0}));
		System.out.println("getFirstElement: " + ArrayUtil.getFirstElement(new double[]{25.0, 15.0, 35.0, 45.0}, 0.0));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new Object[]{"Hi", 25, 458.5, true}));
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new Object[]{"Hi", 25, 458.5, true}, new Object()));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new boolean[]{true, true, false, true}));
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new boolean[]{true, true, false, true}, false));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new byte[]{25, 15, 35, 45}));
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new byte[]{25, 15, 35, 45}, (byte)0));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new int[]{25, 15, 35, 45}));
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new int[]{25, 15, 35, 45}, 0));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new long[]{25, 15, 35, 45}));
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new long[]{25, 15, 35, 45}, 0L));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new char[]{'A', 'B', 'C', 'D'}));
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new char[]{'A', 'B', 'C', 'D'}, '-'));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new short[]{25, 15, 35, 45}));
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new short[]{25, 15, 35, 45}, (short)0));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new float[]{25.0f, 15.0f, 35.0f, 45.0f}));
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new float[]{25.0f, 15.0f, 35.0f, 45.0f}, 0.0f));
		System.out.println("*********************************************************************************************");
		
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new double[]{25.0, 15.0, 35.0, 45.0}));
		System.out.println("getLastElement: " + ArrayUtil.getLastElement(new double[]{25.0, 15.0, 35.0, 45.0}, 0.0));
		System.out.println("*********************************************************************************************");
		
		List<Object> olist = new ArrayList<Object>();
		for(int i = 0; i < 25; i++) olist.add((i + 1) * 2);
		System.out.println("indexOf: " + ArrayUtil.indexOf(olist, 50, (s1, s2) -> s1.equals(s2)));
		System.out.println("*********************************************************************************************");
		
		System.out.println("indexOf: " + ArrayUtil.indexOf(new Object[]{"Hi", 25, 458.5, true}, 25));
		System.out.println("*********************************************************************************************");
		
		Object[] oarr = new Object[25];
		for(int i = 0; i < 25; i++) oarr[i] = (i + 1) * 2;
		System.out.println("indexOf: " + ArrayUtil.indexOf(oarr, 50, (s1, s2) -> s1.equals(s2)));
		System.out.println("*********************************************************************************************");
		
		System.out.println("indexOf: " + ArrayUtil.indexOf(new byte[]{25, 65, 89, 74}, new byte[]{74}, 0));
		System.out.println("*********************************************************************************************");
		
		
		
/**
	------------------> public static <T> T[] append(T[] src, T element) <------------------
	Her hangi bir array tipini alır ve yeniden boyutlandırıp ekleme yapar.
	
	*********************************************************************************************
	
	------------------> public static <T> T[] append(T[] src, T element, ArrayFactory<? extends T> factory) <------------------
	IArrayFactory interface deki create methodu kullanılarak bir array oluşturlup src içindekileri alıp ve eklemek istemilen 
	değeri ekler. Return olarak geri döner.
	
	*********************************************************************************************
	
	------------------> public static <T> T[] append(T[] src, T element, Class<T> componentType) <------------------
	Bir class tipi kulanılarak array oluşturlup src içindekileri alıp ve eklemek istenilen 
	değeri ekler. Return olarak geri döner.
	
	*********************************************************************************************
	
	------------------> public static boolean[] append(boolean[] array, boolean value) <------------------
	Bir boolean primitivs tipinde array ekleme yapar.
	
	*********************************************************************************************
	
	------------------> public static byte[] append(byte[] array, byte value) <------------------
	Bir byte primitivs tipinde array ekleme yapar.
	
	*********************************************************************************************
	
	------------------> public static int[] append(int[] array, int value) <------------------
	Bir int primitivs tipinde array ekleme yapar.
	
	*********************************************************************************************
	
	------------------> public static long[] append(long[] array, long value) <------------------
	Bir long primitivs tipinde array ekleme yapar.
	
	*********************************************************************************************
	
	------------------> public static float[] append(float[] array, float value) <------------------
	Bir float primitivs tipinde array ekleme yapar.
	
	*********************************************************************************************
	
	------------------> public static char[] append(char[] array, char value) <------------------
	Bir char primitivs tipinde array ekleme yapar.
	
	*********************************************************************************************
	
	------------------> public static short[] append(short[] array, short value) <------------------
	Bir short primitivs tipinde array ekleme yapar.
	
	*********************************************************************************************
	
	------------------> public static double[] append(double[] array, short value) <------------------
	Bir short primitivs tipinde array ekleme yapar.
	
	------------------> public static long averageAmongMedians(int[] time, int part) <------------------
	Ne olduğu hakkında en ufak bir fikrim yok
	
	*********************************************************************************************
	
	------------------> public static long averageAmongMedians(long[] time, int part) <------------------
	Ne olduğu hakkında en ufak bir fikrim yok
	
	*********************************************************************************************
	
	------------------> public static <T> boolean contains(T o, T... objects) <------------------
	Verilen objects içerisin o değeri var ise true döner. Her tip verilebilir
	
	*********************************************************************************************
	
	------------------> public static boolean contains(String s, String... strings) <------------------
	Verilen strings içerisin s değeri var ise true döner.
	
	*********************************************************************************************
	
	------------------> public static <T> void copy(Collection<? extends T> src, T[] dst, int dstOffset) <------------------
	Verilen bir Collection listesinden tanımlanan array içerisine kopyalama yapar.
	
	*********************************************************************************************
	
	------------------> public static <T> T[] copyOf(T[] original) <------------------
	Verilen her hangi bir array tipin kopyalayar.
	
	*********************************************************************************************
	
	------------------> public static boolean[] copyOf(boolean[] original) <------------------
	Verilen boolean array tipin kopyalayar.
	
	*********************************************************************************************
	
	------------------> public static byte[] copyOf(byte[] original) <------------------
	Verilen byte array tipin kopyalayar.
	
	*********************************************************************************************
	
	------------------> public static char[] copyOf(char[] original) <------------------
	Verilen char array tipin kopyalayar.
	
	*********************************************************************************************
	
	------------------> public static double[] copyOf(double[] original) <------------------
	Verilen double array tipin kopyalayar.
	
	*********************************************************************************************
	
	------------------> public static float[] copyOf(float[] original) <------------------
	Verilen float array tipin kopyalayar.
	
	*********************************************************************************************
	
	------------------> public static int[] copyOf(int[] original) <------------------
	Verilen int array tipin kopyalayar.
	
	*********************************************************************************************
	
	------------------> public static long[] copyOf(long[] original) <------------------
	Verilen long array tipin kopyalayar.
	
	*********************************************************************************************
	
	------------------> public static short[] copyOf(short[] original) <------------------
	Verilen short array tipin kopyalayar.
	
	*********************************************************************************************
	
	------------------> public static <E> E[] ensureExactSize(int count, E[] sample) <------------------
	Verilen her hangi bir sınıfdan belirlenen uzunluk ile bir kopya döner.
	
	*********************************************************************************************
	
	------------------> public static <T> boolean equals(T[] a1, T[] a2, Comparator<? super T> comparator) <------------------
	Verilen iki değeri bir Comparator kullanarak karşılaştırır.
	
	*********************************************************************************************
	
	------------------> public static <T> int find(T[] src, T obj) <------------------
	Verilen her hangi bir sınıf array değerinden belilen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int find(boolean[] src, boolean obj) <------------------
	Verilen her hangi bir boolean array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int find(byte[] src, byte obj) <------------------
	Verilen her hangi bir byte array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int find(int[] src, int obj) <------------------
	Verilen her hangi bir int array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int find(long[] src, long obj) <------------------
	Verilen her hangi bir long array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int find(char[] src, char obj) <------------------
	Verilen her hangi bir char array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int find(short[] src, short obj) <------------------
	Verilen her hangi bir short array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int find(float[] src, float obj) <------------------
	Verilen her hangi bir float array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int find(double[] src, double obj) <------------------
	Verilen her hangi bir double array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static <T> Class<T> getComponentType(T[] collection) <------------------
	Verilen her hangi bir arrayin tipini döner.
	
	*********************************************************************************************
	
	------------------> public static <T> T getFirstElement(T[] array) <------------------
	------------------> public static <T> T getFirstElement(T[] array, T defaultValue) <------------------
	Verilen her hangi bir sınıf arrayden ilk elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static boolean getFirstElement(boolean[] array) <------------------
	------------------> public static boolean getFirstElement(boolean[] array, boolean defaultValue) <------------------
	Verilen her hangi bir boolean arrayden ilk elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static byte getFirstElement(byte[] array) <------------------
	------------------> public static byte getFirstElement(byte[] array, byte defaultValue) <------------------
	Verilen her hangi bir byte arrayden ilk elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static int getFirstElement(int[] array) <------------------
	------------------> public static int getFirstElement(int[] array, int defaultValue) <------------------
	Verilen her hangi bir int arrayden ilk elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static long getFirstElement(long[] array) <------------------
	------------------> public static long getFirstElement(long[] array, long defaultValue) <------------------
	Verilen her hangi bir long arrayden ilk elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static char getFirstElement(char[] array) <------------------
	------------------> public static char getFirstElement(char[] array, char defaultValue) <------------------
	Verilen her hangi bir char arrayden ilk elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static short getFirstElement(short[] array) <------------------
	------------------> public static short getFirstElement(short[] array, short defaultValue) <------------------
	Verilen her hangi bir short arrayden ilk elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static float getFirstElement(float[] array) <------------------
	------------------> public static float getFirstElement(float[] array, float defaultValue) <------------------
	Verilen her hangi bir float arrayden ilk elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static double getFirstElement(double[] array) <------------------
	------------------> public static double getFirstElement(double[] array, double defaultValue) <------------------
	Verilen her hangi bir double arrayden ilk elemanı döner.
	
	------------------> public static <T> T getLastElement(T[] array) <------------------
	------------------> public static <T> T getLastElement(T[] array, T defaultValue) <------------------
	Verilen her hangi bir sınıf arrayden son elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static boolean getLastElement(boolean[] array) <------------------
	------------------> public static boolean getLastElement(boolean[] array, boolean defaultValue) <------------------
	Verilen her hangi bir boolean arrayden son elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static byte getLastElement(byte[] array) <------------------
	------------------> public static byte getLastElement(byte[] array, byte defaultValue) <------------------
	Verilen her hangi bir byte arrayden son elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static int getLastElement(int[] array) <------------------
	------------------> public static int getLastElement(int[] array, int defaultValue) <------------------
	Verilen her hangi bir int arrayden son elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static long getLastElement(long[] array) <------------------
	------------------> public static long getLastElement(long[] array, long defaultValue) <------------------
	Verilen her hangi bir long arrayden son elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static char getLastElement(char[] array) <------------------
	------------------> public static char getLastElement(char[] array, char defaultValue) <------------------
	Verilen her hangi bir char arrayden son elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static short getLastElement(short[] array) <------------------
	------------------> public static short getLastElement(short[] array, short defaultValue) <------------------
	Verilen her hangi bir short arrayden son elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static float getLastElement(float[] array) <------------------
	------------------> public static float getLastElement(float[] array, float defaultValue) <------------------
	Verilen her hangi bir float arrayden son elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static double getLastElement(double[] array) <------------------
	------------------> public static double getLastElement(double[] array, double defaultValue) <------------------
	Verilen her hangi bir double arrayden son elemanı döner.
	
	*********************************************************************************************
	
	------------------> public static <T> int indexOf(List<? extends T> objects, T object, BiPredicate<? super T, ? super T> predicate) <------------------
	Verilen her hangi bir sınıf arrayin de belirlen BiPredicate funcion kullanılarak karşılaştırılır ve index numarası döner.
	
	*********************************************************************************************
	
	------------------> public static int indexOf(Object[] objects, Object object) <------------------
	Verilen Bir Object arrayinden belirlenen object değerini bulur index değerini döner.
	
	*********************************************************************************************
	
	------------------> public static <T> int indexOf(T[] objects, T object, BiPredicate<? super T, ? super T> comparator) <------------------
	Verilen bir Object arrayin de belirlen BiPredicate funcion kullanılarak karşılaştırılır ve index numarası döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	
	
	
	
	
	
	
	
	
	------------------> public static int indexOf(boolean[] src, boolean obj) <------------------
	Verilen her hangi bir boolean array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int indexOf(byte[] src, byte obj) <------------------
	Verilen her hangi bir byte array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int indexOf(int[] src, int obj) <------------------
	Verilen her hangi bir int array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int indexOf(long[] src, long obj) <------------------
	Verilen her hangi bir long array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int indexOf(char[] src, char obj) <------------------
	Verilen her hangi bir char array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int indexOf(short[] src, short obj) <------------------
	Verilen her hangi bir short array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int indexOf(float[] src, float obj) <------------------
	Verilen her hangi bir float array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	*********************************************************************************************
	
	------------------> public static int indexOf(double[] src, double obj) <------------------
	Verilen her hangi bir double array değerinden belirlen değerin index değerini döner. Aksi durumda -1 döner.
	
	
	
	*********************************************************************************************
	------------------>  <------------------
**/
		
		
		
		/**
			ArrayUtil
			ObjectUtil
				Converter
			
		**/
		
		
		
		
		
		// try{Thread.sleep(10000);}catch(Exception e){}

		// Logger log = Logger.getInstance(Console.class);
		
		/**
			Verieln string içerisinde \r karakter var ise bir AssertionError döner. 
		**/
		// System.out.println("StringUtil.assertValidSeparators(CharSequence): void");
		// StringUtil.assertValidSeparators("Lorem Ipsum is simply dummy text of the printing and type setting industry.");
		
		// System.out.println("StringUtil.capitalize(String): String -> " + StringUtil.capitalize("hello world!"));
		
		// System.out.println("StringUtil.capitalizeWithJavaBeanConvention(String): String -> " + StringUtil.capitalizeWithJavaBeanConvention("hello world!"));
		
		// System.out.println("StringUtil.capitalizeWords(String, String, boolean, boolean): String -> " + StringUtil.capitalizeWords("hello world!", " ", true, true));
		// System.out.println("StringUtil.capitalizeWords(String, boolean): String -> " + StringUtil.capitalizeWords("hello world!", true));
		
		// System.out.println("StringUtil.charsEqualIgnoreCase(char, char): boolean -> " + StringUtil.charsEqualIgnoreCase('a', 'a'));
		// System.out.println("StringUtil.charsEqualIgnoreCase(char, char): boolean -> " + StringUtil.charsEqualIgnoreCase('a', 'A'));
		
		// System.out.println("StringUtil.collapseWhiteSpace(CharSequence): String -> " + StringUtil.collapseWhiteSpace("Hello World!"));
		
		// System.out.println("StringUtil.commonPrefix(String, String): String -> " + StringUtil.commonPrefix("Hello World!", "Hello"));
		
		// System.out.println("StringUtil.commonPrefixLength(CharSequence, CharSequence): int -> " + StringUtil.commonPrefixLength("Hello World!", "hello world!"));
		// System.out.println("StringUtil.commonPrefixLength(CharSequence, CharSequence, boolean): int -> " + StringUtil.commonPrefixLength("Hello World!", "hello world!", true));
		
		// System.out.println("StringUtil.commonSuffix(String, String): String -> " + StringUtil.commonSuffix("Hello World!", "Hello"));
		
		// System.out.println("StringUtil.commonSuffixLength(CharSequence, CharSequence): int -> " + StringUtil.commonSuffixLength("Hello World!", "hello world!"));
		
		// System.out.println("StringUtil.compare(CharSequence, CharSequence, boolean): int -> " + StringUtil.compare("Hello World!", "hello world!", false));
		// System.out.println("StringUtil.compare(CharSequence, CharSequence, boolean): int -> " + StringUtil.compare("Hello World!", "hello world!", true));
		
		// System.out.println("StringUtil.compare(String, String, boolean): int -> " + StringUtil.compare("Hello World!", "hello world!", false));
		// System.out.println("StringUtil.compare(String, String, boolean): int -> " + StringUtil.compare("Hello World!", "hello world!", true));
		
		// System.out.println("StringUtil.compare(char, char, boolean): int -> " + StringUtil.compare('a', 'a', false));
		// System.out.println("StringUtil.compare(char, char, boolean): int -> " + StringUtil.compare('a', 'A', true));
		
		// System.out.println("StringUtil.comparePairs(String, String, String, String, boolean): int -> " + StringUtil.comparePairs("Hello World!", "Hello World!", "Hello World!", "Hello World!", true));
		
		// System.out.println("StringUtil.compareVersionNumbers(String, String): int -> " + StringUtil.compareVersionNumbers("1.0", "1.0"));
		// System.out.println("StringUtil.compareVersionNumbers(String, String): int -> " + StringUtil.compareVersionNumbers("1.0", "1.1"));
		
		// System.out.println("StringUtil.contains(CharSequence, CharSequence): boolean -> " + StringUtil.contains("Hello World!", "!"));
		// System.out.println("StringUtil.contains(CharSequence, int, int, char): boolean -> " + StringUtil.contains("Hello World!", 0, 12, '!'));
		
		// System.out.println("StringUtil.containsAlphaCharacters(String): boolean -> " + StringUtil.containsAlphaCharacters("Hello World!"));
		
		// System.out.println("StringUtil.containsAnyChar(String, String): boolean -> " + StringUtil.containsAnyChar("Hello World!", "Hello World!"));
		// System.out.println("StringUtil.containsAnyChar(String, String, int, int): boolean -> " + StringUtil.containsAnyChar("Hello World!", "Hello World!", 0, 12));
		
		// System.out.println("StringUtil.containsChar(String, char): boolean -> " + StringUtil.containsChar("Hello World!", 'W'));
		
		// System.out.println("StringUtil.containsIgnoreCase(String, String): boolean -> " + StringUtil.containsIgnoreCase("Hello World!", "world"));
		
		// System.out.println("StringUtil.containsLineBreak(CharSequence): boolean -> " + StringUtil.containsLineBreak("Hello \nWorld!"));
		// System.out.println("StringUtil.containsLineBreak(CharSequence): boolean -> " + StringUtil.containsLineBreak("Hello \rWorld!"));
		// System.out.println("StringUtil.containsLineBreak(CharSequence): boolean -> " + StringUtil.containsLineBreak("Hello World!"));
		
		// System.out.println("StringUtil.containsWhitespaces(CharSequence): boolean -> " + StringUtil.containsWhitespaces("Hello World!"));
		// System.out.println("StringUtil.containsWhitespaces(CharSequence): boolean -> " + StringUtil.containsWhitespaces("HelloWorld!"));
		
		// System.out.println("StringUtil.convertLineSeparators(String): String -> " + StringUtil.convertLineSeparators("Hello World!"));
		// System.out.println("StringUtil.convertLineSeparators(String, String): String -> " + StringUtil.convertLineSeparators("Hello World!", "-"));
		// System.out.println("StringUtil.convertLineSeparators(String, String, int[]): String -> " + StringUtil.convertLineSeparators("Hello World!", "-", null));
		// System.out.println("StringUtil.convertLineSeparators(String, boolean): String -> " + StringUtil.convertLineSeparators("Hello World!", true));
		
		// System.out.println("StringUtil.countChars(CharSequence, char): int -> " + StringUtil.countChars("Hello World!", 'o'));
		// System.out.println("StringUtil.countChars(CharSequence, char, int, boolean): int -> " + StringUtil.countChars("Hello World!", 'o', 0, false));
		// System.out.println("StringUtil.countChars(CharSequence, char, int, int, boolean): int -> " + StringUtil.countChars("Hello World!", 'o', 0, 12, false));
		
		// System.out.println("StringUtil.countNewLines(CharSequence): int -> " + StringUtil.countNewLines("Hello\nWorld!"));
		
		// IFunction<String, String> func = StringUtil.createToStringFunction(String.class);
		// System.out.println("StringUtil.createToStringFunction(Class<T>): int -> " + func.fun("Hello World!"));
		
		// System.out.println("StringUtil.decapitalize(String): String -> " + StringUtil.decapitalize("Hello world!"));
		
		// System.out.println("StringUtil.defaultIfEmpty(String, String): String -> " + StringUtil.defaultIfEmpty("Hello world!", "default"));
		// System.out.println("StringUtil.defaultIfEmpty(String, String): String -> " + StringUtil.defaultIfEmpty("", "default"));
		
		
		
		
		
		
		/**
			Verilen bir string datasında verieln karakterlerin \ ile atlatma seçeneğini verir.
		**/
		// String str = "Lorem Ipsum is simply dummy text of the printing and type setting industry.";
		// System.out.println("StringUtil.escapeStringCharacters(String): String -> " + StringUtil.escapeStringCharacters(str));
		// System.out.println("StringUtil.escapeStringCharacters(int, String, String, StringBuilder): StringBuilder -> " + StringUtil.escapeStringCharacters(str.length(), str, "ry", new StringBuilder()));
		// System.out.println("StringUtil.escapeStringCharacters(int, String, String, boolean, StringBuilder): StringBuilder -> " + StringUtil.escapeStringCharacters(str.length(), str, "ry", false, new StringBuilder()));
		// System.out.println("StringUtil.escapeStringCharacters(int, String, String, boolean, boolean, StringBuilder): StringBuilder -> " + StringUtil.escapeStringCharacters(str.length(), str, "ry", false, false, new StringBuilder()));
		
		// System.out.print("StringUtil.escapeStringCharacters(int, String, StringBuilder): void -> ");
		// StringBuilder sb = new StringBuilder();
		// StringUtil.escapeStringCharacters(str.length(), str, "ry", sb);
		// System.out.println(sb);
		
		// System.out.println("StringUtil.first(CharSequence, int, boolean): CharSequence -> " + StringUtil.first((CharSequence)"Hello World!", 5, false));
		// System.out.println("StringUtil.first(CharSequence, int, boolean): CharSequence -> " + StringUtil.first((CharSequence)"Hello World!", 5, true));
		
		// System.out.println("StringUtil.first(String, int, boolean): String -> " + StringUtil.first((String)"Hello World!", 5, false));
		// System.out.println("StringUtil.first(String, int, boolean): String -> " + StringUtil.first((String)"Hello World!", 5, true));
		
		// System.out.println("StringUtil.last(CharSequence, int, boolean): CharSequence -> " + StringUtil.last((CharSequence)"Hello World!", 5, false));
		// System.out.println("StringUtil.last(CharSequence, int, boolean): CharSequence -> " + StringUtil.last((CharSequence)"Hello World!", 5, true));
		
		// System.out.println("StringUtil.isPrintableUnicode(char): boolean -> " + StringUtil.isPrintableUnicode('a'));
		
		// System.out.println("StringUtil.toUpperCase(String): String -> " + StringUtil.toUpperCase("Hello World!"));
		
		// System.out.println("StringUtil.toUpperCase(char): char -> " + StringUtil.toUpperCase('a'));
		
		
		
		
		
		
		
		
		
		
		// System.out.println("SystemInfo.OS_NAME: String -> " + SystemInfo.OS_NAME);
		// System.out.println("SystemInfo.OS_VERSION: String -> " + SystemInfo.OS_VERSION);
		// System.out.println("SystemInfo.JAVA_VERSION: String -> " + SystemInfo.JAVA_VERSION);
		// System.out.println("SystemInfo.JAVA_RUNTIME_VERSION: String -> " + SystemInfo.JAVA_RUNTIME_VERSION);
		// System.out.println("SystemInfo.JAVA_VENDOR: String -> " + SystemInfo.JAVA_VENDOR);
		// System.out.println("SystemInfo.isWindows: boolean -> " + SystemInfo.isWindows);
		// System.out.println("SystemInfo.isMac: boolean -> " + SystemInfo.isMac);
		// System.out.println("SystemInfo.isLinux: boolean -> " + SystemInfo.isLinux);
		// System.out.println("SystemInfo.isFreeBSD: boolean -> " + SystemInfo.isFreeBSD);
		// System.out.println("SystemInfo.isSolaris: boolean -> " + SystemInfo.isSolaris);
		// System.out.println("SystemInfo.isUnix: boolean -> " + SystemInfo.isUnix);
		// System.out.println("SystemInfo.isChromeOS: boolean -> " + SystemInfo.isChromeOS);
		// System.out.println("SystemInfo.isAppleJvm: boolean -> " + SystemInfo.isAppleJvm);
		// System.out.println("SystemInfo.isOracleJvm: boolean -> " + SystemInfo.isOracleJvm);
		// System.out.println("SystemInfo.isSunJvm: boolean -> " + SystemInfo.isSunJvm);
		// System.out.println("SystemInfo.isIbmJvm: boolean -> " + SystemInfo.isIbmJvm);
		// System.out.println("SystemInfo.isAzulJvm: boolean -> " + SystemInfo.isAzulJvm);
		// System.out.println("SystemInfo.isJetBrainsJvm: boolean -> " + SystemInfo.isJetBrainsJvm);
		// System.out.println("SystemInfo.IS_AT_LEAST_JAVA9: boolean -> " + SystemInfo.IS_AT_LEAST_JAVA9);
		
		
		
	
		
		
		// String str = "Lorem Ipsum is simply dummy text of the printing and type setting industry.";
		// int len = str.length();
		
		// int ch = CharArrayUtil.shiftForwardCarefully(str, 0, "m t");
		// System.out.println("Length: " + len + " int: " + ch + " char: " + str.charAt(ch));
		
		// JavaVersion ver = JavaVersion.current();
		// System.out.println("Feature: " + ver.feature + " Minor: " + ver.minor + " Update: " + ver.update + " Build: " + ver.build + " EA: " + ver.ea);
		// System.out.println("current(): JavaVersion -> " + JavaVersion.current());
		// System.out.println("compareTo(JavaVersion): int -> " + JavaVersion.current().compareTo(JavaVersion.compose(8, 0, 271, 9, false)));
		// System.out.println("compose(int): JavaVersion -> " + JavaVersion.compose(8));
		// System.out.println("compose(int, int, int, int, boolean): JavaVersion -> " + JavaVersion.compose(8, 0, 12, 0, false));
		// System.out.println("toString(): String -> " + ver.toString());
		// System.out.println("toFeatureString(): String -> " + ver.toFeatureString());
		// System.out.println("toFeatureMinorUpdateString(): String -> " + ver.toFeatureMinorUpdateString());
		// System.out.println("parse(String): JavaVersion -> " + JavaVersion.parse(System.getProperty("java.version")));
		// System.out.println("tryParse(String): JavaVersion -> " + JavaVersion.tryParse(System.getProperty("java.version")));
		// System.out.println("equals(Object): boolean -> " + ver.equals(JavaVersion.current()));
		// System.out.println("hashCode(): int -> " + ver.hashCode());
		// System.out.println("isAtLeast(int): boolean -> " + ver.isAtLeast(5));
		
		
		
		
		
		
		
		
		
		// ResourceBundle bundle = ResourceBundle.getBundle("messages", new UTF8ResourceControl());
		// System.out.println(bundle.getString("a1"));
	
		
		/**
			ThreadLocal her iş parçacığı kendi özel verisini saklamasına olanak tanır. Diğer iş parçacıklarından bu verilere 
			erişimi engeller.
			
			Abstract ve Interface sınıflardan nesne türetilemez
			
			Abstract Reader sınıfını A adındaki class extends ederse bu sınıfın içindeki abstract methodları Override edilir.
			Eğer A adında bir nesne oluşturulup tipini Reader olarak convert yapılırsa buraki abstract olan read methodu çağrıldığında A sınıfındaki
			Override edilen read methodu çağrılır.
		**/
	}
}