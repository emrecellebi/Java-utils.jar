package com.emrecellebi.util.text;

import com.emrecellebi.openapi.util.text.ICharSequenceWithStringHash;
import com.emrecellebi.openapi.util.text.Strings;
import com.emrecellebi.util.text.ICharArrayExternalizable;
import com.emrecellebi.util.text.CharArrayUtil;

public final class ImmutableText extends ImmutableCharSequence implements ICharArrayExternalizable, ICharSequenceWithStringHash
{
	private static final LeafNode EMPTY_NODE = new Leaf8BitNode(new byte[0]);
	private static final ImmutableText EMPTY = new ImmutableText(EMPTY_NODE);
	private static final int BLOCK_SIZE = 64;
	private static final int BLOCK_MASK = -64;
	
	private final Node myNode;
	private transient int hash;
	private InnerLeaf myLastLeaf;
	
	private ImmutableText(Node node)
	{
		this.myNode = node;
	}
	
	@Override /// ImmutableCharSequence.subtext
	public ImmutableText subtext(int start, int end)
	{
		if(start < 0 || start > end || end > length()) throw new IndexOutOfBoundsException(); 
		if(start == 0 && end == length()) return this; 
		if(start == end) return EMPTY; 
		if(end - start > 64) ensureChunked(); 
		return new ImmutableText(this.myNode.subNode(start, end));
	}
	
	@Override /// ImmutableCharSequence.delete
	public ImmutableText delete(int start, int end)
	{
		if(start == end) return this; 
		if(start > end) throw new IndexOutOfBoundsException(); 
		return subtext(0, start).concat(subtext(end));
	}
	
	@Override /// ImmutableCharSequence.insert
	public ImmutableText insert(int index, CharSequence seq)
	{
		if(seq.length() == 0) return this; 
		return subtext(0, index).concat(valueOf(seq)).concat(subtext(index));
	}
	
	@Override /// ImmutableCharSequence.concat
	public ImmutableText concat(CharSequence sequence)
	{
		return concat(valueOf(sequence));
	}
	
	@Override /// ImmutableCharSequence.toString
	public String toString()
	{
		return this.myNode.toString();
	}
	
	@Override /// ICharArrayExternalizable.getChars
	public void getChars(int start, int end, char[] dest, int destPos)
	{
		this.myNode.getChars(start, end, dest, destPos);
	}
	
	@Override /// CharSequence.subSequence
	public CharSequence subSequence(int start, int end)
	{
		if(start == 0 && end == length()) return this; 
		return (CharSequence)new CharSequenceSubSequence(this, start, end);
	}
	
	@Override /// CharSequence.charAt
	public char charAt(int index)
	{
		InnerLeaf leaf = this.myLastLeaf;
		if(leaf == null || index < leaf.start || index >= leaf.end)
			this.myLastLeaf = leaf = findLeaf(index); 
		return leaf.leafNode.charAt(index - leaf.start);
	}
	
	@Override /// CharSequence.length
	public int length()
	{
		return this.myNode.length();
	}
	
	private ImmutableText concat(ImmutableText that)
	{
		return (that.length() == 0) ? this : ((length() == 0) ? that : new ImmutableText(concatNodes((ensureChunked()).myNode, (that.ensureChunked()).myNode)));
	}
	
	private static Node concatNodes(Node node1, Node node2)
	{
		int length = node1.length() + node2.length();
		Node head = node1;
		Node tail = node2;
		if(shouldRebalance(head, tail))
		{
			do
			{
				if(((CompositeNode)tail).head.length() > ((CompositeNode)tail).tail.length())
					tail = ((CompositeNode)tail).rightRotation(); 
				head = concatNodes(head, ((CompositeNode)tail).head);
				tail = ((CompositeNode)tail).tail;
			}while(shouldRebalance(head, tail));
		}
		else if(shouldRebalance(tail, head))
		{
			do
			{
				if(((CompositeNode)head).tail.length() > ((CompositeNode)head).head.length())
					head = ((CompositeNode)head).leftRotation(); 
				tail = concatNodes(((CompositeNode)head).tail, tail);
				head = ((CompositeNode)head).head;
			} while(shouldRebalance(tail, head));
		} 
		return new CompositeNode(head, tail);
	}
	
