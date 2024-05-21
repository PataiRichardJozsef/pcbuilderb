package com.uni.pcbuilder2.controller;

import com.uni.pcbuilder2.entity.Hardware;
import com.uni.pcbuilder2.repository.HardwareRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(HardwareController.BASE_PATH)
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Hardware")
public class HardwareController {

    public static final String BASE_PATH = "/api/v1/hardware";

    private final HardwareRepository hardwareRepository;

    @GetMapping
    @Operation(summary = "Get all.")
    public Iterable<Hardware> getAll() {
        return hardwareRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get hardware by id.")
    public Hardware getById(@PathVariable Integer id) {
        return hardwareRepository.findById(id).orElse(null);
    }

    @PostMapping
    @Operation(summary ="Save hardware.")
    public Hardware save(@RequestBody Hardware hardware) {
        return hardwareRepository.save(hardware);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete by id.")
    public void deleteById(@PathVariable Integer id) {
        hardwareRepository.deleteById(id);
    }

}
