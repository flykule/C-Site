package com.example.castle.csite.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author 吴志强
 * @time 2016/9/1  18:40
 * @desc ${TODD}
 */
public class NetWorkUtil {

	public static String doPost(String urlPath, HashMap<String, String> params) {
		String result = "";
		BufferedReader reader = null;
		try {
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.getOutputStream().write(parseParams(params).getBytes());
			if(conn.getResponseCode()==200) {
			    reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
				result=reader.readLine();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String doGet(String urlPath){
		try {
			URL url = new URL(urlPath);
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			if(conn.getResponseCode()==200) {
				InputStream is = conn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				return reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String parseParams(HashMap<String, String> params) {
		String paramsStr = "";
		for (HashMap.Entry<String, String> entry : params.entrySet()) {
			paramsStr += "&" + entry.getKey() + "=" + entry.getValue();
		}
		paramsStr=paramsStr.substring(1);
		return paramsStr;
	}
}
