package com.gildata.byinterserver.quartz;

import com.gildata.byinterserver.constant.Constant;
import com.gildata.byinterserver.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by chenchen on 2018/11/19.
 */
public class Init {
    private final static Logger logger = LoggerFactory.getLogger(Init.class);

    private static Properties property = null;

    public static void init() {
        property = PropertiesUtil.getProps(Constant.PROP_FILE_NAME);
    }

    public static Properties getProperty() {
        return property;
    }
}
