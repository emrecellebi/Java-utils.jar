package com.emrecellebi.text;

import java.io.Serializable;

import com.emrecellebi.ISegment;

public class TextRange implements ISegment, Serializable
{
	public static final TextRange EMPTY_RANGE = new TextRange(0, 0);
	private static final long serialVersionUID = -670091356599757430L;
	
	private final int myStartOffset;
	private final int myEndOffset;
	
	public TextRange(int startOffset, int endOffset)
	{
		this(startOffset, endOffset, true);
	}
	
	protected TextRange(int startOffset, int endOffset, boolean checkForProperTextRange)
	{
		this.myStartOffset = startOffset;
		this.myEndOffset = endOffset;
		if(checkForProperTextRange)
			assertProperRange(this);
	}
	
	public static TextRange allOf(String s)
	{
		return new TextRange(0, s.length());
	}
	
	public static boolean areSegmentsEqual(ISegment seg1, ISegment seg2)
	{
		return (seg1.getStartOffset() == seg2.getStartOffset() && seg1.getEndOffset() == seg2.getEndOffset());
	}
	
	public static void assertProperRange(ISegment range) throws AssertionError
	{
		assertProperRange(range, "");
	}
	
	public static void assertProperRange(ISegment range, Object message) throws AssertionError
	{
		assertProperRange(range.getStartOffset(), range.getEndOffset(), message);
	}
	
	public static void assertProperRange(int startOffset, int endOffset, Object message)
	{
		if(!isProperRange(startOffset, endOffset))
			throw new IllegalArgumentException("Invalid range specified: (" + startOffset + ", " + endOffset + "); " + message);
	}
	
	public boolean contains(ISegment range)
	{
		return containsRange(range.getStartOffset(), range.getEndOffset());
	}
	
	public boolean contains(TextRange range)
	{
		return contains((ISegment)range);
	}
	
	public boolean contains(int offset)
	{
		return (this.myStartOffset <= offset && offset < this.myEndOffset);
	}
	
	public boolean containsOffset(int offset)
	{
		return (this.myStartOffset <= offset && offset <= this.myEndOffset);
	}
	
	public static boolean containsRange(ISegment outer, ISegment inner)
	{
		return (outer.getStartOffset() <= inner.getStartOffset() && inner.getEndOffset() <= outer.getEndOffset());
	}
	
	public boolean containsRange(int startOffset, int endOffset)
	{
		return (getStartOffset() <= startOffset && endOffset <= getEndOffset());
	}
	
	public static TextRange create(ISegment segment)
	{
		return create(segment.getStartOffset(), segment.getEndOffset());
	}
	
	public static TextRange create(int startOffset, int endOffset)
	{
		return new TextRange(startOffset, endOffset);
	}
	
	public TextRange cutOut(TextRange subRange)
	{
		if(subRange.getStartOffset() > getLength())
			throw new IllegalArgumentException("SubRange: " + subRange + "; this=" + this); 
		if(subRange.getEndOffset() > getLength())
			throw new IllegalArgumentException("SubRange: " + subRange + "; this=" + this); 
		assertProperRange(subRange);
		return new TextRange(this.myStartOffset + subRange.getStartOffset(), Math.min(this.myEndOffset, this.myStartOffset + subRange.getEndOffset()));
	}
	
	public boolean equals(Object obj)
	{
		if(!(obj instanceof TextRange)) return false; 
		TextRange range = (TextRange)obj;
		return (this.myStartOffset == range.myStartOffset && this.myEndOffset == range.myEndOffset);
	}
	
	public boolean equalsToRange(int startOffset, int endOffset)
	{
		return (startOffset == this.myStartOffset && endOffset == this.myEndOffset);
	}
	
	public static TextRange from(int offset, int length)
	{
		return create(offset, offset + length);
	}
	
	@Override
	public final int getEndOffset()
	{
		return this.myEndOffset;
	}
	
	public final int getLength()
	{
		return this.myEndOffset - this.myStartOffset;
	}
	
	@Override
	public final int getStartOffset()
	{
		return this.myStartOffset;
	}
	
	public TextRange grown(int lengthDelta)
	{
		return from(this.myStartOffset, getLength() + lengthDelta);
	}
	
	public int hashCode()
	{
		return this.myStartOffset + this.myEndOffset;
	}
	
	public TextRange intersection(TextRange range)
	{
		int newStart = Math.max(this.myStartOffset, range.getStartOffset());
		int newEnd = Math.min(this.myEndOffset, range.getEndOffset());
		return isProperRange(newStart, newEnd) ? new TextRange(newStart, newEnd) : null;
	}
	
	public boolean intersects(ISegment textRange)
	{
		return intersects(textRange.getStartOffset(), textRange.getEndOffset());
	}
	
	public boolean intersects(TextRange textRange)
	{
		return intersects((ISegment)textRange);
	}
	
	public boolean intersects(int startOffset, int endOffset)
	{
		return (Math.max(this.myStartOffset, startOffset) <= Math.min(this.myEndOffset, endOffset));
	}
	
	public boolean intersectsStrict(TextRange textRange)
	{
		return intersectsStrict(textRange.getStartOffset(), textRange.getEndOffset());
	}
	
	public boolean intersectsStrict(int startOffset, int endOffset)
	{
		return (Math.max(this.myStartOffset, startOffset) < Math.min(this.myEndOffset, endOffset));
	}
	
	public boolean isEmpty()
	{
		return (this.myStartOffset >= this.myEndOffset);
	}
	
	public static boolean isProperRange(int startOffset, int endOffset)
	{
		return (startOffset <= endOffset && startOffset >= 0);
	}
	
	public String replace(String original, String replacement)
	{
		String beginning = original.substring(0, getStartOffset());
		String ending = original.substring(getEndOffset());
		return beginning + replacement + ending;
	}
	
	public TextRange shiftLeft(int delta)
	{
		if (delta == 0) return this;
		return new TextRange(this.myStartOffset - delta, this.myEndOffset - delta);
	}
	
	public TextRange shiftRight(int delta)
	{
		if (delta == 0) return this;
		return new TextRange(this.myStartOffset + delta, this.myEndOffset + delta);
	}
	
	public CharSequence subSequence(CharSequence str)
	{
		return str.subSequence(this.myStartOffset, this.myEndOffset);
	}
	
	public String substring(String str)
	{
		return str.substring(this.myStartOffset, this.myEndOffset);
	}
	
	@Override
	public String toString()
	{
		return "(" + this.myStartOffset + "," + this.myEndOffset + ")";
	}
	
	public TextRange union(TextRange textRange)
	{
		return new TextRange(Math.min(this.myStartOffset, textRange.getStartOffset()), Math.max(this.myEndOffset, textRange.getEndOffset()));
	}
}