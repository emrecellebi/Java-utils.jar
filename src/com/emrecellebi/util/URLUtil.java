package com.emrecellebi.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.emrecellebi.ThreeState;
import com.emrecellebi.text.Pair;
import com.emrecellebi.text.TextRange;

public final class URLUtil
{
	public static final String SCHEME_SEPARATOR = "://";
	public static final String FILE_PROTOCOL = "file";
	public static final String HTTP_PROTOCOL = "http";
	public static final String HTTPS_PROTOCOL = "https";
	public static final String JAR_PROTOCOL = "jar";
	public static final String JRT_PROTOCOL = "jrt";
	public static final String JAR_SEPARATOR = "!/";
	public static final Pattern DATA_URI_PATTERN = Pattern.compile("data:([^,;]+/[^,;]+)(;charset(?:=|:)[^,;]+)?(;base64)?,(.+)");
	public static final Pattern URL_PATTERN = Pattern.compile("\\b(mailto:|(news|(ht|f)tp(s?))://|((?<![\\p{L}0-9_.])(www\\.)))[-A-Za-z0-9+$&@#/%?=~_|!:,.;]*[-A-Za-z0-9+$&@#/%=~_|]");
	public static final Pattern URL_WITH_PARENS_PATTERN = Pattern.compile("\\b(mailto:|(news|(ht|f)tp(s?))://|((?<![\\p{L}0-9_.])(www\\.)))[-A-Za-z0-9+$&@#/%?=~_|!:,.;()]*[-A-Za-z0-9+$&@#/%=~_|()]");
	public static final Pattern FILE_URL_PATTERN = Pattern.compile("\\b(file:///)[-A-Za-z0-9+$&@#/%?=~_|!:,.;]*[-A-Za-z0-9+$&@#/%=~_|]");
	public static final Pattern HREF_PATTERN = Pattern.compile("<a(?:\\s+href\\s*=\\s*[\"']([^\"']*)[\"'])?\\s*>([^<]*)</a>");

	public static boolean canContainUrl(String line)
	{
		return (line.contains("mailto:") || line.contains("://") || line.contains("www."));
	}
	
	public static boolean containsScheme(String url)
	{
		return url.contains("://");
	}
	
	public static String decode(String string)
	{
		try
		{
			return URLDecoder.decode(string, StandardCharsets.UTF_8.name());
		}catch(UnsupportedEncodingException uee){return URLDecoder.decode(string);} 
	}
	
	private static int decode(char c)
	{
		if(c >= '0' && c <= '9') return c - 48;
		if(c >= 'a' && c <= 'f') return c - 97 + 10;
		if(c >= 'A' && c <= 'F') return c - 65 + 10;
		return -1;
	}
	
	public static String encodeURIComponent(String s)
	{
		try
		{
			return URLEncoder.encode(s, StandardCharsets.UTF_8.name()).replace("+", "%20").replace("%21", "!").replace("%27", "'").replace("%28", "(").replace("%29", ")").replace("%7E", "~");
		}catch(UnsupportedEncodingException uee){return s;} 
	}
	
	public static TextRange findUrl(CharSequence text, int startOffset, int endOffset)
	{
		Matcher m = URL_WITH_PARENS_PATTERN.matcher(text);
		m.region(startOffset, endOffset);
		if(!m.find()) return null; 
		int start = m.start();
		int end = m.end();
		int unmatchedPos = 0;
		int unmatchedCount = 0;
		for(int i = m.end(1); i < end; i++)
		{
			char c = text.charAt(i);
			if(c == '(')
				if(unmatchedCount++ == 0) unmatchedPos = i; 
			else if(c == ')' && unmatchedCount-- == 0)
				return new TextRange(start, i);
		}
		if(unmatchedCount > 0) return new TextRange(start, unmatchedPos); 
		return new TextRange(start, end);
	}
	
	public static byte[] getBytesFromDataUri(String dataUrl)
	{
		Matcher matcher = DATA_URI_PATTERN.matcher(StringUtilRt.unquoteString(dataUrl));
		if(matcher.matches())
		{
			try
			{
				String content = matcher.group(4);
				return ";base64".equalsIgnoreCase(matcher.group(3)) ? Base64.getDecoder().decode(content) : decode(content).getBytes(StandardCharsets.UTF_8);
			}catch(IllegalArgumentException e){return null;}  
		}
		return null;
	}
	
	public static URL getJarEntryURL(File file, String pathInJar) throws MalformedURLException
	{
		String fileURL = file.toURI().toASCIIString().replace("!", "%21");
		int index = 0;
		while(index < pathInJar.length() && pathInJar.charAt(index) == '/') index++; 
		return new URL("jar:" + fileURL + "!/" + pathInJar.substring(index));
	}
	
	public static boolean isDataUri(String value)
	{
		return (!value.isEmpty() && value.startsWith("data:", (value.charAt(0) == '"' || value.charAt(0) == '\'') ? 1 : 0));
	}
	
