package com.emrecellebi;

import java.util.Collection;

import com.emrecellebi.util.IFunction;

public class ObjectFunction implements IFunction<Object, Object>
{
	@Override
	public Object fun(Object param)
	{
		return param;
	}
	
	public static Object first(Object[] objs)
	{
		return new First<Object>().fun(objs);
	}
	
	public static Object firstCollection(Collection<Object> col)
	{
		return new FirstCollection<Object>().fun(col);
	}
	
	public static Object instanceOf(Object obj)
	{
		return new InstanceOf(Object.class).fun(obj);
	}
	
	public static boolean isInstanceOf(Object obj)
	{
		return (new InstanceOf(Object.class).fun(obj) == null) ? false : true;
	}
}