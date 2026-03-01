package com.httpServer.config;

import java.io.FileReader;

public class ConfigurationManager {

    /* We are using a singleton here because we don't need more than one configuration manager */
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {
    }

    /* If we don't have any configurationManager, we create one */

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null) {
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    }

    /* Loads a configuration file by the path provided */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = new FileReader(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        while((int i = FileReader.read()) != -1)
    }

    /* Return the current loaded configuration */
    public void getCurrentConfiguration(){}
}