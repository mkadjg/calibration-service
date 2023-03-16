package com.calibration.dto;

import lombok.Data;

@Data
public class EquipmentDto {
    String serialNumber;
    String equipmentName;
    String manufacturer;
    String modelType;
    int capacity;
    int graduation;
    int customerId;
}
