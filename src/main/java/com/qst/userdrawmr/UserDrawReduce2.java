package com.qst.userdrawmr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import com.qst.userdraw.UserDraw;
import com.qst.util.LoadHdfsTable;

public class UserDrawReduce2 extends Reducer<Text, Text, Text, Text> {

	// 10001 {QQ,0.001,0.001,0,0.2,0.3,0.2,0.3}
	Map<String, String[]> appMap = LoadHdfsTable.getAppMap();
	private MultipleOutputs<Text, Text> mos;

	// 初始化mos
	@Override
	protected void setup(Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		mos = new MultipleOutputs<Text, Text>(context);
	}

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		// UserDraw类型参见93行
		Map<String, UserDraw> userDrawMap = new HashMap<String, UserDraw>();
		Set<String> keySet = userDrawMap.keySet();
		String keyMDN = null;

		// t:20160813|+/Ogc1sFMIAOwxF82zploQ==|010005|6|116648
		for (Text t : values) {

			String[] dataArray = t.toString().split("\\|");
			keyMDN = dataArray[1]; // 用户MDN
			String appID = dataArray[2]; // APPID
			// 根据appID获取对应的标签信息(和AppTab.txt文件进行交互)
			if (appID.length() > 0) { // appID不能为空
				if (appMap.get(appID) == null) {
					continue;
				}

				// 男性权重？？？？？？？？？？？
				String favourite = appMap.get(appID)[2];
				float male = Float.parseFloat(appMap.get(appID)[1]);
				float female = Float.parseFloat(appMap.get(appID)[2]);
				float age1 = Float.parseFloat(appMap.get(appID)[3]);
				float age2 = Float.parseFloat(appMap.get(appID)[4]);
				float age3 = Float.parseFloat(appMap.get(appID)[5]);
				float age4 = Float.parseFloat(appMap.get(appID)[6]);
				float age5 = Float.parseFloat(appMap.get(appID)[7]);

				long times = Long.parseLong(dataArray[4]);
				//如果含有此key（MDN）,则进行融合
				if (userDrawMap.containsKey(keyMDN) == true) {

					// 性别以及年龄融合，难点！！！！！！！！！！！！！！！！
					UserDraw userDraw = userDrawMap.get(keyMDN);
					// 性别权重
					userDraw.sumSex(male, female, times);
					// 年龄段权重
					userDraw.sumAge(age1, age2, age3, age4, age5, times);

					userDraw.protraitSex();
					userDraw.protraitAge();

				} else {

					// 如果MDN不存在，将userDrawMap里面放入K，V
					userDrawMap.put(keyMDN,
							createDrawData(dataArray, favourite, male, female, age1, age2, age3, age4, age5, times));

				}
			}

		}
		for (String keys : keySet) {
			if (keys.length() > 0) {
				String str = userDrawMap.get(keys).toString();
				String[] arr = str.split("=======>");
				String seqKey = arr[1].substring(0, 24);
				String seqValue = arr[1].substring(25);
				mos.write("protrait",null, arr[0]);
				mos.write("time",new Text(seqKey), new Text(seqValue), "D:/draw/time/time");
			}

		}
	}

	// 创建画像数据
	private static UserDraw createDrawData(String[] dataArray, //
			String favourite, // 兴趣爱好
			float male, float female, // 性别
			float age1, float age2, float age3, float age4, float age5, // 年龄
			long times) {

		UserDraw userDraw = new UserDraw();
		userDraw.setMDN(dataArray[1]);

		// 初始化，难点！！！！！！！！！！！！！！！！！！！
		userDraw.sumAge(age1, age2, age3, age4, age5, times);
		userDraw.sumSex(male, female, times);
		//？
		userDraw.protraitSex();
		userDraw.protraitAge();

		return userDraw;
	}

	@Override
	protected void cleanup(Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		mos.close();
	}
	
	

}
