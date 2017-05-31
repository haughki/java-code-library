package org.haughki.codeLibrary;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleHttp {
    private final String USER_AGENT = "Mozilla/5.0";
    public static void main(String[] args) throws Exception {

        SimpleHttp http = new SimpleHttp();

        System.out.println("Testing 1 - Send Http GET request");
        http.apacheGet();
        System.out.println();
        http.javaGet();
    }
    
    private void apacheGet() throws IOException {
        String target = "http://www.google.com/search?q=developer";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(target);
        request.addHeader("User-Agent", USER_AGENT);
        // The underlying HTTP connection is still held by the response object to allow the response content to be 
        // streamed directly from the network socket.  In order to ensure correct de-allocation of system resources the 
        // user MUST call CloseableHttpResponse#close() from a finally clause.  Please note that if response content is 
        // not fully consumed, the underlying connection cannot be safely re-used and will be shut down and discarded
        // by the connection manager.
        System.out.println("\nSending GET via apacheGet to: " + target);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println("apacheGet response code: " + response.getStatusLine());

            HttpEntity entity = response.getEntity();
            
            try (BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    System.out.print(line);
                }
            }
            
            // Ensure response body is fully consumed
            EntityUtils.consume(entity);
        }
    }

    private void javaGet() throws Exception {

        String target = "http://www.google.com/search?q=developer";

        URL targetUrl = new URL(target);
        HttpURLConnection con = (HttpURLConnection) targetUrl.openConnection();
        
        con.setRequestMethod("GET");  // optional default is GET  
        con.setRequestProperty("User-Agent", USER_AGENT);  //add request header
        
        System.out.println("\nSending GET via javaGet to: " + target);
        int responseCode = con.getResponseCode();

        System.out.println("javaGet response code: " + responseCode);

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.print(line);
            }
        }
    }
}

