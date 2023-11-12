package com.emrecellebi;

public class FileUtilRtExample
{
	public static void main(String[] args)
	{
		/**
			org.fs.useChannels = Boolean 					--> File Channel kullanmak için true gönderilir. Büyük küçük duyarlı.
			org.max.content.load.large.preview.size = Long 	--> Dosya boyutu bilgisi gönderilir. # FileUtilRt.getLargeFilePreviewSize()
			org.max.content.load.filesize = Long 			--> Dosya boyutu bilgisi gönderilir. # FileUtilRt.getUserContentLoadLimit()
			org.max.emrecellebi.filesize = Long 			--> Dosya boyutu bilgisi gönderilir. # FileUtilRt.getUserFileSizeLimit()
		**/
		
		/**
			calcCanonicalTempPath(): String --> System Temp folder yolunu döner.
		**/
		System.out.println("FileUtilRt.calcCanonicalTempPath(): String -> " + FileUtilRt.calcCanonicalTempPath());
		
		/**
			calcName(File, String, String, int): File --> Dosya adı generate eder.
			1) Directory Path
			2) File Name
			3) File Extensions
			4) Kaçıncı dosya sayısı
		**/
		File f = null;
		try{f = FileUtilRt.calcName(new File("./tmp"), "fsociety", ".dat", 1);}catch(IOException e){};
		System.out.println("FileUtilRt.calcName(File, String, String, int): File -> " + f);
		
		
		/**
			copy(File, File): void --> Dosya kopyalama yapar.
			1) Source File Name
			2) Destenation File Name
		**/
		// try{FileUtilRt.copy(new File("../utils.jar"), new File("./tmp/copied_utils.zip"));}catch(Exception e){e.printStackTrace();}
		
		/**
			copy(InputStream, OutputStream): void --> Dosya kopyalama yapar.
			1) Source File Name
			2) Destenation File Name
		**/
		// try{FileUtilRt.copy(new FileInputStream("../utils.jar"), new FileOutputStream("./copied_utils.zip"));}catch(Exception e){e.printStackTrace();}
		
		/**
			createDirectory(File): boolean --> Var olamyan bir dizinleri oluşturur.
			1) Directory Path
		**/
		// System.out.println("FileUtilRt.createDirectory(File): boolean -> " + FileUtilRt.createDirectory(new File("./tmp")));
		
		/**
			createIfNotExists(File): boolean --> Dosya olsun yada olmasın true döner. Dosya yoksa oluşturulur varsa oluşturulmaz.
			1) File Name
		**/
		// System.out.println("FileUtilRt.createIfNotExists(File): boolean -> " + FileUtilRt.createIfNotExists(new File("./tmp/fsociety.dat")));
		
		/**
			createParentDirs(File): boolean --> Var olamyan bir dizinleri oluşturur. Verilen pathin bir üst dizine kadar oluşrur.
			1) Directory Path
		**/
		// System.out.println("FileUtilRt.createParentDirs(File): boolean -> " + FileUtilRt.createParentDirs(new File("./tmp/xdata/")));
		
		/**
			createTempDirectory(File, String, String): File --> Klasör adı generate eder.
			1) Directory Path
			2) Prefix Name
			3) Suffix Name
		**/
		// try{f = FileUtilRt.createTempDirectory(new File("./tmp"), "fsociety", ".dat");}catch(IOException e){}
		// System.out.println("FileUtilRt.createTempDirectory(File, String, String): File -> " + f);
		
		/**
			createTempDirectory(File, String, String, boolean): File --> Klasör adı generate eder.
			1) Directory Path
			2) Prefix Name
			3) Suffix Name
			4) Kapanırken Silinsin mi
		**/
		// try{f = FileUtilRt.createTempDirectory(new File("./tmp"), "fsociety", ".dat", true);}catch(IOException e){}
		// System.out.println("FileUtilRt.createTempDirectory(File, String, String, boolean): File -> " + f);
		
		/**
			createTempDirectory(String, String): File --> Klasör adı generate eder.
			1) Prefix Name
			2) Suffix Name
		**/
		// try{f = FileUtilRt.createTempDirectory("fsociety", ".dat");}catch(IOException e){}
		// System.out.println("FileUtilRt.createTempDirectory(String, String): File -> " + f);
		
		/**
			createTempDirectory(String, String, boolean): File --> Klasör adı generate eder.
			1) Prefix Name
			2) Suffix Name
			3) Kapanırken Silinsin mi
		**/
		// try{f = FileUtilRt.createTempDirectory("fsociety", ".dat", true);}catch(IOException e){}
		// System.out.println("FileUtilRt.createTempDirectory(String, String, boolean): File -> " + f);
		
		/**
			createTempFile(File, String, String): File --> Dosya adı generate eder.
			1) Directory Path
			2) Prefix Name
			3) Suffix Name
		**/
		// try{f = FileUtilRt.createTempFile(new File("./tmp"), "fsociety", ".dat");}catch(IOException e){}
		// System.out.println("FileUtilRt.createTempFile(File, String, String): File -> " + f);
		
		/**
			createTempFile(File, String, String, boolean): File --> Dosya adı generate eder.
			1) Directory Path
			2) Prefix Name
			3) Suffix Name
			4) Dosya Oluşturulsun mu
		**/
		// try{f = FileUtilRt.createTempFile(new File("./tmp"), "fsociety", ".dat", true);}catch(IOException e){}
		// System.out.println("FileUtilRt.createTempFile(File, String, String, boolean): File -> " + f);
		
		/**
			createTempFile(File, String, String, boolean, boolean): File --> Dosya adı generate eder.
			1) Directory Path
			2) Prefix Name
			3) Suffix Name
			4) Dosya Oluşturulsun mu
			5) Kapanırken Silinsin mi
		**/
		// try{f = FileUtilRt.createTempFile(new File("./tmp"), "fsociety", ".dat", true, true);}catch(IOException e){}
		// System.out.println("FileUtilRt.createTempFile(File, String, String, boolean, boolean): File -> " + f);
		
		/**
			createTempFile(String, String): File --> Dosya adı generate eder.
			1) Prefix Name
			2) Suffix Name
		**/
		// try{f = FileUtilRt.createTempFile("fsociety", ".dat");}catch(IOException e){}
		// System.out.println("FileUtilRt.createTempFile(String, String): File -> " + f);
		
		/**
			createTempFile(String, String, boolean): File --> Dosya adı generate eder.
			1) Prefix Name
			2) Suffix Name
			3) Kapanırken Silinsin mi
		**/
		// try{f = FileUtilRt.createTempFile("fsociety", ".dat", true);}catch(IOException e){}
		// System.out.println("FileUtilRt.createTempFile(String, String, boolean): File -> " + f);
		
		/**
			delete(File): boolean --> Dosya siler.
			1) File Name
		**/
		// System.out.println("FileUtilRt.delete(File): boolean -> " + FileUtilRt.delete(new File("./tmp")));
		
		/**
			deleteFile(File): boolean --> Dosya siler.
			1) File Name
		**/
		// System.out.println("FileUtilRt.deleteFile(File): boolean -> " + FileUtilRt.deleteFile(new File("copied_utils.zip")));
		
		/**
			deleteRecursively(File): boolean --> Dosya siler.
			1) File Name
		**/
		// System.out.println("FileUtilRt.deleteRecursively(File): boolean -> " + FileUtilRt.deleteRecursively(new File("./tmp/")));
		
		/**
			doCreateTempFile(File, String, String, boolean): File --> Dosya oluşturulur. Eğer dosya var ise bir artırarak oluşturur.
			1) Directory Path
			2) Prefix Name
			3) Suffix Name
			4) false ise File true ise Directory
		**/
		try{f = FileUtilRt.doCreateTempFile(new File("./tmp"), "fsociety", ".dat", false);}catch(IOException e){}
		System.out.println("FileUtilRt.doCreateTempFile(File, String, String, boolean): File -> " + f);
		
		/**
			ensureCanCreateFile(File): boolean -->
			1) File Name
		**/
		System.out.println("FileUtilRt.ensureCanCreateFile(File): boolean -> " + FileUtilRt.ensureCanCreateFile(new File("./text.txt")));
		
		/**
			ensureEnds(String, char): String --> Verilen karakter sondan eşleşmez ise birleştirip döner.
			1) String data
			2) Eşleşecek karakter
		**/
		System.out.println("FileUtilRt.ensureEnds(String, char): String -> " + FileUtilRt.ensureEnds("fsociet", 'y'));
		
		/**
			extensionEquals(String, String): boolean --> Uzantı eşitliğini kontrol eder.
			1) String File Name
			2) Uzantı
		**/
		System.out.println("FileUtilRt.extensionEquals(String, String): boolean -> " + FileUtilRt.extensionEquals("./tmp/fsociety.dat", "dat"));
		
		/**
			fileNameEquals(CharSequence, CharSequence): boolean --> File name eşitliğini kontrol eder.
			1) String File Name
			2) String File Name
		**/
		System.out.println("FileUtilRt.fileNameEquals(CharSequence, CharSequence): boolean -> " + FileUtilRt.fileNameEquals("fsociety", "fsociety"));
		
		/**
			fileNameEquals(File, String): boolean --> File name eşitliğini kontrol eder.
			1) File Name
			2) String File Name
		**/
		System.out.println("FileUtilRt.fileNameEquals(File, String): boolean -> " + FileUtilRt.fileNameEquals(new File("fsociety.dat"), "fsociety.dat"));
		
		/**
			fileToUri(File): URI --> Dosyayı url formatında döner.
			1) File Name
		**/
		System.out.println("FileUtilRt.fileToUri(File): URI -> " + FileUtilRt.fileToUri(new File("fsociety.dat")));
		
		/**
			filesEqual(File, File): boolean --> Dosyaları eşleştrir.
			1) File Name
			2) File Name
		**/
		System.out.println("FileUtilRt.filesEqual(File, File): boolean -> " + FileUtilRt.filesEqual(new File("fsociety.dat"), new File("fsociety.dat")));
		
		/**
			generateRandomTemporaryPath(): File --> Generate UUID olarak path üretir.
		**/
		try{f = FileUtilRt.generateRandomTemporaryPath();}catch(IOException e){}
		System.out.println("FileUtilRt.generateRandomTemporaryPath(): File -> " + f);
		
		/**
			getExtension(CharSequence, String): CharSequence --> Dosyanın uzantısını getirir.
			1) String File Name
			2) Default uzantı
		**/
		System.out.println("FileUtilRt.getExtension(CharSequence, String): CharSequence -> " + FileUtilRt.getExtension("fsociety.dat", "fs"));
		System.out.println("FileUtilRt.getExtension(CharSequence, String): CharSequence -> " + FileUtilRt.getExtension("fsociety", "fs"));
		
		/**
			getExtension(CharSequence): CharSequence --> Dosyanın uzantısını getirir.
			1) String File Name
		**/
		System.out.println("FileUtilRt.getExtension(CharSequence): CharSequence -> " + FileUtilRt.getExtension("fsociety.dat"));
		System.out.println("FileUtilRt.getExtension(CharSequence): CharSequence -> " + FileUtilRt.getExtension("fsociety"));
		
		/**
			getExtension(String): String --> Dosyanın uzantısını getirir.
			1) String File Name
		**/
		System.out.println("FileUtilRt.getExtension(String): String -> " + FileUtilRt.getExtension("fsociety.dat"));
		System.out.println("FileUtilRt.getExtension(String): String -> " + FileUtilRt.getExtension("fsociety"));
		
		/**
			getLargeFilePreviewSize(): int --> Büyük dosya tanımlı dosya boyutunu döner. Veya property ile belirlene bilir.
		**/
		System.out.println("FileUtilRt.getLargeFilePreviewSize(): int -> " + FileUtilRt.getLargeFilePreviewSize());
		
		/**
			getNameWithoutExtension(CharSequence): CharSequence --> Uzantısı olmadan dosya adını döner.
			1) String File Name
		**/
		System.out.println("FileUtilRt.getNameWithoutExtension(CharSequence): CharSequence -> " + FileUtilRt.getNameWithoutExtension("fsociety.dat"));
		
		/**
			getNameWithoutExtension(String): String --> Uzantısı olmadan dosya adını döner.
			1) String File Name
		**/
		System.out.println("FileUtilRt.getNameWithoutExtension(String): String -> " + FileUtilRt.getNameWithoutExtension("fsociety.dat"));
		
		/**
			getParentFile(File): File --> Verilen path'in bir üstünü döner.
			1) File Path
		**/
		System.out.println("FileUtilRt.getParentFile(File): File -> " + FileUtilRt.getParentFile(new File("D:\\Cizimler\\Java\\MyProject\\utils\\src\\")));
		
		/**
			getRelativePath(File, File): String
			1) Base Path
			2) File Path
		**/
		System.out.println("FileUtilRt.getRelativePath(File, File): String -> " + FileUtilRt.getRelativePath(new File("D:\\Cizimler\\Java\\MyProject\\utils\\src\\"), new File("utils.jar")));
		
		/**
			getRelativePath(String, String, char): String
			1) Base Path
			2) File Path
			3) Sperator
		**/
		System.out.println("FileUtilRt.getRelativePath(String, String, char): String -> " + FileUtilRt.getRelativePath("D:\\Cizimler\\Java\\MyProject\\utils\\src\\", "D:\\Cizimler\\Java\\MyProject\\utils\\src\\tmp", '\\'));
		
		/**
			getRelativePath(String, String, char, boolean): String
			1) Base Path
			2) File Path
			3) Sperator
			4) Case Sensitive
		**/
		System.out.println("FileUtilRt.getRelativePath(String, String, char, boolean): String -> " + FileUtilRt.getRelativePath("D:\\Cizimler\\Java\\MyProject\\utils\\src\\", "D:\\Cizimler\\Java\\MyProject\\utils\\src\\tmp", '\\', false));
		
		/**
			getTempDirectory(): String --> Temp Directory döner.
		**/
		// FileUtilRt.resetCanonicalTempPathCache("./");
		System.out.println("FileUtilRt.getTempDirectory(): String -> " + FileUtilRt.getTempDirectory());
		
		/**
			getThreadLocalBuffer(): byte[] --> Local Buffer döner.
		**/
		System.out.print("FileUtilRt.getThreadLocalBuffer(): byte[] -> ");
		byte[] bytes = FileUtilRt.getThreadLocalBuffer();
		// for(int i = 0; i < bytes.length; i++)
			// System.out.print(bytes[i]);
		System.out.println();
		
		/**
			getUserContentLoadLimit(): int --> Büyük tanımlı dosya boyutunu döner. Veya property ile belirlene bilir.
		**/
		System.out.println("FileUtilRt.getUserContentLoadLimit(): int -> " + FileUtilRt.getUserContentLoadLimit());
		
		/**
			getUserFileSizeLimit(): int --> Büyük tanımlı dosya boyutunu döner. Veya property ile belirlene bilir.
		**/
		System.out.println("FileUtilRt.getUserFileSizeLimit(): int -> " + FileUtilRt.getUserFileSizeLimit());
		
		// System.out.println("FileUtilRt.isFilePathAcceptable(File, FileFilter): boolean -> " + FileUtilRt.isFilePathAcceptable());
		
		/**
			isJarOrZip(File): boolean --> Dosyanın zip veya jar olup olmadığı kontrol eder.
			1) File Name
		**/
		System.out.println("FileUtilRt.isJarOrZip(File): boolean -> " + FileUtilRt.isJarOrZip(new File("../utils.jar")));
		
		/**
			isJarOrZip(File, boolean): boolean --> Dosyanın zip veya jar olup olmadığı kontrol eder.
			1) File Name
			2) Directory mi Değil mi
		**/
		System.out.println("FileUtilRt.isJarOrZip(File, boolean): boolean -> " + FileUtilRt.isJarOrZip(new File("../utils.jar"), true));
		
		/**
			isTooLarge(long): boolean --> 
		**/
		System.out.println("FileUtilRt.isTooLarge(long): boolean -> " + FileUtilRt.isTooLarge(1024));
		
		/**
			loadBytes(InputStream): byte[] --> Verilen InputStream byte array olarak geri döner.
			1) InputStream alır.
		**/
		System.out.print("FileUtilRt.loadBytes(InputStream): byte[] -> ");
		try{bytes = FileUtilRt.loadBytes(new FileInputStream("../utils.jar"));}catch(IOException e){}
		// for(int i = 0; i < bytes.length; i++)
			// System.out.print(bytes[i]);
		System.out.println();
		
		/**
			loadBytes(InputStream, int): byte[] --> Verilen InputStream byte array olarak geri döner.
			1) InputStream alır.
			2) Uzunluk bilgisi
		**/
		System.out.print("FileUtilRt.loadBytes(InputStream, int): byte[] -> ");
		try{bytes = FileUtilRt.loadBytes(new FileInputStream("../utils.jar"), 64);}catch(IOException e){}
		// for(int i = 0; i < bytes.length; i++)
			// System.out.print(bytes[i]);
		System.out.println();
		
		/**
			loadFile(File): String --> 
			1) File bilgisi
		**/
		String str = "";
		try{str = FileUtilRt.loadFile(new File("01.txt"));}catch(IOException e){}
		System.out.println("FileUtilRt.loadFile(File): String -> " + str);
		
		/**
			loadFile(File, String): String --> 
			1) File bilgisi
			2) Encoding bilgisi
		**/
		try{str = FileUtilRt.loadFile(new File("01.txt"), "UTF-8");}catch(IOException e){}
		System.out.println("FileUtilRt.loadFile(File, String): String -> " + str);
		
		/**
			loadFile(File, String, boolean): String --> Bir dosyayı load eder.
			1) File bilgisi
			2) Encoding bilgisi
			3) Line Sperator Convert
		**/
		try{str = FileUtilRt.loadFile(new File("01.txt"), "UTF-8", true);}catch(IOException e){}
		System.out.println("FileUtilRt.loadFile(File, String, boolean): String -> " + str);
		
		/**
			loadFile(File, boolean): String --> Bir dosyayı load eder.
			1) File bilgisi
			2) Line Sperator Convert
		**/
		try{str = FileUtilRt.loadFile(new File("01.txt"), true);}catch(IOException e){}
		System.out.println("FileUtilRt.loadFile(File, boolean): String -> " + str);
		
		/**
			loadFileText(File): char[] --> Bir dosyayı load eder. char array olarak
			1) File bilgisi
		**/
		char[] ch = new char[0];
		System.out.print("FileUtilRt.loadFileText(File): char[] -> ");
		try{ch = FileUtilRt.loadFileText(new File("01.txt"));}catch(IOException e){}
		for(int i = 0; i < ch.length; i++)
			System.out.print(ch[i]);
		System.out.println();
		
		/**
			loadFileText(File, Charset): char[] --> Bir dosyayı load eder. char array olarak
			1) File bilgisi
			2) Charset bilgisi
		**/
		System.out.print("FileUtilRt.loadFileText(File, Charset): char[] -> ");
		try{ch = FileUtilRt.loadFileText(new File("01.txt"), Charset.forName("UTF-8"));}catch(IOException e){}
		for(int i = 0; i < ch.length; i++)
			System.out.print(ch[i]);
		System.out.println();
		
		/**
			loadFileText(File, String): char[] --> Bir dosyayı load eder. char array olarak
			1) File bilgisi
			2) String olarak Charset bilgisi
		**/
		System.out.print("FileUtilRt.loadFileText(File, String): char[] -> ");
		try{ch = FileUtilRt.loadFileText(new File("01.txt"), "UTF-8");}catch(IOException e){}
		for(int i = 0; i < ch.length; i++)
			System.out.print(ch[i]);
		System.out.println();
		
		/**
			loadLines(BufferedReader): List<String> --> Bir dosyayı load eder.
			1) BufferedReader alır
		**/
		List<String> lines = new ArrayList<String>();
		try{lines = FileUtilRt.loadLines(new BufferedReader(new InputStreamReader(new FileInputStream("01.txt"), "UTF-8")));}catch(IOException e){}
		System.out.println("FileUtilRt.loadLines(BufferedReader): List<String> -> " + lines);
		
		/**
			loadLines(File): List<String> --> Bir dosyayı load eder.
			1) File Name
		**/
		try{lines = FileUtilRt.loadLines(new File("01.txt"));}catch(IOException e){}
		System.out.println("FileUtilRt.loadLines(File): List<String> -> " + lines);
		
		/**
			loadLines(File, String): List<String> --> Bir dosyayı load eder.
			1) File Name
			2) String Charset
		**/
		try{lines = FileUtilRt.loadLines(new File("01.txt"), "UTF-8");}catch(IOException e){}
		System.out.println("FileUtilRt.loadLines(File, String): List<String> -> " + lines);
		
		/**
			loadLines(String): List<String> --> Bir dosyayı load eder.
			1) String File Name
		**/
		try{lines = FileUtilRt.loadLines("01.txt");}catch(IOException e){}
		System.out.println("FileUtilRt.loadLines(String): List<String> -> " + lines);
		
		/**
			loadLines(String, String): List<String> --> Bir dosyayı load eder.
			1) String File Name
			2) String Charset
		**/
		try{lines = FileUtilRt.loadLines("01.txt", "UTF-8");}catch(IOException e){}
		System.out.println("FileUtilRt.loadLines(String, String): List<String> -> " + lines);
		
		/**
			loadText(Reader, int): char[] --> Bir dosyayı load eder. char array olarak
			1) Reader bilgisi
			2) Uzunluk bilgisi
		**/
		System.out.print("FileUtilRt.loadText(Reader, int): char[] -> ");
		try{ch = FileUtilRt.loadText(new InputStreamReader(new FileInputStream("01.txt"), "UTF-8"), 12);}catch(IOException e){}
		for(int i = 0; i < ch.length; i++)
			System.out.print(ch[i]);
		System.out.println();
		
		/**
			normalizeFile(File): File --> 
			1) File bilgisi
		**/
		try{f = FileUtilRt.normalizeFile(new File("01.txt"));}catch(IOException e){}
		System.out.println("FileUtilRt.normalizeFile(File): File -> " + f);
		
		/**
			parseKilobyteProperty(String, int): int --> 
			1) Propertiy bilgisi
			2) Default değer.
		**/
		System.out.println("FileUtilRt.parseKilobyteProperty(String, int): int -> " + FileUtilRt.parseKilobyteProperty("org.emrecellebi.kb", 1024));
		
		/**
			pathHashCode(String): int --> Yolun hash codunu döner.
			1) Path bilgisi
		**/
		System.out.println("FileUtilRt.pathHashCode(String): int -> " + FileUtilRt.pathHashCode("./01.txt"));
		
		/**
			pathsEqual(String, String): boolean --> iki path karşılaştırır.
			1) Path bilgisi
			2) Path bilgisi
		**/
		System.out.println("FileUtilRt.pathsEqual(String, String): boolean -> " + FileUtilRt.pathsEqual("./01.txt", "./01.txt"));
		
		/**
			setExecutableAttribute(String, boolean): void
			1) String File bilgisi
			2) Yürütülebilirlik bilgisi.
		**/
		try{FileUtilRt.setExecutableAttribute("01.txt", true);}catch(IOException e){}
		System.out.println("FileUtilRt.setExecutableAttribute(String, boolean): void -> ");
		
		/**
			splitPath(String, char): List<String> --> Verilen Path bilgisini verilen karater ile böler.
			1) String Path bilgisi
			2) Bölünecek karakter bilgisi
		**/
		System.out.println("FileUtilRt.splitPath(String, char): boolean -> " + FileUtilRt.splitPath("D:/Cizimler/Java/MyProject/utils/src/utils.jar", '/'));
		
		/**
			toCanonicalPath(String, char, boolean): String --> 
			1) String Path bilgisi
			2) Bölünecek karakter bilgisi
			3) 
		**/
		System.out.println("FileUtilRt.toCanonicalPath(String, char, boolean): String -> " + FileUtilRt.toCanonicalPath("D:/Cizimler/Java/MyProject/utils/src/utils.jar", '/', true));
		
		System.out.println("FileUtilRt.toSystemDependentName(String): String -> " + FileUtilRt.toSystemDependentName("D:/Cizimler/Java/MyProject/utils/src/utils.jar"));
		System.out.println("FileUtilRt.toSystemDependentName(String, char): String -> " + FileUtilRt.toSystemDependentName("D:/Cizimler/Java/MyProject/utils/src/utils.jar", '/'));
		System.out.println("FileUtilRt.toSystemIndependentName(String): String -> " + FileUtilRt.toSystemIndependentName("D:/Cizimler/Java/MyProject/utils/src/utils.jar"));
		
	}
}