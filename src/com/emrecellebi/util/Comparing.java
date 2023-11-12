package com.emrecellebi.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public final class Comparing
{
	public static <T extends Comparable<? super T>> int compare(T t1, T t2)
	{
		if(t1 == t2) return 0;
		if(t1 == null) return -1; 
		if(t2 == null) return 1; 
		return t1.compareTo(t2);
	}
	
	public static <T> int compare(T t1, T t2, Comparator<? super T> comparator)
	{
		if(t1 == t2) return 0; 
		if(t1 == null) return -1; 
		if(t2 == null) return 1; 
		return comparator.compare(t1, t2);
	}
	
	public static int compare(boolean t1, boolean t2)
	{
		return (t1 == t2) ? 0 : (t1 ? 1 : -1);
	}
	
	public static int compare(byte t1, byte t2)
	{
		return (t1 < t2) ? -1 : ((t1 == t2) ? 0 : 1);
	}
	
	public static int compare(byte[] t1, byte[] t2)
	{
		if(t1 == t2) return 0; 
		if(t1 == null) return 1; 
		if(t2 == null) return -1; 
		if(t1.length > t2.length) return 1; 
		if(t1.length < t2.length) return -1; 
		for(int i = 0; i < t1.length; i++)
		{
			if(t1[i] > t2[i]) return 1; 
			if(t1[i] < t2[i]) return -1; 
		}
		return 0;
	}
	
	public static int compare(double t1, double t2)
	{
		return Double.compare(t1, t2);
	}
	
	public static int compare(int o1, int o2)
	{
		return (o1 < o2) ? -1 : ((o1 == o2) ? 0 : 1);
	}
	
	public static int compare(long o1, long o2)
	{
		return (o1 < o2) ? -1 : ((o1 == o2) ? 0 : 1);
	}
	
	public static boolean equal(CharSequence s1, CharSequence s2)
	{
		return StringUtilRt.equal(s1, s2, true);
	}
	
	public static boolean equal(CharSequence s1, CharSequence s2, boolean caseSensitive)
	{
		return StringUtilRt.equal(s1, s2, caseSensitive);
	}
	
	public static <T> boolean equal(T arg1, T arg2)
	{
		if(arg1 == arg2) return true; 
		if(arg1 == null || arg2 == null) return false; 
		if(arg1 instanceof Object[] && arg2 instanceof Object[])
		{
			Object[] arr1 = (Object[])arg1;
			Object[] arr2 = (Object[])arg2;
			return Arrays.equals(arr1, arr2);
		}
		if(arg1 instanceof CharSequence && arg2 instanceof CharSequence)
			return equal((CharSequence)arg1, (CharSequence)arg2, true); 
		return arg1.equals(arg2);
	}
	
	public static <T> boolean equal(T[] arr1, T[] arr2)
	{
		return Arrays.equals((Object[])arr1, (Object[])arr2);
	}
	
	public static boolean equal(String arg1, String arg2)
	{
		return (arg1 == null) ? ((arg2 == null)) : arg1.equals(arg2);
	}
	
	public static boolean equal(String arg1, String arg2, boolean caseSensitive)
	{
		return (arg1 == null) ? ((arg2 == null)) : (caseSensitive ? arg1.equals(arg2) : arg1.equalsIgnoreCase(arg2));
	}
	
	public static int hashcode(Object obj)
	{
		return (obj == null) ? 0 : obj.hashCode();
	}
	
	public static int hashcode(Object obj1, Object obj2)
	{
		return hashcode(obj1) ^ hashcode(obj2);
	}
	
	public static <T> boolean haveEqualElements(Collection<? extends T> a, Collection<? extends T> b)
	{
		if(a.size() != b.size()) return false;
		Set<T> aSet = new HashSet<T>(a);
		for(T t : b)
			if(!aSet.contains(t)) return false;
		return true;
	}
	
	public static <T> boolean haveEqualElements(T[] a, T[] b)
	{
		if(a == null || b == null) return (a == b); 
		if(a.length != b.length) return false; 
		Set<T> aSet = new HashSet<T>(Arrays.asList(a));
		for(T t : b)
			if(!aSet.contains(t)) return false;
		return true;
	}
	
	public static boolean strEqual(String arg1, String arg2)
	{
		return strEqual(arg1, arg2, true);
	}
	
	public static boolean strEqual(String arg1, String arg2, boolean caseSensitive)
	{
		return equal((arg1 == null) ? "" : arg1, (arg2 == null) ? "" : arg2, caseSensitive);
	}
	
	public static int unorderedHashcode(Collection<?> collection)
	{
		int h = 0;
		for(Object obj : collection) 
			if(obj != null) h += obj.hashCode();
		return h;
	}
}