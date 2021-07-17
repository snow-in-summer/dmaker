package com.developers.dmaker.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Snow
 */
@Getter
@AllArgsConstructor
public enum DMakerErrorCode {
    NO_DEVELOPER("해당되는 개발자가 없습니다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다.");

    private final String message;
}
