package com.mangokiwi.config.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by zhenfeng on 5/4/17.
 */
public class AuthenticationFilter extends GenericFilterBean {

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = asHttp(servletRequest);
        HttpServletResponse httpResponse = asHttp(servletResponse);

        Optional<String> token = Optional.ofNullable(httpRequest.getHeader("Auth-Token"));

        try {
            if (token.isPresent()) {
                processTokenAuthentication(token);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch(AuthenticationException authenticationException){
            SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
        }
    }

    private HttpServletRequest asHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    private HttpServletResponse asHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

    private void processTokenAuthentication(Optional<String> token) {
        Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
    }

    private Authentication tryToAuthenticateWithToken(Optional<String> token) {
        PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token, null);
        return tryToAuthenticate(requestAuthentication);
    }

    private Authentication tryToAuthenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
        }
        return responseAuthentication;
    }

}
