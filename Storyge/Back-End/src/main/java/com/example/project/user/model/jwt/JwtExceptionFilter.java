//package com.example.project.user.model.jwt;
//
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JwtExceptionFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            filterChain.doFilter(request, response);
//        } catch (TokenExpiredException e) {
//            setErrorResponse(response, e);
//        }
//    }
//
//    private void setErrorResponse(HttpServletResponse response, TokenExpiredException e) throws IOException, IOException {
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setCharacterEncoding("UTF-8");
//        Map<String, Object> body = new HashMap<>();
//        body.put("status", HttpStatus.UNAUTHORIZED.value());
//        body.put("error", "Unauthorized");
//        body.put("message", e.getMessage());
//        ObjectMapper om = new ObjectMapper();
//        om.writeValue(response.getOutputStream(), body);
//    }
//}
