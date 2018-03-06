package com.society.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5工具类
 * 
 * @author beyond
 *
 */
public class Md5Util {
	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static MessageDigest getMd5Digest() {
		return getDigest("MD5");
	}

	private static MessageDigest getDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public final static String md5(String pwd) {
		// 使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
		try {
			byte[] btInput = pwd.getBytes("UTF-8");
			return md5(btInput, false);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public final static String md5(byte[] btInput) {
		return md5(btInput, false);
	}

	public final static String md5(byte[] btInput, boolean toUpperCase) {
		// 用于加密的字符
		// 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
		MessageDigest mdInst = getMd5Digest();

		// MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
		mdInst.update(btInput);

		// 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
		byte[] md = mdInst.digest();

		char[] toDigits = DIGITS_LOWER;
		if (true == toUpperCase) {
			toDigits = DIGITS_UPPER;
		}
		// 把密文转换成十六进制的字符串形式
		// 返回经过加密后的字符串
		return new String(encodeHex(md, toDigits));

	}

	public static String md5(InputStream data) throws IOException {
		byte[] md = digest(getMd5Digest(), data);
		return new String(encodeHex(md, DIGITS_LOWER));
	}

	private static byte[] digest(MessageDigest digest, InputStream data) throws IOException {
		byte[] buffer = new byte[1024];
		int read = data.read(buffer, 0, 1024);
		while (read > -1) {
			digest.update(buffer, 0, read);
			read = data.read(buffer, 0, 1024);
		}
		return digest.digest();
	}

	private static final char[] encodeHex(byte[] data, char[] toDigits) {
		int j = data.length;
		char[] str = new char[j << 1];
		int k = 0;
		for (int i = 0; i < j; i++) { // i = 0
			byte byte0 = data[i]; // 95
			str[k++] = toDigits[byte0 >>> 4 & 0xf]; // 5
			str[k++] = toDigits[byte0 & 0xf]; // F
		}
		// 返回经过加密后的字符串
		return str;
	}

	public static void main(String[] args) {
		System.out.println(md5("何阳贵"));
	}
}
