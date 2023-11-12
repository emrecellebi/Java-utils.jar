package com.emrecellebi.other;

import java.util.function.Supplier;

public final class DefaultBundleService
{
	private static final DefaultBundleService INSTANCE = new DefaultBundleService();
	private static final ThreadLocal<Boolean> ourDefaultBundle = ThreadLocal.withInitial(() -> Boolean.valueOf(false));
	
	public static DefaultBundleService getInstance()
	{
		return INSTANCE;
	}
	
	public <T> T compute(Supplier<? extends T> computable)
	{
		boolean isDefault = isDefaultBundle();
		if(!isDefault) ourDefaultBundle.set(Boolean.valueOf(true));
		try
		{
			return computable.get();
		}
		finally
		{
			if(!isDefault) ourDefaultBundle.set(Boolean.valueOf(false));
		}
	}
	
	public static boolean isDefaultBundle()
	{
		return ((Boolean)ourDefaultBundle.get()).booleanValue();
	}
}