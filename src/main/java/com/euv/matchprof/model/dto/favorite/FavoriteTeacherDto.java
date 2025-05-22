package com.euv.matchprof.model.dto.favorite;

import com.euv.matchprof.model.dto.user.StudentDto;
import lombok.Data;

import java.util.Set;

@Data
public class FavoriteTeacherDto {
    private Long id;

    private StudentDto student;

    private Set<Long> favoriteTeacherIds;
}
