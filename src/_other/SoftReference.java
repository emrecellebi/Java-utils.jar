package com.emrecellebi.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class SoftReference<T> extends java.lang.ref.SoftReference<T>
{
	public SoftReference(T referent)
	{
		super(referent);
	}
	
	public SoftReference(T referent, ReferenceQueue<? super T> q)
	{
		super(referent, q);
	}
	
	public static <T> T dereference(Reference<T> ref)
	{
		return (ref == null) ? null : ref.get();
	}
}