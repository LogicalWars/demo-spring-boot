package ru.netology.demospringboot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private final static int PORT = 8080;

    private final static GenericContainer<?> devContainer = new GenericContainer<>("app-dev").withExposedPorts(PORT);
    private final static GenericContainer<?> prodContainer = new GenericContainer<>("app-prod").withExposedPorts(PORT);

    @BeforeAll
    public static void setUp() {
        devContainer.start();
        prodContainer.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> devEntity = restTemplate.getForEntity("http://localhost:" + devContainer.getMappedPort(PORT) + "/profile", String.class);
        ResponseEntity<String> prodEntity = restTemplate.getForEntity("http://localhost:" + prodContainer.getMappedPort(PORT) + "/profile", String.class);

        assertEquals("Current profile is dev", devEntity.getBody());
        assertEquals("Current profile is production", prodEntity.getBody());
    }
}
