package ee.smkv.springbootmvc;

import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

public class EnvironmentBasedPropertySourceFactory implements PropertySourceFactory {

    public static final String CONFIG_PROPERTIES_FILE_NAME = "config.properties";

    @Override
    public PropertiesPropertySource createPropertySource(String name, EncodedResource ignore) throws IOException {
        Properties configProperties = getProperties(getEncodedResource(CONFIG_PROPERTIES_FILE_NAME));
        Properties environmentProperties = getProperties(getEncodedResource(configProperties.getProperty("environment") + File.separator + CONFIG_PROPERTIES_FILE_NAME));

        Properties finalProperties = new Properties();
        finalProperties.putAll(environmentProperties);
        finalProperties.putAll(configProperties);

        return new PropertiesPropertySource("config", finalProperties);
    }

    private EncodedResource getEncodedResource(String file) {
        return new EncodedResource(new ClassPathResource(file), Charset.forName("UTF-8"));
    }

    private Properties getProperties(EncodedResource resource) throws IOException {
        Properties properties = new Properties();
        properties.load(resource.getReader());
        return properties;
    }
}
