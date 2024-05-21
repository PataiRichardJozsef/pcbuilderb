package com.uni.pcbuilder2.controller;

import com.uni.pcbuilder2.entity.Mapping;
import com.uni.pcbuilder2.repository.MappingRepository;
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
@RequestMapping(MappingController.BASE_PATH)
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Mapping")
public class MappingController {

    public static final String BASE_PATH = "/api/v1/mapping";

    private final MappingRepository mappingRepository;

    @GetMapping
    @Operation(summary = "Get all mappings.")
    public Iterable<Mapping> getAll() {
        return mappingRepository.findAll();
    }

    @PostMapping
    @Operation(summary ="Save mapping.")
    public Mapping save(@RequestBody Mapping mapping) {
        return mappingRepository.save(mapping);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete mapping by id.")
    public Mapping getById(@PathVariable Integer id) {
        return mappingRepository.findById(id)
            .orElse(null);
    }
}
