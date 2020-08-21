package com.yangjie.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Administrator
 *
 */
public class AES {

	// 加密
	public static byte[] Encrypt(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes("utf-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

		return encrypted;// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	// 解密
	public static String Decrypt(byte[] sSrc, String sKey) throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			// byte[] encrypted1 = Base64.decode(sSrc);//先用base64解密
			try {
				byte[] original = cipher.doFinal(sSrc);
				String originalString = new String(original, "utf-8");
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		/*
		 * // * 此处使用AES-128-ECB加密模式，key需要为16位。 //
		 */
		String cKey = "1234567890123456";
		// // 需要加密的字串
		String cSrc = "www.gowhere.so";
		System.out.println(cSrc);
		// // 加密
		byte[] enString = AES.Encrypt(cSrc, cKey);
		System.out.println("加密后的字串是：" + Base64Util.getBase64(enString.toString()));

		// 解密
		String DeString = AES.Decrypt(enString, cKey);
		System.out.println("解密后的字串是：" + DeString);
	}
}