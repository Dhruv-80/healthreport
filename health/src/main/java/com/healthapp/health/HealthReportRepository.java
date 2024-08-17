package com.healthapp.health;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HealthReportRepository extends JpaRepository<HealthReport, Long> {

    @Query("SELECT h FROM HealthReport h WHERE h.name LIKE %:name%")
    Page<HealthReport> searchByName(@Param("name") String name, Pageable pageable);
}


