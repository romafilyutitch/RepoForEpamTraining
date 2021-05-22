package com.epam.jwd.final_task.properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolProperties {
    private static final String CONNECTION_POOL_PROPERTIES_PATH = "src\\main\\resources\\connectionPool.properties";
    private static final Properties connectionPoolProperties ;
    static {
        connectionPoolProperties = new Properties();
        try {
            connectionPoolProperties.load(new BufferedReader(new FileReader(CONNECTION_POOL_PROPERTIES_PATH)));
        } catch (IOException e) {
            throw new RuntimeException("Cannot get connection pool properties file", e);
        }
    }

    public static String getUrl() {
        return connectionPoolProperties.getProperty("url");
    }

    public static String getUserName() {
        return connectionPoolProperties.getProperty("user");
    }

    public static String getPassword() {
        return connectionPoolProperties.getProperty("password");
    }

    public static int getMinimalPoolSize() {
        return Integer.parseInt(connectionPoolProperties.getProperty("minimalPoolSize"));
    }

    public static int getMaximalPoolSize() {
        return Integer.parseInt(connectionPoolProperties.getProperty("maximalPoolSize"));

    }

    public static int getResizeQuantity() {
        return Integer.parseInt(connectionPoolProperties.getProperty("resizeQuantity"));
    }

    public static int getPoolResizeTimerTaskCheckDelayTime() {
        return Integer.parseInt(connectionPoolProperties.getProperty("poolResizeTimerTask.checkDelay"));
    }

    public static int getPoolResizeTimerTaskCheckPeriodTime() {
        return Integer.parseInt(connectionPoolProperties.getProperty("poolResizeTimerTask.checkPeriod"));
    }

    public static double getPoolResizeTimerTaskResizeFactor() {
        return Double.parseDouble(connectionPoolProperties.getProperty("poolResizeTimerTask.resizeFactor"));
    }


}
