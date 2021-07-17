package com.developers.dmaker.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Snow
 */
@AllArgsConstructor
@Getter
public enum StatusCode {
    EMPLOYED("고용"),
    RETIRED("퇴직");

    private final String description;
}
