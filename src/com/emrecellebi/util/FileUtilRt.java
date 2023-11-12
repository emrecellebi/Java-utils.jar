package com.emrecellebi.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.emrecellebi.NullAppendable;
import com.emrecellebi.logging.LoggerRt;

public class FileUtilRt
{
	private static final Random RANDOM = new Random();
	private static final boolean USE_FILE_CHANNELS = "true".equalsIgnoreCase(System.getProperty("org.fs.useChannels"));
	private static final int DEFAULT_INTELLISENSE_LIMIT = 2560000;
	private static final int KILOBYTE = 1024;
	public static final int LARGE_FOR_CONTENT_LOADING = Math.max(20971520, Math.max(getUserFileSizeLimit(), getUserContentLoadLimit()));
	public static final int LARGE_FILE_PREVIEW_SIZE = Math.min(getLargeFilePreviewSize(), LARGE_FOR_CONTENT_LOADING);
	private static final int MAX_FILE_IO_ATTEMPTS = 10;
	public static final int MEGABYTE = 1048576;
	public static final int THREAD_LOCAL_BUFFER_LENGTH = 20480;
	private static String ourCanonicalTempPathCache;
	
	public static final FileFilter ALL_DIRECTORIES = new FileFilter()
	{
		public boolean accept(File file)
		{
			return file.isDirectory();
		}
	};
	
	public static final FileFilter ALL_FILES = new FileFilter()
	{
		public boolean accept(File file)
		{
			return true;
		}
	};
	
	protected static final ThreadLocal<byte[]> BUFFER = new ThreadLocal<byte[]>()
	{
		@Override
		protected byte[] initialValue()
		{
			return new byte[20480];
		}
	};
	
	public static String calcCanonicalTempPath()
	{
		File file = new File(System.getProperty("java.io.tmpdir"));
		try
		{
			String canonical = file.getCanonicalPath();
			if(!SystemInfoRt.isWindows || !canonical.contains(" ")) return canonical;
		}catch(IOException e){}
		
		return file.getAbsolutePath();
	}
	
	public static File calcName(File dir, String prefix, String suffix, int i) throws IOException
	{
		prefix = (i == 0) ? prefix : (prefix + i);
		if(prefix.endsWith(".") && suffix.startsWith("."))
			prefix = prefix.substring(0, prefix.length() - 1); 
		String name = prefix + suffix;
		File f = new File(dir, name);
		if(!name.equals(f.getName()))
			throw new IOException("A generated name is malformed: '" + name + "' (" + f + ")");
		return f;
	}
	
	public static void copy(File fromFile, File toFile) throws IOException
	{
		if(!ensureCanCreateFile(toFile))return; 
		FileOutputStream fos = new FileOutputStream(toFile);
		try
		{
			FileInputStream fis = new FileInputStream(fromFile);
			try
			{
				copy(fis, fos);
			}
			finally
			{
				fis.close();
			}
		}
		finally
		{
			fos.close();
		} 
		long timeStamp = fromFile.lastModified();
		if(timeStamp < 0L)
		{
			logger().warn("Invalid timestamp " + timeStamp + " of '" + fromFile + "'");
		}
		else if(!toFile.setLastModified(timeStamp))
		{
			logger().warn("Unable to set timestamp " + timeStamp + " to '" + toFile + "'");
		}
	}
	
	public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException
	{
		if(USE_FILE_CHANNELS && inputStream instanceof FileInputStream && outputStream instanceof FileOutputStream)
		{
			FileChannel fromChannel = ((FileInputStream)inputStream).getChannel();
			try
			{
				FileChannel toChannel = ((FileOutputStream)outputStream).getChannel();
				try
				{
					fromChannel.transferTo(0L, Long.MAX_VALUE, toChannel);
				}finally
				{
					toChannel.close();
				} 
			}
			finally
			{
				fromChannel.close();
			} 
		}
		else
		{
			byte[] buffer = getThreadLocalBuffer();
			while(true)
			{
				int read = inputStream.read(buffer);
				if(read < 0) break; 
				outputStream.write(buffer, 0, read);
			} 
		} 
	}
	
	public static boolean createDirectory(File path)
	{
		return (path.isDirectory() || path.mkdirs());
	}
	
