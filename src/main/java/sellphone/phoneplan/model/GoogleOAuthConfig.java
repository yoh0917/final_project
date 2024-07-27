package sellphone.phoneplan.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleOAuthConfig {

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.auth.uri}")
    private String authUri;

    @Value("${google.token.uri}")
    private String tokenUri;

    @Value("${google.auth.provider.cert.url}")
    private String authProviderCertUrl;

    // Getters
    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getAuthUri() {
        return authUri;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public String getAuthProviderCertUrl() {
        return authProviderCertUrl;
    }
}
