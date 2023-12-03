package com.emrecellebi.openapi.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.emrecellebi.reference.SoftReference;
import com.emrecellebi.util.IFunction;

public final class Conditions
{
	public static <T> ICondition<T> alwaysFalse()
	{
		return (ICondition)ICondition.FALSE;
	}
	
	public static <T> ICondition<T> alwaysTrue()
	{
		return (ICondition)ICondition.TRUE;
	}
	
	public static <T> ICondition<T> and(ICondition<? super T> c1, ICondition<? super T> c2)
	{
		return and2(c1, c2);
	}
	
	public static <T> ICondition<T> and2(ICondition<? super T> c1, ICondition<? super T> c2)
	{
		if(c1 == alwaysTrue() || c2 == alwaysFalse()) return (ICondition)c2;
		if(c2 == alwaysTrue() || c1 == alwaysFalse()) return (ICondition)c1;
		return new And<T>(c1, c2);
	}
	
	public static ICondition<Class<?>> assignableTo(final Class<?> clazz)
	{
		return new ICondition<Class<?>>()
		{
			@Override
			public boolean value(Class<?> t)
			{
				return clazz.isAssignableFrom(t);
			}
		};
	}
	
	public static <T> ICondition<T> cached(ICondition<? super T> c)
	{
		return new SoftRefCache<T>(c);
	}
	
	public static <A, B> ICondition<A> compose(final IFunction<? super A, B> fun, final ICondition<? super B> condition)
	{
		return new ICondition<A>()
		{
			@Override
			public boolean value(A o)
			{
				return condition.value(fun.fun(o));
			}
		};
	}
	
	public static <T> ICondition<T> constant(boolean value)
	{
		return value ? alwaysTrue() : alwaysFalse();
	}
	
	public static <T> ICondition<T> equalTo(final Object option)
	{
		return new ICondition<T>()
		{
			@Override
			public boolean value(T t)
			{
				return Comparing.equal(t, option);
			}
		};
	}
	
	public static <T> ICondition<T> instanceOf(final Class<?> clazz)
	{
		return new ICondition<T>()
		{
			@Override
			public boolean value(T t)
			{
				return clazz.isInstance(t);
			}
		};
	}
	
	public static <T> ICondition<T> instanceOf(Class<?>... clazz)
	{
		return new ICondition<T>()
		{
			public boolean value(T t)
			{
				for(Class<?> aClass : clazz)
					if(aClass.isInstance(t)) return true; 
				return false;
			}
		};
	}
	
	public static <T> ICondition<T> is(T option)
	{
		return equalTo(option);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static class And<T> implements ICondition<T>
	{
		final ICondition<? super T> c1;
		final ICondition<? super T> c2;

		And(ICondition<? super T> c1, ICondition<? super T> c2)
		{
			this.c1 = c1;
			this.c2 = c2;
		}

		@Override
		public boolean value(T object)
		{
			return (this.c1.value(object) && this.c2.value(object));
		}
	}
	
	private static class SoftRefCache<T> implements ICondition<T>
	{
		private final Map<Integer, Pair<SoftReference<T>, Boolean>> myCache = new HashMap<Integer, Pair<SoftReference<T>, Boolean>>();
		private final ICondition<? super T> myCondition;

		SoftRefCache(ICondition<? super T> condition)
		{
			this.myCondition = condition;
		}

		public final boolean value(T object)
		{
			int key = object.hashCode();
			Pair<SoftReference<T>, Boolean> entry = this.myCache.get(Integer.valueOf(key));
			if(entry == null || ((SoftReference)entry.first).get() != object)
			{
				boolean value = this.myCondition.value(object);
				this.myCache.put(Integer.valueOf(key), Pair.create(new SoftReference(object), Boolean.valueOf(value)));
				return value;
			} 
			return ((Boolean)entry.second).booleanValue();
		}
	}
}