package com.emrecellebi.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class ObjectFactory
{
	public static <T> T getConstructor(Class<T> cls, Class<?>[] constTypeParams, Object... constParams)
	{
		try
		{
			Constructor<T> cns = cls.getDeclaredConstructor(constTypeParams);
			cns.setAccessible(true);
			return cns.newInstance(constParams);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> void getConstructors(Class<T> cls)
	{
		try
		{
			Constructor<?>[] cns = cls.getDeclaredConstructors();
			for(int i = 0; i < cns.length; i++)
				System.out.println(cns[i]);
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static <T> Object getMethod(Class<T> cls, String methodName, Class<?>[] constTypeParams, Object... constParams)
	{
		try
		{
			Method cns = cls.getDeclaredMethod(methodName, constTypeParams);
			cns.setAccessible(true);
			return cns.invoke(null, constParams);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> void getMethods(Class<T> cls)
	{
		try
		{
			Method[] cns = cls.getDeclaredMethods();
			for(int i = 0; i < cns.length; i++)
				System.out.println(cns[i]);
		}catch(Exception e){e.printStackTrace();}
	}
}