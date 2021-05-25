package com.epam.jwd.final_task.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolProperties {
    private static final String CONNECTION_POOL_PROPERTIES_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "connectionPool.properties";
    private static final Properties connectionPoolProperties;
    public static final String URL_KEY = "url";
    public static final String USER_KEY = "user";
    public static final String PASSWORD_KEY = "password";
    public static final String MINIMAL_POOL_SIZE_KEY = "minimalPoolSize";
    public static final String MAXIMAL_POOL_SIZE_KEY = "maximalPoolSize";
    public static final String RESIZE_QUANTITY_KEY = "resizeQuantity";
    public static final String POOL_RESIZE_TIMER_TASK_CHECK_DELAY_KEY = "poolResizeTimerTask.checkDelay";
    public static final String POOL_RESIZE_TIMER_TASK_CHECK_PERIOD_KEY = "poolResizeTimerTask.checkPeriod";
    public static final String POOL_RESIZE_TIMER_TASK_RESIZE_FACTOR_KEY = "poolResizeTimerTask.resizeFactor";

    static {
        connectionPoolProperties = new Properties();
        try {
            connectionPoolProperties.load(new BufferedReader(new FileReader(CONNECTION_POOL_PROPERTIES_PATH)));
        } catch (IOException e) {
            throw new RuntimeException("Cannot get connection pool properties file", e);
        }
    }

    public static String getUrl() {
        return connectionPoolProperties.getProperty(URL_KEY);
    }

    public static String getUserName() {
        return connectionPoolProperties.getProperty(USER_KEY);
    }

    public static String getPassword() {
        return connectionPoolProperties.getProperty(PASSWORD_KEY);
    }

    public static int getMinimalPoolSize() {
        return Integer.parseInt(connectionPoolProperties.getProperty(MINIMAL_POOL_SIZE_KEY));
    }

    public static int getMaximalPoolSize() {
        return Integer.parseInt(connectionPoolProperties.getProperty(MAXIMAL_POOL_SIZE_KEY));

    }

    public static int getResizeQuantity() {
        return Integer.parseInt(connectionPoolProperties.getProperty(RESIZE_QUANTITY_KEY));
    }

    public static int getPoolResizeTimerTaskCheckDelayTime() {
        return Integer.parseInt(connectionPoolProperties.getProperty(POOL_RESIZE_TIMER_TASK_CHECK_DELAY_KEY));
    }

    public static int getPoolResizeTimerTaskCheckPeriodTime() {
        return Integer.parseInt(connectionPoolProperties.getProperty(POOL_RESIZE_TIMER_TASK_CHECK_PERIOD_KEY));
    }

    public static double getPoolResizeTimerTaskResizeFactor() {
        return Double.parseDouble(connectionPoolProperties.getProperty(POOL_RESIZE_TIMER_TASK_RESIZE_FACTOR_KEY));
    }


}
