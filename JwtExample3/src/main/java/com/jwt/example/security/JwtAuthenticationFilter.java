package com.jwt.example.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
		@Autowired
	    private JwtHelper jwtHelper;

	    @Autowired
	    private UserDetailsService userDetailsService;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain)
	            throws ServletException, IOException {

	        String authHeader = request.getHeader("Authorization");
	       // logger.info("heder": authHeader);
	        String token = null;
	        String username = null;

	        // 1. Extract token from Authorization header
			/*
			 * if (authHeader != null && authHeader.startsWith("Bearer ")) { token =
			 * authHeader.substring(7);
			 * 
			 * try { username= jwtHelper.getUsernameFromToken(token); // 2. Extract username
			 * from token // Claims claims = jwtHelper.getAllClaimsForToken(token);
			 * //username = claims.getSubject(); } catch (Exception e) {
			 * System.out.println("Invalid token: " + e.getMessage()); } }
			 */
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            // 1. Extract the token by removing the "Bearer " prefix
	            token = authHeader.substring(7);

	            try {
	                // 2. Extract username from token (validates signature, structure, etc.)
	                username = jwtHelper.getUsernameFromToken(token);
	                //Claims claims = jwtHelper.getAllClaimsForToken(token);
	                //username = claims.getSubject();

	            } catch (io.jsonwebtoken.ExpiredJwtException e) {
	                // Thrown when the token is expired
	                System.out.println("JWT token is expired: " + e.getMessage());

	            } catch (io.jsonwebtoken.MalformedJwtException e) {
	                // Thrown when the token is not valid JWT (corrupted or broken)
	                System.out.println("Invalid JWT token (malformed): " + e.getMessage());

	            } catch (io.jsonwebtoken.UnsupportedJwtException e) {
	                // Thrown when the JWT token is using an unsupported format
	                System.out.println("JWT token is unsupported: " + e.getMessage());

	            } catch (io.jsonwebtoken.security.SignatureException e) {
	                // Thrown when the JWT signature is invalid
	                System.out.println("JWT signature does not match: " + e.getMessage());

	            } catch (IllegalArgumentException e) {
	                // Thrown when claims string is empty or null
	                System.out.println("JWT token string is empty or null: " + e.getMessage());

	            } catch (Exception e) {
	                // Any other exception
	                System.out.println("An error occurred while parsing JWT: " + e.getMessage());
	            }
	        }

	        // 3. Validate token and set authentication
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	            if (jwtHelper.validateToken(token, userDetails.getUsername())) {
	                UsernamePasswordAuthenticationToken authenticationToken =
	                        new UsernamePasswordAuthenticationToken(
	                                userDetails, null, userDetails.getAuthorities()
	                        );
	                authenticationToken.setDetails(
	                        new WebAuthenticationDetailsSource().buildDetails(request)
	                );

	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            }
	        }

	        // 4. Continue with filter chain
	        filterChain.doFilter(request, response);
	    }
		
	

}
