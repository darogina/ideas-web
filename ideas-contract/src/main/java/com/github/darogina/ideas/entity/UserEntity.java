package com.github.darogina.ideas.entity;

import org.hibernate.envers.Audited;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Audited
@Table(name = "User")
public class UserEntity extends BaseEntity implements UserDetails, CredentialsContainer {

    @Column(nullable = false, unique = true)
    private final String username;

    @Size(min = 8)
    private String password;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleEntity> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<UserConnectionEntity> userConnections;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    //~ Constructors ===================================================================================================

    public UserEntity(String username, String password, Set<? extends RoleEntity> roles) {
        this(username, password, true, true, true, true, roles, null);
    }

    /**
     * Construct the <code>User</code> with the details required by
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
     *
     * @param username              the username presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param password              the password that should be presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param enabled               set to <code>true</code> if the user is enabled
     * @param accountNonExpired     set to <code>true</code> if the account has not
     *                              expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials
     *                              have not expired
     * @param accountNonLocked      set to <code>true</code> if the account is not
     *                              locked
     * @param roles           the roles that should be granted to the caller
     *                              if they presented the correct username and password and the user
     *                              is enabled. Not null.
     * @throws IllegalArgumentException if a <code>null</code> value was passed
     *                                  either as a parameter or as an element in the
     *                                  <code>GrantedAuthority</code> collection
     */
    public UserEntity(String username, String password, boolean enabled, boolean accountNonExpired,
                      boolean credentialsNonExpired, boolean accountNonLocked, Set<? extends RoleEntity> roles, Set<UserConnectionEntity> userConnections) {

        if (((username == null) || "".equals(username)) || (password == null)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }

        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.roles = Collections.unmodifiableSet(roles);
        this.userConnections = userConnections;
    }

    //~ Methods ========================================================================================================

    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());

        return authorities;
    }
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Set<RoleEntity> getRoles() {
        if (roles == null) {
            roles = new HashSet<>();
        }
        return roles;
    }

    @SuppressWarnings("unused")
    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<UserConnectionEntity> getUserConnections() {
        if (userConnections == null) {
            userConnections = new HashSet<>();
        }
        return userConnections;

    }

    public void setUserConnections(Set<UserConnectionEntity> userConnections) {
        this.userConnections = userConnections;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void eraseCredentials() {
        password = null;
    }

    /**
     * Returns {@code true} if the supplied object is a {@code User} instance with the
     * same {@code username} value.
     * <p/>
     * In other words, the objects are equal if they have the same username, representing the
     * same principal.
     */
    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof UserEntity) {
            return username.equals(((UserEntity) rhs).username);
        }
        return false;
    }

    /**
     * Returns the hashcode of the {@code username}.
     */
    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

        if (!CollectionUtils.isEmpty(this.roles)) {
            sb.append("Roles: ");
            sb.append(StringUtils.collectionToCommaDelimitedString(this.roles));
        } else {
            sb.append("Not granted any roles");
        }

        return sb.toString();
    }
}
