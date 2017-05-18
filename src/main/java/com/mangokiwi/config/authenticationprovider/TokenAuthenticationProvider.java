package com.mangokiwi.config.authenticationprovider;

import com.mangokiwi.config.APIClientUtil.Facebook.FacebookAuth;
import com.mangokiwi.core.exception.EntityNotFoundException;
import com.mangokiwi.core.exception.VendorNotFoundException;
import com.mangokiwi.model.Token;
import com.mangokiwi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Created by zhenfeng on 5/4/17.
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private FacebookAuth facebookAuth;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Map<String, Optional<String>> tokenPackage = (Map<String, Optional<String>>) authentication.getPrincipal();
        Optional<String> tokenVal = tokenPackage.get("Auth-Token");
        Optional<String> vendorVal = tokenPackage.get("Auth-Vendor");
        if (!tokenVal.isPresent() || tokenVal.get().isEmpty() || !vendorVal.isPresent() || vendorVal.get().isEmpty()) {
            throw new BadCredentialsException("Token Required");
        }
        Token token = null;
        try {
            token = tokenService.getTokenByAccessTokenAndVendor(tokenVal.get(), vendorVal.get());
            if(tokenService.checkTokenExpired(token)){
                throw new  BadCredentialsException("Token expired");
            }
        } catch (EntityNotFoundException entityNotFoundException){
            // should use face link here
            switch (vendorVal.get()) {
                case "facebook":
                    facebookAuth.retrieveUserDataAndUpdateUserInfo(tokenVal.get(), token);
                    break;
                default:
                    throw new VendorNotFoundException("Unknown vendor");
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
