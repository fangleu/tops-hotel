package com.travelzen.tops.hotel.elong.mongo.dao.base.impl;

import java.io.Serializable;

public class Paging implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 5117222246393694247L;
    private int offset;
    private int limit;

    public Paging() {

    }

    public Paging(int pOffset, int pLimit) {
	offset = pOffset;
	limit = pLimit;
    }

    public int getOffset() {
	if(offset<0){
	    offset = 0;
	}
	return offset;
    }

    public void setOffset(int pOffset) {
	offset = pOffset;
    }

    public int getLimit() {
	if(limit<=0){
	    limit = Integer.MAX_VALUE;
	}
	return limit;
    }

    public void setLimit(int pLimit) {
	limit = pLimit;
    }
}
