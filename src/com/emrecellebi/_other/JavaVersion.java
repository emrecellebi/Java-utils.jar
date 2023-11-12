package com.emrecellebi.util.lang;

import java.util.ArrayList;
import java.util.List;

public final class JavaVersion implements Comparable<JavaVersion>
{
	public final int feature;
	public final int minor;
	public final int update;
	public final int build;
	public final boolean ea;
	
	private static JavaVersion current;
	
	private JavaVersion(int feature, int minor, int update, int build, boolean ea)
	{
		this.feature = feature;
		this.minor = minor;
		this.update = update;
		this.build = build;
		this.ea = ea;
	}
	
	public int compareTo(JavaVersion o)
	{
		int diff = this.feature - o.feature;
		if(diff != 0) return diff;
		
		diff = this.minor - o.minor;
		if(diff != 0) return diff;
		
		diff = this.update - o.update;
		if(diff != 0) return diff; 
		
		diff = this.build - o.build;
		if(diff != 0) return diff; 
		
		return (this.ea ? 0 : 1) - (o.ea ? 0 : 1);
	}
	
	public boolean isAtLeast(int feature)
	{
		return (this.feature >= feature);
	}
	
	public boolean equals(Object o)
	{
		if(this == o) return true; 
		if(!(o instanceof JavaVersion)) return false;
		JavaVersion other = (JavaVersion)o;
		return (this.feature == other.feature && this.minor == other.minor && this.update == other.update && this.build == other.build && this.ea == other.ea);
	}
	
	public int hashCode()
	{
		int hash = this.feature;
		hash = 31 * hash + this.minor;
		hash = 31 * hash + this.update;
		hash = 31 * hash + this.build;
		hash = 31 * hash + (this.ea ? 1231 : 1237);
		return hash;
	}
	
	public static JavaVersion current()
	{
		if(current == null)
		{
			JavaVersion fallback = parse(System.getProperty("java.version"));
			JavaVersion rt = rtVersion();
			if(rt == null) try{rt = parse(System.getProperty("java.runtime.version"));}catch (Throwable t){}
			current = (rt != null && rt.feature == fallback.feature && rt.minor == fallback.minor) ? rt : fallback;
		}
		return current;
	}
	
	public static JavaVersion compose(int feature, int minor, int update, int build, boolean ea) throws IllegalArgumentException
	{
		if(feature < 0) throw new IllegalArgumentException(); 
		if(minor < 0) throw new IllegalArgumentException(); 
		if(update < 0) throw new IllegalArgumentException(); 
		if(build < 0) throw new IllegalArgumentException(); 
		return new JavaVersion(feature, minor, update, build, ea);
	}
	
	public static JavaVersion compose(int feature)
	{
		return compose(feature, 0, 0, 0, false);
	}
	
	public String toFeatureString()
	{
		return formatVersionTo(true, true);
	}
	
	public String toFeatureMinorUpdateString()
	{
		return formatVersionTo(false, true);
	}
	
