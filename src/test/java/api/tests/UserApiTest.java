package api.tests;

import api.base.ApiSpecs;
import api.base.BaseApiTest;
import api.clients.UserClient;
import io.restassured.response.Response;
import models.CreateUserRequest;
import models.User;
import org.bouncycastle.asn1.ocsp.Request;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserApiTest extends BaseApiTest {

    @Test
    public void getUsersTest() {
        UserClient userClient = new UserClient();
        Response response = userClient.getUsers();
        response.prettyPrint();
        response.then()
                .spec(ApiSpecs.success200Spec());
    }

    @Test
    public void createUserTest() {
        UserClient userClient = new UserClient();

        CreateUserRequest request =
                new CreateUserRequest("Yiğit", "SDET");

        Response response =
                userClient.createUser(request);

        response.then()
                .spec(ApiSpecs.success201Spec());
        Assert.assertEquals(response.jsonPath().getString("name"), "Yiğit");
        Assert.assertEquals(response.jsonPath().getString("job"), "SDET");
        Assert.assertNotNull(response.jsonPath().getString("id"));

    }

    @Test
    public void getUsersAsPojoTest() {

        UserClient userClient = new UserClient();

        Response response = userClient.getUsers();

        response.then()
                .spec(ApiSpecs.success200Spec());

        List<User> users =
                response.jsonPath().getList("", User.class);

        Assert.assertFalse(users.isEmpty());

        Assert.assertEquals(
                users.get(0).getName(),
                "Leanne Graham"
        );

        Assert.assertEquals(
                users.get(0).getEmail(),
                "Sincere@april.biz"
        );
    }
}