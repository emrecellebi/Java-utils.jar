package com.emrecellebi.util;

import com.emrecellebi.IArrayFactory;

public class Test implements IArrayFactory<Object>
{
	public Object[] create(int paramInt)
	{
		return new Object[paramInt];
	}
}