package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.nio.file.Paths;

@Configuration
public class GoogleCloudConfig {

    @Value("${google.cloud.credentials.path}")
    private String credentialsPath;

    @PostConstruct
    public void init() {
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", credentialsPath);
    }

    // Getters and other methods if necessary
    public String getCredentialsPath() {
        return credentialsPath;
    }
}