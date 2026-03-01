package com.httpServer.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.httpServer.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpConnectTimeoutException;

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
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(filePath);
        }
        catch(FileNotFoundException e){
            throw new HttpConfigurationException(e);
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i;
        try {
            while((i = fileReader.read()) != -1){
                stringBuilder.append((char)i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(stringBuilder.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing JSON configuration file", e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Configuration file internal",e);
        }
    }

    /* Return the current loaded configuration */
    public Configuration getCurrentConfiguration(){
        if(myCurrentConfiguration == null){
            throw new HttpConfigurationException("No Current Configuration Set.");
        }
        return myCurrentConfiguration;
    }
}