package org.mmlak.organizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
//@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    //    @Autowired
//    private UserDetailsService userDetailsService;
//
    @Value("${security.oauth2.client.client-id:glee-o-meter}")
    private String clientId;
    //
    @Value("${security.oauth2.client.client-secret:secret}")
    private String clientSecret;

    //
//    @Value("${jwt.signing-key:123}")
//    private String jwtSigningKey;
//
    @Value("${jwt.accessTokenValidititySeconds:43200}") // 12 hours
    private int accessTokenValiditySeconds;
    //
//    @Value("${jwt.authorizedGrantTypes:password,authorization_code,refresh_token,client-credentials}")
//    private String[] authorizedGrantTypes;
//
    @Value("${jwt.refreshTokenValiditySeconds:2592000}") // 30 days
    private int refreshTokenValiditySeconds;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(clientId)
                .secret(clientSecret)
                .scopes("read")
                .authorizedGrantTypes("password", "client_credentials")
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}