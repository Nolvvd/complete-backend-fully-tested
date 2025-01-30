package com.cims.computerinventorymanagmentsystem.controller;

import com.cims.computerinventorymanagmentsystem.model.AssetMaintenanceHistory;
import com.cims.computerinventorymanagmentsystem.model.Maintenance;
import com.cims.computerinventorymanagmentsystem.service.maintenance.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    public ResponseEntity<Maintenance> addMaintenance(
            @RequestParam LocalDateTime completionDate,
            @RequestParam String details,
            @RequestParam String serviceProvider,
            @RequestParam int cost,
            @RequestParam Long assetId) {
        Maintenance newMaintenance = maintenanceService.addMaintenance(completionDate, details, serviceProvider, cost, assetId);
        return ResponseEntity.ok(newMaintenance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(
            @PathVariable Long id,
            @RequestParam LocalDateTime completionDate,
            @RequestParam String details,
            @RequestParam String serviceProvider,
            @RequestParam int cost,
            @RequestParam Long assetId) {
        Maintenance updatedMaintenance = maintenanceService.updateMaintenance(id, completionDate, details, serviceProvider, cost, assetId);
        return ResponseEntity.ok(updatedMaintenance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        return ResponseEntity.ok(maintenance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenanceById(@PathVariable Long id) {
        maintenanceService.deleteMaintenanceById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        List<Maintenance> maintenances = maintenanceService.getAllMaintenances();
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping("/history")
    public ResponseEntity<List<AssetMaintenanceHistory>> getAllMaintenanceHistory() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenanceHistory());
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<AssetMaintenanceHistory>> getMaintenanceHistory(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceHistory(id));
    }

}