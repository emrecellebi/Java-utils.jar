package com.emrecellebi.util.containers;

@FunctionalInterface
public interface IConvertor<Src, Dst>
{
	public static final IntoSelf SELF = new IntoSelf();

	Dst convert(Src paramSrc);

	public static class IntoSelf<Src> implements IConvertor<Src, Src>
	{
		@Override
		public Src convert(Src o)
		{
			return o;
		}
	}

	public static <T> IConvertor<T, T> self()
	{
		return SELF;
	}
}