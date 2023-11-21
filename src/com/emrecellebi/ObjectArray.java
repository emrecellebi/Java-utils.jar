package com.emrecellebi;

public class ObjectArray implements IArrayFactory<Object>
{
	@Override
	public Object[] create(int param)
	{
		return new Object[param];
	}
}