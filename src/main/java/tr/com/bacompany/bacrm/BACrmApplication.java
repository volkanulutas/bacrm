package tr.com.bacompany.bacrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BACrmApplication {
    public static void main(String[] args) {
        SpringApplication.run(BACrmApplication.class, args);
    }
}