package org.mmlak.organizer.config;

import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

public interface AuthorizationDatabase {
    OAuth2AuthorizationRequest getById(String id);

    void save(String key, OAuth2AuthorizationRequest authorizationRequest);

    OAuth2AuthorizationRequest remove(String id);
}
