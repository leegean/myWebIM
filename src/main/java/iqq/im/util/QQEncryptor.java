/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Project  : MiniQQ2
 * Package  : net.solosky.miniqq
 * File     : Encryptor.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : 2010-4-10
 * License  : Apache License 2.0 
 */
package iqq.im.util;

import iqq.im.T_01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * QQ加密解码
 * 
 * @author solosky
 */
public class QQEncryptor {

	private static ScriptEngine engine;

	/**
	 * 登录邮箱时用到的，auth_token
	 * 
	 * @param str
	 *            a {@link java.lang.String} object.
	 * @return a long.
	 */
	public static long time33(String str) {
		long hash = 0;
		for (int i = 0, length = str.length(); i < length; i++) {
			hash = hash * 33 + str.charAt(i);
		}
		return hash % 4294967296L;
	}

	/**
	 * 获取好友列表时计算的Hash参数 v2014.06.14更新
	 * 
	 * @param uin
	 *            当前登录用户UIN
	 * @param ptwebqq
	 *            Cookie中的ptwebqq的值
	 * @return hash
	 */
	public static String hash(String uin, String ptwebqq) {
		String s = "";
		try {
			// String js =
			// "P=function(i,a){var j=[];j[0]=i>>24&255;j[1]=i>>16&255;j[2]=i>>8&255;j[3]=i&255;for(var s=[],e=0;e<a.length;++e)s.push(a.charCodeAt(e));e=[];for(e.push(new b(0,s.length-1));e.length>0;){var c=e.pop();if(!(c.s>=c.e||c.s<0||c.e>=s.length))if(c.s+1==c.e){if(s[c.s]>s[c.e]){var J=s[c.s];s[c.s]=s[c.e];s[c.e]=J}}else{for(var J=c.s,l=c.e,f=s[c.s];c.s<c.e;){for(;c.s<c.e&&s[c.e]>=f;)c.e--,j[0]=j[0]+3&255;c.s<c.e&&(s[c.s]=s[c.e],c.s++,j[1]=j[1]*13+43&255);for(;c.s<c.e&&s[c.s]<=f;)c.s++,j[2]=j[2]-3&255;c.s<c.e&&(s[c.e]=s[c.s],c.e--,j[3]=(j[0]^j[1]^j[2]^j[3]+1)&255)}s[c.s]=f;e.push(new b(J,c.s-1));e.push(new b(c.s+1,l))}}s=[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"A\",\"B\",\"C\",\"D\",\"E\",\"F\"];e=\"\";for(c=0;c<j.length;c++)e+=s[j[c]>>4&15],e+=s[j[c]&15];return e},b=function(b,i){this.s=b||0;this.e=i||0}";
			// 20140614修改
			StringBuffer sqlSB = new StringBuffer();
			sqlSB.setLength(0);
			sqlSB.append("P = function(b, j) { \n");
			sqlSB.append("\tfor (var a = j + \"password error\", i = \"\", E = [];;) \n");
			sqlSB.append("\t\tif (i.length <= a.length) { \n");
			sqlSB.append("\t\t\tif (i += b, i.length == a.length) \n");
			sqlSB.append("\t\t\t\tbreak \n");
			sqlSB.append("\t\t} else { \n");
			sqlSB.append("\t\t\ti = i.slice(0, a.length); \n");
			sqlSB.append("\t\t\tbreak \n");
			sqlSB.append("\t\t} \n");
			sqlSB.append("\tfor (var c = 0; c < i.length; c++) \n");
			sqlSB.append("\t\tE[c] = i.charCodeAt(c) ^ a.charCodeAt(c); \n");
			sqlSB.append("\ta = [\"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\", \"A\", \"B\", \"C\", \"D\", \n");
			sqlSB.append("\t\t  \"E\", \"F\"]; \n");
			sqlSB.append("  i = \"\"; \n");
			sqlSB.append("  for (c = 0; c < E.length; c++) \n");
			sqlSB.append("    i += a[E[c] >> 4 & 15], i += a[E[c] & 15]; \n");
			sqlSB.append("  return i \n");
			sqlSB.append("} \n");
			String js = sqlSB.toString();
			// end
			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByMimeType("application/javascript");
			engine.eval(js);
			Invocable inv = (Invocable) engine;
			s = (String) inv.invokeFunction("P", uin, ptwebqq);

			System.out.println("hash:    " + uin + "   " + ptwebqq + "   " + s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;

	}

	/**
	 * 
	 * 计算登录时密码HASH值
	 * 
	 * @param uin
	 *            a long.
	 * @param plain
	 *            a {@link java.lang.String} object.
	 * @param verify
	 *            a {@link java.lang.String} object.
	 * @return a {@link java.lang.String} object.
	 */
	public static String encrypt(long uin, String plain, String verify) {
		byte[] data = concat(md5(plain.getBytes()), long2bytes(uin));
		String code = byte2HexString(md5(data));
		data = md5((code + verify.toUpperCase()).getBytes());
		return byte2HexString(data);
	}

	public static String encryptQm(String password, String verify) {
		ScriptEngineManager sem = new ScriptEngineManager(); /* script引擎管理 */
		ScriptEngine se = sem.getEngineByName("javascript"); /* script引擎 */

		String callbackvalue = null;
		try {

			se.eval(" var $ = new Object(); var navigator = new Object();navigator.appName == 'Microsoft Internet Explorer';"); /* 执行一段script */

			Scanner s = new Scanner(T_01.class.getClassLoader().getResourceAsStream("js.txt"));
			StringBuilder sb = new StringBuilder();
			while (s.hasNextLine()) {
				sb.append(s.nextLine());
			}
			s.close();
			Object Encryption = se.eval(sb.toString());
			// ";º ·"
			String salt = String.valueOf(new char[] { (char) 0, (char) 0, (char) 0, (char) 0, (char) 59, (char) 186, (char) 32, (char) 183 });
			Invocable invocableEngine = (Invocable) se;
			callbackvalue = (String) invocableEngine.invokeMethod(Encryption, "getEncryption", password, salt, verify);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return callbackvalue;
	}

	private static byte[] concat(byte[] bytes1, byte[] bytes2) {
		byte[] big = new byte[bytes1.length + bytes2.length];
		System.arraycopy(bytes1, 0, big, 0, bytes1.length);
		System.arraycopy(bytes2, 0, big, bytes1.length, bytes2.length);
		return big;
	}

	/**
	 * 计算一个字节数组的Md5值
	 * 
	 */
	private static byte[] md5(byte[] bytes) {
		MessageDigest dist = null;
		byte[] result = null;
		try {
			dist = MessageDigest.getInstance("MD5");
			result = dist.digest(bytes);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
		return result;
	}

	/**
	 * 把字节数组转换为16进制表示的字符串
	 * 
	 */
	private static String byte2HexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		char[] hex = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		if (b == null)
			return "null";

		int offset = 0;
		int len = b.length;

		// 检查索引范围
		int end = offset + len;
		if (end > b.length)
			end = b.length;

		sb.delete(0, sb.length());
		for (int i = offset; i < end; i++) {
			sb.append(hex[(b[i] & 0xF0) >>> 4]).append(hex[b[i] & 0xF]);
		}
		return sb.toString();
	}

	/**
	 * 计算GTK(gtk啥东东？)
	 * 
	 * @param skey
	 *            a {@link java.lang.String} object.
	 * @return a {@link java.lang.String} object.
	 */
	public static String gtk(String skey) {
		int hash = 5381;
		for (int i = 0; i < skey.length(); i++) {
			hash += (hash << 5) + skey.charAt(i);
		}
		return Integer.toString(hash & 0x7fffffff);
	}

	/**
	 * 把整形数转换为字节数组
	 * 
	 * @param i
	 *            a long.
	 * @return an array of byte.
	 */
	public static byte[] long2bytes(long i) {
		byte[] b = new byte[8];
		for (int m = 0; m < 8; m++, i >>= 8) {
			b[7 - m] = (byte) (i & 0x000000FF); // 奇怪, 在C# 整型数是低字节在前 byte[]
												// bytes =
												// BitConverter.GetBytes(i);
												// 而在JAVA里，是高字节在前
		}
		return b;
	}

	/**
	 * 把一个16进制字符串转换为字节数组，字符串没有空格，所以每两个字符 一个字节
	 * 
	 * @param s
	 *            a {@link java.lang.String} object.
	 * @return an array of byte.
	 */
	public static byte[] hexString2Byte(String s) {
		int len = s.length();
		byte[] ret = new byte[len >>> 1];
		for (int i = 0; i <= len - 2; i += 2) {
			ret[i >>> 1] = (byte) (Integer.parseInt(s.substring(i, i + 2).trim(), 16) & 0xFF);
		}
		return ret;
	}

	public static String getWbSu(String username) {
		String su = "";
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				su = (String) engine.eval("sinaSSOEncoder.base64.encode('" + URLEncoder.encode(username, "utf-8") + "');");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return su;
	}

	public static String getWbSp(String password, String pubkey, long servertime, String nonce) {
		String su = "";
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				su = (String) engine.eval("sinaSSOEncoder.base64.makeRequest('" + password + "','" + pubkey + "'," + servertime + ",'" + nonce + "');");
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return su;
	}

	public static String getWbTimestamp() {
		String su = "";
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				su = (String) engine.eval("(new Date).toUTCString();");
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return su;
	}
	public static String getWbLocaleDate() {
		String su = "";
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				su = (String) engine.eval("new Date+''");
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return su;
	}

	public static String getWbTimesyncTc() {
		String su = "";
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				su = String.format("%.0f", engine.eval("(new Date).getTime();"));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return su;
	}
	public static String getWbTimesyncL() {
		String su = "";
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				su = (String) engine.eval("g+''");
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return su;
	}
	public static String getWbTimesyncO() {
		String su = "";
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				su = (String) engine.eval("h+''");
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return su;
	}
	public static void updateWbTimesync(String ts, String tc, String p) {
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				engine.eval(" i.incoming("+ts+","+tc+","+p+")");
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String getImCallback() {
		String result = "";
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				result = (String)engine.eval("var l = 0;'IM_' + +(new Date) + l++");
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	public static String getRnd() {
		String result = "";
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				result = (String) engine.eval("+(new Date)+''");
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	public static String urlEncode(String str) {
		String result = "";
		ScriptEngine engine = initScriptEngine();
		if (engine != null) {
			try {
				result = (String) engine.eval("encodeURIComponent('"+str+"')");
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	public static JSONObject updateExt(Object ack) throws JSONException {
		JSONObject ext = new JSONObject();
		JSONObject timesync =new JSONObject();
		timesync.put("tc", QQEncryptor.getWbTimesyncTc());
		timesync.put("l", Double.parseDouble(QQEncryptor.getWbTimesyncL()));
		timesync.put("o", Double.parseDouble(QQEncryptor.getWbTimesyncO()));
		if(ack!=null){
			if(ack instanceof Boolean){
				ext.put("ack", (Boolean)ack);
			}else if(ack instanceof Long){
				ext.put("ack", (Long)ack);
			}
		}
		
		 ext.put("timesync", timesync);
		return ext;
	}
	private static ScriptEngine initScriptEngine() {
		if(engine!=null)return engine;
		ScriptEngineManager sem = new ScriptEngineManager(); /* script引擎管理 */
		engine = sem.getEngineByName("javascript"); /* script引擎 */

		try {
			engine.eval("var window = new Object();var navigator = new Object();navigator.userAgent = 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36';");
			engine.eval(new FileReader("qq.js"));
			engine.eval(new FileReader("ext.js"));
			return engine;
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* 执行一段script */catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 把Unicode编码转换为汉字
	 * 
	 * @param source
	 * @return
	 */
	public static String convertUnicodeToChar(String source) {
		if (null == source || " ".equals(source)) {
			return source;
		}

		StringBuffer sb = new StringBuffer();
		int i = 0;
		while (i < source.length()) {
			if (source.charAt(i) == '\\') {
				int j = Integer.parseInt(source.substring(i + 2, i + 6), 16);
				sb.append((char) j);
				i += 6;
			} else {
				sb.append(source.charAt(i));
				i++;
			}
		}
		return sb.toString();
	}

}
