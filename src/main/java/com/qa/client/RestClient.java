package com.qa.client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestClient
{
    //1. GET method
    public CloseableHttpResponse get (String url) throws IOException
    {
        //Get method
        CloseableHttpClient httpClient = HttpClients.createDefault(); // create 1 client connection/ Abstract class
        HttpGet httpget = new HttpGet(url); // create get connection with this particular URL/ http get request
        //store our Response body//
        CloseableHttpResponse closebleHttpResponse = httpClient.execute(httpget); // like hit the button run in Postman
        return closebleHttpResponse;// returning  http close object
        // closebleHttpResponse - object
    }

    //2. GET method with Headers
    public CloseableHttpResponse get (String url, HashMap<String, String> headerMap) throws IOException
    {
        //Get method
        CloseableHttpClient httpClient = HttpClients.createDefault(); // create 1 client connection/ Abstract class
        HttpGet httpget = new HttpGet(url); // create get connection with this particular URL/ http get request

        for(Map.Entry<String, String> entry : headerMap.entrySet())
        {
            httpget.addHeader(entry.getKey(), entry.getValue()); // set the header value
        }
        CloseableHttpResponse closebleHttpResponse = httpClient.execute(httpget); // like hit the button run in Postman
        return closebleHttpResponse;// returning  http close object
        // closebleHttpResponse - object
    }

    //POST method
    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws IOException
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();   // Create Client connection
        HttpPost httpPost = new HttpPost(url);                          // Generate Post request
        httpPost.setEntity(new StringEntity(entityString));             // Define Payload data

        // for headers set the headers
        for(Map.Entry<String, String> entry : headerMap.entrySet())
        {
            httpPost.addHeader(entry.getKey(), entry.getValue()); // Define (set) the header value
        }
        // execution  POST request
        CloseableHttpResponse closebleHttpResponse = httpClient.execute(httpPost);
        return closebleHttpResponse; //and returns CloseableHttpResponse we are getting response body
    }














//    //Status code
//    int statusCode = closebleHttpResponse.getStatusLine().getStatusCode(); //we getting status code/ return integer
//        System.out.println("Status Code is : " + statusCode);
//
//    //Json string
//    String responseBodyString = EntityUtils.toString(closebleHttpResponse.getEntity(), "UTF-8"); // we get body response entire
//    JSONObject responseJsonBody = new JSONObject(responseBodyString);// parsing to JSON format
//        System.out.println("Response body JSON : " + responseJsonBody);
//
//    //All headers
//    Header[] headerArray = closebleHttpResponse.getAllHeaders(); // we store header of response array
//    HashMap<String, String> allHeaders = new HashMap<String, String>();
//        for(Header header : headerArray)  // we set to hashmap all the value from headerArray
//    {
//        allHeaders.put(header.getName(), header.getValue());
//    }
//        System.out.println("Headers Array is : " + allHeaders);
}
