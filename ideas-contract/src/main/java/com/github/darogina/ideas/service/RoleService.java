package com.github.darogina.ideas.service;

import com.github.darogina.ideas.entity.RoleEntity;

public interface RoleService extends CrudService<RoleEntity, Long> {
    RoleEntity findByAuthority(String authority);
}
