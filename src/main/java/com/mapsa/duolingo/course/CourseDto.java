package com.mapsa.duolingo.course;

import com.mapsa.duolingo.language.Language;
import com.mapsa.duolingo.language.LanguageDto;
import com.mapsa.duolingo.level.Level;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CourseDto implements Serializable {

    @ApiModelProperty(required = false, hidden = true)
    private Long id;

    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(required = false)
    private Language language;

    @ApiModelProperty(required = false)
    private Level level;

}
