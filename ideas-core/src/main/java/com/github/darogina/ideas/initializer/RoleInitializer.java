package com.github.darogina.ideas.initializer;

import com.github.darogina.ideas.entity.RoleEntity;
import com.github.darogina.ideas.enums.UserRole;
import com.github.darogina.ideas.service.RoleService;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named("roleInitializer")
public class RoleInitializer {

    @Inject
    @Named("roleService")
    private RoleService roleService;

    @PostConstruct
    public void initialize() {
        for (UserRole role : UserRole.values()) {
            if (roleService.findByAuthority(role.toString()) == null) {
                final RoleEntity entity = new RoleEntity(role);

                Assert.notNull(roleService.create(entity), "Unable to create RoleEntity for " + role);
            }
        }
    }
}
