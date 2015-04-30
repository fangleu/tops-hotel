package com.travelzen.tops.hotel.elong.mongo.entity.base;


public interface MorphiaEntity<I> {

	I getId();

	void setId(I id);

	void setId(String id);

}
