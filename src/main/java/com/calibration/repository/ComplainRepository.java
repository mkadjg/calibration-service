package com.calibration.repository;

import com.calibration.model.Complain;
import com.calibration.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ComplainRepository extends JpaRepository<Complain, Integer> {

    @Query("select c from Complain c where c.calibration.equipment.customers.id=:id")
    List<Complain> findByCustomerId(int id);

    @Query("select c from Complain c " +
            "where c.calibration.equipment.customers.id=:id " +
            "and c.complainStatus.id =10 " +
            "and c.complainDate between :start and :end")
    List<Complain> findHistoryByCustomerId(int id, LocalDate start, LocalDate end);

    @Query("select c from Complain c where c.technician.id=:id")
    List<Complain> findByTechnicianId(int id);

    @Query("select c from Complain c where c.typewriter.id=:id")
    List<Complain> findByTypewriterId(int id);

    @Query("select c from Complain c where c.complainStatus.id <> 10 and c.calibration.equipment.customers.id =:customerId order by c.complainStatus.id desc ")
    List<Complain> findInProgressComplain(int customerId);

    @Query("select distinct c.calibration.equipment.customers from Complain c")
    List<Customers> findAllCustomer();

    @Query("select count(c.id) from Complain c where c.calibration.equipment.customers.id =:id")
    Integer countComplainByCustomerId(int id);

    @Query("select count(c.id) from Complain c " +
            "where c.calibration.equipment.customers.id =:id " +
            "and c.technician.id =:technicianId")
    Integer countComplainByCustomerIdAndTechnicianId(int id, int technicianId);

    @Query("select count(c.id) from Complain c " +
            "where c.calibration.equipment.customers.id =:id " +
            "and c.typewriter.id =:typewriterId")
    Integer countComplainByCustomerIdAndTypewriterId(int id, int typewriterId);

}
