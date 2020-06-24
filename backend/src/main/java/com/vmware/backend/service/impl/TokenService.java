package com.vmware.backend.service.impl;
import java.util.UUID;

import com.vmware.backend.model.AuthenticationToken;
import com.vmware.backend.model.User;
import com.vmware.backend.repository.AuthenticationTokenRepository;
import com.vmware.backend.service.ITokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements ITokenService {

    //@Autowired
    //private ModelMapper modelMapper;

    @Autowired
    private AuthenticationTokenRepository authenticationTokenRepository;

    @Override
    public AuthenticationToken createAuthenticationToken(final User user) {

        AuthenticationToken accessToken = new AuthenticationToken(user, UUID.randomUUID().toString());
        return accessToken; //this.authenticationTokenRepository.save(accessToken);
    }

    @Override
    public User findUserByAccessToken(final String token) {
        AuthenticationToken accessToken = this.authenticationTokenRepository.findByToken(token);

        if (null == accessToken) {
            return null;
        }

        if (accessToken.isExpired()) {
            this.authenticationTokenRepository.delete(accessToken);
            return null;
        }

        return accessToken.getUser();
    }
}
