package my.benzourry.ebooking.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
//@EntityScan(basePackages = {"my.unimas.ebooking.core.model"})

@Configuration
@ComponentScan(basePackages = {"my.unimas.ebooking"})
@EnableJpaRepositories(basePackages = "my.unimas.ebooking.core.dao")
@EntityScan(basePackages = {"my.unimas.ebooking.core.model"})
@EnableAutoConfiguration
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
