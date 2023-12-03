package com.emrecellebi.openapi.util;

import com.emrecellebi.util.IFunction;

public class Pair<A, B>
{
	public static class NonNull<A, B> extends Pair<A, B>
	{
		public NonNull(A first, B second)
		{
			super(first, second);
		}
	}
	
	public final A first;
	public final B second;
	
	private static final Pair EMPTY = create(null, null);
	
	public Pair(A first, B second)
	{
		this.first = first;
		this.second = second;
	}
	
	public static <A, B> Pair<A, B> create(A first, B second)
	{
		return new Pair<A, B>(first, second);
	}
	
	public static <A, B> IFunction<A, Pair<A, B>> createFunction(final B value)
	{
		return new IFunction<A, Pair<A, B>>()
		{
			@Override
			public Pair<A, B> fun(A a)
			{
				return Pair.create(a, (B)value);
			}
		};
	}
	
	public static <A, B> NonNull<A, B> createNonNull(A first, B second)
	{
		return new NonNull<A, B>(first, second);
	}
	
	public static <A, B> Pair<A, B> empty()
	{
		return EMPTY;
	}
	
	public final boolean equals(Object o)
	{
		return (o instanceof Pair && Comparing.equal(this.first, ((Pair)o).first) && Comparing.equal(this.second, ((Pair)o).second));
	}
	
	public final A getFirst()
	{
		return this.first;
	}
	
	public static <T> T getFirst(Pair<T, ?> pair)
	{
		return (pair != null) ? (T)pair.first : null;
	}
	
	public final B getSecond()
	{
		return this.second;
	}
	
	public static <T> T getSecond(Pair<?, T> pair)
	{
		return (pair != null) ? (T)pair.second : null;
	}
	
	public int hashCode()
	{
		int result = (this.first != null) ? this.first.hashCode() : 0;
		result = 31 * result + ((this.second != null) ? this.second.hashCode() : 0);
		return result;
	}
	
	public static <A, B> Pair<A, B> pair(A first, B second)
	{
		return new Pair<A, B>(first, second);
	}
	
	@Override
	public String toString()
	{
		return "<" + this.first + "," + this.second + ">";
	}
}