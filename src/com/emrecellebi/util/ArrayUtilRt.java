package com.emrecellebi.util;

import java.io.File;
import java.util.Collection;

public final class ArrayUtilRt
{
	public static <T> int find(T[] src, T obj)
	{
		return indexOf(src, obj, 0, src.length);
	}
	
	public static <T> int indexOf(T[] src, T obj, int start, int end)
	{
		if(obj == null)
		{
			for(int i = start; i < end; i++)
				if(src[i] == null) return i;
		}
		else
		{
			for(int i = start; i < end; i++)
				if(obj.equals(src[i])) return i; 
		}
		return -1;
	}
	
	public static String[] toStringArray(Collection<String> collection)
	{
		return (collection == null || collection.isEmpty()) ? new String[0] : collection.toArray(new String[0]);
	}
}