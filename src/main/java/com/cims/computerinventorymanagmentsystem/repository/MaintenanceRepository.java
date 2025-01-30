package com.cims.computerinventorymanagmentsystem.repository;

import com.cims.computerinventorymanagmentsystem.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

}
