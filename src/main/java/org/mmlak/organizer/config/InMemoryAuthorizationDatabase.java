package org.mmlak.organizer.config;

import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryAuthorizationDatabase implements AuthorizationDatabase {

    private final Map<String, OAuth2AuthorizationRequest> REQUESTS = new HashMap<>();

    @Override
    public OAuth2AuthorizationRequest getById(final String id) {
        return REQUESTS.get(id);
    }

    @Override
    public void save(String key, OAuth2AuthorizationRequest authorizationRequest) {
        REQUESTS.put(key, authorizationRequest);
    }

    @Override
    public OAuth2AuthorizationRequest remove(String id) {
        return REQUESTS.remove(id);
    }
}
