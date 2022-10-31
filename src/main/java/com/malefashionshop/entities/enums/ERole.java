package com.malefashionshop.entities.enums;

import java.util.Arrays;

public enum ERole {
    ROLE_USER("ROLE_USER"),
    ROLE_MODERATOR("ROLE_MODERATOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String text;

    ERole(String text) {
        this.text = text;
    }
    public static ERole fromText(String text) {
        return Arrays.stream(values())
                .filter(bl -> bl.text.equalsIgnoreCase(text))
                .findFirst()
                .orElse(null);
    }
}