	public static JavaVersion parse(String versionString) throws IllegalArgumentException 
	{		
		String str = versionString.trim();
		if(str.contains("Runtime Environment"))
		{
			int i = str.indexOf("(build ");
			if(i > 0) str = str.substring(i);
		}
		List<String> numbers = new ArrayList<>(), separators = new ArrayList<>();
		int length = str.length(), p = 0;
		boolean number = false;
		while(p < length)
		{
			int start = p;
			for(; p < length && Character.isDigit(str.charAt(p)) == number; p++);
			String part = str.substring(start, p);
			(number ? numbers : separators).add(part);
			number = !number;
		}
		if(!numbers.isEmpty() && !separators.isEmpty())
		{
			try
			{
				int feature = Integer.parseInt(numbers.get(0)), minor = 0, update = 0, build = 0;
				boolean ea = false;
				if(feature >= 5 && feature < 25)
				{
					p = 1;
					for(; p < separators.size() && ".".equals(separators.get(p)); p++);
					if(p > 1 && numbers.size() > 2)
					{
						minor = Integer.parseInt(numbers.get(1));
						update = Integer.parseInt(numbers.get(2));
					}
					if(p < separators.size())
					{
						String s = separators.get(p);
						if(s != null && s.length() != 0 && s.charAt(0) == '-')
						{
							ea = (startsWithWord(s, "-ea") || startsWithWord(s, "-internal"));
							if (p < numbers.size() && s.charAt(s.length() - 1) == '+')
							build = Integer.parseInt(numbers.get(p)); 
							p++;
						}
						if(build == 0 && p < separators.size() && p < numbers.size() && "+".equals(separators.get(p)))
							build = Integer.parseInt(numbers.get(p)); 
					}
					return new JavaVersion(feature, minor, update, build, ea);
				}
				if(feature == 1 && numbers.size() > 1 && separators.size() > 1 && ".".equals(separators.get(1)))
				{
					feature = Integer.parseInt(numbers.get(1));
					if(feature <= 25)
					{
						if(numbers.size() > 2 && separators.size() > 2 && ".".equals(separators.get(2))) 
						{
							minor = Integer.parseInt(numbers.get(2));
							if(numbers.size() > 3 && separators.size() > 3 && "_".equals(separators.get(3)))
							{
								update = Integer.parseInt(numbers.get(3));
								if(separators.size() > 4)
								{
									String s = separators.get(4);
									if(s != null && s.length() != 0 && s.charAt(0) == '-')
										ea = (startsWithWord(s, "-ea") || startsWithWord(s, "-internal")); 
									p = 4;
									for(; p < separators.size() && !((String)separators.get(p)).endsWith("-b"); p++);
									if(p < numbers.size())
										build = Integer.parseInt(numbers.get(p)); 
								}
							}
						}
						return new JavaVersion(feature, minor, update, build, ea);
					}
				}
			}catch(NumberFormatException nfe){}
		}
		throw new IllegalArgumentException(versionString);
	}
	
	public String toString()
	{
		return formatVersionTo(false, false);
	}
	
	public static JavaVersion tryParse(String versionString)
	{
		if(versionString != null) try{return parse(versionString);}catch(IllegalArgumentException iae){}
		return null;
	}
	
	private String formatVersionTo(boolean upToFeature, boolean upToUpdate)
	{
		StringBuilder sb = new StringBuilder();
		if(this.feature > 8)
		{
			sb.append(this.feature);
			if(!upToFeature)
			{
				if(this.minor > 0 || this.update > 0) sb.append('.').append(this.minor); 
				if(this.update > 0) sb.append('.').append(this.update); 
				if(!upToUpdate)
				{
				  if(this.ea) sb.append("-ea"); 
				  if(this.build > 0) sb.append('+').append(this.build); 
				} 
			}
		}
		else
		{
			sb.append("1.").append(this.feature);
			if(!upToFeature)
			{
				if(this.minor > 0 || this.update > 0 || this.ea || this.build > 0) sb.append('.').append(this.minor); 
				if(this.update > 0) sb.append('_').append(this.update); 
				if(!upToUpdate)
				{
					if(this.ea) sb.append("-ea"); 
					if(this.build > 0) sb.append("-b").append(this.build); 
				}
			}
		}
		return sb.toString();
	}
	
	private static JavaVersion rtVersion()
	{
		try
		{
			Object version = Runtime.class.getMethod("version", new Class[0]).invoke(null, new Object[0]);
			int major = ((Integer)version.getClass().getMethod("major", new Class[0]).invoke(version, new Object[0])).intValue();
			int minor = ((Integer)version.getClass().getMethod("minor", new Class[0]).invoke(version, new Object[0])).intValue();
			int security = ((Integer)version.getClass().getMethod("security", new Class[0]).invoke(version, new Object[0])).intValue();
			
			Object buildOpt = version.getClass().getMethod("build", new Class[0]).invoke(version, new Object[0]);
			int build = ((Integer)buildOpt.getClass().getMethod("orElse", new Class[] { Object.class }).invoke(buildOpt, new Object[] { Integer.valueOf(0) })).intValue();
			
			Object preOpt = version.getClass().getMethod("pre", new Class[0]).invoke(version, new Object[0]);
			boolean ea = ((Boolean)preOpt.getClass().getMethod("isPresent", new Class[0]).invoke(preOpt, new Object[0])).booleanValue();
			
			return new JavaVersion(major, minor, security, build, ea);
		}catch(Throwable ignored){return null;}
	}
	
	private static boolean startsWithWord(String s, String word)
	{
		return (s.startsWith(word) && (s.length() == word.length() || !Character.isLetterOrDigit(s.charAt(word.length()))));
	}
}