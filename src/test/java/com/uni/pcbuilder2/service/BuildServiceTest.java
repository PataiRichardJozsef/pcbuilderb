package com.uni.pcbuilder2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import com.uni.pcbuilder2.entity.Hardware;
import com.uni.pcbuilder2.entity.Mapping;
import com.uni.pcbuilder2.exception.NotAvailableHardwareException;
import com.uni.pcbuilder2.model.Type;
import com.uni.pcbuilder2.repository.HardwareRepository;
import com.uni.pcbuilder2.repository.MappingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuildServiceTest {

    @Mock
    HardwareRepository hardwareRepository;
    @Mock
    MappingRepository mappingRepository;
    @InjectMocks
    BuildService buildService;

    @Test
    void build() {
        when(hardwareRepository.findByType(Type.MOTHERBOARD)).thenReturn(List.of(testMotherboard()));
        when(hardwareRepository.findAllById(any())).thenReturn(List.of(testCpu(), testGpu(), testMotherboard()));
        when(mappingRepository.findByMotherboardId(any())).thenReturn(List.of(
            new Mapping(1, 12, 13),
            new Mapping(2, 12, 14)
        ));

        var actual = buildService.build(60000, true, true);

        assertThat(actual.getHardware()).containsExactlyInAnyOrder(testCpu(), testMotherboard(), testGpu());
        assertThat(actual.getGadgets()).isNull();
        assertThat(actual.getMoneyLeft()).isEqualTo(52539);
        assertThat(actual.getTotalPrice()).isEqualTo(7161);
    }

    @Test
    void build_throwsException() {
        when(hardwareRepository.findByType(Type.MOTHERBOARD)).thenReturn(List.of(testMotherboard()));
        when(hardwareRepository.findAllById(any())).thenReturn(Collections.emptyList());
        when(mappingRepository.findByMotherboardId(any())).thenReturn(List.of(
            new Mapping(1, 12, 13),
            new Mapping(2, 12, 14)
        ));

        assertThrows(NotAvailableHardwareException.class, () -> buildService.build(6000, true, true));
    }

    private Hardware testMotherboard() {
        var hardware = new Hardware();
        hardware.setId(12);
        hardware.setManufacturer("ANUS");
        hardware.setType(Type.MOTHERBOARD);
        hardware.setModel("I'm dumb");
        hardware.setName("Anus manufact");
        hardware.setMarketPrice(69);
        return hardware;
    }

    private Hardware testGpu() {
        var hardware = new Hardware();
        hardware.setId(13);
        hardware.setManufacturer("ANUS-ADVANCED");
        hardware.setType(Type.GPU);
        hardware.setModel("I'm dumb");
        hardware.setName("Anus manufact");
        hardware.setMarketPrice(6996);
        return hardware;
    }

    private Hardware testCpu() {
        var hardware = new Hardware();
        hardware.setId(14);
        hardware.setManufacturer("ANUS");
        hardware.setType(Type.CPU);
        hardware.setModel("I'm dumb");
        hardware.setName("Anus CPU pro");
        hardware.setMarketPrice(96);
        return hardware;
    }

}
