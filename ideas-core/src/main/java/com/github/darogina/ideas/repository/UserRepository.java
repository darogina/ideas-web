package com.github.darogina.ideas.repository;

import com.github.darogina.ideas.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find user by username
     *
     * @param username
     * @return
     */
    UserEntity findByUsername(String username);
}
