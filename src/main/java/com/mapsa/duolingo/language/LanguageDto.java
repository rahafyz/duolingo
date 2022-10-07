package com.mapsa.duolingo.language;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class LanguageDto implements Serializable {
    @NotNull
    private String language;
}