	public static InputStream openJarStream(URL url) throws IOException
	{
		Pair<String, String> paths = splitJarUrl(url.getFile());
		if(paths == null) throw new MalformedURLException(url.getFile()); 
		final ZipFile zipFile = new ZipFile((String)paths.first);
		ZipEntry zipEntry = zipFile.getEntry((String)paths.second);
		if(zipEntry == null)
		{
			zipFile.close();
			throw new FileNotFoundException("Entry " + (String)paths.second + " not found in " + (String)paths.first);
		}
		return new FilterInputStream(zipFile.getInputStream(zipEntry))
		{
			@Override
			public void close() throws IOException
			{
				super.close();
				zipFile.close();
			}
		};
	}
	
	public static InputStream openResourceStream(URL url) throws IOException
	{
		try
		{
			return openStream(url);
		}catch(FileNotFoundException ex)
		{
			String protocol = url.getProtocol();
			String file = null;
			if(protocol.equals("file"))
				file = url.getFile();
			else if (protocol.equals("jar"))
			{
				int pos = url.getFile().indexOf("!");
				if(pos >= 0) file = url.getFile().substring(pos + 1); 
			}
			if(file != null && file.startsWith("/"))
			{
				InputStream resourceStream = URLUtil.class.getResourceAsStream(file);
				if (resourceStream != null) return resourceStream;
			} 
			throw ex;
		} 
	}

	public static InputStream openStream(URL url) throws IOException
	{
		String protocol = url.getProtocol();
		return protocol.equals("jar") ? openJarStream(url) : url.openStream();
	}
	
	public static String parseHostFromSshUrl(String sshUrl)
	{
		String host = sshUrl;
		int at = host.lastIndexOf('@');
		if(at > 0)
			host = host.substring(at + 1);
		else
		{
			int firstColon = host.indexOf(':');
			if(firstColon > 0) host = host.substring(firstColon + 3); 
		}
		int colon = host.indexOf(':');
		if(colon > 0)
			host = host.substring(0, colon);
		else
		{
			int slash = host.indexOf('/');
			if(slash > 0) host = host.substring(0, slash); 
		}
		return host;
	}
	
	public static ThreeState resourceExists(URL url)
	{
		if(url.getProtocol().equals("file"))
			return ThreeState.fromBoolean(urlToFile(url).exists());
		if(url.getProtocol().equals("jar"))
		{
			Pair<String, String> paths = splitJarUrl(url.getFile());
			if(paths == null) return ThreeState.NO;
			if(!(new File((String)paths.first)).isFile()) return ThreeState.NO;
			try
			{
				ZipFile file = new ZipFile((String)paths.first);
				try
				{
					ThreeState threeState = ThreeState.fromBoolean((file.getEntry((String)paths.second) != null));
					file.close();
					return threeState;
				}
				catch(Throwable throwable)
				{
					try{file.close();}catch(Throwable throwable1) {throwable.addSuppressed(throwable1);} 
					throw throwable;
				}
			}catch(IOException e){return ThreeState.NO;} 
		}
		return ThreeState.UNSURE;
	}
	
	public static Pair<String, String> splitJarUrl(String url)
	{
		int pivot = url.indexOf("!/");
		if(pivot < 0) return null; 
		String resourcePath = url.substring(pivot + 2);
		String jarPath = url.substring(0, pivot);
		if(jarPath.startsWith("jar:")) jarPath = jarPath.substring("jar".length() + 1); 
		if(jarPath.startsWith("file"))
		{
			try
			{
				jarPath = urlToFile(new URL(jarPath)).getPath().replace('\\', '/');
			}
			catch(Exception e)
			{
				jarPath = jarPath.substring("file".length());
				if(jarPath.startsWith("://"))
					jarPath = jarPath.substring("://".length()); 
				else if(jarPath.length() != 0 && jarPath.charAt(0) == ':')
					jarPath = jarPath.substring(1);
			}
		}
		return new Pair(jarPath, resourcePath);
	}
	
	public static CharSequence unescapePercentSequences(CharSequence s, int from, int end)
	{
		int i = StringUtil.indexOf(s, '%', from, end);
		if(i == -1)
		{
			if(s.subSequence(from, end) == null) System.exit(0);
			return s.subSequence(from, end);
		}
		StringBuilder decode = new StringBuilder();
		decode.append(s, from, i);
		byte[] byteBuffer = null;
		int byteBufferSize = 0;
		while(i < end)
		{
			char c = s.charAt(i);
			if(c == '%')
			{
				if(byteBuffer == null)
					byteBuffer = new byte[end - from];
				else 
					byteBufferSize = 0;
				
				while(i + 2 < end && s.charAt(i) == '%')
				{
					int d1 = decode(s.charAt(i + 1));
					int d2 = decode(s.charAt(i + 2));
					if(d1 != -1 && d2 != -1)
					{
						byteBuffer[byteBufferSize++] = (byte)((d1 & 0xF) << 4 | d2 & 0xF);
						i += 3;
					}					
				}
				
				if(byteBufferSize != 0)
				{
					decode.append(new String(byteBuffer, 0, byteBufferSize, StandardCharsets.UTF_8));
					continue;
				}
			}
			decode.append(c);
			i++;
		}
		return decode;
	}
	
	public static String unescapePercentSequences(String s)
	{
		return unescapePercentSequences(s, 0, s.length()).toString();
	}
	
	public static File urlToFile(URL url)
	{
		try
		{
			return new File(url.toURI().getSchemeSpecificPart());
		}catch(URISyntaxException e){throw new IllegalArgumentException("URL='" + url + "'", e);} 
	}
}