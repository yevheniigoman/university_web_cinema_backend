//package com.iasaweb.cinema;
//
//import com.iasaweb.cinema.entity.User;
//import com.iasaweb.cinema.service.JwtService;
//import com.iasaweb.cinema.service.UserService;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import org.jspecify.annotations.NonNull;
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private static final String AUTH_HEADER = "Authorization";
//    private static final String BEARER_PREFIX = "Bearer ";
//
//    private final JwtService jwtService;
//    private final UserService userService;
//
//    public JwtAuthenticationFilter(JwtService jwtService, UserService userService) {
//        this.jwtService = jwtService;
//        this.userService = userService;
//    }
//
//    @Override
//    protected void doFilterInternal(
//        @NonNull HttpServletRequest request,
//        @NonNull HttpServletResponse response,
//        @NonNull FilterChain filterChain
//    ) throws IOException, ServletException {
//        String authHeader = request.getHeader(AUTH_HEADER);
//        if (authHeader == null || authHeader.isEmpty() || authHeader.startsWith(BEARER_PREFIX)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String token = authHeader.substring(BEARER_PREFIX.length());
//        String username = jwtService.extractUserName(token);
//        if (username == null || username.isEmpty()) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        User user = userService.findByName(username);
//        if (!jwtService.isExpired(token)) {
//            var authToken = new UsernamePasswordAuthenticationToken(user, null);
//            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            var context = SecurityContextHolder.createEmptyContext();
//            context.setAuthentication(authToken);
//            SecurityContextHolder.setContext(context);
//        }
//        filterChain.doFilter(request, response);
//    }
//}
