package com.calibration.repository;


import com.calibration.model.ComplainTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplainTrackRepository extends JpaRepository<ComplainTrack, Integer> {
    @Query("select ct from ComplainTrack ct where ct.complain.id =:id")
    List<ComplainTrack> findByComplainId(Integer id);
}
