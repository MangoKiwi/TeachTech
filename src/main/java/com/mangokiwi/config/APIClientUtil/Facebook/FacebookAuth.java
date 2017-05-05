package com.mangokiwi.config.APIClientUtil.Facebook;

import com.mangokiwi.model.Token;
import com.mangokiwi.model.User;
import com.mangokiwi.service.TokenService;
import com.mangokiwi.service.UserService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import javax.persistence.EntityNotFoundException;

/**
 * Created by tangmaolei on 5/5/17.
 */
@Component
public class FacebookAuth {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserService userService;

	@Autowired
	private DefaultFacebookClient facebookClient;

	public void retrieveUserDataAndUpdateUserInfo(String tokenVal, Token token) {
		FacebookClient.DebugTokenInfo info = facebookClient.debugToken(tokenVal);
		if(!info.isValid())
			throw new BadCredentialsException("Invalid token");
		try {
			User user = userService.findById(Long.parseLong(info.getUserId()));
			token = tokenService.findByUserId(user.getId());
			token.setAccessToken(tokenVal);
			token.setVendor("facebook");
			token.setExpireAt(info.getExpiresAt());
			token.setIssueAt(info.getIssuedAt());
			tokenService.update(token);
		} catch (EntityNotFoundException entityNotFoundException){
			DefaultFacebookClient userFaceBookClient = new DefaultFacebookClient(tokenVal, Version.LATEST);
			com.restfb.types.User fbUser = userFaceBookClient.fetchObject("me", com.restfb.types.User.class);
			com.mangokiwi.model.User user = new User(Long.parseLong(info.getUserId()), fbUser.getName());
			token = new Token(tokenVal, "facebook", user, info.getIssuedAt(),info.getExpiresAt());
			userService.add(user);
			tokenService.add(token);
		}
	}
}
