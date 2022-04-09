package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class SimpleTests {

//    @Test
//    void listUserTest() {
//        given()
//                .contentType(JSON)
//                .when()
//                .get("https://reqres.in/api/users?page=2")
//                .then()
//                .body("data.last_name",is("Lawson")); можно ли получить данные определенного человека?
//    }

    @Test
    void singleUserTest(){
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void singleUserNotFoundTest(){
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    String userData = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

    @Test
    void createUserTest() {
        given()
                .body(userData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);
    }

    String userData1 = "{ \"name\": \"neo\", \"job\": \"zion resident\" }";

    @Test
    void updateUserTest() {
        given()
                .body(userData1)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users/2")
                .then()
                .body("name", is("neo"));
    }

    String userData2 = "{ \"email\": \"sydney@fife\" }";

    @Test
    void registerUserTest() {
        given()
                .body(userData2)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }


}
