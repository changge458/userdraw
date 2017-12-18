package com.qst.userdraw;


import java.math.BigDecimal;



/**
 * 用户画像
 **/
public class UserDraw {
	
	// 属性
	//private String statTimeDay;
	private String MDN;
	private double male;
	private double female;
	private double age1;
	private double age2;
	private double age3;
	private double age4;
	private double age5;
	private double maleTime;
	private double femaleTime;
	private double age1Time;
	private double age2Time;
	private double age3Time;
	private double age4Time;
	private double age5Time;
	

	// 重写toString
	public String toString() {
		StringBuffer sb = new StringBuffer();
		//sb.append(statTimeDay).append("|");
		sb.append(MDN).append("|");
		sb.append(new BigDecimal(male).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(female).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(age1).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(age2).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(age3).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(age4).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(age5).setScale(3, 4).doubleValue()).append("=======>");
		
		sb.append(MDN).append("|");
		sb.append(new BigDecimal(maleTime).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(femaleTime).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(age1Time).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(age2Time).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(age3Time).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(age4Time).setScale(3, 4).doubleValue()).append("|");
		sb.append(new BigDecimal(age5Time).setScale(3, 4).doubleValue());

		return sb.toString();
	}

	// 融合方法
	/** 性别融合 */
	public void protraitSex() {
		double sum = this.maleTime + this.femaleTime ;
		
		if(sum != 0){
			this.male = this.maleTime  / sum;
			this.female = this.femaleTime  / sum;
		}
	}

	/** 年龄段融合 */
	public void protraitAge() {
		double sum = this.age1Time + this.age2Time + this.age3Time + this.age4Time + this.age5Time;
				
		if(sum != 0){
			this.age1 = this.age1Time / sum;
			this.age2 = this.age2Time / sum;
			this.age3 = this.age3Time / sum;
			this.age4 = this.age4Time / sum;
			this.age5 = this.age5Time / sum;
		}
	}

	/** 男女权重和 */
	public void sumSex(float male, float female, long time) {
		this.maleTime += male * time; 
		this.femaleTime += female * time; 
		//double sum = this.male + this.female;
	}

	/** 年龄段权重和 */
	public void sumAge(float pAge1, float pAge2, float pAge3, float pAge4, float pAge5, long time) {
		this.age1Time += pAge1 * time ;
		this.age2Time += pAge2 * time ;
		this.age3Time += pAge3 * time ;
		this.age4Time += pAge4 * time ;
		this.age5Time += pAge5 * time ;
		//double sum = this.age1Time + this.age2Time + this.age3Time + this.age4Time+ this.age5Time;
	}

	//get set方法
	public String getMDN() {
		return MDN;
	}

	public void setMDN(String mDN) {
		MDN = mDN;
	}

	public double getMale() {
		return male;
	}

	public void setMale(double male) {
		this.male = male;
	}

	public double getFemale() {
		return female;
	}

	public void setFemale(double female) {
		this.female = female;
	}

	public double getAge1() {
		return age1;
	}

	public void setAge1(double age1) {
		this.age1 = age1;
	}

	public double getAge2() {
		return age2;
	}

	public void setAge2(double age2) {
		this.age2 = age2;
	}

	public double getAge3() {
		return age3;
	}

	public void setAge3(double age3) {
		this.age3 = age3;
	}

	public double getAge4() {
		return age4;
	}

	public void setAge4(double age4) {
		this.age4 = age4;
	}

	public double getAge5() {
		return age5;
	}

	public void setAge5(double age5) {
		this.age5 = age5;
	}

	public double getMaleTime() {
		return maleTime;
	}

	public void setMaleTime(double maleTime) {
		this.maleTime = maleTime;
	}

	public double getFemaleTime() {
		return femaleTime;
	}

	public void setFemaleTime(double femaleTime) {
		this.femaleTime = femaleTime;
	}

	public double getAge1Time() {
		return age1Time;
	}

	public void setAge1Time(double age1Time) {
		this.age1Time = age1Time;
	}

	public double getAge2Time() {
		return age2Time;
	}

	public void setAge2Time(double age2Time) {
		this.age2Time = age2Time;
	}

	public double getAge3Time() {
		return age3Time;
	}

	public void setAge3Time(double age3Time) {
		this.age3Time = age3Time;
	}

	public double getAge4Time() {
		return age4Time;
	}

	public void setAge4Time(double age4Time) {
		this.age4Time = age4Time;
	}

	public double getAge5Time() {
		return age5Time;
	}

	public void setAge5Time(double age5Time) {
		this.age5Time = age5Time;
	}

//	public String getStatTimeDay() {
//		return statTimeDay;
//	}
//
//	public void setStatTimeDay(String statTimeDay) {
//		this.statTimeDay = statTimeDay;
//	}

	

	// setter and getter method
	


}
