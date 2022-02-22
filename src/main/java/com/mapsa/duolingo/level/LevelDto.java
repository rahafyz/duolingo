package com.mapsa.duolingo.level;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LevelDto implements Serializable {
    @ApiModelProperty(hidden = true)
    private Long id;
    private String level;
}
