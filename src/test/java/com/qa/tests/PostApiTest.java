package com.qa.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostApiTest extends TestBase
{
    TestBase testBase;
    String serviceUrl;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse closebleHttpResponse; // we define method from RestClient to class level like global variable

    @BeforeMethod
    public void setUp()
    {
        testBase = new TestBase(); //initialize Base class
        serviceUrl = prop.getProperty("URL"); //URL from property file
        apiUrl = prop.getProperty("serviceURL");
        //target is get URL from property file
        url = serviceUrl + apiUrl; // add together
    }

    @Test
    public void postApiTest() throws IOException
    {
        restClient = new RestClient(); //object RestClient class
        HashMap<String, String> headerMap = new HashMap<String, String>(); // hash map for data
        headerMap.put("Content-Type", "application/json; charset=utf-8"); // put data to header

        //jackson API // Marshalling from java to json
        ObjectMapper mapper = new ObjectMapper(); // from <groupId>com.fasterxml.jackson.core</groupId>
        Users users = new Users("Leader", "Morpheus"); //expected object user

        //object Users convert to json file and store into File.json
        mapper.writeValue(new File("C:\\Users\\liesn\\IdeaProjects\\restapi_test_java\\src\\main\\java\\com\\qa\\data\\users.json"), users);
        // https://jsonlint.com/  -- json online validator

        // java object to json in String
        String usersJsonString = mapper.writeValueAsString(users);
        System.out.println(usersJsonString);

        // we call a POST call and store it in closebleHttpResponse variable
        closebleHttpResponse = restClient.post(url, usersJsonString, headerMap);

        //1. status code check
        int statusCode = closebleHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);

        //2. to JsonString convert
        String responseString = EntityUtils.toString(closebleHttpResponse.getEntity(), "UTF-8");

        // from String to original Json body
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("The response from API is :  " +  responseJson);

        // convert json to java object//    read value from Users.class
        Users userResponseObj = mapper.readValue(responseString, Users.class); //returns object// actual users object
        System.out.println("User response is :  " + userResponseObj );

        //print
        Assert.assertTrue(users.getName().equals(userResponseObj.getName()));

        Assert.assertTrue(users.getJob().equals(userResponseObj.getJob()));

        System.out.println(userResponseObj.getJob());
        System.out.println(userResponseObj.getName());

    }

}
// solved Jackson problem site -- https://blog.tratif.com/2017/12/03/dealing-with-value-objects-in-rest-api-with-jackson/