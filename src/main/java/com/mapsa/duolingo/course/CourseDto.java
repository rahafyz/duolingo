package com.mapsa.duolingo.course;

import com.mapsa.duolingo.language.Language;
import com.mapsa.duolingo.language.LanguageDto;
import com.mapsa.duolingo.level.Level;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class CourseDto implements Serializable {

    private Long id;
    @NotNull
    private String name;

    private Language language;

    private Level level;

}
