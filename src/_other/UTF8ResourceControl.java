package com.emrecellebi.other;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class UTF8ResourceControl extends ResourceBundle.Control
{
	public static final UTF8ResourceControl INSTANCE = new UTF8ResourceControl();

	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException
	{
		String bundleName = toBundleName(baseName, locale);
		ResourceBundle bundle = null;
		
		if(format.equals("java.class"))
		{
			// try
			// {
				// Class<? extends ResourceBundle> bundleClass = (Class)loader.loadClass(bundleName);
				// if(ResourceBundle.class.isAssignableFrom(bundleClass)) 
					// bundle = bundleClass.newInstance();
				// else throw new ClassCastException(bundleClass.getName() + " cannot be cast to ResourceBundle");
			// }catch (ClassNotFoundException classNotFoundException){}
		}
		else if(format.equals("java.properties"))
		{
			if(bundleName.contains("://")) return bundle;
			final String resourceName = toResourceName(bundleName, "properties");
			final ClassLoader classLoader = loader;
			final boolean reloadFlag = reload;
			InputStream stream = null;
			
			try
			{
				stream = AccessController.<InputStream>doPrivileged(new PrivilegedExceptionAction<InputStream>()
				{
					@Override
					public InputStream run() throws IOException
					{
						InputStream is = null;
						if(reloadFlag)
						{
							System.out.println("Yeniden yüüklemek");
							// URL url = classLoader.getResource(resourceName);
							// if(url != null)
							// {
								// URLConnection connection = url.openConnection();
								// if(connection != null)
								// {
									// connection.setUseCaches(false);
									// is = connection.getInputStream();
								// }
							// }
						} else is = classLoader.getResourceAsStream(resourceName);
						return is;
					}
				});
			}catch(PrivilegedActionException e){throw (IOException)e.getException();}
			
			if(stream != null)
				try {bundle = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));} finally {stream.close();}
		
			// System.out.println("Bundle Name: " + bundleName);
			// System.out.println("Bundle Format: " + format);
			// System.out.println("Bundle Locale: " + locale);
			// System.out.println("Resource Name: " + resourceName);
			// System.out.println();
		} else throw new IllegalArgumentException("unknown format: " + format);
		
		return bundle;
	}
}