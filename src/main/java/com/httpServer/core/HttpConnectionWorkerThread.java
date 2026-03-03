package com.httpServer.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private Socket socket;
    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            /*To read from the socket*/

            outputStream = socket.getOutputStream();
            /*To write to the socket*/

            /*Defining the page to send to the browser*/
            String html = "<html><head><title>Simple Java HTTP Server</title></head><body>This page was served using my simple Java HTTP server.</body></html>";

            final String CRLF = "\r\n";
            String response =
                    "HTTP/1.1 200 OK" + CRLF + // This is the Status Line: It entails HTTP VERSION, RESPONSE_CODE, RESPONSE_MESSAGE
                            "Content-Length: " + html.getBytes().length + CRLF + //This is the Header line.
                            CRLF +
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            LOGGER.info("Connection processing finished.");
        } catch (IOException e) {
            LOGGER.info("Problem with the connection.", e);
        } finally{
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
    }
}
