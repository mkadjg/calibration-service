package com.calibration.controller;

import com.calibration.dto.EquipmentDto;
import com.calibration.model.Customers;
import com.calibration.model.Equipment;
import com.calibration.repository.CustomersRepository;
import com.calibration.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

    @GetMapping("/find-all")
    public Object findAll() {
        return equipmentRepository.findAll();
    }

    @PostMapping("/create")
    public Object create(@RequestBody EquipmentDto dto) {
        // equipment
        Equipment equipment = new Equipment();
        equipment.setEquipmentName(dto.getEquipmentName());
        equipment.setCapacity(dto.getCapacity());
        equipment.setManufacturer(dto.getManufacturer());
        equipment.setGraduation(dto.getGraduation());
        equipment.setModelType(dto.getModelType());
        equipment.setSerialNumber(dto.getSerialNumber());

        // customer
        Customers customer = customersRepository.findById(dto.getCustomerId()).orElse(null);
        if (Objects.isNull(customer)) {
            return ResponseEntity.badRequest().body("Pelanggan tidak terdaftar!");
        }
        equipment.setCustomers(customer);

        return ResponseEntity.status(201).body(equipmentRepository.save(equipment));
    }

}
