package com.travelzen.tops.hotel.elong.mongo.dao.base.impl;

import java.util.List;

/**
 * 分页结果集
 * @author wangmeng
 *
 * @param <T>
 */
public class PagingResult<T> {

	private long totalCount;
	private Paging paging;
	private List<T> result;

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
}
