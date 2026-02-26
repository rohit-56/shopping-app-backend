package com.user.service.common.enums;

public enum USER_ENTITY_ROLE {
    ADMIN("ADMIN"),
    USER("USER");

    private String roleName;

    USER_ENTITY_ROLE(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

}
