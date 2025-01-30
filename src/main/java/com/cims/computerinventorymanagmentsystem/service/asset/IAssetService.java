package com.cims.computerinventorymanagmentsystem.service.asset;

import com.cims.computerinventorymanagmentsystem.model.Asset;

import java.time.LocalDateTime;
import java.util.List;

public interface IAssetService {
    Asset addAsset(String kind, String type, String status, LocalDateTime assignmentDate, String userName);
    Asset getAssetById(Long id);
    Asset updateAsset(Long assetId, String kind, String type, String status, LocalDateTime assignmentDate, String userName);
    void deleteAssetById(Long id);
    List<Asset> getAllAssets();
    List<Asset> getAssetsByKind(String kind);
    List<Asset> getAssetsByType(String type);
    List<Asset> getAssetsByTypeAndKind(String type, String kind);
    Long countAssetsByTypeAndKind(String type, String kind);
}
