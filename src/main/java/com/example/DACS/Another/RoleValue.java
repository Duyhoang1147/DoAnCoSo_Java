package com.example.DACS.Another;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleValue {
    ADMIN("1"),
    STAFF("2"),
    CUSTOMER("3");
    public final String value;
}
