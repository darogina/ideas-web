package com.github.darogina.ideas.service;

import com.github.darogina.ideas.entity.UserEntity;
import com.github.darogina.ideas.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import javax.inject.Named;

@Named("userService")
public class UserServiceImpl extends AbstractCrudService<UserEntity, Long, UserRepository> implements UserService {

    @Override @Inject
    @Named("userRepository")
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
