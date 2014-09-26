package com.github.darogina.ideas.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserConnection",
        indexes = {
                @Index(name = "I_USER_CONNECTION_RANK",
                        columnList = "USER_ID, PROVIDER_ID, RANK"),
                @Index(name = "I_PROVIDER_USER",
                        columnList = "USER_ID, PROVIDER_ID, RANK, PROVIDER_USER_ID"),
                @Index(name = "I_PROVIDER",
                        columnList = "PROVIDER_ID, PROVIDER_USER_ID")},
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"PROVIDER_USER_ID", "PROVIDER_ID"})
        })
@IdClass(UserConnectionEntity.UserConnectionEntityId.class)
public class UserConnectionEntity implements Serializable {

    private static final long serialVersionUID = -2535198568575091217L;

    @Id
    @Column(name = "PROVIDER_USER_ID", length = 25, nullable = false)
    private String providerUserId;

    @Id
    @Column(name = "PROVIDER_ID", length = 25, nullable = false)
    private String providerId;

    @Column
    private Long expireTime;

    @Column(length = 55)
    private String secret;

    @Column(length = 55)
    private String displayName;

    @Column
    private String profileUrl;

    @Column
    private String imageUrl;

    @Column(length = 55)
    private String accessToken;

    @Column(length = 55)
    private String refreshToken;

    @Column
    private int rank;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private UserEntity user;

    public UserConnectionEntity() {
    }

    public UserConnectionEntity(UserEntity user, String providerId, String providerUserId,
                                int rank, String displayName, String profileUrl, String imageUrl,
                                String accessToken, String secret, String refreshToken,
                                Long expireTime) {
        super();
        this.expireTime = expireTime;
        this.providerUserId = providerUserId;
        this.providerId = providerId;
        this.secret = secret;
        this.displayName = displayName;
        this.profileUrl = profileUrl;
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.rank = rank;
        this.user = user;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Transient
    public String getUserId() {
        return user.getUsername();
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((providerUserId == null) ? 0 : providerUserId.hashCode());
        result = prime * result
                + ((providerId == null) ? 0 : providerId.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        UserConnectionEntity other = (UserConnectionEntity) obj;
        if (providerUserId == null) {
            if (other.providerUserId != null)
                return false;
        } else if (!providerUserId.equals(other.providerUserId))
            return false;
        if (providerId == null) {
            if (other.providerId != null)
                return false;
        } else if (!providerId.equals(other.providerId))
            return false;
        return true;
    }

    class UserConnectionEntityId implements Serializable {
        @Transient
        private static final long serialVersionUID = -7657908440068594673L;

        String providerUserId;
        String providerId;
    }

}