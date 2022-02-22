package com.mapsa.duolingo.language;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LanguageDto implements Serializable {
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(required = true, hidden = false)
    private String language;
}
