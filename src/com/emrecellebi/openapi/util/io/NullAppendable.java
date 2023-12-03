package com.emrecellebi.openapi.util.io;

import java.io.IOException;

public class NullAppendable implements Appendable
{
	public static Appendable INSTANCE = new NullAppendable();
	
	@Override
	public Appendable append(CharSequence csq) throws IOException
	{
		return this;
	}

	@Override
	public Appendable append(CharSequence csq, int start, int end) throws IOException
	{
		return this;
	}

	@Override
	public Appendable append(char c) throws IOException
	{
		return this;
	}
}