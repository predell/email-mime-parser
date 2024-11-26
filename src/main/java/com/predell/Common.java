package com.predell;

import com.predell.configuration.AppConfig;

import java.util.Properties;

/**
 *
 */
public class Common {

    /**
     *
     * @param charSet Charset
     * @return Fallback charset
     */
    public static String getFallbackCharset(String charSet) {
        Properties charSetMap;
        charSetMap = AppConfig.getInstance().getCharSetMap();
        charSet = charSetMap.getProperty(charSet.toLowerCase(), charSet);
        return charSet;
    }
}
