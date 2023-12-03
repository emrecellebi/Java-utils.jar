package com.emrecellebi.util;

import java.util.Collection;

public interface IFunction<Param, Result>
{
	public static interface Mono<T> extends IFunction<T, T>{}
	
	public static final class First<P> implements IFunction<P[], P>
	{
		@Override
		public P fun(P[] ps)
		{
			return ps[0];
		}
	}
	
	public static final class FirstCollection<P> implements IFunction<Collection<P>, P>
	{
		@Override
		public P fun(Collection<P> ps)
		{
			return ps.iterator().next();
		}
	}
	
	public static final class InstanceOf<P, R extends P> implements INullableFunction<P, R>
	{
		private final Class<R> myResultClass;
		
		public InstanceOf(Class<R> resCls)
		{
			this.myResultClass = resCls;
		}
		
		@Override
		public R fun(P ps)
		{
			return this.myResultClass.isInstance(ps) ? this.myResultClass.cast(ps) : null;
		}
	}
	
	@Deprecated
	public static final IFunction<?, ?> ID = new Mono()
	{
		@Override
		public Object fun(Object obj)
		{
			return obj;
		}
		
		@Override
		public String toString()
		{
			return "IFunction.ID";
		}
	};
	
	@Deprecated
	public static final IFunction<?, ?> NULL = INullableFunction.NULL;
	
	@Deprecated
	public static final IFunction<?, String> TO_STRING = new IFunction<Object, String>()
	{
		@Override
		public String fun(Object obj)
		{
			return String.valueOf(obj);
		}
		
		@Override
		public String toString()
		{
			return "IFunction.TO_STRING";
		}
	};
	
	Result fun(Param param);
}