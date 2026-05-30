package api.base;

import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ApiSpecs {

    private ApiSpecs() {
    }

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("apiBaseUrl"))
                .setContentType(ContentType.JSON)
                .build();
    }
    public static ResponseSpecification success200Spec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification success201Spec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }
}