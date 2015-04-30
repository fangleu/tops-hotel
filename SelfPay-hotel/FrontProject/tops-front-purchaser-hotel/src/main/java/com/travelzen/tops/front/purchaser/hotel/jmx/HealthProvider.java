package com.travelzen.tops.front.purchaser.hotel.jmx;

import org.springframework.stereotype.Component;

import com.travelzen.framework.quality.jmx.HealthLevel;
import com.travelzen.framework.quality.jmx.IHealthProvider;

@Component
public class HealthProvider implements IHealthProvider {

	@Override
	public HealthLevel getHealthLevel() {
		return HealthLevel.HEALTHY;
	}

	@Override
	public String getHealthInfo() {
		return "Running";
	}

}
