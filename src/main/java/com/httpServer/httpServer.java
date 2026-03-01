package com.httpServer;

/*Driver class for HTTP Server*/

import com.httpServer.config.Configuration;
import com.httpServer.config.ConfigurationManager;

public class httpServer {
    public static void main(String[] args) {
        System.out.println("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Current Port: " + conf.getPort());
        System.out.println("Current Webroot: " + conf.getWebroot());
    }
}