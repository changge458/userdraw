package com.qst.userdrawmr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UserDrawMap2 extends Mapper<LongWritable, Text, Text, Text> {
	Text k = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] dataArray = line.split("\\|");
		String newkey = dataArray[1]; // MDN
		k.set(newkey);
		/**
		 * key：mdn value：一行数据
		 * 20160813|+/Ogc1sFMIAOwxF82zploQ==|010005|6|116648
		 */
		context.write(k, value);
	}
}