	public static boolean createIfNotExists(File file)
	{
		if(file.exists()) return true; 
		try
		{
			if(!createParentDirs(file)) return false;
			OutputStream s = new FileOutputStream(file);
			s.close();
			return true;
		}
		catch(IOException e)
		{
			logger().info(e);
			return false;
		}
	}
	
	public static boolean createParentDirs(File file)
	{
		File parentPath = file.getParentFile();
		return (parentPath == null || createDirectory(parentPath));
	}
	
	public static File createTempDirectory(File dir, String prefix, String suffix) throws IOException
	{
		return createTempDirectory(dir, prefix, suffix, true);
	}
	
	public static File createTempDirectory(File dir, String prefix, String suffix, boolean deleteOnExit) throws IOException
	{
		File file = doCreateTempFile(dir, prefix, suffix, true);
		if(deleteOnExit) FilesToDeleteHolder.ourFilesToDelete.add(file.getPath()); 
		if(!file.isDirectory()) throw new IOException("Cannot create a directory: " + file); 
		return file;
	}
	
	public static File createTempDirectory(String prefix, String suffix) throws IOException
	{
		return createTempDirectory(prefix, suffix, true);
	}
	
	public static File createTempDirectory(String prefix, String suffix, boolean deleteOnExit) throws IOException
	{
		File dir = new File(getTempDirectory());
		return createTempDirectory(dir, prefix, suffix, deleteOnExit);
	}
	
	public static File createTempFile(File dir, String prefix, String suffix) throws IOException
	{
		return createTempFile(dir, prefix, suffix, true, true);
	}
	
	public static File createTempFile(File dir, String prefix, String suffix, boolean create) throws IOException
	{
		return createTempFile(dir, prefix, suffix, create, true);
	}
	
	public static File createTempFile(File dir, String prefix, String suffix, boolean create, boolean deleteOnExit) throws IOException
	{
		File file = doCreateTempFile(dir, prefix, suffix, false);
		if(deleteOnExit) file.deleteOnExit();
		if(!create && !file.delete() && file.exists())
			throw new IOException("Cannot delete a file: " + file); 
		return file;
	}
	
	public static File createTempFile(String prefix, String suffix) throws IOException
	{
		return createTempFile(prefix, suffix, false);
	}
	
	public static File createTempFile(String prefix, String suffix, boolean deleteOnExit) throws IOException
	{
		File dir = new File(getTempDirectory());
		return createTempFile(dir, prefix, suffix, true, deleteOnExit);
	}
	
