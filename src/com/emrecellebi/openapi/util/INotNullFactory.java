package com.emrecellebi.openapi.util;

@FunctionalInterface
public interface INotNullFactory<T> extends IFactory<T>
{
	T create();
}