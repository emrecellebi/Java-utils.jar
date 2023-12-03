package com.emrecellebi.openapi.diagnostic;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class LoggerRt
{
	private static IFactory ourFactory;
	
	private static synchronized IFactory getFactory()
	{
		if(ourFactory == null) try{ourFactory = new IdeaFactory();}catch(Throwable t){ t.printStackTrace(); ourFactory = new JavaFactory();}
		return ourFactory;
	}
	
	public static LoggerRt getInstance(Class<?> clazz)
	{
		return getInstance('#' + clazz.getName());
	}
	
	public static LoggerRt getInstance(String category)
	{
		return getFactory().getInstance(category);
	}
	
	public void error(String message)
	{
		error(message, null);
	}
	
	public void error(Throwable t)
	{
		error(t.getMessage(), t);
	}
	
	public void info(String message)
	{
		info(message, null);
	}
	
	public void info(Throwable t)
	{
		info(t.getMessage(), t);
	}
	
	public void warn(String message)
	{
		warn(message, null);
	}
	
	public void warn(Throwable t)
	{
		warn(t.getMessage(), t);
	}
	
	public abstract void info(String paramString, Throwable paramThrowable);
	public abstract void warn(String paramString, Throwable paramThrowable);
	public abstract void error(String paramString, Throwable paramThrowable);
	
	private static final class IdeaFactory implements IFactory
	{
		private final Method myGetInstance;
		private final Method myInfo;
		private final Method myWarn;
		private final Method myError;
		
		private IdeaFactory() throws Exception
		{
			// Class<?> loggerClass = Class.forName("com.intellij.openapi.diagnostic.Logger");
			Class<?> loggerClass = Class.forName("com.emrecellebi.logging.Logger");
			this.myGetInstance = loggerClass.getMethod("getInstance", new Class[]{String.class});
			this.myGetInstance.setAccessible(true);
			
			this.myInfo = loggerClass.getMethod("info", new Class[]{String.class, Throwable.class});
			this.myInfo.setAccessible(true);
			
			this.myWarn = loggerClass.getMethod("warn", new Class[]{String.class, Throwable.class});
			this.myInfo.setAccessible(true);
			
			this.myError = loggerClass.getMethod("error", new Class[]{String.class, Throwable.class});
			this.myError.setAccessible(true);
		}
		
		@Override /// IFactory.getInstance
		public LoggerRt getInstance(String category)
		{
			try
			{
				final Object logger = this.myGetInstance.invoke((Object)null, new Object[]{category});
				return new LoggerRt()
				{
					@Override /// LoggerRt.info
					public void info(String message, Throwable t)
					{
						try{LoggerRt.IdeaFactory.this.myInfo.invoke(logger, new Object[]{message, t});}catch(Exception exception){}
					}
					
					@Override /// LoggerRt.warn
					public void warn(String message, Throwable t)
					{
						try{LoggerRt.IdeaFactory.this.myWarn.invoke(logger, new Object[]{message, t});}catch(Exception exception){}
					}
					
					@Override /// LoggerRt.error
					public void error(String message, Throwable t)
					{
						try{LoggerRt.IdeaFactory.this.myError.invoke(logger, new Object[]{message, t});}catch(Exception exception){}
					}
				};
			}catch(Exception e){throw new RuntimeException(e);}
		}
	}
	
	private static class JavaFactory implements IFactory
	{
		private JavaFactory(){}
		
		@Override /// IFactory.getInstance
		public LoggerRt getInstance(String category)
		{
			final Logger logger = Logger.getLogger(category);
			return new LoggerRt()
			{
				@Override /// LoggerRt.info
				public void info(String message, Throwable t)
				{
					logger.log(Level.INFO, message, t);
				}
				
				@Override /// LoggerRt.warn
				public void warn(String message, Throwable t)
				{
					logger.log(Level.WARNING, message, t);
				}
				
				@Override /// LoggerRt.error
				public void error(String message, Throwable t)
				{
					logger.log(Level.SEVERE, message, t);
				}
			};
		}
	}
	
	private static interface IFactory
	{
		LoggerRt getInstance(String param1String);
	}
}