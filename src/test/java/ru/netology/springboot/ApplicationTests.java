package ru.netology.springboot;

//import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer devappContainer = new GenericContainer("devapp:1.0")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer prodappContainer = new GenericContainer("prodapp:1.0")
            .withExposedPorts(8081);

    /*
    @BeforeAll
    public static void setUp() {
        devappContainer.start();
        prodappContainer.start();
    }
    */

    @Test
    void contextLoadsDevapp() {
        Integer devappPort = devappContainer.getMappedPort(8080);

        ResponseEntity<String> devappEntity = restTemplate.getForEntity("http://localhost:" + devappPort + "/profile", String.class);

        System.out.println("devapp: " + devappEntity.getBody());

        assertEquals("Current profile is dev", devappEntity.getBody());
    }

    @Test
    void contextLoadsProdapp() {
        Integer prodappPort = prodappContainer.getMappedPort(8081);

        ResponseEntity<String> prodappEntity = restTemplate.getForEntity("http://localhost:" + prodappPort + "/profile", String.class);

        System.out.println("prodapp: " + prodappEntity.getBody());

        assertEquals("Current profile is production", prodappEntity.getBody());
    }

}
