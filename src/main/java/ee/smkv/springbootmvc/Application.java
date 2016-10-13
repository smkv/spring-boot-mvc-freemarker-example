package ee.smkv.springbootmvc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "config.properties" ,factory = EnvironmentBasedPropertySourceFactory.class )
public class Application {


}
