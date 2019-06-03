package br.com.rk;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Rhuan Karlus
 * @since 6/3/19
 */
@SpringBootApplication(scanBasePackages = "br.com.rk")
public class IntegrationTestContextConfiguration implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(IntegrationTestContextConfiguration.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) {
        // nothing but tests
    }

}
