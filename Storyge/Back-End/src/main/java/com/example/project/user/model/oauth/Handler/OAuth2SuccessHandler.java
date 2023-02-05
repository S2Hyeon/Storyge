package com.example.project.user.model.oauth.Handler;

import com.example.project.user.model.jwt.TokenInfo;
import com.example.project.user.model.jwt.TokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("성공");
        clearAuthenticationAttributes(request);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to ");
            return;
        }
        // rest 안되서 직접 넣어줘야함
        // json파일(토큰)을 만들어서 넘겨줘야함
        // jwtUtil 헤더에 담어줄 때마다
        clearAuthenticationAttributes(request);
        TokenInfo tokenInfo = tokenProvider.generateToken(authentication);
        ObjectMapper om = new ObjectMapper();
        String jsonStr = null;

        PrintWriter writer = response.getWriter();
        try {
            jsonStr = om.writeValueAsString(tokenInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        writer.print(jsonStr);
}

}
