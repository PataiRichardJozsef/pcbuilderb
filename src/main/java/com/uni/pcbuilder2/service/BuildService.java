package com.uni.pcbuilder2.service;

import static com.uni.pcbuilder2.util.PriceUtil.calculateComponentPrice;
import static com.uni.pcbuilder2.util.PriceUtil.calculateRealBudget;

import java.util.Comparator;
import java.util.List;

import com.uni.pcbuilder2.entity.Hardware;
import com.uni.pcbuilder2.entity.Mapping;
import com.uni.pcbuilder2.exception.NotAvailableHardwareException;
import com.uni.pcbuilder2.model.Setup;
import com.uni.pcbuilder2.model.Type;
import com.uni.pcbuilder2.repository.HardwareRepository;
import com.uni.pcbuilder2.repository.MappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuildService {

    private final HardwareRepository hardwareRepository;
    private final MappingRepository mappingRepository;

    // Using pre defined percentiles to determine the possible price ranges for given components.
    private final static Float CPU_PERCENTILE = 0.25F;
    private final static Float GPU_PERCENTILE = 0.25F;
    private final static Float MOTHERBOARD_PERCENTILE = 0.50F;
    public Setup build(Integer budget, Boolean includeGadgets, Boolean isPrebuilt) {

        // Calculation budgets
        var realBudget = calculateRealBudget(budget, includeGadgets, isPrebuilt);
        var cpuBudget = calculateComponentPrice(realBudget, CPU_PERCENTILE);
        var gpuBudget = calculateComponentPrice(realBudget, GPU_PERCENTILE);
        var motherboardBudget = calculateComponentPrice(realBudget, MOTHERBOARD_PERCENTILE);

        // Getting best motherboard
        var availableMotherboards = getAvailableMotherboards(motherboardBudget);
        var bestMotherboard = findBestComponent(availableMotherboards);

        // Find best GPU and CPU
        var allCompatibleComponents = getAllCompatibleComponents(bestMotherboard);
        var bestGpu = findBestComponent(filterHardware(allCompatibleComponents, Type.GPU, gpuBudget));
        var bestCpu = findBestComponent(filterHardware(allCompatibleComponents, Type.CPU, cpuBudget));


         var setup = Setup.builder()
            // TODO: Implement gadgets
            .gadgets(null)
            .hardware(List.of(bestCpu, bestGpu, bestMotherboard))
            .build();

         setup.setMoneyLeft(realBudget - setup.getTotalPrice());

         return setup;
    }

    private List<Hardware> getAllCompatibleComponents(Hardware bestMotherboard) {
        var allCompatibleComponentsIds = getAllCompatibleComponentsIds(bestMotherboard);
        return hardwareRepository.findAllById(allCompatibleComponentsIds);
    }

    private List<Integer> getAllCompatibleComponentsIds(Hardware bestMotherboard) {
        return mappingRepository.findByMotherboardId(bestMotherboard.getId()).stream().map(Mapping::getHardwareId).toList();
    }

    private List<Hardware> getAvailableMotherboards(Integer motherboardBudget) {
        return hardwareRepository.findByType(Type.MOTHERBOARD)
            .stream()
            // Filter motherboards for you budget
            .filter(hardware -> hardware.getMarketPrice() < motherboardBudget)
            .toList();
    }

    private List<Hardware> filterHardware(List<Hardware> allCompatibleComponents, Type type, Integer budget) {
        return allCompatibleComponents
            .stream()
            // Filter hardware for your budget and by type
            .filter(hardware -> hardware.getType().equals(type))
            .filter(hardware -> hardware.getMarketPrice() < budget)
            .toList();
    }

    private Hardware findBestComponent(List<Hardware> hardware) {
        return hardware
            .stream()
            .sorted(
                // TODO: Fine tune logic to pick the best option
                Comparator.comparing(Hardware::getMarketPrice)
            )
            .findFirst()
            .orElseThrow(() -> new NotAvailableHardwareException(hardware.toString()));

    }
}
