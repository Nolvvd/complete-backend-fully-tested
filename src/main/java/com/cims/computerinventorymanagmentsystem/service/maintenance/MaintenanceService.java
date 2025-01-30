package com.cims.computerinventorymanagmentsystem.service.maintenance;


import com.cims.computerinventorymanagmentsystem.exceptions.AssetNotFoundException;
import com.cims.computerinventorymanagmentsystem.exceptions.MaintenanceNotFoundException;
import com.cims.computerinventorymanagmentsystem.model.Asset;
import com.cims.computerinventorymanagmentsystem.model.AssetMaintenanceHistory;
import com.cims.computerinventorymanagmentsystem.model.Maintenance;
import com.cims.computerinventorymanagmentsystem.repository.AssetMaintenaceHistoryRepository;
import com.cims.computerinventorymanagmentsystem.repository.AssetRepository;
import com.cims.computerinventorymanagmentsystem.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService implements IMaintenaceService{

    private final AssetRepository assetRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final AssetMaintenaceHistoryRepository assetMaintenanceHistoryRepository;

    @Override
    public Maintenance addMaintenance(LocalDateTime completionDate, String details, String serviceProvider, int cost, Long assetId) {
        Maintenance newMaintenance = new Maintenance();

        return addOrUpdateMaintenance(completionDate, details, serviceProvider, cost, assetId, newMaintenance);
    }

    @Override
    public Maintenance updateMaintenance(Long maintenanceId, LocalDateTime completionDate, String details, String serviceProvider, int cost, Long assetId) {
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new MaintenanceNotFoundException("Maintenance not found!"));
        return addOrUpdateMaintenance(completionDate, details, serviceProvider, cost, assetId, maintenance);
    }

    public Maintenance addOrUpdateMaintenance(LocalDateTime completionDate, String details, String serviceProvider, int cost, Long assetId, Maintenance maintenance) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new AssetNotFoundException("Asset not found!"));

        boolean isNewMaintenance = maintenance.getId() == null;

        String oldDetails = maintenance.getDetails();

        maintenance.setCompletionDate(completionDate);
        maintenance.setDetails(details);
        maintenance.setServiceProvider(serviceProvider);
        maintenance.setCost(cost);
        maintenance.setAsset(asset);

        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);

        if (isNewMaintenance || (oldDetails != null && !oldDetails.equals(details))) {
            AssetMaintenanceHistory history = new AssetMaintenanceHistory();
            history.setAsset(asset);
            history.setMaintenance(savedMaintenance);
            history.setAssetMaintenanceDate(LocalDateTime.now());
            history.setMaintenanceDetails(details);

            assetMaintenanceHistoryRepository.save(history);
        }

        return savedMaintenance;
    }



    @Override
    public void deleteMaintenanceById(Long id) {
        maintenanceRepository.findById(id).ifPresentOrElse(maintenanceRepository::delete,
                () ->{throw new MaintenanceNotFoundException("Maintenance not found!");});
    }

    @Override
    public Maintenance getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Maintenance not found!"));
    }

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }


    public List<AssetMaintenanceHistory> getAllMaintenanceHistory() {
        return assetMaintenanceHistoryRepository.findAll();
    }

    public List<AssetMaintenanceHistory> getMaintenanceHistory(Long maintenanceId) {
        return assetMaintenanceHistoryRepository.findByAssetId(maintenanceId);
    }

}
