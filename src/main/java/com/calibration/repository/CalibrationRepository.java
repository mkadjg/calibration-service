package com.calibration.repository;

import com.calibration.model.Calibration;
import com.calibration.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CalibrationRepository extends JpaRepository<Calibration, Integer> {

    @Query("select c from Calibration c where c.equipment.customers.id =:id")
    List<Calibration> findByCustomerId(int id);

    @Query("select c from Calibration c " +
            "where c.equipment.customers.id =:id " +
            "and c.calibrationStatus.id =10 " +
            "and c.calibrationDate between :start and :end")
    List<Calibration> findHistoryByCustomerId(int id, LocalDate start, LocalDate end);

    @Query("select c from Calibration c where c.technician.id =:id")
    List<Calibration> findByTechnicianId(int id);

    @Query("select c from Calibration c where c.typewriter.id =:id")
    List<Calibration> findByTypewriterId(int id);

    @Query("select c from Calibration c where c.calibrationStatus.id <> 10 and c.equipment.customers.id =:customerId order by c.calibrationStatus.id desc ")
    List<Calibration> findInProgressCalibration(int customerId);

    @Query("select distinct c.equipment.customers from Calibration c")
    List<Customers> findAllCustomer();

    @Query("select distinct c.equipment.customers from Calibration c " +
            "where c.technician.id =:id")
    List<Customers> findAllCustomerByTechnician(int id);

    @Query("select distinct c.equipment.customers from Calibration c " +
            "where c.typewriter.id =:id")
    List<Customers> findAllCustomerByTypewriter(int id);

    @Query("select count(c.id) from Calibration c where c.equipment.customers.id =:id")
    Integer countCalibrationByCustomerId(int id);

    @Query("select count(c.id) from Calibration c " +
            "where c.equipment.customers.id =:id " +
            "and c.technician.id =:technicianId")
    Integer countCalibrationByCustomerIdAndTechnicianId(int id, int technicianId);

    @Query("select count(c.id) from Calibration c " +
            "where c.equipment.customers.id =:id " +
            "and c.typewriter.id =:typewriterId")
    Integer countCalibrationByCustomerIdAndTypewriterId(int id, int typewriterId);

}
