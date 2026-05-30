package api.clients;

import config.ConfigReader;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import models.CreateUserRequest;
import api.base.ApiSpecs;
import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserClient {

    public Response getUsers() {

        return given()
                .spec(ApiSpecs.requestSpec())
                .when()
                .get("/users");
    }
    public Response getUsersPage(int page) {

        return given()
                .queryParam("page", page)
                .when()
                .get("/api/users");
    }
    public Response createUser(CreateUserRequest request) {
        return given()
                .spec(ApiSpecs.requestSpec())
                .body(request)
                .when()
                .post("/posts");
    }
}