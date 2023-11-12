package com.emrecellebi;

import com.emrecellebi.util.ISegment;

public class PluralizerUtilExample implements ISegment
{
	private int startOffset;
	private int endOffset;
	
	public Console(int startOffset, int endOffset)
	{
		this.startOffset = startOffset;
		this.endOffset = endOffset;
	}
	
	@Override
	public int getStartOffset()
	{
		return this.startOffset;
	}
	
	@Override
	public int getEndOffset()
	{
		return this.endOffset;
	}
	
	public static void main(String[] args)
	{
		/**
			ISegment.EMPTY_ARRAY: ISegment[] --> Sıfır elemanlı bir array
			ISegment.BY_START_OFFSET_THEN_END_OFFSET: Comparator --> Start ve End methodlarını karşılaştırır.
			1) Her iki değer bir biri ile aynı durumunda 0 döner
			2) Soldaki değer küçük Sağdaki değer büyük olduğunda -1 döner
			3) Soldaki değer büyük Sağdaki değer küçük olduğunda 1 döner
		**/
		System.out.println("ISegment.BY_START_OFFSET_THEN_END_OFFSET: Comparator -> " + ISegment.BY_START_OFFSET_THEN_END_OFFSET.compare(new PluralizerUtilExample(0, 10), new PluralizerUtilExample(0, 10)));
		System.out.println("ISegment.BY_START_OFFSET_THEN_END_OFFSET: Comparator -> " + ISegment.BY_START_OFFSET_THEN_END_OFFSET.compare(new PluralizerUtilExample(0, 10), new PluralizerUtilExample(5, 15)));
		System.out.println("ISegment.BY_START_OFFSET_THEN_END_OFFSET: Comparator -> " + ISegment.BY_START_OFFSET_THEN_END_OFFSET.compare(new PluralizerUtilExample(5, 15), new PluralizerUtilExample(0, 10)));
	}
}