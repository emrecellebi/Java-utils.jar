package com.emrecellebi.openapi;

public interface IDisposable
{
	void dispose();

	public static interface Parent extends IDisposable
	{
		void beforeTreeDispose();
	}
}