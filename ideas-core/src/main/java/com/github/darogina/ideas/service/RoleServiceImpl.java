package com.github.darogina.ideas.service;

import com.github.darogina.ideas.entity.RoleEntity;
import com.github.darogina.ideas.repository.RoleRepository;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.inject.Named;

@Named("roleService")
public class RoleServiceImpl extends AbstractCrudService<RoleEntity, Long, RoleRepository> implements RoleService {

    @Override @Inject @Named("roleRepository")
    public void setRepository(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoleEntity findByAuthority(String authority) {
        Assert.hasText(authority, "An authority must be supplied");
        return repository.findByAuthority(authority);
    }
}
