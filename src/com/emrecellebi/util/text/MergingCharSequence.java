package com.emrecellebi.util.text;

public final class MergingCharSequence implements CharSequence
{
	private final CharSequence s1;
	private final CharSequence s2;
	
	public MergingCharSequence(CharSequence s1, CharSequence s2)
	{
		this.s1 = s1;
		this.s2 = s2;
	}
	
	@Override
	public char charAt(int index)
	{
		if(index < this.s1.length()) return this.s1.charAt(index); 
		return this.s2.charAt(index - this.s1.length());
	}
	
	@Override
	public int length()
	{
		return this.s1.length() + this.s2.length();
	}
	
	@Override
	public CharSequence subSequence(int start, int end)
	{
		if(start == 0 && end == length()) return this;
		if(start < this.s1.length() && end < this.s1.length()) return this.s1.subSequence(start, end); 
		if(start >= this.s1.length() && end >= this.s1.length()) return this.s2.subSequence(start - this.s1.length(), end - this.s1.length()); 
		return new MergingCharSequence(this.s1.subSequence(start, this.s1.length()), this.s2.subSequence(0, end - this.s1.length()));
	}
	
	@Override
	public String toString()
	{
		return this.s1 + this.s2.toString();
	}
}