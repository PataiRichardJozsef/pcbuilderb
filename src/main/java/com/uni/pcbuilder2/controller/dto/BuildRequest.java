package com.uni.pcbuilder2.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BuildRequest {

    @NotNull
    @Max(Integer.MAX_VALUE)
    private Integer budget;
    @NotNull
    private Boolean includeGadgets;
    @NotNull
    private Boolean isPrebuilt;

}
