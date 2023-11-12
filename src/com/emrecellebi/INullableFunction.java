package com.emrecellebi;

public interface INullableFunction<Param, Result> extends IFunction<Param, Result>
{
	@Deprecated
	public static final INullableFunction<?, ?> NULL = new INullableFunction<Object, Object>()
	{
		@Override
		public Object fun(Object obj)
		{
			return null;
		}
		
		@Override
		public String toString()
		{
			return "INullableFunction.NULL";
		}
	};
	
	Result fun(Param param);
}