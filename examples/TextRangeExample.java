package com.emrecellebi;

import com.emrecellebi.util.text.TextRange;

public class TextRangeExample
{
	public static void main(String[] args)
	{
		/**
			EMPTY_RANGE: TextRange -> Boş bir TextRange nesnesi yaratır.
		**/
		System.out.println("TextRange.EMPTY_RANGE: TextRange -> " + TextRange.EMPTY_RANGE);
		
		/**
			TextRange(int, int) -> Verilen int değerlerinde TextRange nesnesi yaratır.
		**/
		TextRange txt = new TextRange(5, 50);
		
		/**
			allOf(String): TextRange --> Verilen string datasında bir TextRange nesnesi yaratır.
		**/
		System.out.println("TextRange.allOf(String): TextRange -> " + TextRange.allOf("Hello World!"));
		
		/**
			areSegmentsEqual(ISegment, ISegment): boolean --> Verilen ISegment değerini karşılaştırır.
			1) İki ISegment bir birine eşit ise true
			1) İki ISegment bir birine eşit değil ise false
		**/
		System.out.println("TextRange.areSegmentsEqual(ISegment, ISegment): boolean -> " + TextRange.areSegmentsEqual(new TextRange(5, 50), new TextRange(5, 50)));
		System.out.println("TextRange.areSegmentsEqual(ISegment, ISegment): boolean -> " + TextRange.areSegmentsEqual(new TextRange(15, 50), new TextRange(5, 50)));
		
		/**
			assertProperRange(ISegment): void --> Verilen Start ve End bir birlerinden farklı olup olmadığını döner.
			1) Start küçük End büyük olduğunda Exception dönemez.
			2) Start büyük End küçük olduğunda Exception döner.
		**/
		TextRange.assertProperRange(txt);
		System.out.println("TextRange.assertProperRange(ISegment): void");
		
		/**
			assertProperRange(ISegment, Object): void --> Verilen Start ve End bir birlerinden farklı olup olmadığını döner.
			1) Start küçük End büyük olduğunda Exception dönemez.
			2) Start büyük End küçük olduğunda Exception döner.
		**/
		TextRange.assertProperRange(txt, "Ranges");
		System.out.println("TextRange.assertProperRange(ISegment, Object): void");
		
		/**
			assertProperRange(int, int, Object): void --> Verilen Start ve End bir birlerinden farklı olup olmadığını döner.
			1) Start küçük End büyük olduğunda Exception dönemez.
			2) Start büyük End küçük olduğunda Exception döner.
		**/
		TextRange.assertProperRange(5, 50, "Ranges");
		// TextRange.assertProperRange(52, 50, "Ranges");
		System.out.println("TextRange.assertProperRange(int, int, Object): void");
		
		/**
			contains(ISegment): boolean --> Verilen ISegment oluşturulan TextRange nenesi içerisinde geçiyor mu
		**/
		System.out.println("txt.contains(ISegment): boolean -> " + txt.contains(new TextRange(6, 40)));
		System.out.println("txt.contains(ISegment): boolean -> " + txt.contains(new TextRange(2, 65)));
		
		/**
			contains(TextRange): boolean --> Verilen TextRange oluşturulan TextRange nenesi içerisinde geçiyor mu
		**/
		System.out.println("txt.contains(TextRange): boolean -> " + txt.contains(new TextRange(6, 40)));
		System.out.println("txt.contains(TextRange): boolean -> " + txt.contains(new TextRange(2, 65)));
		
		/**
			contains(int): boolean --> Verilen offset oluşturulan TextRange nenesi içerisinde geçiyor mu
		**/
		System.out.println("txt.contains(int): boolean -> " + txt.contains(25));
		System.out.println("txt.contains(int): boolean -> " + txt.contains(65));
		
		/**
			containsOffset(int): boolean --> Verilen offset oluşturulan TextRange nenesi içerisinde geçiyor mu
		**/
		System.out.println("txt.containsOffset(int): boolean -> " + txt.containsOffset(25));
		System.out.println("txt.containsOffset(int): boolean -> " + txt.containsOffset(65));
		
		/**
			containsRange(ISegment, ISegment): boolean --> Verilen ISegment oluşturulan TextRange nenesi içerisinde geçiyor mu
		**/
		System.out.println("TextRange.containsRange(ISegment, ISegment): boolean -> " + TextRange.containsRange(new TextRange(5, 50), new TextRange(6, 25)));
		System.out.println("TextRange.containsRange(ISegment, ISegment): boolean -> " + TextRange.containsRange(new TextRange(5, 50), new TextRange(2, 65)));
		
		/**
			containsRange(int, int): boolean --> Verilen Start ve End oluşturulan TextRange nenesi içerisinde geçiyor mu
		**/
		System.out.println("txt.containsRange(int, int): boolean -> " + txt.containsRange(5, 25));
		System.out.println("txt.containsRange(int, int): boolean -> " + txt.containsRange(0, 65));
		
		/**
			create(ISegment): TextRange --> Verilen ISegment ile bir TextRange nesnesi yaratır.
		**/
		System.out.println("TextRange.create(ISegment): TextRange -> " + TextRange.create(new TextRange(5, 50)));
		
		/**
			create(int, int): TextRange --> Verilen Start ve End ile bir TextRange nesnesi yaratır.
		**/
		System.out.println("TextRange.create(int, int): TextRange -> " + TextRange.create(5, 25));
		
		/**
			cutOut(TextRange): TextRange --> Oluşturulan TextRange nesnesiden bir kesit alır TextRange olarak döner.
		**/
		System.out.println("txt.cutOut(TextRange): TextRange -> " + txt.cutOut(new TextRange(20, 40)));
		
		/**
			equals(Object): boolean --> Oluşturulan TextRange nesnesini karşılaştrır.
		**/
		System.out.println("txt.equals(Object): boolean -> " + txt.equals(new TextRange(5, 50)));
		System.out.println("txt.equals(Object): boolean -> " + txt.equals(new TextRange(6, 14)));
		
		/**
			equalsToRange(int, int): boolean --> Oluşturulan Start ve End değerlerini karşılaştrır.
		**/
		System.out.println("txt.equalsToRange(int, int): boolean -> " + txt.equalsToRange(5, 50));
		System.out.println("txt.equalsToRange(int, int): boolean -> " + txt.equalsToRange(6, 14));
		
		/**
			from(int, int): TextRange --> Verilene belirli bir aralığı TextRange nesnesi olarak döner.
		**/
		System.out.println("TextRange.from(int, int): TextRange -> " + TextRange.from(2, 50));
		
		/**
			getEndOffset(): int --> TextRange nesnesini End offsetini döner.
		**/
		System.out.println("txt.getEndOffset(): int -> " + txt.getEndOffset());
		
		/**
			getLength(): int --> TextRange nesnesinin uzunluğunu döner.
		**/
		System.out.println("txt.getLength(): int -> " + txt.getLength());
		
		/**
			getStartOffset(): int --> TextRange nesnesini Start offsetini döner.
		**/
		System.out.println("txt.getStartOffset(): int -> " + txt.getStartOffset());
		
		/**
			grown(int): TextRange --> TextRange nesnesini range aralığını uzata biliyoruz.
		**/
		System.out.println("txt.grown(int): TextRange -> " + txt.grown(0));
		System.out.println("txt.grown(int): TextRange -> " + txt.grown(25));
		
		/**
			hashCode(): int --> TextRange nesnesinin hashCode döner.
		**/
		System.out.println("txt.hashCode(): int -> " + txt.hashCode());
		
		/**
			intersection(TextRange): TextRange --> TextRange nesnesine TextRange nesnesini insert olarak ekler.
		**/
		System.out.println("txt.intersection(TextRange): TextRange -> " + txt.intersection(new TextRange(20, 30)));
		
		/**
			intersects(ISegment): boolean --> ISegment nesnesine insert edilecek range aralıkta mı değil mi
		**/
		System.out.println("txt.intersects(ISegment): boolean -> " + txt.intersects(new TextRange(20, 30)));
		
		/**
			intersects(TextRange): boolean --> TextRange nesnesine insert edilecek range aralıkta mı değil mi
		**/
		System.out.println("txt.intersects(TextRange): boolean -> " + txt.intersects(new TextRange(20, 30)));
		
		/**
			intersects(int, int): boolean --> TextRange nesnesine insert edilecek range aralıkta mı değil mi
		**/
		System.out.println("txt.intersects(int, int): boolean -> " + txt.intersects(20, 30));
		System.out.println("txt.intersects(int, int): boolean -> " + txt.intersects(60, 30));
		
		/**
			intersectsStrict(TextRange): boolean --> Verilen TextRange nesnesine insert edilecek range aralıkta mı değil mi
		**/
		System.out.println("txt.intersectsStrict(TextRange): boolean -> " + txt.intersectsStrict(new TextRange(20, 30)));
		
		/**
			intersectsStrict(int, int): boolean --> Verilen Start ve End insert edilecek range aralıkta mı değil mi
		**/
		System.out.println("txt.intersectsStrict(int, int): boolean -> " + txt.intersectsStrict(5, 50));
		
		/**
			isEmpty(): boolean --> TextRange nesnesi boş olup olmasığını döner.
		**/
		System.out.println("txt.isEmpty(): boolean -> " + txt.isEmpty());
		
		/**
			isProperRange(int, int): boolean --> Verilen Start ve End bir birlerinden farklı olup olmadığını döner.
			1) Start küçük End büyük olduğunda true döner.
			2) Start büyük End küçük olduğunda false döner.
		**/
		System.out.println("TextRange.isProperRange(int, int): boolean -> " + TextRange.isProperRange(5, 50));
		System.out.println("TextRange.isProperRange(int, int): boolean -> " + TextRange.isProperRange(52, 50));
		
		/**
			replace(String, String): String --> Oluşturulan TextRange nesnesini verilen String değerinde belirlenen değer ile değişir.
		**/
		System.out.println("txt.replace(String, String): String -> " + new TextRange(3, 9).replace("Hello World!", "-"));
		
		/**
			shiftLeft(int): TextRange --> Oluşturulan TextRange nesnesini belirlenen değer kadar sola shift yapar.
		**/
		System.out.println("txt.shiftLeft(int): TextRange -> " + txt.shiftLeft(0));
		System.out.println("txt.shiftLeft(int): TextRange -> " + txt.shiftLeft(4));
		
		/**
			shiftRight(int): TextRange --> Oluşturulan TextRange nesnesini belirlenen değer kadar sağa shift yapar.
		**/
		System.out.println("txt.shiftRight(int): TextRange -> " + txt.shiftRight(0));
		System.out.println("txt.shiftRight(int): TextRange -> " + txt.shiftRight(4));
		
		/**
			subSequence(CharSequence): CharSequence --> Oluşturulan TextRange nesnesini verilen String değerini keser.
		**/
		System.out.println("txt.subSequence(CharSequence): CharSequence -> " + new TextRange(3, 9).subSequence("Hello World!"));
		
		/**
			substring(String): String --> Oluşturulan TextRange nesnesini verilen String değerini keser.
		**/
		System.out.println("txt.substring(String): String -> " + new TextRange(3, 9).substring("Hello World!"));
		
		/**
			toString(): String --> Oluşturulan TextRange nesnesini String olarak döner.
		**/
		System.out.println("txt.toString(): String -> " + txt);
		System.out.println("txt.toString(): String -> " + txt.toString());
		
		/**
			union(): String --> Verilen TextRange aralığının dışında ise o TextRange değerini döner.
		**/
		System.out.println("txt.union(TextRange): TextRange -> " + txt.union(new TextRange(1, 60)));
	}
}