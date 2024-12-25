package com.euv.proffme.converter;

import com.euv.proffme.model.dto.favorite.FavoriteTeacherDto;
import com.euv.proffme.model.entity.favorite.FavoriteTeacherEntity;
import org.springframework.stereotype.Component;

@Component
public class FavoriteTeacherConverter {
    public FavoriteTeacherEntity toEntity(FavoriteTeacherDto source) {
        FavoriteTeacherEntity target = new FavoriteTeacherEntity();
        return target;
    }

    public FavoriteTeacherDto toDto(FavoriteTeacherEntity source) {
        FavoriteTeacherDto target = new FavoriteTeacherDto();
        return target;
    }
}
