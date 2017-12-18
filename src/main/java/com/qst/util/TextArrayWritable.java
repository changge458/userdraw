package com.qst.util;

import java.util.ArrayList;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;

/**
 * 定义一个新的TextArrayWritable，用于将输入的String数组或ArrayList<String>
 * 转化为Text[]
 */
public class TextArrayWritable extends ArrayWritable {
	public TextArrayWritable() {
		super(Text.class);
	}

	//将输入的String数组，变化为Text数组
	public TextArrayWritable(String[] strings) {
		super(Text.class);
		Text[] texts = new Text[strings.length];
		for (int i = 0; i < strings.length; i++) {
			texts[i] = new Text(strings[i]);
		}
		//设置新的Text数组
		set(texts);
	}

	//new TextArrayWritable({"4","5"})
	//new ArrayList<String>
	//将ArrayList转化为Text数组, 暂时没用
	public TextArrayWritable(ArrayList<String> strings) {
		super(Text.class);

		Text[] texts = new Text[strings.size()];
		int i = 0;
		for (String str : strings) {
			texts[i] = new Text(str);
			i++;
		}
		//设置新的Text数组
		set(texts);
	}

	//数组转化成ArrayList
	public ArrayList<String> toArrayList(String[] writables) {
		ArrayList<String> arraylist = new ArrayList<String>();
		for (String writable : writables) {
			arraylist.add(writable.toString());
		}
		return arraylist;
	}

	public ArrayList<String> toArrayList() {
		return toArrayList(super.toStrings());
	}

}