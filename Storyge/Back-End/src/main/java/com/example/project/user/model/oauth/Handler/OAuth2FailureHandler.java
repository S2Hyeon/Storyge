package com.example.project.user.model.oauth.Handler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("request getContextPath= " + request.getContextPath());
        System.out.println("request getAuthType= " + request.getAuthType());
        System.out.println("request authenticate= " + request.authenticate(response));
        System.out.println("request getRequestURI= " + request.getRequestURI());
        System.out.println("response = " + response.toString());
        System.out.println("exception = " + exception);
        System.out.println("request changeSessionId= " + request.changeSessionId());
        System.out.println("실패");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
