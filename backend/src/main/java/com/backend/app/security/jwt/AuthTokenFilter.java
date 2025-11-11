package com.backend.app.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.app.security.user.AppUserDetailsService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
  
  private JwtUtils jwtUtils;
  private AppUserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = parseJwt(request);
      if (StringUtils.hasText(jwt) && jwtUtils.validatedToken(jwt)) {
        String username = jwtUtils.getUsernameFromToken(jwt);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (JwtException e) {
      if (!response.isCommitted()) {
        response.setStatus(401);
        response.getWriter().write(e.getMessage() + " : Invalid or expired token");
      }
      return;
    } catch (Exception e) {
      if (!response.isCommitted()) {
        response.setStatus(500);
        response.getWriter().write(e.getMessage());
      }
      return;
    }
    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }
    return null;
  }
}
