package com.euv.matchprof.model.dto.message;

import lombok.Data;

@Data
public class MessageDto {
    private Long id;

    private long dateTime;

    private String message;

    private Long senderId;

    private Long takerId;
}
