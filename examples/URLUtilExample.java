package com.emrecellebi;

import com.emrecellebi.util.URLUtil;

public class URLUtilExample
{
	public static void main(String[] args)
	{
		String uri = "http://78.135.80.229:32764/orders/n11/update?companyCode=Fr-5500175&number=205818585916";
		
		/// Bir URL de (mailto: :// www.) geçiyor ise true döner.
		System.out.println("URLUtil.canContainUrl(String): boolean -> " + URLUtil.canContainUrl(uri));
		
		/// Bir URL de (://) geçiyor ise true döner.
		System.out.println("URLUtil.containsScheme(String): boolean -> " + URLUtil.containsScheme(uri));
		
		/// Bir URL de %20 gibi değerleri decode eder.
		System.out.println("URLUtil.decode(String): String -> " + URLUtil.decode(uri));
		
		/// Bir URL encode eder.
		System.out.println("URLUtil.encodeURIComponent(String): String -> " + URLUtil.encodeURIComponent(uri));
		
		/// Bir html veya her hangi data bilgisindeki URL bulur ve TextRange olarak geri döner.
		System.out.println("URLUtil.findUrl(CharSequence, int, int): TextRange -> " + URLUtil.findUrl(uri, 0, uri.length()));
		
		/// Bir hrml datası içinde data,base64,charset gibi kelimelirin geçtiği bir yer olduğunda base64 değerini alıp decode eder ve byte array olarak döner.
		byte[] bytes = new byte[0];
		System.out.print("URLUtil.getBytesFromDataUri(String): byte[] -> ");
		// bytes = URLUtil.getBytesFromDataUri(uri);
		for(int i = 0; i < bytes.length; i++)
			System.out.print(bytes[i]);
		System.out.println();
		
		/// Bir jar file içinde ki class yolu ile bir URL path döner.
		URL url = null;
		try{url = URLUtil.getJarEntryURL(new File("../utils.jar"), "com/emrecellebi/util/URLUtil");}catch(Exception e){}
		System.out.println("URLUtil.getJarEntryURL(File, String): URL -> " + url);
		
		/// Bir html veya her hangi data içindeki data kelimesi geçiyor ise true değerini döner.
		System.out.println("URLUtil.isDataUri(String): boolean -> " + URLUtil.isDataUri(uri));
		
		/// Bir jar Path URL verilir ve InputStream olarak geri döner.
		// System.out.println("URLUtil.openJarStream(URL): InputStream -> " + URLUtil.openJarStream(url));
		// System.out.println("URLUtil.openResourceStream(URL): InputStream -> " + URLUtil.openResourceStream(url));
		// System.out.println("URLUtil.openStream(URL): InputStream -> " + URLUtil.openStream(url));
		
		/// Verilen bir ssh urli parse eder ve host bilgisini döner.
		String ssh = "git@gitlab.com:emrecellebi/emrecellebi.git";
		System.out.println("URLUtil.parseHostFromSshUrl(String): String -> " + URLUtil.parseHostFromSshUrl(ssh));
		
		/// Resource var mı diye kontrol eder ThreeState enum olarak döner.
		System.out.println("URLUtil.resourceExists(URL): ThreeState -> " + URLUtil.resourceExists(url));
		
		/// Verilen Jar file ve resource kayank yolunu böler iki ayrı değer olarak döner.
		System.out.println("URLUtil.splitJarUrl(String): Pair<String, String> -> " + URLUtil.splitJarUrl(url.toString()));
		
		/// Encode edilmiş bir URL değerini decode eder.
		System.out.println("URLUtil.unescapePercentSequences(CharSequence, int, int): CharSequence -> " + URLUtil.unescapePercentSequences("%20%21%22", 0, 9));
		System.out.println("URLUtil.unescapePercentSequences(CharSequence): CharSequence -> " + URLUtil.unescapePercentSequences("%20%21%22"));
		
		/// URL değerini File olarak geri döner.
		System.out.println("URLUtil.urlToFile(URL): File -> " + URLUtil.urlToFile(url));
	}
}