	public static LeafNode createLeafNode(CharSequence str)
	{
		byte[] bytes = ByteArrayCharSequence.toBytesIfPossible(str);
		if(bytes != null) return new Leaf8BitNode(bytes); 
		char[] chars = new char[str.length()];
		CharArrayUtil.getChars(str, chars, 0, 0, str.length());
		return new WideLeafNode(chars);
	}
	
	private ImmutableText ensureChunked()
	{
		if(length() > 64 && this.myNode instanceof LeafNode)
			return new ImmutableText(nodeOf((LeafNode)this.myNode, 0, length())); 
		return this;
	}
	
	public boolean equals(Object obj)
	{
		if(this == obj) return true; 
		if(!(obj instanceof ImmutableText)) return false; 
		return CharArrayUtil.regionMatches(this, 0, (ImmutableText)obj);
	}
	
	private InnerLeaf findLeaf(int index)
	{
		if(index < 0) throw outOfRange(index); 
		Node node = this.myNode;
		int nodeLength = node.length();
		int offset = 0;
		while(true)
		{
			if(index >= nodeLength) throw outOfRange(index); 
			if(node instanceof LeafNode) return new InnerLeaf((LeafNode)node, offset, offset + nodeLength); 
			CompositeNode composite = (CompositeNode)node;
			int headLength = composite.head.length();
			if(index < headLength)
			{
				node = composite.head;
				nodeLength = headLength;
				continue;
			} 
			offset += headLength;
			index -= headLength;
			node = composite.tail;
			nodeLength -= headLength;
		} 
	}
	
	public int hashCode()
	{
		int h = this.hash;
		if(h == 0) this.hash = h = Strings.stringHashCode(this, 0, length()); 
		return h;
	}
	
	private static Node nodeOf(LeafNode node, int offset, int length)
	{
		if(length <= 64) return node.subNode(offset, offset + length); 
		int half = length + 64 >> 1 & 0xFFFFFFC0;
		return new CompositeNode(nodeOf(node, offset, half), nodeOf(node, offset + half, length - half));
	}
	
	private static IndexOutOfBoundsException outOfRange(int index)
	{
		return new IndexOutOfBoundsException("Index out of range: " + index);
	}
	
	private static boolean shouldRebalance(Node shorter, Node longer)
	{
		return (shorter.length() << 1 < longer.length() && longer instanceof CompositeNode);
	}
	
	private ImmutableText subtext(int start)
	{
		return subtext(start, length());
	}
	
	private static ImmutableText valueOf(CharSequence str)
	{
		return new ImmutableText(createLeafNode(str));
	}
	
	public static ImmutableText valueOf(Object obj)
	{
		if(obj instanceof ImmutableText) return (ImmutableText)obj; 
		if(obj instanceof CharSequence) return (((CharSequence)obj).length() == 0) ? EMPTY : valueOf((CharSequence)obj); 
		return valueOf(String.valueOf(obj));
	}
	
	/** Node Class **/
	static abstract class Node implements CharSequence
	{
		abstract void getChars(int param1Int1, int param1Int2, char[] param1ArrayOfchar, int param1Int3);
		abstract Node subNode(int param1Int1, int param1Int2);
		
		public String toString()
		{
			int len = length();
			char[] data = new char[len];
			getChars(0, len, data, 0);
			return new String(data);
		}
		
		@Override
		public CharSequence subSequence(int start, int end)
		{
			return subNode(start, end);
		}
	}
	
	/** CompositeNode Class **/
	static class CompositeNode extends Node
	{
		final int count;
		final ImmutableText.Node head;
		final ImmutableText.Node tail;

		CompositeNode(ImmutableText.Node head, ImmutableText.Node tail)
		{
			this.count = head.length() + tail.length();
			this.head = head;
			this.tail = tail;
		}

		@Override /// CharSequence.length
		public int length()
		{
			return this.count;
		}
		
		@Override /// CharSequence.charAt
		public char charAt(int index)
		{
			int headLength = this.head.length();
			return (index < headLength) ? this.head.charAt(index) : this.tail.charAt(index - headLength);
		}

