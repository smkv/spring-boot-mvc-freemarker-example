package ee.smkv.springbootmvc;

import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentBasedPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertiesPropertySource createPropertySource(String name, EncodedResource resource) throws IOException {
        Properties configProperties = getProperties(resource);
        Properties environmentProperties = getProperties(getEncodedResource(configProperties.getProperty("environment") + File.separator + "config.properties"));

        Properties finalProperties = new Properties();
        finalProperties.putAll(environmentProperties);
        finalProperties.putAll(configProperties);

        return new PropertiesPropertySource("config",finalProperties);
    }

    private EncodedResource getEncodedResource(String file) {
        return new EncodedResource(new ClassPathResource(file));
    }

    private Properties getProperties(EncodedResource resource) throws IOException {
        Properties properties = new Properties();
        properties.load(resource.getReader());
        return properties;
    }
}
