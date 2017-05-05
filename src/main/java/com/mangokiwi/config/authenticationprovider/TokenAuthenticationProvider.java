package com.mangokiwi.config.authenticationprovider;

import com.mangokiwi.model.Token;
import com.mangokiwi.model.User;
import com.mangokiwi.service.TokenService;
import com.mangokiwi.service.UserService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Created by zhenfeng on 5/4/17.
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultFacebookClient facebookClient;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<String> tokenVal = (Optional<String>) authentication.getPrincipal();
        if (!tokenVal.isPresent() || tokenVal.get().isEmpty()) {
            throw new BadCredentialsException("Token Required");
        }
        Token token = null;
        try {
            token = tokenService.findByAccessToken(tokenVal.get());
            if(tokenService.checkTokenExpired(token)){
                throw new  BadCredentialsException("Token expired");
            }
        }catch (EntityNotFoundException entityNotFoundException){
            // should use face link here
            FacebookClient.DebugTokenInfo info = facebookClient.debugToken(tokenVal.get());
            if(!info.isValid())
                throw new BadCredentialsException("Invalid token");
            try {
                User user = userService.findById(Long.parseLong(info.getUserId()));
                token = tokenService.findByUserId(user.getId());
                token.setAccessToken(tokenVal.get());
                token.setExpireAt(info.getExpiresAt());
                token.setIssueAt(info.getIssuedAt());
                tokenService.update(token);
            }catch (EntityNotFoundException anotherEntityNotFoundException){
                DefaultFacebookClient userFaceBookClient = new DefaultFacebookClient(tokenVal.get(), Version.LATEST);
                com.restfb.types.User fbUser = userFaceBookClient.fetchObject("me", com.restfb.types.User.class);
                com.mangokiwi.model.User user = new User(Long.parseLong(info.getUserId()), fbUser.getName());
                token = new Token(tokenVal.get(), user,info.getIssuedAt(),info.getExpiresAt());
                userService.add(user);
                tokenService.add(token);
            }
        }
        authentication.setAuthenticated(true);
        return authentication;
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
