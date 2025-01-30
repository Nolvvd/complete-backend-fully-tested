package com.cims.computerinventorymanagmentsystem.service.maintenance;

import com.cims.computerinventorymanagmentsystem.model.Maintenance;

import java.time.LocalDateTime;
import java.util.List;

public interface IMaintenaceService {
    Maintenance addMaintenance(LocalDateTime completionDate, String details, String serviceProvider, int cost, Long assetId);
    void deleteMaintenanceById(Long id);
    Maintenance updateMaintenance(Long maintenaceId, LocalDateTime completionDate, String details, String serviceProvider, int cost, Long assetId);
    Maintenance getMaintenanceById(Long id);
    List<Maintenance> getAllMaintenances();
}
