package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utilities.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetAPItest extends TestBase
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

    @Test(priority = 1)
    public void getApiTextWithoutHeaders() throws ClientProtocolException, IOException
    {
        //===========================================================================//
        restClient = new RestClient(); //object RestClient class
        //restClient.get(url); // String url
        closebleHttpResponse = restClient.get(url); // we store in this particular object reference
        //=============================================================================//
        //Status code
        int statusCode = closebleHttpResponse.getStatusLine().getStatusCode(); //we getting status code/ return integer
        System.out.println("Status Code is : " + statusCode);
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");// Assertion equals (ecsual, expected)
        //=============================================================================//
        //Json string
        String responseBodyString = EntityUtils.toString(closebleHttpResponse.getEntity(), "UTF-8"); // we get body response entire
        JSONObject responseJsonBody = new JSONObject(responseBodyString);// parsing to JSON format
        System.out.println("Response body JSON : " + responseJsonBody);

       // String s = TestUtil.getValueByJPath(responseJsonBody,"/per_page");
       // String s = TestUtil.getValueByJPath(responseJsonBody,"/per_page");
       // String s = TestUtil.getValueByJPath(responseJsonBody,"/per_page");
        //--------------------------------------------------------------------------//
        String pageValue = TestUtil.getValueByJPath(responseJsonBody,"/page");
        System.out.println("Page value : " + pageValue);
        //single value assertion
                           //parsing from String to integer and compare
        Assert.assertEquals(Integer.parseInt(pageValue), 1, "Value of page is not equals");
        //-------------------------------------------------------------------------//
        String prePageValue = TestUtil.getValueByJPath(responseJsonBody,"per_page");
        System.out.println("Pre page value : " + prePageValue);
        //single value assertion
                           //parsing from String to integer and compare
        Assert.assertEquals(Integer.parseInt(prePageValue), 3, "Value of pre page is not equals");
        //-------------------------------------------------------------------------//
        //get the value from the JSON Array
        String id = TestUtil.getValueByJPath(responseJsonBody, "data[0]/id");
        System.out.println("ID : " + id);
        String email = TestUtil.getValueByJPath(responseJsonBody, "data[0]/email");
        System.out.println("Email : " + email);
        String firstName = TestUtil.getValueByJPath(responseJsonBody, "data[0]/first_name");
        System.out.println("First name : " + firstName);
        String lastName = TestUtil.getValueByJPath(responseJsonBody, "data[0]/last_name");
        System.out.println("Last name : " + lastName);
        String avatar = TestUtil.getValueByJPath(responseJsonBody, "data[0]/avatar");
        System.out.println("Avatar : " + avatar);
        //=============================================================================//
        //All headers
        Header[] headerArray = closebleHttpResponse.getAllHeaders(); // we store header of response array
        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for(Header header : headerArray)  // we set to hashmap all the value from headerArray
        {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers Array is : " + allHeaders);
        //=============================================================================//

    }
//=====================================================================================================================//
@Test(priority = 2)
public void getApiTextWithHeaders() throws ClientProtocolException, IOException
{
    //===========================================================================//
    restClient = new RestClient(); //object RestClient class

    HashMap<String, String> headerMap = new HashMap<String, String>(); // hash map for data
    headerMap.put("Content-Type", "application/json; charset=utf-8"); // put data to header
//    headerMap.put("Transfer-Encoding", "chunked"); // put data to header
//    headerMap.put("Connection", "keep-alive"); // put data to header
//    headerMap.put("Content-Type", "application/json; charset=utf-8"); // put data to header

    closebleHttpResponse = restClient.get(url, headerMap); // we store in this particular object reference
    //=============================================================================//
    //Status code
    int statusCode = closebleHttpResponse.getStatusLine().getStatusCode(); //we getting status code/ return integer
    System.out.println("Status Code is : " + statusCode);
    Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");// Assertion equals (ecsual, expected)
    //=============================================================================//
    //Json string
    String responseBodyString = EntityUtils.toString(closebleHttpResponse.getEntity(), "UTF-8"); // we get body response entire
    JSONObject responseJsonBody = new JSONObject(responseBodyString);// parsing to JSON format
    System.out.println("Response body JSON : " + responseJsonBody);

    // String s = TestUtil.getValueByJPath(responseJsonBody,"/per_page");
    // String s = TestUtil.getValueByJPath(responseJsonBody,"/per_page");
    // String s = TestUtil.getValueByJPath(responseJsonBody,"/per_page");
    //--------------------------------------------------------------------------//
    String pageValue = TestUtil.getValueByJPath(responseJsonBody,"/page");
    System.out.println("Page value : " + pageValue);
    //single value assertion
    //parsing from String to integer and compare
    Assert.assertEquals(Integer.parseInt(pageValue), 1, "Value of page is not equals");
    //-------------------------------------------------------------------------//
    String prePageValue = TestUtil.getValueByJPath(responseJsonBody,"per_page");
    System.out.println("Pre page value : " + prePageValue);
    //single value assertion
    //parsing from String to integer and compare
    Assert.assertEquals(Integer.parseInt(prePageValue), 3, "Value of pre page is not equals");
    //-------------------------------------------------------------------------//
    //get the value from the JSON Array
    String id = TestUtil.getValueByJPath(responseJsonBody, "data[0]/id");
    System.out.println("ID : " + id);
    String email = TestUtil.getValueByJPath(responseJsonBody, "data[0]/email");
    System.out.println("Email : " + email);
    String firstName = TestUtil.getValueByJPath(responseJsonBody, "data[0]/first_name");
    System.out.println("First name : " + firstName);
    String lastName = TestUtil.getValueByJPath(responseJsonBody, "data[0]/last_name");
    System.out.println("Last name : " + lastName);
    String avatar = TestUtil.getValueByJPath(responseJsonBody, "data[0]/avatar");
    System.out.println("Avatar : " + avatar);
    //=============================================================================//
    //All headers
    Header[] headerArray = closebleHttpResponse.getAllHeaders(); // we store header of response array
    HashMap<String, String> allHeaders = new HashMap<String, String>();
    for(Header header : headerArray)  // we set to hashmap all the value from headerArray
    {
        allHeaders.put(header.getName(), header.getValue());
    }
    System.out.println("Headers Array is : " + allHeaders);
    //=============================================================================//

}


}
