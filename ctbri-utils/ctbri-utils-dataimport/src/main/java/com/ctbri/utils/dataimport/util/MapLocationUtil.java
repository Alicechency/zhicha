package com.ctbri.utils.dataimport.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 地图地址工具栏
 * 
 * @author Hogan
 *
 */
public class MapLocationUtil {

	/**
	 * 获取地址对应的经纬度
	 * 
	 * @param address
	 * @return
	 */
	public static Map<String, Double> getLngAndLat(String address) {
		Map<String, Double> map = new HashMap<String, Double>();
		String url = generateUrl(address, PropUtil.getValue("baidu.default.city"), "json", PropUtil.getValue("baidu.ak"));
		String locationJson = getResponse(url);
		JSONObject obj = JSONObject.parseObject(locationJson);
		if (obj != null && obj.getInteger("status") == Consts.BAIDU_API_STATUS_SUCCESS) {
			double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
			double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
			map.put("lng", lng);
			map.put("lat", lat);
		}
		return map;
	}

	/**
	 * 
	 * 
	 * @param url
	 * @return
	 */
	public static String getResponse(String url) {
		StringBuilder responseBody = new StringBuilder();
		BufferedReader in = null;
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				responseBody.append(inputLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseBody.toString();
	}

	/**
	 * 组合URL
	 * 
	 * @param address
	 * @param city
	 * @param output
	 * @param key
	 * @return
	 */
	private static String generateUrl(String address, String city, String output, String key) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://api.map.baidu.com/geocoder/v2/?address=");
		sb.append(address);
		sb.append("&city=");
		sb.append(city);
		sb.append("&output=");
		sb.append(output);
		sb.append("&ak=");
		sb.append(key);
		return sb.toString();
	}

}
