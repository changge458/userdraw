package com.qst.userdrawmr;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.qst.util.TextArrayWritable;

public class UserDrawReduce extends Reducer<Text, TextArrayWritable, Text, Text> {
	Text v = new Text();

	public void reduce(Text key, Iterable<TextArrayWritable> values, Context context)
			throws IOException, InterruptedException {
		long sum = 0;
		int count = 0;
		String[] res = new String[5];
		boolean flg = true;
		for (TextArrayWritable t : values) {
			String[] vals = t.toStrings();
			if (flg) {
				res = vals;
			}
			if (vals[3] != null) {
				count = count + 1;

			}
			if (vals[4] != null) {
				sum += Long.valueOf(vals[4]);
			}
		}
		res[3] = String.valueOf(count);
		res[4] = String.valueOf(sum);

		StringBuffer sb = new StringBuffer();
		sb.append(res[0]).append("|");// 时间
		sb.append(res[1]).append("|");// 手机号
		sb.append(res[2]).append("|");// appID
		sb.append(res[3]).append("|");// 计数
		sb.append(res[4]);// 使用时长
		v.set(sb.toString());
		context.write(null, v);
	}
}