package com.qst.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Reader;
import org.apache.hadoop.io.Text;

@SuppressWarnings({ "deprecation", "resource" })
public class LoadUseTime {
	public static String UseTime = "D:/draw/time/time-r-00000";
	
	private static Map<String, double[]> TimeMap = new HashMap<String, double[]>();
	
	//这个代码写的很low，大家自行进行修改
	static {
		try {
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(conf);
			
			Reader r = new SequenceFile.Reader(fs, new Path(UseTime), conf);
			
			NullWritable key = NullWritable.get();
			Text value = new Text();
			
			StringBuffer sb = new StringBuffer();
			while (r.next(key,value)) {
				
				String[] arr = value.toString().split("\\|");
				double maleTime = Double.parseDouble(arr[1]);
				double femaleTime= Double.parseDouble(arr[2]);
				double age1Time= Double.parseDouble(arr[3]);
				double age2Time= Double.parseDouble(arr[4]);
				double age3Time= Double.parseDouble(arr[5]);
				double age4Time= Double.parseDouble(arr[6]);
				double age5Time= Double.parseDouble(arr[7]);
				
				double[] timeArr = {maleTime, femaleTime, age1Time, age2Time, age3Time, age4Time,age5Time };
				TimeMap.put(arr[0], timeArr);
				sb.delete(0, sb.length());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String, double[]> getTimeMapp() {
		return TimeMap;
	}

	public static void setTimeMap(Map<String, double[]> timeMap) {
		LoadUseTime.TimeMap = timeMap;
	}

}