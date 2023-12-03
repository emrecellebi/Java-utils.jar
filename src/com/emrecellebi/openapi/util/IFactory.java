package com.emrecellebi.openapi.util;

@FunctionalInterface
public interface IFactory<T>
{
	T create();
}