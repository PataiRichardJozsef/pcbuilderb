package com.uni.pcbuilder2.controller;

import static com.uni.pcbuilder2.controller.BuildController.BASE_PATH;

import com.uni.pcbuilder2.controller.dto.BuildRequest;
import com.uni.pcbuilder2.controller.dto.ErrorResponse;
import com.uni.pcbuilder2.exception.NotAvailableHardwareException;
import com.uni.pcbuilder2.model.Setup;
import com.uni.pcbuilder2.service.BuildService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(BASE_PATH)
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Build")
public class BuildController {

    public static final String BASE_PATH = "/api/v1/build";

    private final BuildService service;

    @PostMapping
    @Operation(summary ="Create build.")
    public Setup build(@RequestBody BuildRequest request) {
        return service.build(request.getBudget(), request.getIncludeGadgets(), request.getIsPrebuilt());
    }

    @ExceptionHandler(NotAvailableHardwareException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorResponse handle(NotAvailableHardwareException e) {
        return new ErrorResponse(e.getMessage());
    }
}
