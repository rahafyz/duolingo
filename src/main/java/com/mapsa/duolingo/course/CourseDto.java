package com.mapsa.duolingo.course;

import com.mapsa.duolingo.language.Language;
import com.mapsa.duolingo.level.Level;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CourseDto implements Serializable {

    @ApiModelProperty(required = false, hidden = true)
    private final Long id;

    @ApiModelProperty(required = true)
    private final String name;

    @ApiModelProperty(required = true)
    private final Language language;

    @ApiModelProperty(required = true)
    private final Level level;
}
