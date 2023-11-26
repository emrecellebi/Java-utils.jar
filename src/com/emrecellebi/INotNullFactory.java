package com.emrecellebi;

@FunctionalInterface
public interface INotNullFactory<T> extends IFactory<T>
{
	T create();
}