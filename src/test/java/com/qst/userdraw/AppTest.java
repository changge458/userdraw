package com.qst.userdraw;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.SequenceFile.Reader;

/**
 * Unit test for simple App.
 */
public class AppTest {
	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);

		Reader r = new SequenceFile.Reader(fs, new Path("D:/draw/time/time-r-00000"), conf);

		Text key = new Text();
		Text value = new Text();
		while (r.next(key,value)) {
			System.out.println(key+ ": "+ value);
		}
		
	}
}
