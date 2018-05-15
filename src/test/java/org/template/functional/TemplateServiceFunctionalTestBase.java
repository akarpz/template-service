package org.template.functional;

import org.template.Application;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


//TODO: fix this configuration so that application starts up correctly
//Functional tests will currently run but you need to start the app separately
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class })
@ActiveProfiles("test")
@WebAppConfiguration
public abstract class TemplateServiceFunctionalTestBase {

    @Before
    public void setUp() {
        RestAssured.port = 8080;
    }
}