package com.github.darogina.ideas.initializer;

import com.github.darogina.ideas.entity.RoleEntity;
import com.github.darogina.ideas.entity.UserConnectionEntity;
import com.github.darogina.ideas.entity.UserEntity;
import com.github.darogina.ideas.enums.UserRole;
import com.github.darogina.ideas.service.RoleService;
import com.github.darogina.ideas.service.UserService;
import com.google.common.collect.Sets;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named("userInitializer")
public class UserInitializer {

    private static final String   USER_USERNAME = "testuser";
    private static final String   USER_PASSWORD = "password";
    private static final UserRole USER_ROLE     = UserRole.ROLE_USER;

    @Inject
    @Named("userService")
    private UserService userService;

    @Inject
    @Named("roleService")
    private RoleService roleService;

    @PostConstruct
    public void setup() {
        userService.deleteAll();

        RoleEntity roleEntity = roleService.findByAuthority(USER_ROLE.toString());
        Assert.notNull(roleEntity);
        Assert.notNull(roleEntity.getId());


        UserEntity userEntity = new UserEntity(USER_USERNAME, USER_PASSWORD, Sets.newHashSet(roleEntity));
        UserConnectionEntity userConnectionEntity = new UserConnectionEntity(userEntity, "google", "googleUserId",
                1, "User Display", "profile url", "image url", "access token", "secret", "refresh", new Date().getTime() + 10000);
        userEntity.getUserConnections().add(userConnectionEntity);

        userEntity = userService.create(userEntity);
        Assert.notNull(userEntity);
        Assert.notNull(userEntity.getId());
    }
}
