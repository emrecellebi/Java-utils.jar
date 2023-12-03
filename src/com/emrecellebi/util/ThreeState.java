package com.emrecellebi.util;

public enum ThreeState
{
	YES, NO, UNSURE;

	public static ThreeState fromBoolean(boolean value)
	{
		return value ? YES : NO;
	}

	public ThreeState merge(ThreeState other)
	{
		return (this == other) ? this : UNSURE;
	}

	public boolean toBoolean()
	{
		if(this == UNSURE) throw new IllegalStateException("Must be or YES, or NO"); 
		return (this == YES);
	}

	public static ThreeState mostPositive(Iterable<ThreeState> states)
	{
		ThreeState result = NO;
		for(ThreeState state : states)
		{
			switch(state)
			{
				case YES:
					return YES;
				case UNSURE:
					result = UNSURE;
			}
		}
		return result;
	}

	public static ThreeState merge(Iterable<ThreeState> states)
	{
		ThreeState result = null;
		for(ThreeState state : states)
		{
			if(state == UNSURE) return UNSURE;
			if (result == null)
			{
				result = state;
				continue;
			}
			if(result != state) return UNSURE;
		} 
		if(result == null) throw new IllegalArgumentException("Argument should not be empty"); 
		return result;
	}
}
