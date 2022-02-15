package com.mapsa.duolingo.exam;

import com.mapsa.duolingo.language.Language;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExamDto implements Serializable {
    @ApiModelProperty(hidden = true)
    private final Long id;
    @ApiModelProperty(required = true, hidden = true)
    private final String location;
    @ApiModelProperty(required = true)
    private final Language language;
    @ApiModelProperty(required = true)
    private final Language level;
}
