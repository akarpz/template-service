package org.template.functional;

import static com.jayway.restassured.RestAssured.*;
import static org.mockito.ArgumentMatchers.*;

import org.template.user.User;
import com.jayway.restassured.http.ContentType;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.google.gson.Gson;

public class UserControllerFunctionalTest extends TemplateServiceFunctionalTestBase {

    private static final String ENDPOINT = "api/v1/users";

    private static final Gson GSON = new Gson();

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testCreateUserWithValidData() {

        User user = new User();
        user.setEmailAddress("akarpowich94@gmail.com");
        user.setFirstName("Adam");
        user.setLastName("Karpowich");


        Mockito.when(namedParameterJdbcTemplate.update(anyString(), anyMap())).thenReturn(1);

        given()
            .when()
            .contentType(ContentType.JSON)
            .body(GSON.toJson(user))
            .post(ENDPOINT)
            .then()
            .statusCode(200);
    }


}
