package com.demoblaze.test;

import com.demoblaze.testdata.ApiCallData;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CartTest {

    // USING API CALL - ADD ITEM ID=4 TO THE CART
    @Test (testName = "API_add_Item_4_to_Cart")
    public void addItem_4_toCart() {
        // Item "Samsung galaxy s7" -> for get Request Payload data please refer to the file: src/test/resources.txt
        OkHttpClient client = new OkHttpClient();                                       // Add a new OkHTTP client;

        String cookie = ApiCallData.COOKIE;                                             // Get data for the "cookie" key;
        String id4 = ApiCallData.ID_4;                                                  // Get data for the "id" key;
        int prodId4 = ApiCallData.PROD_ID_4;                                            // Get data for the "prodId" key;
        boolean flag = ApiCallData.FLAG;                                                // Get data for the "flag" key;

        MediaType JSON = MediaType.parse("application/json; charset=utf-8"); // Provide Media Type as JSON;
        String jsonRequestBody = String.format("{\"id\": \"%s\", \"cookie\": \"%s\", \"flag\": %s, \"prod_id\": %d}",
                id4, cookie, flag, prodId4);                                            // Create JSON request body
        RequestBody requestBody = RequestBody.create(JSON, jsonRequestBody);            // Create a new object as JSON.

        Request request = new Request.Builder()                                         // Create a new Request object;
                .url("https://api.demoblaze.com/addtocart")                             // define the URL;
                .method("POST", requestBody)                                    // define the method;
                .header("Content-Type", "application/json")                 // define the header;
                .build();

        try (Response response = client.newCall(request).execute()) {                   // Execute the request;
            Assert.assertEquals(response.code(), 200);
            System.out.println("Status code: " + response.code());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // USING API CALL - ADD ITEM ID=5 TO THE CART
    @Test (testName = "API_add_Item_5_to_Cart")
    public void addItem_5_toCart() {
        // Item "Iphone 6 32gb" -> for get Request Payload data please refer to the file: src/test/resources.txt
        OkHttpClient client = new OkHttpClient();                                       // Add a new OkHTTP client;

        String cookie = ApiCallData.COOKIE;                                             // Get data for the "cookie" key;
        String id5 = ApiCallData.ID_5;                                                  // Get data for the "id" key;
        int prodId5 = ApiCallData.PROD_ID_5;                                            // Get data for the "prodId" key;
        boolean flag = ApiCallData.FLAG;                                                // Get data for the "flag" key;

        MediaType JSON = MediaType.parse("application/json; charset=utf-8"); // Provide Media Type as JSON;
        String jsonRequestBody = String.format("{\"id\": \"%s\", \"cookie\": \"%s\", \"flag\": %s, \"prod_id\": %d}",
                id5, cookie, flag, prodId5);                                            // Create JSON request body;
        RequestBody requestBody = RequestBody.create(JSON, jsonRequestBody);            // Create a new object as JSON.

        Request request = new Request.Builder()                                         // Create a new Request object;
                .url("https://api.demoblaze.com/addtocart")                             // define the URL;
                .method("POST", requestBody)                                    // define the method;
                .header("Content-Type", "application/json")                 // define the header;
                .build();

        try (Response response = client.newCall(request).execute()) {                   // Execute the request;
            Assert.assertEquals(response.code(), 200);
            System.out.println("Status code: " + response.code());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
