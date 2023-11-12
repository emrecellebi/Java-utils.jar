package com.emrecellebi;

import com.emrecellebi.util.text.CharSequenceReader;

public class CharSequenceReaderExamples
{
	public static void main(String[] args)
	{
		/// CharSequenceReader sınıfı java.io.Reader abstruct sınıfını extends eder.
		
		/**
			CharSequenceReader(CharSequence) --> CharSequenceReader nesnesi yaratır.
			1) String data bilgisi
		**/
		CharSequenceReader rd = new CharSequenceReader("Hello World!");
		
		/**
			read(char[], int, int): int --> Nesne içerisindeki veriyi okuyup buffer üzerine kayıt eder. Başarı durumunda okunan uzunluk bilgisini döner.
			1) char buffer array data bilgisi
			2) Başlangıc offset bilgisi
			3) Uzunluk bilgisi
		**/
		char[] buffer = new char[12];
		System.out.println("rd.read(char[], int, int): int -> " + rd.read(buffer, 0, buffer.length));
		for(int i = 0; i < buffer.length; i++)
			System.out.print(buffer[i]);
		
		/**
			read(): int --> Nesne içerisindeki veriyi int tipinde döner.
		**/
		int ch = 0;
		while((ch = rd.read()) != -1)
			System.out.print((char)ch);
	}
}