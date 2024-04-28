package com.raza.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.raza.blog.utils.CommonUtils;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private UserDetailsService userDetailsService;

	// this method is executed whenever we call any rest endpoints
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwtToken = getJwtFromRequest(request);
		System.out.println("doFilterInternal() " + jwtToken);
		System.out.println("doFilterInternal() " + CommonUtils.hasText(jwtToken));

		if (requiresAuthentication(request) && jwtProvider.validatioToken(jwtToken) && CommonUtils.hasText(jwtToken)) {
			String username = jwtProvider.getUserNameFromJWT(jwtToken);
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		} else {
			// If no JWT token is provided, check if the request requires authentication.
			// If it does not require authentication, proceed with the filter chain.
			if (!requiresAuthentication(request)) {
				filterChain.doFilter(request, response);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

	private boolean requiresAuthentication(HttpServletRequest request) {
		String path = request.getRequestURI();
		// Add additional conditions if necessary to exempt certain endpoints from
		// authentication.
		boolean isRequired = !path.startsWith("/api/auth"); // Skip authentication for authentication endpoints
		return isRequired;
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String jwtToken = request.getHeader(AUTHORIZATION);
		logger.debug("getJwtFromRequest() " + jwtToken);
		System.out.println("getJwtFromRequest() " + jwtToken);
		if (CommonUtils.hasText(jwtToken) && jwtToken.startsWith(BEARER)) {
			return jwtToken.substring(7);
		}
		return jwtToken;

	}

}
