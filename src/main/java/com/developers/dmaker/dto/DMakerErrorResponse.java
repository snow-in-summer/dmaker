package com.developers.dmaker.dto;

import com.developers.dmaker.code.DMakerErrorCode;
import lombok.*;

/**
 * @author Snow
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DMakerErrorResponse {
    private DMakerErrorCode errorCode;
    private String errorMessage;
}
