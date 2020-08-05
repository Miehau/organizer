package org.mmlak.organizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Repository
public class DefaultAuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String REDIRECT_URI_COOKIE_NAME = "redirect_uri";

    @Autowired
    private AuthorizationDatabase authorizationDatabase;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return authorizationDatabase.getById(getState(request));
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        authorizationDatabase.save(authorizationRequest.getState(), authorizationRequest);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return authorizationDatabase.remove(getState(request));
    }

    private String getState(HttpServletRequest request) {
        return request.getParameter(OAuth2ParameterNames.STATE);
    }
}
