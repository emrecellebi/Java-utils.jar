package com.emrecellebi.util;

public interface IConsumer<T>
{
	void consume(T paramT);
	
	@Deprecated
	public static final IConsumer<Object> EMPTY_CONSUMER = new IConsumer<Object>()
	{
		@Override
		public void consume(Object t){}
	};
}