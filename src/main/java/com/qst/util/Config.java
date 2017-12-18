package com.qst.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 通过指定文件(UserDraw.properties) 获取配置信息
 * 并将配置文件内容作为常量输出
 */
public class Config {
	
	//对Properties进行操作，将配置文件内容加载进去。
	static Properties properties;
	static {
		properties = new Properties();
		// 可以获取资源文件
		InputStream inStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("UserDraw.properties");
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// UserDraw
	public String Separator = properties.getProperty("Separator");
	public String Date = properties.getProperty("Date");
	public String MDN = properties.getProperty("MDN");
	public String appID = properties.getProperty("appID");
	public String count = properties.getProperty("count");
	public String ProcedureTime = properties.getProperty("ProcedureTime");
	
}
