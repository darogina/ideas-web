package com.github.darogina.ideas.enums;

public enum UserRole {
    ROLE_ADMIN("role.admin"),
    ROLE_USER("role.user");

    private final String messageKey;

    private UserRole(String messageKey) {
        this.messageKey = messageKey;
    }

    @SuppressWarnings("unused")
    public String getMessageKey() {
        return messageKey;
    }


    @Override
    public String toString() {
        return name().toUpperCase();
    }
}