package com.emrecellebi.util.text;

public interface ICharSequenceBackedByArray extends CharSequence
{
	char[] getChars();
	
	void getChars(char[] paramArrayOfchar, int paramInt);
}