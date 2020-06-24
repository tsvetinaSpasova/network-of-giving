package com.vmware.backend.repository;


import com.vmware.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

public User getUserByUsername(String username);
}
