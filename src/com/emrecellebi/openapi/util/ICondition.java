package com.emrecellebi.openapi.util;

public interface ICondition<T>
{
	boolean value(T paramT);
	
	@Deprecated
	public static final ICondition<Object> NOT_NULL = new ICondition<Object>()
	{
		@Override
		public boolean value(Object object)
		{
			return (object != null);
		}

		public String toString()
		{
			return "ICondition.NOT_NULL";
		}
	};

	@Deprecated
	public static final ICondition<Object> TRUE = new ICondition<Object>()
	{
		@Override
		public boolean value(Object object)
		{
			return true;
		}

		public String toString()
		{
			return "ICondition.TRUE";
		}
	};

	@Deprecated
	public static final ICondition<Object> FALSE = new ICondition<Object>()
	{
		@Override
		public boolean value(Object object)
		{
			return false;
		}

		public String toString()
		{
			return "ICondition.FALSE";
		}
	};
}