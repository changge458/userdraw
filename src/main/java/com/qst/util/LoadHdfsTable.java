package com.qst.util;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class LoadHdfsTable {
	public static String appTab = "D:/draw/appTab.txt";

	private static Map<String, String[]> appMap = new HashMap<String, String[]>();

	// 这个代码写的很low，大家自行进行修改
	static {
		try {
			StringBuffer sb = new StringBuffer();
			String line = null;
			BufferedReader breader = ReadHdfsFile.fileReader(appTab);
			while ((line = breader.readLine()) != null) {
				String[] appArray = line.split("\\|");
				// appName
				sb.append(appArray[1]).append(",");
				// 性别权重
				sb.append(appArray[2]).append(",").append(appArray[3]).append(",");
				// 年龄段权重
				sb.append(appArray[4]).append(",").append(appArray[5]).append(",").append(appArray[6]).append(",");
				sb.append(appArray[7]).append(",").append(appArray[8]);

				String[] appToValueArray = sb.toString().split(",");
				appMap.put(appArray[0], appToValueArray);
				sb.delete(0, sb.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String[]> getAppMap() {
		return appMap;
	}

	public static void setAppMap(Map<String, String[]> appMap) {
		LoadHdfsTable.appMap = appMap;
	}

}
