package com.github.darogina.ideas.service;

import com.github.darogina.ideas.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends CrudService<UserEntity, Long>, UserDetailsService {
}
