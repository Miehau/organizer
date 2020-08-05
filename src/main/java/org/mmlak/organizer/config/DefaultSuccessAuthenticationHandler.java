package org.mmlak.organizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DefaultSuccessAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final String token = tokenProvider.createToken(authentication);
        final String urlWithToken = UriComponentsBuilder.fromUriString("http://localhost:8081/dupa")
                .queryParam("token", token)
                .build().toString();
        getRedirectStrategy().sendRedirect(request, response, urlWithToken);
    }


}
