package com.emrecellebi;

import com.emrecellebi.util.IArrayFactory;

public class ObjectArray implements IArrayFactory<Object>
{
	@Override
	public Object[] create(int param)
	{
		return new Object[param];
	}
}