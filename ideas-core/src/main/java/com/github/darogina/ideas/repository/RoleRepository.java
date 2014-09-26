package com.github.darogina.ideas.repository;

import com.github.darogina.ideas.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * Find Role by authority
     *
     * @param authority
     * @return
     */
    RoleEntity findByAuthority(String authority);
}
