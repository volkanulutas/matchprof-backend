package com.euv.matchprof.model.dto.comment;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    private String commentText;

    private Long studentId;

    private Long teacherId;

    private long date;

    private boolean isEnabled;
}
