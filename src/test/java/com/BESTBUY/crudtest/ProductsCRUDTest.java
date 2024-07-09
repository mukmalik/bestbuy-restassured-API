package com.BESTBUY.crudtest;

import com.BESTBUY.model.ProductPojo;
import com.BESTBUY.testbase.ProductTestBase;
import com.BESTBUY.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends ProductTestBase
{
    static String name = "Duracell - AA Batteries (8-Pack)" + TestUtils.getRandomValue();
    static String type = "HardWareGoods";
    static double price = 99.99;
    static int shipping = 0;
    static String upc = TestUtils.getRandomValue();
    static String description = "Compatible with select electronic devices";
    static String manufacturer = "DuracellCompany";
    static String model = "DURA550Z";
    static String url = "http://www.bestbuy.com/";
    static String image = TestUtils.getRandomValue();
    static int productId;

    @Test
    public void T1verifyProductCreatedSuccessfully() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);
        productId = response.then().extract().path("id");
        System.out.println("id = " + productId);

    }

    @Test
    public void T2VerifyProductReadSuccessfully() {

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + productId);
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void T3verifyProductUpdateSuccessfully() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Iphone");
        productPojo.setType("Mobile");
        productPojo.setDescription("Oldest Model");
        productPojo.setManufacturer("Apple");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParams("id", productId)
                .when()
                .body(productPojo)
                .patch("/{id}");
        response.then().log().body().statusCode(200);

    }

    @Test
    public void T4VerifyProductDeleteSuccessfully() {

        given()
                .when()
                .delete("/" + productId)
                .then()
                .statusCode(200);

    }

}
