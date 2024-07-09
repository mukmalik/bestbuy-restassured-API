package com.BESTBUY.crudtest;

import com.BESTBUY.model.StorePojo;
import com.BESTBUY.testbase.StoreTestBase;
import com.BESTBUY.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoreCRUDTest extends StoreTestBase
{
    static int storeId;
    static String name = "Promotion Store";
    static String type = "Shop";
    static String address = "1234 Road";
    static String address2 = "Wembley";
    static String city = "Brent";
    static String state = "London";
    static String zip = "12345";
    static double lat = 12.3456;
    static double lng = -65.4321;
    static String hours = "Mon: 9-5; Tue: 9-5; Wed: 9-5; Thurs: 9-5; Fri: 9-5; Sat: 9-5; Sun: 9-5";

    @Test
    public void T1verifyStoreCreatedSuccessfully() {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);


        Response response = given().log().ifValidationFails()

                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);

        storeId = response.then().extract().path("id");
        System.out.println("ID = " + storeId);
    }

    @Test
    public void T2VerifyStoreReadSuccessfully() {

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + storeId);
        response.then().statusCode(200);

        response.prettyPrint();

    }

    @Test
    public void T3verifyStoreUpdateSuccessfully() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName("Westfield");
        storePojo.setType(type + TestUtils.getRandomValue());
        storePojo.setCity("London");
        storePojo.setHours("Mon: 8-5; Tue: 8-5; Wed: 8-5; Thurs: 8-5; Fri: 8-5; Sat: 8-5; Sun: 8-5");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParams("id", storeId)
                .when()
                .body(storePojo)
                .patch("/{id}");
        response.then().log().body().statusCode(200);

    }

    @Test
    public void T4VerifyStoreDeleteSuccessfully() {

        given()
                .when()
                .delete("/" + storeId)
                .then()
                .statusCode(200);

    }

}
