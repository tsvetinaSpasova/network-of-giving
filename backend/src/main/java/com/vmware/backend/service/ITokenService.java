package com.vmware.backend.service;

import com.vmware.backend.model.AuthenticationToken;
import com.vmware.backend.model.User;

public interface ITokenService {
    /**
     * Creates a new token for the user and returns its {@link AuthenticationToken}.
     * It may add it to the token list or replace the previous one for the user. Never returns {@code null}.
     */
    AuthenticationToken createAuthenticationToken(User user);

    /** Returns user details for a token. */
    User findUserByAccessToken(String token);

}