package com.vmware.backend.repository;


import com.vmware.backend.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long> {
    public AuthenticationToken findByToken(String authenticationToken);
}
