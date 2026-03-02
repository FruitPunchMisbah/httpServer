package com.httpServer;

/*Driver class for HTTP Server*/

import com.httpServer.config.Configuration;
import com.httpServer.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class httpServer {
    public static void main(String[] args) {
        System.out.println("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Current Port: " + conf.getPort());
        System.out.println("Current Webroot: " + conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            /*To read from the socket*/
            InputStream inputStream = socket.getInputStream();
            /*To write to the socket*/
            OutputStream outputStream = socket.getOutputStream();

            /*Defining the page to send to the browser*/
            String html = "<html><head><title>Simple Java HTTP Server</title></head><body>This page was served using my simple Java HTTP server.</body></html>";

            final String CRLF = "\r\n";
            String response =
                    "HTTP/1.1 200 OK" + CRLF + // This is the Status Line: It entails HTTP VERSION, RESPONSE_CODE, RESPONSE_MESSAGE
                    "Content-Length: " + html.getBytes().length + CRLF + //This is the Header line.
                            CRLF+
                            html+
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}