package com.college.yi.EcSite.entity.enums;

public enum UserRole {
    ADMIN((short) 1),
    USER((short) 2);

    private final short code;

    UserRole(short code) {
        this.code = code;
    }

    public short getCode() {
        return code;
    }

    public static UserRole fromCode(short code) {
        for (UserRole role : values()) {
            if (role.getCode() == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role code: " + code);
    }
}

