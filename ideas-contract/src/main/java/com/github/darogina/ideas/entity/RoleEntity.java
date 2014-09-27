package com.github.darogina.ideas.entity;

import com.github.darogina.ideas.enums.UserRole;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * A Role is the permission a User has as pertains to Knappsack or a domain.
 */
@Entity
@Audited
@Table(name = "Role")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class RoleEntity extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = -117212611936641518L;

    private String authority;

    RoleEntity() {}

    public RoleEntity(final UserRole userRole) {
        Assert.notNull(userRole);
        this.authority = userRole.name().toUpperCase();
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Transient
    public UserRole getUserRole() {
        return UserRole.valueOf(this.authority);
    }
}
