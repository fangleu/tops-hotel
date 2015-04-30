package com.travelzen.tops.hotel.elong.common.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.tops.hotel.elong.common.constants.CommonConstants;
import com.travelzen.tops.hotel.elong.common.factory.ConfigurationFactory;
import com.travelzen.tops.hotel.elong.common.utils.ElongConfiguration;

public class ElongConfigurationFactory extends ConfigurationFactory   {
	
	private static Logger LOG = LoggerFactory.getLogger(ElongConfigurationFactory.class);
	
	static{
		configuration = new ElongConfiguration();
		try {
			byte[] confContentBytes = TopsConfReader.getConfContent(CommonConstants.ConfigurationPath.ELONG_CONFIGURATION_PATH.path(), ConfScope.R);
			configuration.addResource(confContentBytes);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}
	
	public static ElongConfiguration getConfiguration(){
		return configuration;
	}

}
