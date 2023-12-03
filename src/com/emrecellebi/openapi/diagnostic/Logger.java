package com.emrecellebi.openapi.diagnostic;

public abstract class Logger
{
	private static IFactory ourFactory = new DefaultFactory();
	
	protected static Throwable checkException(Throwable t)
	{
		return (t instanceof IControlFlowException) ? new Throwable("Control-Flow exceptions (like " + t.getClass().getSimpleName() + ") should never be logged", t) : t;
	}
	
	public static Logger getInstance(Class<?> cl)
	{
		return ourFactory.getLoggerInstance("#" + cl.getName());
	}
	
	public static Logger getInstance(String category)
	{
		return ourFactory.getLoggerInstance(category);
	}
	
	
	
	
	
	
	public abstract boolean isDebugEnabled();
	public abstract void debug(String paramString);
	public abstract void debug(Throwable paramThrowable);
	public abstract void debug(String paramString, Throwable paramThrowable);
	public abstract void info(String paramString);
	public abstract void info(String paramString, Throwable paramThrowable);
	public abstract void warn(String paramString, Throwable paramThrowable);
	public abstract void error(String paramString, Throwable paramThrowable, String... paramVarArgs);
	// public abstract void setLevel(Level paramLevel);
	
	private static final class DefaultFactory implements IFactory
	{
		private DefaultFactory(){}
		
		@Override /// Factory.getLoggerInstance
		public Logger getLoggerInstance(String category)
		{
			return new DefaultLogger(category);
		}
	}
	
	public static interface IFactory
	{
		Logger getLoggerInstance(String param1String);
	}
}