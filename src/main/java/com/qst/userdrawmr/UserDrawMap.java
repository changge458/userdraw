package com.qst.userdrawmr;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.qst.util.Config;
import com.qst.util.TextArrayWritable;

public class UserDrawMap extends Mapper<LongWritable, Text, Text, TextArrayWritable> {
	Text k = new Text();
	public static Config config = new Config();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] dataArray = line.split(config.Separator);

		// GtfeQucYxTd0i6xIiFdw8w==151740
		String uiqkey = dataArray[Integer.parseInt(config.MDN)] + dataArray[Integer.parseInt(config.appID)]; // MDN
																												// +
																												// appID
		String[] val = new String[5];
		// 1471017996695时间戳
		String timenow = dataArray[Integer.parseInt(config.Date)];

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		val[0] = sdf.format(Long.parseLong(timenow));// 时间
		val[1] = dataArray[Integer.parseInt(config.MDN)];// 手机号
		val[2] = dataArray[Integer.parseInt(config.appID)];// appID
		val[3] = "1";// 计数
		val[4] = dataArray[Integer.parseInt(config.ProcedureTime)];// 使用时长
		k.set(uiqkey);
		context.write(k, new TextArrayWritable(val));

	}
}