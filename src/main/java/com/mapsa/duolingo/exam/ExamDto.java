package com.mapsa.duolingo.exam;

import com.mapsa.duolingo.language.Language;
import com.mapsa.duolingo.level.Level;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExamDto implements Serializable {
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(required = true, hidden = true)
    private String location;
    @ApiModelProperty
    private Language language;
    @ApiModelProperty
    private Level level;
}
