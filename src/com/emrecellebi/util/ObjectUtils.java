package com.emrecellebi.util;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import com.emrecellebi.openapi.util.INotNullFactory;
import com.emrecellebi.util.containers.IConvertor;

public final class ObjectUtils
{
	public static <T> void assertAllElementsNotNull(T[] array)
	{ 
		for(int i = 0; i < array.length; i++)
		{
			T t = array[i];
			if(t == null)
				throw new NullPointerException("Element [" + i + "] is null");
		}
	}
	
	@Deprecated
	public static <T> T assertNotNull(T t)
	{
		return Objects.requireNonNull(t);
	}
	
	public static int binarySearch(int fromIndex, int toIndex, IntUnaryOperator indexComparator)
	{
		int low = fromIndex;
		int high = toIndex - 1;
		while(low <= high)
		{
			int mid = low + high >>> 1;
			int cmp = indexComparator.applyAsInt(mid);
			if(cmp < 0)
			{
				low = mid + 1;
				continue;
			}
			if(cmp > 0)
			{
				high = mid - 1;
				continue;
			}
			return mid;
		}
		return -(low + 1);
	}
	
	public static <T> T chooseNotNull(T t1, T t2)
	{
		return (t1 == null) ? t2 : t1;
	}
	
	public static <T> T coalesce(Iterable<? extends T> o)
	{
		if(o == null) return null;
		for(T t : o)
			if(t != null) return t;
		return null;
	}
	
	public static <T> T coalesce(T t1, T t2)
	{
		return chooseNotNull(t1, t2);
	}
	
	public static <T> T coalesce(T t1, T t2, T t3)
	{
		return (t1 != null) ? t1 : ((t2 != null) ? t2 : t3);
	}
	
	public static <T> void consumeIfCast(T obj, Class<T> clazz, IConsumer<? super T> consumer)
	{
		if(clazz.isInstance(obj)) consumer.consume(obj);
	}
	
	public static <T> void consumeIfNotNull(T obj, IConsumer<? super T> consumer)
	{
		if(obj != null) consumer.consume(obj); 
	}
	
	public static <T, S> S doIfCast(T obj, Class<T> clazz, IConvertor<? super T, ? extends S> convertor)
	{
		return clazz.isInstance(obj) ? convertor.convert(obj) : null;
	}
	
	public static <T, S> S doIfNotNull(T obj, IFunction<? super T, ? extends S> function)
	{
		return (obj == null) ? null : (S)function.fun(obj);
	}
	
	@Deprecated
	public static <T> T notNull(T value)
	{
		return Objects.requireNonNull(value);
	}
	
	public static <T> T notNull(T value, INotNullFactory<? extends T> defaultValue)
	{
		return (value == null) ? (T)defaultValue.create() : value;
	}
	
	public static <T> T notNull(T value, T defaultValue)
	{
		return (value == null) ? defaultValue : value;
	}
	
	public static <T> T nullizeByCondition(T obj, Predicate<? super T> condition)
	{
		return condition.test(obj) ? null : obj;
	}
	
	public static <T> T nullizeIfDefaultValue(T obj, T defaultValue)
	{
		return (obj != defaultValue) ? obj : null;
	}
	
	public static Object sentinel(String name)
	{
		return new Sentinel(name);
	}
	
	public static <T> T sentinel(String name, Class<T> ofInterface)
	{
		if(!ofInterface.isInterface())
			throw new IllegalArgumentException("Expected interface but got: " + ofInterface); 
		return (T)Proxy.newProxyInstance(ofInterface.getClassLoader(), new Class[]{ofInterface}, (__, method, args) -> {
				if("toString".equals(method.getName()) && args.length == 0)
					return name;
				throw new AbstractMethodError();
			}
		);
	}
	
	public static <T> T tryCast(Object obj, Class<T> clazz)
	{
		return clazz.isInstance(obj) ? clazz.cast(obj) : null;
	}
	
	private static final class Sentinel
	{
		private final String myName;

		Sentinel(String name)
		{
			this.myName = name;
		}

		public String toString()
		{
			return this.myName;
		}
	}
}