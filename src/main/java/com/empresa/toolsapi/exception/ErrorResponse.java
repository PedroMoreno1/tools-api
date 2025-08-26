package com.empresa.toolsapi.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private LocalDateTime dateTime;
    private int status;
    private String error;
    private String message;
    private String path;
}
