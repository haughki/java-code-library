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
import java.net.MalformedURLException;
import java.net.ProtocolException;
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

    private void javaGet() throws MalformedURLException, ProtocolException {

        String target = "http://www.google.com/search?q=developer";

        URL targetUrl = new URL(target);
        // https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html
        // "Each HttpURLConnection instance is used to make a single request but the underlying network connection to 
        // the HTTP server may be transparently shared by other instances. Calling the close() methods on the 
        // InputStream or OutputStream of an HttpURLConnection after a request may free network resources associated 
        // with this instance but has no effect on any shared persistent connection. Calling the disconnect() method 
        // may close the underlying socket if a persistent connection is otherwise idle at that time."
        // 
        // Take this to mean that:
        // - you should, literally, create a new HttpURLConnection for each request
        // - you should close any streams after you're done with the request
        // - there's an underlying TCP connection which is persisted and shared.
        //
        // More info on java persistent connections: https://docs.oracle.com/javase/8/docs/technotes/guides/net/http-keepalive.html
        HttpURLConnection con;
        try {
            con = (HttpURLConnection) targetUrl.openConnection();
        } catch (IOException e) {
            e.printStackTrace(); throw new IllegalStateException("Couldn't open connection.", e);
        }

        con.setRequestMethod("GET");  // optional default is GET  
        con.setRequestProperty("User-Agent", USER_AGENT);  //add request header
        
        System.out.println("\nSending GET via javaGet to: " + target);
        int responseCode;
        String responseMessage;
        try {
            responseCode = con.getResponseCode();
            responseMessage = con.getResponseMessage();
        } catch (IOException e) {
            e.printStackTrace(); throw new IllegalStateException("Couldn't get response code.", e);
        }

        
        System.out.println("javaGet response code/msg: " + responseCode + " " + responseMessage);

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.print(line);
            }
        } catch (IOException e) {
            e.printStackTrace();

            try (BufferedReader errRd = new BufferedReader(new InputStreamReader(con.getErrorStream()))) {
                String line;
                while ((line = errRd.readLine()) != null) {
                    System.out.print(line);
                }
            } catch (IOException e1) {
                System.err.println("Error reading connection error stream.  Ugh.");
                e1.printStackTrace();  // TODO: really shouldn't swallow this error -- somehow rethrow...
            } 
            
            throw new IllegalStateException("Error reading connection input stream.", e); // throw with original error
        }
    }
}

