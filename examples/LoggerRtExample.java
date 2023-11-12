package com.emrecellebi;

import com.emrecellebi.logging.LoggerRt;

public class LoggerRtsExample
{
	public static void main(String[] args)
	{
		/**
			getInstance(String): LoggerRt
			getInstance(Class<?>): LoggerRt
		**/
		LoggerRt log2 = LoggerRt.getInstance("#");
		LoggerRt log = LoggerRt.getInstance(Console.class);
		
		/// log.info(String): void
		/// log.info(Throwable): void
		/// log.info(String, Throwable): void
		log.info("Hello World!", null);
		
		/// log.warn(String): void
		/// log.warn(Throwable): void
		/// log.warn(String, Throwable): void
		log.warn("Hello World!", null);
		
		/// log.error(String): void
		/// log.error(Throwable): void
		/// log.error(String, Throwable): void
		log.error("Hello World!", null);
	}
}