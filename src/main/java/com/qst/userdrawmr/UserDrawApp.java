package com.qst.userdrawmr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.qst.util.TextArrayWritable;

public class UserDrawApp {

	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		 
		Job job1 = Job.getInstance(conf, "UserDrawMapReduceJob1");
		job1.setJarByClass(UserDrawApp.class);

		job1.setMapperClass(UserDrawMap.class);
		job1.setReducerClass(UserDrawReduce.class);

		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(TextArrayWritable.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);

		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job1, new Path(args[0]));// 输入路径
		FileOutputFormat.setOutputPath(job1, new Path(args[1]));// 输出路径
		if(fs.exists(new Path(args[1]))){
			fs.delete(new Path(args[1]),true);
		}

		Boolean state1 = job1.waitForCompletion(true);
		System.out.println("job1执行成功！！！");

		// 如果上述程序成功执行，则执行以下代码。
		if (state1) {
			conf = new Configuration();
			Job job2 = Job.getInstance(conf, "UserDrawMapReduceJob2");
			job2.setJarByClass(UserDrawApp.class);

			job2.setMapperClass(UserDrawMap2.class);
			job2.setReducerClass(UserDrawReduce2.class);

			job2.setMapOutputKeyClass(Text.class);
			job2.setMapOutputValueClass(Text.class);
			job2.setOutputKeyClass(Text.class);
			job2.setOutputValueClass(Text.class);

			job2.setInputFormatClass(TextInputFormat.class);
			job2.setOutputFormatClass(TextOutputFormat.class);

			FileInputFormat.addInputPath(job2, new Path(args[1]));// 输入路径
			FileOutputFormat.setOutputPath(job2, new Path(args[2]));// 输出路径
			
			LazyOutputFormat.setOutputFormatClass(job2, TextOutputFormat.class);
			//注册输出类型，设置输出格式
			MultipleOutputs.addNamedOutput(job2, "protrait", TextOutputFormat.class, Text.class, Text.class);
			MultipleOutputs.addNamedOutput(job2, "time", SequenceFileOutputFormat.class, Text.class, Text.class);
			
			if(fs.exists(new Path(args[2]))){
				fs.delete(new Path(args[2]),true);
			}

			System.exit(job2.waitForCompletion(true) ? 0 : 1);
			System.out.println("job2执行成功！！！");

		}
	}
}