		@Override /// Node.getChars
		void getChars(int start, int end, char[] dest, int destPos)
		{
			int cesure = this.head.length();
			if(end <= cesure)
				this.head.getChars(start, end, dest, destPos);
			else if(start >= cesure)
				this.tail.getChars(start - cesure, end - cesure, dest, destPos);
			else
			{
				this.head.getChars(start, cesure, dest, destPos);
				this.tail.getChars(0, end - cesure, dest, destPos + cesure - start);
			} 
		}

		@Override /// Node.subNode
		ImmutableText.Node subNode(int start, int end)
		{
			int cesure = this.head.length();
			if(end <= cesure) return this.head.subNode(start, end); 
			if(start >= cesure) return this.tail.subNode(start - cesure, end - cesure); 
			if(start == 0 && end == this.count) return this; 
			return ImmutableText.concatNodes(this.head.subNode(start, cesure), this.tail.subNode(0, end - cesure));
		}
		
		ImmutableText.Node rightRotation()
		{
			ImmutableText.Node P = this.head;
			if(!(P instanceof CompositeNode)) return this; 
			ImmutableText.Node A = ((CompositeNode)P).head;
			ImmutableText.Node B = ((CompositeNode)P).tail;
			ImmutableText.Node C = this.tail;
			return new CompositeNode(A, new CompositeNode(B, C));
		}

		ImmutableText.Node leftRotation()
		{
			ImmutableText.Node Q = this.tail;
			if(!(Q instanceof CompositeNode)) return this; 
			ImmutableText.Node B = ((CompositeNode)Q).head;
			ImmutableText.Node C = ((CompositeNode)Q).tail;
			ImmutableText.Node A = this.head;
			return new CompositeNode(new CompositeNode(A, B), C);
		}

	}
	
	/** LeafNode Class **/
	private static abstract class LeafNode extends Node
	{
		private LeafNode(){}
	}
	
	/** WideLeafNode Class **/
	private static class WideLeafNode extends LeafNode
	{
		private final char[] data;

		WideLeafNode(char[] data)
		{
			this.data = data;
		}
		
		@Override  /// CharSequence.length
		public int length()
		{
			return this.data.length;
		}
		
		@Override /// CharSequence.charAt
		public char charAt(int index)
		{
			return this.data[index];
		}
		
		@Override /// Node.getChars
		void getChars(int start, int end, char[] dest, int destPos)
		{
			if(start < 0 || end > length() || start > end)
				throw new IndexOutOfBoundsException(); 
			System.arraycopy(this.data, start, dest, destPos, end - start);
		}
		
		@Override /// Node.subNode
		ImmutableText.Node subNode(int start, int end)
		{
			if (start == 0 && end == length())return this; 
			return ImmutableText.createLeafNode((CharSequence)new CharArrayCharSequence(this.data, start, end));
		}

		public String toString()
		{
			return new String(this.data);
		}
	}
	
	/** Leaf8BitNode Class **/
	private static class Leaf8BitNode extends LeafNode
	{
		private final byte[] data;

		Leaf8BitNode(byte[] data)
		{
			this.data = data;
		}
		
		@Override /// CharSequence.length
		public int length()
		{
			return this.data.length;
		}
		
		@Override /// CharSequence.charAt
		public char charAt(int index)
		{
			return byteToChar(this.data[index]);
		}
		
		@Override /// Node.getChars
		void getChars(int start, int end, char[] dest, int destPos)
		{
			if(start < 0 || end > length() || start > end) throw new IndexOutOfBoundsException(); 
			for(int i = start; i < end; i++)
				dest[destPos++] = byteToChar(this.data[i]);
		}
		
		@Override /// Node.subNode
		ImmutableText.LeafNode subNode(int start, int end)
		{
			if(start == 0 && end == length()) return this; 
			int length = end - start;
			byte[] chars = new byte[length];
			System.arraycopy(this.data, start, chars, 0, length);
			return new Leaf8BitNode(chars);
		}
		
		private static char byteToChar(byte b)
		{
			return (char)(b & 0xFF);
		}
	}
	
	/** InnerLeaf Class **/
	private static final class InnerLeaf
	{
		final ImmutableText.LeafNode leafNode;
		final int start;
		final int end;

		private InnerLeaf(ImmutableText.LeafNode leafNode, int start, int end)
		{
			this.leafNode = leafNode;
			this.start = start;
			this.end = end;
		}
	}
}