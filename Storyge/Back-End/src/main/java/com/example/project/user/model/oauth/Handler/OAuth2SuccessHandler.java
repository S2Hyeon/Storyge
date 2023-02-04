package com.example.project.user.model.oauth.Handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("성공");
//
//        if (response.isCommitted()) {
//            logger.debug("Response has already been committed. Unable to redirect to ");
//            return;
//        }
//        clearAuthenticationAttributes(request);
//        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication); // tokenInfo 만들어서
//        ObjectMapper om = new ObjectMapper();
//        String jsonStr = null;
//        PrintWriter writer = response.getWriter();
//        try {
//            jsonStr = om.writeValueAsString(tokenInfo);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        //writer에 써줌(json으로 response)
//        writer.print(jsonStr);
    }

//    @Override
//    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
//        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth/redirect")
//                .build().toUriString();
//    }

}
