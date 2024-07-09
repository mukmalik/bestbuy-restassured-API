package com.BESTBUY.testbase;

import com.BESTBUY.utils.PropertyReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class ProductTestBase
{
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
//        propertyReader = PropertyReader.getInstance();
//        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
//        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
//        RestAssured.basePath = Path.PRODUCTS;

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";

    }

}
