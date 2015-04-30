package com.travelzen.tops.hotel.elong.mongo.json.hotsuggest;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class SuggestTagDataNode {
    @JSONField(name = "TagName")
	private String tagName;
    @JSONField(name = "SuggestDatas")
	private List<SuggestData> suggestDatas;
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public List<SuggestData> getSuggestDatas() {
		return suggestDatas;
	}
	public void setSuggestDatas(List<SuggestData> suggestDatas) {
		this.suggestDatas = suggestDatas;
	}
}
