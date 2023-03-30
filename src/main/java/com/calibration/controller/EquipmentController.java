package com.calibration.controller;

import com.calibration.dto.AutocompleteDto;
import com.calibration.dto.EquipmentDto;
import com.calibration.model.Equipment;
import com.calibration.repository.CustomersRepository;
import com.calibration.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    EquipmentRepository equipmentRepository;

    CustomersRepository customersRepository;

    @Autowired
    public EquipmentController(EquipmentRepository equipmentRepository, CustomersRepository customersRepository) {
        this.equipmentRepository = equipmentRepository;
        this.customersRepository = customersRepository;
    }

    @GetMapping("/find-by-customer-id/{customerId}")
    public Object findAll(@PathVariable int customerId) {
        return equipmentRepository.findByCustomerId(customerId);
    }

    @GetMapping("/autocomplete/{customerId}")
    public Object autocomplete(@PathVariable int customerId) {
        return equipmentRepository.findByCustomerId(customerId)
                .stream()
                .map(equipment -> AutocompleteDto.builder()
                        .id(equipment.getId())
                        .label(equipment.getEquipmentName()
                                .concat(" - ")
                                .concat(equipment.getManufacturer())
                                .concat(" - ")
                                .concat(equipment.getModelType())
                                .concat(" - ")
                                .concat(equipment.getSerialNumber()))
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public Object create(@RequestBody EquipmentDto dto) {
        return ResponseEntity.status(201).body(equipmentRepository.save(Equipment.builder()
                .equipmentName(dto.getEquipmentName())
                .capacity(dto.getCapacity())
                .graduation(dto.getGraduation())
                .manufacturer(dto.getManufacturer())
                .modelType(dto.getModelType())
                .serialNumber(dto.getSerialNumber())
                .customers(customersRepository.findById(dto.getCustomerId()).orElseThrow(EntityNotFoundException::new))
                .build()));
    }

    @PutMapping("/{id}")
    public Object update(@RequestBody EquipmentDto dto, @PathVariable int id) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.status(200).body(equipmentRepository.save(equipment.toBuilder()
                .equipmentName(dto.getEquipmentName())
                .capacity(dto.getCapacity())
                .graduation(dto.getGraduation())
                .manufacturer(dto.getManufacturer())
                .modelType(dto.getModelType())
                .serialNumber(dto.getSerialNumber())
                .customers(customersRepository.findById(dto.getCustomerId()).orElseThrow(EntityNotFoundException::new))
                .build()));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id) {
        equipmentRepository.findById(id)
                .ifPresentOrElse(equipment -> equipmentRepository.delete(equipment), () -> { throw new EntityNotFoundException(); });
        return ResponseEntity.status(200).body("Hapus data berhasil!");
    }

}
