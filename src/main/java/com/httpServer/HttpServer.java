package com.httpServer;

/*Driver class for HTTP Server*/

import com.httpServer.config.Configuration;
import com.httpServer.config.ConfigurationManager;
import com.httpServer.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {
        LOGGER.info("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Current Port: " + conf.getPort());
        LOGGER.info("Current Webroot: " + conf.getWebroot());

        ServerListenerThread serverListenerThread = null;
        try {
            serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        serverListenerThread.start();
    }
}