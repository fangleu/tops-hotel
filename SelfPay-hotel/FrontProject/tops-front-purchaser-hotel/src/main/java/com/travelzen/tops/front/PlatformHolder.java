package com.travelzen.tops.front;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.tops.member.common.enums.Platform;

public class PlatformHolder {

    private static final String DISTRIBUTOR = "distributor";
    private static final Logger LGR = LoggerFactory.getLogger(PlatformHolder.class);

	public static Platform get() {
	    try {
	        InputStream stream = PlatformHolder.class.getResourceAsStream("/platform");
	        if (stream != null) {
	            String str = IOUtils.toString(stream);
	            if (DISTRIBUTOR.equalsIgnoreCase(str.trim())) {
	                return Platform.DISTRIBUTOR;
	            }
	        }
	        if (DISTRIBUTOR.equalsIgnoreCase(System.getProperty("platform"))) {
	            return Platform.DISTRIBUTOR;
	        }
	        return Platform.PURCHASER;
        } catch (Exception e) {
            LGR.warn("fetch platfrom info failed, set to PURCHASER.");
            return Platform.PURCHASER;
        }
	}
	
}
