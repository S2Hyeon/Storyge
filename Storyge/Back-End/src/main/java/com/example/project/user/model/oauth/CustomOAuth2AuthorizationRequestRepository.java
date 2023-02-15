package com.example.project.user.model.oauth;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomOAuth2AuthorizationRequestRepository<T extends OAuth2AuthorizationRequest> implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private final ClientRegistrationRepository clientRegistrationRepository;

    public CustomOAuth2AuthorizationRequestRepository(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    private static String expandRedirectUri(HttpServletRequest request, ClientRegistration clientRegistration) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("registrationId", clientRegistration.getRegistrationId());
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(
                        UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .fragment(null)
                .build();

        String scheme = uriComponents.getScheme();
        uriVariables.put("baseScheme", (scheme != null) ? scheme : "");
        String host = uriComponents.getHost();
        uriVariables.put("baseHost", (host != null) ? host : "");
        int port = uriComponents.getPort();
        uriVariables.put("basePort", (port == -1) ? "" : ":" + port);
        String path = uriComponents.getPath();
        if (org.springframework.util.StringUtils.hasLength(path)) {
            if (path.charAt(0) != '/') {
                path = '/' + path;
            }
        }
        uriVariables.put("basePath", (path != null) ? path : "");
        uriVariables.put("baseUrl", uriComponents.toUriString());
        uriVariables.put("action", ("login" != null) ? "login" : "");
        return UriComponentsBuilder.fromUriString(clientRegistration.getRedirectUri())
                .buildAndExpand(uriVariables)
                .toUriString();
    }

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return null;
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        String registrationId = request.getParameter("state");
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(registrationId);
        OAuth2AuthorizationRequest.Builder builder = OAuth2AuthorizationRequest.authorizationCode()
                .attributes((attrs) -> attrs.put(OAuth2ParameterNames.REGISTRATION_ID, clientRegistration.getRegistrationId()));


        String redirectUri = expandRedirectUri(request, clientRegistration);
        OAuth2AuthorizationRequest build = builder.clientId(clientRegistration.getClientId())
                .authorizationUri(clientRegistration.getProviderDetails().getAuthorizationUri())
                .redirectUri(redirectUri)
                .scopes(clientRegistration.getScopes())
                .state(registrationId).build();

        return build;
    }
}
