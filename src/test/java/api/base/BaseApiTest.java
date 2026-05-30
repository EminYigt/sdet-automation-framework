package api.base;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    @BeforeClass
    public void setupApi() {

        RestAssured.baseURI =
                ConfigReader.get("apiBaseUrl");
    }
}