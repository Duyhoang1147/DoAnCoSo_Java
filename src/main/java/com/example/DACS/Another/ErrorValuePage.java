package com.example.DACS.Another;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorValuePage {
    NOTFOUND("redirect:/error/notfound/");
    public final String value;
}
