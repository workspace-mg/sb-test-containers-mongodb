package com.gajula.sbtestcontainersmongodb;

import com.gajula.sbtestcontainersmongodb.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Testcontainers
class SbTestContainersMongodbApplicationTests {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    TestRestTemplate testRestTemplate = new TestRestTemplate();
   
    @Test
    public void whenPostMethodIsInvokedThenUserIsCreatedAndResponseShouldHaveId() {
       User user = new User(null,"test-first","test-last","test-country");
        User user1 = testRestTemplate
                .postForObject("http://localhost:8080/users", user, User.class);
        Assertions.assertEquals(user.getFirstName(), user1.getFirstName());
        Assertions.assertEquals(user.getLastName(), user1.getLastName());
        Assertions.assertEquals(user.getCountry(), user1.getCountry());
        Assertions.assertNotNull(user1.getId());
    }

    @Test
    public void givenUserExistsWhenGetMethodIsInvokedPerIdThenUserObjectIsReturned() {
        List<?> userList = testRestTemplate
                .getForObject("http://localhost:8080/users", List.class);
        Assertions.assertEquals(1, userList.size());
    }

}
