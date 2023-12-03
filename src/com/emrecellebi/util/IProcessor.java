package com.emrecellebi.util;

@FunctionalInterface
public interface IProcessor<T>
{
	boolean process(T paramT);
}