package com.mangokiwi.config.authenticationprovider;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by tangmaolei on 5/5/17.
 */
public class VendorNotFoundException extends AuthenticationException {
	public VendorNotFoundException(String msg) {
		super(msg);
	}
}