	public static boolean delete(File file)
	{
		if(NIOReflect.IS_AVAILABLE)
		try
		{
			deleteRecursivelyNIO(NIOReflect.toPath(file));
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
		catch(Exception e)
		{
			logger().info(e);
			return false;
		}
		return deleteRecursively(file);
	}
	
	public static boolean deleteFile(final File file)
	{
		Boolean result = doIOOperation(new RepeatableIOOperation<Boolean, RuntimeException>()
		{
			@Override
			public Boolean execute(boolean lastAttempt)
			{
				if(file.delete() || !file.exists()) return Boolean.TRUE; 
				if(lastAttempt) return Boolean.FALSE; 
				return null;
			}
		});
		return Boolean.TRUE.equals(result);
	}
	
	public static boolean deleteRecursively(File file)
	{
		File[] files = file.listFiles();
		if(files != null)
			for(File child : files)
				if (!deleteRecursively(child)) return false;
		return deleteFile(file);
	}
	
	static void deleteRecursivelyNIO(Object path) throws IOException
	{
		try
		{
			NIOReflect.deleteRecursively(path);
		}
		catch(InvocationTargetException e)
		{
			Throwable cause = e.getCause();
			if(cause instanceof IOException) throw(IOException)cause; 
		}catch(Exception e){logger().info(e);} 
	}
	
	public static File doCreateTempFile(File dir, String prefix, String suffix, boolean isDirectory) throws IOException
	{
		dir.mkdirs();
		if(prefix.length() < 3) prefix = (prefix + "___").substring(0, 3); 
		if(suffix == null) suffix = ""; 
		prefix = (new File(prefix)).getName();
		int attempts = 0;
		int i = 0;
		int maxFileNumber = 10;
		IOException exception = null;
		while(true)
		{
			File f = null;
			try
			{
				f = calcName(dir, prefix, suffix, i);
				boolean success = isDirectory ? f.mkdir() : f.createNewFile();
				if(success) return normalizeFile(f); 
			}
			catch (IOException e)
			{
				exception = e;
			}
			attempts++;
			int MAX_ATTEMPTS = 100;
			if(attempts > maxFileNumber / 2 || attempts > MAX_ATTEMPTS)
			{
				String[] children = dir.list();
				int size = (children == null) ? 0 : children.length;
				maxFileNumber = Math.max(10, size * 10);
				if(attempts > MAX_ATTEMPTS)
					throw (exception != null) ? exception : new IOException("Unable to create a temporary file " + f + "\nDirectory '" + dir + "' list (" + size + " children): " + 
				Arrays.toString(children)); 
			} 
			i++;
			if(i > 2) i = 2 + RANDOM.nextInt(maxFileNumber); 
		} 
	}
	
	public static <T, E extends Throwable> T doIOOperation(RepeatableIOOperation<T, E> ioTask) throws E
	{
		for(int i = 10; i > 0; i--)
		{
			T result = ioTask.execute((i == 1));
			if(result != null) return result; 
			try
			{
				Thread.sleep(10L);
			}catch(InterruptedException ie){}
		}
		return null;
	}
	
	public static boolean ensureCanCreateFile(File file)
	{
		if(file.exists()) return file.canWrite(); 
		if(!createIfNotExists(file)) return false;
		return delete(file);
	}
	
	public static String ensureEnds(String s, char endsWith)
	{
		return StringUtilRt.endsWithChar(s, endsWith) ? s : (s + endsWith);
	}
	
	public static boolean extensionEquals(String filePath, String extension)
	{
		int extLen = extension.length();
		if(extLen == 0)
		{
			int lastSlash = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));
			return (filePath.indexOf('.', lastSlash + 1) == -1);
		} 
		int extStart = filePath.length() - extLen;
		return (extStart >= 1 && filePath.charAt(extStart - 1) == '.' && filePath.regionMatches(!SystemInfoRt.isFileSystemCaseSensitive, extStart, extension, 0, extLen));
	}
	
	public static boolean fileNameEquals(CharSequence fileName, CharSequence expectedName)
	{
		return StringUtilRt.equal(expectedName, fileName, SystemInfoRt.isFileSystemCaseSensitive);
	}
	
	public static boolean fileNameEquals(File file, String name)
	{
		return fileNameEquals(file.getName(), name);
	}
	
	public static URI fileToUri(File file)
	{
		String path = file.getAbsolutePath();
		if(File.separatorChar != '/') path = path.replace(File.separatorChar, '/'); 
		if(!path.startsWith("/")) path = '/' + path; 
		if (path.startsWith("//")) path = "//" + path; 
		try
		{
			return new URI("file", null, path, null);
		}catch(URISyntaxException e){throw new IllegalArgumentException(path, e);}
	}
	
	public static boolean filesEqual(File file1, File file2)
	{
		return pathsEqual((file1 == null) ? null : file1.getPath(), (file2 == null) ? null : file2.getPath());
	}
	
	public static File generateRandomTemporaryPath() throws IOException
	{
		File file = new File(getTempDirectory(), UUID.randomUUID().toString());
		int i = 0;
		while(file.exists() && i < 5)
		{
			file = new File(getTempDirectory(), UUID.randomUUID().toString());
			i++;
		} 
		if(file.exists())
			throw new IOException("Couldn't generate unique random path."); 
		return normalizeFile(file);
	}
	
	public static CharSequence getExtension(CharSequence fileName)
	{
		return getExtension(fileName, "");
	}
	
	public static CharSequence getExtension(CharSequence fileName, String defaultValue)
	{
		int index = StringUtilRt.lastIndexOf(fileName, '.', 0, fileName.length());
		if(index < 0) return defaultValue; 
		return fileName.subSequence(index + 1, fileName.length());
	}
	
	public static String getExtension(String fileName)
	{
		int index = fileName.lastIndexOf('.');
		if(index < 0) return "";
		return fileName.substring(index + 1);
	}
	
	public static int getLargeFilePreviewSize()
	{
		return parseKilobyteProperty("org.max.content.load.large.preview.size", 2560000);
	}
	
	public static CharSequence getNameWithoutExtension(CharSequence name)
	{
		int i = StringUtilRt.lastIndexOf(name, '.', 0, name.length());
		return (i < 0) ? name : name.subSequence(0, i);
	}
	
	public static String getNameWithoutExtension(String name)
	{
		return getNameWithoutExtension((CharSequence)name).toString();
	}
	
	public static File getParentFile(File file)
	{
		int skipCount = 0;
		File parentFile = file;
		while(true)
		{
			parentFile = parentFile.getParentFile();
			if(parentFile == null) return null; 
			if(".".equals(parentFile.getName())) continue; 
			if("..".equals(parentFile.getName()))
			{
				skipCount++;
				continue;
			}
			if(skipCount > 0)
			{
				skipCount--;
				continue;
			} 
			break;
		} 
		return parentFile;
	}
	
	public static String getRelativePath(File base, File file)
		{
		if(base == null || file == null) return null; 
		if(base.equals(file)) return "."; 
		String filePath = file.getAbsolutePath();
		String basePath = base.getAbsolutePath();
		return getRelativePath(basePath, filePath, File.separatorChar);
	}
	
	public static String getRelativePath(String basePath, String filePath, char separator)
	{
		return getRelativePath(basePath, filePath, separator, SystemInfoRt.isFileSystemCaseSensitive);
	}
	
	public static String getRelativePath(String basePath, String filePath, char separator, boolean caseSensitive)
	{
		basePath = ensureEnds(basePath, separator);
		if(caseSensitive ? basePath.equals(ensureEnds(filePath, separator)) : basePath.equalsIgnoreCase(ensureEnds(filePath, separator))) return "."; 
		int len = 0;
		int lastSeparatorIndex = 0;
		CharComparingStrategy strategy = caseSensitive ? CharComparingStrategy.IDENTITY : CharComparingStrategy.CASE_INSENSITIVE;
		while(len < filePath.length() && len < basePath.length() && strategy.charsEqual(filePath.charAt(len), basePath.charAt(len)))
		{
			if(basePath.charAt(len) == separator) lastSeparatorIndex = len; 
			len++;
		} 
		if(len == 0) return null; 
		StringBuilder relativePath = new StringBuilder();
		for(int i = len; i < basePath.length(); i++)
		{
			if(basePath.charAt(i) == separator)
			{
				relativePath.append("..");
				relativePath.append(separator);
			} 
		} 
		relativePath.append(filePath.substring(lastSeparatorIndex + 1));
		return relativePath.toString();
	}
	
	public static String getTempDirectory()
	{
		if(ourCanonicalTempPathCache == null)
			ourCanonicalTempPathCache = calcCanonicalTempPath(); 
		return ourCanonicalTempPathCache;
	}
	
	public static byte[] getThreadLocalBuffer()
	{
		return BUFFER.get();
	}
	
	public static int getUserContentLoadLimit()
	{
		return parseKilobyteProperty("org.max.content.load.filesize", 20971520);
	}
	
	public static int getUserFileSizeLimit()
	{
		return parseKilobyteProperty("org.max.emrecellebi.filesize", 2560000);
	}
	
	public static boolean isFilePathAcceptable(File root, FileFilter fileFilter)
	{
		if(fileFilter == null) return true; 
		File file = root;
		while(true)
		{
			if(!fileFilter.accept(file)) return false; 
			file = file.getParentFile();
			if(file == null) return true; 
		}
	}
	
	public static boolean isJarOrZip(File file)
	{
		return isJarOrZip(file, true);
	}
	
	public static boolean isJarOrZip(File file, boolean isCheckIsDirectory)
	{
		if(isCheckIsDirectory && file.isDirectory()) return false; 
		String path = file.getPath();
		return (StringUtilRt.endsWithIgnoreCase(path, ".jar") || StringUtilRt.endsWithIgnoreCase(path, ".zip"));
	}
	
	public static boolean isTooLarge(long len)
	{
		return (len > LARGE_FOR_CONTENT_LOADING);
	}
	
	public static byte[] loadBytes(InputStream stream) throws IOException
	{
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		copy(stream, buffer);
		return buffer.toByteArray();
	}
	
	public static byte[] loadBytes(InputStream stream, int length) throws IOException
	{
		if(length == 0) return new byte[0];
		byte[] bytes = new byte[length];
		int count = 0;
		while(count < length)
		{
			int n = stream.read(bytes, count, length - count);
			if(n <= 0) break; 
			count += n;
		}
		return bytes;
	}
	
	public static String loadFile(File file) throws IOException
	{
		return loadFile(file, null, false);
	}
	
	public static String loadFile(File file, String encoding) throws IOException
	{
		return loadFile(file, encoding, false);
	}
	
	public static String loadFile(File file, String encoding, boolean convertLineSeparators) throws IOException
	{
		String s = new String(loadFileText(file, encoding));
		return convertLineSeparators ? StringUtilRt.convertLineSeparators(s) : s;
	}
	
	public static String loadFile(File file, boolean convertLineSeparators) throws IOException
	{
		return loadFile(file, null, convertLineSeparators);
	}
	
	public static char[] loadFileText(File file) throws IOException
	{
		return loadFileText(file, (String)null);
	}
	
	public static char[] loadFileText(File file, Charset encoding) throws IOException
	{
		Reader reader = new InputStreamReader(new FileInputStream(file), encoding);
		try
		{
			char[] arrayOfChar = loadText(reader, (int)file.length());
			reader.close();
			return arrayOfChar;
		}finally{reader.close();} 
	}
	
	public static char[] loadFileText(File file, String encoding) throws IOException
	{
		InputStream stream = new FileInputStream(file);
		Reader reader = (encoding == null) ? new InputStreamReader(stream) : new InputStreamReader(stream, encoding);
		try
		{
			char[] arrayOfChar = loadText(reader, (int)file.length());
			reader.close();
			return arrayOfChar;
		}finally{reader.close();}
	}
	
	public static List<String> loadLines(BufferedReader reader) throws IOException
	{
		List<String> lines = new ArrayList<String>();
		String line;
		while((line = reader.readLine()) != null) lines.add(line); 
		return lines;
	}
	
	public static List<String> loadLines(File file) throws IOException
	{
		return loadLines(file.getPath());
	}
	
	public static List<String> loadLines(File file, String encoding) throws IOException
	{
		return loadLines(file.getPath(), encoding);
	}
	
	public static List<String> loadLines(String path) throws IOException
	{
		return loadLines(path, (String)null);
	}
	
	public static List<String> loadLines(String path, String encoding) throws IOException
	{
		InputStream stream = new FileInputStream(path);
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader((encoding == null) ? new InputStreamReader(stream) : new InputStreamReader(stream, encoding));
		}finally{stream.close();}
		return loadLines(reader);
	}
	
	public static char[] loadText(Reader reader, int length) throws IOException
	{
		char[] chars = new char[length];
		int count = 0;
		while(count < chars.length)
		{
			int n = reader.read(chars, count, chars.length - count);
			if(n <= 0) break; 
			count += n;
		} 
		if(count == chars.length) return chars;
		char[] newChars = new char[count];
		System.arraycopy(chars, 0, newChars, 0, count);
		return newChars;
	}
	
	private static LoggerRt logger()
	{
		return LoggerRt.getInstance("#com.emrecellebi.util.FileUtilRt");
	}
	
	public static File normalizeFile(File temp) throws IOException
	{
		File canonical = temp.getCanonicalFile();
		return (SystemInfoRt.isWindows && canonical.getAbsolutePath().contains(" ")) ? temp.getAbsoluteFile() : canonical;
	}
	
	public static int parseKilobyteProperty(String key, int defaultValue)
	{
		try
		{
			long i = Integer.parseInt(System.getProperty(key, String.valueOf(defaultValue / 1024)));
			if(i < 0L) return Integer.MAX_VALUE; 
			return (int)Math.min(i * 1024L, 2147483647L);
		}catch(NumberFormatException e){return defaultValue;} 
	}
	
	public static int pathHashCode(String path)
	{
		if(path == null || path.isEmpty()) return 0; 
		path = toCanonicalPath(path, File.separatorChar, true);
		return SystemInfoRt.isFileSystemCaseSensitive ? path.hashCode() : StringUtilRt.stringHashCodeInsensitive(path);
	}
	
	public static boolean pathsEqual(String path1, String path2)
	{
		if(path1 == path2) return true; 
		if(path1 == null || path2 == null) return false; 
		path1 = toCanonicalPath(path1, File.separatorChar, true);
		path2 = toCanonicalPath(path2, File.separatorChar, true);
		if(SystemInfoRt.isFileSystemCaseSensitive) return path1.equals(path2); 
		return path1.equalsIgnoreCase(path2);
	}
	
	private static boolean processDots(StringBuilder result, int dots, int start, SymlinkResolver symlinkResolver)
	{
		if(dots == 2)
		{
			int pos = -1;
			if(!StringUtilRt.endsWith(result, "/../") && !"../".contentEquals(result))
			{
				pos = StringUtilRt.lastIndexOf(result, '/', start, result.length() - 1);
				if(pos >= 0) pos++; else if (start > 0) pos = start; else if (result.length() > 0) pos = 0;
			} 
			if(pos >= 0)
			{
				if(symlinkResolver != null && symlinkResolver.isSymlink(result)) return false; 
				result.delete(pos, result.length());
			}else{result.append("../");} 
		}
		else if(dots != 1)
		{
			for(int i = 0; i < dots; i++)
				result.append('.'); 
			result.append('/');
		} 
		return true;
	}
	
	private static int processRoot(String path, Appendable result)
	{
		try
		{
			if(SystemInfoRt.isWindows && path.length() > 1 && path.charAt(0) == '/' && path.charAt(1) == '/')
			{
				result.append("//");
				int hostStart = 2;
				for(; hostStart < path.length() && path.charAt(hostStart) == '/'; hostStart++);
				if(hostStart == path.length()) return hostStart; 
				int hostEnd = path.indexOf('/', hostStart);
				if(hostEnd < 0) hostEnd = path.length(); 
				result.append(path, hostStart, hostEnd);
				result.append('/');
				int shareStart = hostEnd;
				for(; shareStart < path.length() && path.charAt(shareStart) == '/'; shareStart++);
				if(shareStart == path.length()) return shareStart; 
				int shareEnd = path.indexOf('/', shareStart);
				if(shareEnd < 0) shareEnd = path.length(); 
				result.append(path, shareStart, shareEnd);
				result.append('/');
				return shareEnd;
			}
			if(!path.isEmpty() && path.charAt(0) == '/')
			{
				result.append('/');
				return 1;
			} 
			if(path.length() > 2 && path.charAt(1) == ':' && path.charAt(2) == '/')
			{
				result.append(path, 0, 3);
				return 3;
			}
			return 0;
		}catch (IOException e){throw new RuntimeException(e);}
	}
	
	public static void resetCanonicalTempPathCache(String tempPath)
	{
		ourCanonicalTempPathCache = tempPath;
	}
	
	public static void setExecutableAttribute(String path, boolean executableFlag) throws IOException
	{
		try
		{
			File file = new File(path);
			if(!file.setExecutable(executableFlag) && file.canExecute() != executableFlag)
				logger().warn("Can't set executable attribute of '" + path + "' to " + executableFlag); 
		}catch(LinkageError linkageError){}
	}
	
	public static List<String> splitPath(String path, char separatorChar)
	{
		List<String> list = new ArrayList<String>();
		int index = 0;
		int nextSeparator;
		while((nextSeparator = path.indexOf(separatorChar, index)) != -1)
		{
			list.add(path.substring(index, nextSeparator));
			index = nextSeparator + 1;
		}
		list.add(path.substring(index));
		return list;
	}
	
	public static String toCanonicalPath(String path, char separatorChar, boolean removeLastSlash)
	{
		return toCanonicalPath(path, separatorChar, removeLastSlash, null);
	}
	
	protected static String toCanonicalPath(String path, char separatorChar, boolean removeLastSlash, SymlinkResolver resolver)
	{
		if(path == null || path.isEmpty())return path; 
		if(path.charAt(0) == '.')
		{
			if(path.length() == 1) return ""; 
			char c = path.charAt(1);
			if(c == '/' || c == separatorChar) path = path.substring(2); 
		} 
		if(separatorChar != '/') path = path.replace(separatorChar, '/'); 
		int index = -1;
		do
		{
			index = path.indexOf('/', index + 1);
			char next = (index == path.length() - 1) ? Character.MIN_VALUE : path.charAt(index + 1);
			if(next == '.' || next == '/') break; 
		}while (index != -1);
		if(index == -1)
		{
			if(removeLastSlash)
			{
				int j = processRoot(path, NullAppendable.INSTANCE);
				int slashIndex = path.lastIndexOf('/');
				return (slashIndex != -1 && slashIndex > j && slashIndex == path.length() - 1) ? path.substring(0, path.length() - 1) : path;
			} 
			return path;
		} 
		StringBuilder result = new StringBuilder(path.length());
		int start = processRoot(path, result);
		int dots = 0;
		boolean separator = true;
		for(int i = start; i < path.length(); i++)
		{
			char c = path.charAt(i);
			if(c == '/')
			{
				if(!separator)
				{
					if(!processDots(result, dots, start, resolver)) return resolver.resolveSymlinksAndCanonicalize(path, separatorChar, removeLastSlash); 
					dots = 0;
				} 
				separator = true;
			}
			else if(c == '.')
			{
				if(separator || dots > 0) dots++; else result.append('.');
				separator = false;
			}
			else
			{
				while(dots > 0)
				{
					result.append('.');
					dots--;
				}
				result.append(c);
				separator = false;
			} 
		} 
		if(dots > 0 && !processDots(result, dots, start, resolver))
			return resolver.resolveSymlinksAndCanonicalize(path, separatorChar, removeLastSlash); 
		int lastChar = result.length() - 1;
		if(removeLastSlash && lastChar >= 0 && result.charAt(lastChar) == '/' && lastChar > start)
			result.deleteCharAt(lastChar); 
		return result.toString();
	}
	
	public static String toSystemDependentName(String fileName)
	{
		return toSystemDependentName(fileName, File.separatorChar);
	}
	
	public static String toSystemDependentName(String fileName, char separatorChar)
	{
		return fileName.replace('/', separatorChar).replace('\\', separatorChar);
	}
	
	public static String toSystemIndependentName(String fileName)
	{
		return fileName.replace('\\', '/');
	}
	
	/** NIOReflect Class **/
	private static final class NIOReflect
	{
		static final boolean IS_AVAILABLE;
		private static Method ourFilesDeleteIfExistsMethod;
		private static Method ourFilesWalkMethod;
		private static Method ourFileToPathMethod;
		private static Method ourPathToFileMethod;
		private static Method ourAttributesIsOtherMethod;
		private static Object ourDeletionVisitor;
		private static Class<?> ourNoSuchFileExceptionClass;
		private static Class<?> ourAccessDeniedExceptionClass;

		static Object toPath(File file) throws InvocationTargetException, IllegalAccessException
		{
			return ourFileToPathMethod.invoke(file, new Object[0]);
		}

		static void deleteRecursively(Object path) throws InvocationTargetException, IllegalAccessException
		{
			try
			{
				ourFilesWalkMethod.invoke(null, new Object[] {path, ourDeletionVisitor});
			}catch (InvocationTargetException e){if(!ourNoSuchFileExceptionClass.isInstance(e.getCause())) throw e;}
		}

		static
		{
			boolean initSuccess = false;
			try
			{
				Class<?> pathClass = Class.forName("java.nio.file.Path");
				Class<?> visitorClass = Class.forName("java.nio.file.FileVisitor");
				Class<?> filesClass = Class.forName("java.nio.file.Files");
				ourNoSuchFileExceptionClass = Class.forName("java.nio.file.NoSuchFileException");
				ourAccessDeniedExceptionClass = Class.forName("java.nio.file.AccessDeniedException");
				ourFileToPathMethod = Class.forName("java.io.File").getMethod("toPath", new Class[0]);
				ourPathToFileMethod = pathClass.getMethod("toFile", new Class[0]);
				ourFilesWalkMethod = filesClass.getMethod("walkFileTree", new Class[] { pathClass, visitorClass });
				ourAttributesIsOtherMethod = Class.forName("java.nio.file.attribute.BasicFileAttributes").getDeclaredMethod("isOther", new Class[0]);
				ourFilesDeleteIfExistsMethod = filesClass.getMethod("deleteIfExists", new Class[] { pathClass });
				final Object Result_Continue = Class.forName("java.nio.file.FileVisitResult").getDeclaredField("CONTINUE").get(null);
				final Object Result_Skip = Class.forName("java.nio.file.FileVisitResult").getDeclaredField("SKIP_SUBTREE").get(null);
				ourDeletionVisitor = Proxy.newProxyInstance(FileUtilRt.class.getClassLoader(), new Class[] { visitorClass }, new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
					{
						if(args.length == 2)
						{
							String methodName = method.getName();
							Object second = args[1];
							if(second instanceof Throwable)
							{
								if(SystemInfoRt.isWindows && "visitFileFailed".equals(methodName) && FileUtilRt.NIOReflect.ourNoSuchFileExceptionClass.isInstance(second))
									performDelete(args[0]);
								else
									throw (Throwable)second;
							}
							else if("visitFile".equals(methodName) || "postVisitDirectory".equals(methodName))
							{
								performDelete(args[0]);
							}
							else if(SystemInfoRt.isWindows && "preVisitDirectory".equals(methodName))
							{
								boolean notDirectory = false;
								try
								{
									notDirectory = Boolean.TRUE.equals(FileUtilRt.NIOReflect.ourAttributesIsOtherMethod.invoke(second, new Object[0]));
								}catch(Throwable throwable){}
								if(notDirectory)
								{
									performDelete(args[0]);
									return Result_Skip;
								} 
							} 
						} 
						return Result_Continue;
					}
					
					private void performDelete(final Object fileObject) throws IOException, Throwable
					{
						Boolean result = FileUtilRt.<Boolean, Throwable>doIOOperation((FileUtilRt.RepeatableIOOperation)new FileUtilRt.RepeatableIOOperation<Boolean, RuntimeException>()
						{
							@Override
							public Boolean execute(boolean lastAttempt)
							{
								try
								{
									FileUtilRt.NIOReflect.ourFilesDeleteIfExistsMethod.invoke(null, new Object[] { fileObject });
									return Boolean.TRUE;
								}
								catch(InvocationTargetException e)
								{
									Throwable cause = e.getCause();
									if(!(cause instanceof IOException)) return Boolean.FALSE; 
									if(FileUtilRt.NIOReflect.ourAccessDeniedExceptionClass.isInstance(cause))
									try
									{
										File file = (File)FileUtilRt.NIOReflect.ourPathToFileMethod.invoke(fileObject, new Object[0]);
										if(file == null) return Boolean.FALSE; 
										if(file.delete() || !file.exists()) return Boolean.TRUE; 
									}
									catch (Throwable ignored)
									{
										return Boolean.FALSE;
									}
								}
								catch(IllegalAccessException e)
								{
									return Boolean.FALSE;
								}
								return lastAttempt ? Boolean.FALSE : null;
							}
						});
						if(!Boolean.TRUE.equals(result))
							throw new IOException("Failed to delete " + fileObject)
							{
								@Override
								public synchronized Throwable fillInStackTrace()
								{
									return this;
								}
							};
					}
				});
				initSuccess = true;
			}catch(Throwable ignored)
			{
				FileUtilRt.logger().info("Was not able to detect NIO API");
				ourFileToPathMethod = null;
				ourFilesWalkMethod = null;
				ourFilesDeleteIfExistsMethod = null;
				ourDeletionVisitor = null;
				ourNoSuchFileExceptionClass = null;
			} 
			IS_AVAILABLE = initSuccess;
		}
	}
	
	/** RepeatableIOOperation Interface **/
	public static interface RepeatableIOOperation<T, E extends Throwable>
	{
		T execute(boolean param1Boolean) throws E;
	}
	
	/** FilesToDeleteHolder Class **/
	private static class FilesToDeleteHolder
	{
		private static final Queue<String> ourFilesToDelete = createFilesToDelete();

		private static Queue<String> createFilesToDelete()
		{
			final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
			Runtime.getRuntime().addShutdownHook(new Thread("FileUtil deleteOnExit")
			{
				public void run()
				{
					String name;
					while((name = queue.poll()) != null) FileUtilRt.delete(new File(name)); 
				}
			});
			return queue;
		}
	}
	
	/** SymlinkResolver Interface **/
	protected static interface SymlinkResolver
	{
		String resolveSymlinksAndCanonicalize(String param1String, char param1Char, boolean param1Boolean);
		boolean isSymlink(CharSequence param1CharSequence);
	}
	
	/** CharComparingStrategy Interface **/
	private static interface CharComparingStrategy
	{
		public static final CharComparingStrategy IDENTITY = new CharComparingStrategy()
		{
			public boolean charsEqual(char ch1, char ch2)
			{
				return (ch1 == ch2);
			}
		};

		public static final CharComparingStrategy CASE_INSENSITIVE = new CharComparingStrategy()
		{
			public boolean charsEqual(char ch1, char ch2)
			{
				return StringUtilRt.charsEqualIgnoreCase(ch1, ch2);
			}
		};

		boolean charsEqual(char param1Char1, char param1Char2);
	}
}