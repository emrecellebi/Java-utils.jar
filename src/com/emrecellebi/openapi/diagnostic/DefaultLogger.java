package com.emrecellebi.openapi.diagnostic;

public class DefaultLogger extends Logger
{
	public DefaultLogger(String category)
	{
		
	}
	
	@Override /// Logger.debug
	public void debug(String message)
	{
		/// Nothing
	}

	@Override /// Logger.debug
	public void debug(Throwable t)
	{
		/// Nothing
	}

	@Override /// Logger.debug
	public void debug(String message, Throwable t)
	{
		/// Nothing
	}
	
	@Override /// Logger.error
	public void error(String message, Throwable t, String... details)
	{
		
	}
	
	@Override /// Logger.info
	public void info(String message)
	{
		
	}
	
	@Override /// Logger.info
	public void info(String message, Throwable t)
	{
		
	}
	
	@Override /// Logger.isDebugEnabled
	public boolean isDebugEnabled()
	{
		return false;
	}
	
	@Override /// Logger.warn
	public void warn(String message, Throwable t)
	{
		
	}
}