package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import com.github.jmkgreen.morphia.annotations.Entity;

@Entity("elong.Review")
public class Review {
	/**
	 * 点评数
	 */
	private int count;
	/**
	 * 好评数
	 */
	private int good;
	/**
	 * 差评数
	 */
	private int poor;
	/**
	 * 评分
	 */
	private String score;
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getPoor() {
		return poor;
	}
	public void setPoor(int poor) {
		this.poor = poor;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
