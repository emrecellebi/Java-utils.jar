package com.emrecellebi.openapi.util;

import java.util.Comparator;

public interface ISegment
{
	public static final Comparator<ISegment> BY_START_OFFSET_THEN_END_OFFSET = Comparator.<ISegment>comparingInt(ISegment::getStartOffset).thenComparingInt(ISegment::getEndOffset);

	int getStartOffset();

	int getEndOffset();
}