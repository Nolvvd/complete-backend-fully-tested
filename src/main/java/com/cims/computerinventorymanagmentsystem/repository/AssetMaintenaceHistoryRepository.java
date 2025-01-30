package com.cims.computerinventorymanagmentsystem.repository;

import com.cims.computerinventorymanagmentsystem.model.AssetMaintenanceHistory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetMaintenaceHistoryRepository extends JpaRepository<AssetMaintenanceHistory, Long> {
    List<AssetMaintenanceHistory> findByAssetId(Long maintenanceId);

}
