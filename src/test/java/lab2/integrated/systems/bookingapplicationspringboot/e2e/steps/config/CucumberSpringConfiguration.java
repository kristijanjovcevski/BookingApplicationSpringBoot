package lab2.integrated.systems.bookingapplicationspringboot.e2e.steps.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@TestContainers
@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {

        @Container
        static PostgreSQLContainer<?> postgres = new PostgresSQLContainer<>("postgres:17")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

        @DynamicPropertySource
        static void configureProperties(DynamicPropertySource registry) {
                registry.add("spring.datasource.url", postgres::getJdbcUrl);
                registry.add("spring.datasource.username", postgres::getUsername);
                registry.add("spring.datasource.password", postgres::getPassword);
        }
}
