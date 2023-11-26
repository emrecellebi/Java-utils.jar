package com.emrecellebi;

@FunctionalInterface
public interface IFactory<T>
{
	T create();
}