package com.emrecellebi;

@FunctionalInterface
public interface IProcessor<T>
{
	boolean process(T paramT);
}