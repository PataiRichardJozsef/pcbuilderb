package com.uni.pcbuilder2.controller;

import java.util.List;

import com.uni.pcbuilder2.entity.Gadget;
import com.uni.pcbuilder2.entity.Hardware;
import com.uni.pcbuilder2.repository.GadgetRepository;
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
@RequestMapping(GadgetsController.BASE_PATH)
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Gadget")
public class GadgetsController {

    public static final String BASE_PATH = "/api/v1/gadgets";

    private final GadgetRepository gadgetRepository;

    @GetMapping
    @Operation(summary = "Get all gadgets.")
    public Iterable<Gadget> getAll() {
        return gadgetRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get hardware by id.")
    public Gadget getById(@PathVariable Integer id) {
        return gadgetRepository.findById(id)
            .orElse(null);
    }

    @PostMapping
    @Operation(summary ="Save gadget.")
    public Gadget save(@RequestBody Gadget gadget) {
        return gadgetRepository.save(gadget);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete gadget by id.")
    public void deleteById(@PathVariable Integer id) {
         gadgetRepository.deleteById(id);
    }
}
