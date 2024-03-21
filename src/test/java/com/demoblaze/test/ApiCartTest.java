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

public class ApiCartTest {
    public String addToCartUrl = "https://api.demoblaze.com/addtocart";
    public String viewCartUrl = "https://api.demoblaze.com/viewcart";

    // USING API CALL - METHOD TO ADD ANY ITEM TO THE CART
    private void addItemToCart(String id, int prodId, boolean flag) {
        // Item "Samsung galaxy s7" -> for get Request Payload data please refer to the file: src/test/resources.txt
        // Item "Iphone 6 32gb" -> for get Request Payload data please refer to the file: src/test/resources.txt
        OkHttpClient client = new OkHttpClient();                                       // Add a new OkHTTP client;
        String cookie = ApiCallData.COOKIE;                                             // Get data for the "cookie" key;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8"); // Provide Media Type as JSON;
        String jsonRequestBody = String.format("{\"id\": \"%s\", \"cookie\": \"%s\", \"flag\": %s, \"prod_id\": %d}",
                id, cookie, flag, prodId);                                            // Create JSON request body
        RequestBody requestBody = RequestBody.create(JSON, jsonRequestBody);            // Create a new object as JSON.

        Request request = new Request.Builder()                                         // Create a new Request object;
                .url(addToCartUrl)                                                      // define the URL;
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

    // USING API CALL - ADD ITEM ID=4 TO THE CART
    @Test (testName = "API_add_Item_4_to_Cart")
    // Item "Samsung galaxy s7" -> for get Request Payload data please refer to the file: src/test/resources.txt
    public void addItem_4_toCart() {
        addItemToCart(ApiCallData.ID_4, ApiCallData.PROD_ID_4, ApiCallData.FLAG); // Add item with the method using data.
    }

    // USING API CALL - ADD ITEM ID=5 TO THE CART
    @Test (testName = "API_add_Item_5_to_Cart")
    public void addItem_5_toCart() {
        addItemToCart(ApiCallData.ID_5, ApiCallData.PROD_ID_5, ApiCallData.FLAG); // Add item with the method using data.
    }

    // USING API CALL - CHECK ITEMS PRESENT IN THE CART
    @Test (testName = "API_check_Item_4_and_Item_5_are_in_cart")
    public void checkItem_4_andItem_5_areInCart() throws IOException {
        OkHttpClient client = new OkHttpClient();                           // Create new instance;
        String cookie = ApiCallData.COOKIE;                                 // Get cookies from the constant;

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");        // Set format: JSON;
        String jsonRequestBody = String.format("{\"cookie\": \"%s\", \"flag\": true}", cookie);// Req.body - Structure;
        RequestBody requestBody = RequestBody.create(JSON, jsonRequestBody);//R.B. will be created with JSON & Structure.

        Request request = new Request.Builder()                         // Construct HTTP request:
                .url(viewCartUrl)                                       // define the URL;
                .method("POST", requestBody)                     // define the method POST with Req.Body;
                .header("Content-Type", "application/json") // define the header;
                .build();

        try(Response response = client.newCall(request).execute()) {    // Execute the Request
            Assert.assertEquals(response.code(),200,"Status expected 200, but get :" + response.code());
            assert response.body() != null;
            String responseBody = response.body().string();             // Convert Response Body to the String;
            System.out.println("Response body :" + responseBody);       // Print out Response Body;
            Assert.assertTrue(responseBody.contains(ApiCallData.ID_4), "Item with ID=4 is not in the cart");
            Assert.assertTrue(responseBody.contains(ApiCallData.ID_5), "Item with ID=5 is not in the cart");
        } catch (IOException e) {                                       // If IOException is found ->
            throw new RuntimeException(e);                              // -> create Runtime Exception;
        }
    }
}
