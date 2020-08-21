package com.yangjie.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import net.sf.json.JSONObject;

public class DxInterface {
	public static String sendPost(String params, String requestUrl) throws IOException {
		byte[] requestBytes = params.getBytes("utf-8"); // 将参数转为二进制流
		HttpClient httpClient = new HttpClient();// 客户端实例化
		PostMethod postMethod = new PostMethod(requestUrl);
		// 设置请求头Authorization
		postMethod.setRequestHeader("vendor", "18");
		// 设置请求头 Content-Type
		postMethod.setRequestHeader("Content-Type", "application/json");
		postMethod.setRequestHeader("zone", getTimeZone());
		postMethod.setRequestHeader("time", new Date().getTime() + "");

		InputStream inputStream = new ByteArrayInputStream(requestBytes, 0, requestBytes.length);
		RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, requestBytes.length,
				"application/json; charset=utf-8"); // 请求体
		postMethod.setRequestEntity(requestEntity);
		httpClient.executeMethod(postMethod);// 执行请求
		InputStream soapResponseStream = postMethod.getResponseBodyAsStream();// 获取返回的流
		byte[] datas = null;
		try {
			datas = readInputStream(soapResponseStream);// 从输入流中读取数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = new String(datas, "UTF-8");// 将二进制流转为String
		// 打印返回结果
		// System.out.println(result);

		return result;

	}

	/**
	 * 从输入流中读取数据
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;
	}

	public static String getTimeZone() {
		Calendar cal = Calendar.getInstance();
		int offset = cal.get(Calendar.ZONE_OFFSET);
		cal.add(Calendar.MILLISECOND, -offset);
		Long timeStampUTC = cal.getTimeInMillis();
		Long timeStamp = System.currentTimeMillis();
		Long timeZone = (timeStamp - timeStampUTC) / (1000 * 3600);
//		System.out.println(timeZone.intValue());
		return String.valueOf(timeZone);
	}

	public static String getSkey() throws Exception {
		String dxIp = ResourceBundle.getValue("dx.ip");
		dxIp = dxIp + "/api/auth.skey";
		HashMap<String, Object> paramsInfc = new HashMap<String, Object>();
		paramsInfc.put("id", "18");
		String info = "{" + "\"jsonrpc\":" + "\"2.0\"" + "," + "\"id\":" + 18 + "," + "\"method\":" + "\"auth.skey\""
				+ "," + "\"params\":" + JSONObject.fromObject(paramsInfc) + "}";
		String returnJson = DxInterface.sendPost(info, dxIp);
		String strt = JsonUtil.getJsonValueByJsonKey(returnJson, "result");
		String str = JsonUtil.getJsonValueByJsonKey(strt, "skey");
		str = str.trim();
		String str2 = "";
		if (str != null && !"".equals(str)) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
					str2 += str.charAt(i);
				}
			}
		}
		str2 = str2.substring(str2.length() - 2, str2.length() - 1);
		String ss = str.substring(Integer.parseInt(str2), Integer.parseInt(str2) + 16);
		return ss;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getSkey());
	}

}
