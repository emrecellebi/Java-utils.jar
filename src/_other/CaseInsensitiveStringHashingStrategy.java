package com.emrecellebi.util.text;

import gnu.trove.strategy.HashingStrategy;

import com.emrecellebi.util.text.Strings;

public final class CaseInsensitiveStringHashingStrategy implements HashingStrategy<String>
{
	public static final CaseInsensitiveStringHashingStrategy INSTANCE = new CaseInsensitiveStringHashingStrategy();

	@Override
	public int computeHashCode(String s)
	{
		return Strings.stringHashCodeInsensitive((CharSequence)s);
	}
	
	@Override
	public boolean equals(String s1, String s2)
	{
		return s1.equalsIgnoreCase(s2);
	}
}