package com.cims.computerinventorymanagmentsystem.service.asset;

import com.cims.computerinventorymanagmentsystem.exceptions.AssetNotFoundException;
import com.cims.computerinventorymanagmentsystem.exceptions.UserNotFoundException;
import com.cims.computerinventorymanagmentsystem.model.Asset;
import com.cims.computerinventorymanagmentsystem.model.AssetAssociationHistory;
import com.cims.computerinventorymanagmentsystem.model.User;
import com.cims.computerinventorymanagmentsystem.repository.AssetAssociationHistoryRepository;
import com.cims.computerinventorymanagmentsystem.repository.AssetRepository;
import com.cims.computerinventorymanagmentsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AssetService implements IAssetService {
    private final AssetRepository assetRepository;
    private final AssetAssociationHistoryRepository assetAssociationHistoryRepository;
    private final UserRepository userRepository;


    @Override
    public Asset addAsset(String kind, String type, String status, LocalDateTime assignmentDate, String userName) {

        Asset newAsset = new Asset();

        return addOrUpdateAsset(kind, type, status, assignmentDate, userName, newAsset);
    }

    @Override
    public Asset updateAsset(Long assetId, String kind, String type, String status, LocalDateTime assignmentDate, String userName) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new AssetNotFoundException("Asset not found!"));

        return addOrUpdateAsset(kind, type, status, assignmentDate, userName, asset);
    }

    public Asset addOrUpdateAsset(String kind, String type, String status, LocalDateTime assignmentDate, String userName, Asset asset) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        boolean isNewAssignment = asset.getUser() == null || !asset.getUser().getUsername().equals(userName);

        asset.setKind(kind);
        asset.setType(type);
        asset.setStatus(status);
        asset.setAssignmentDate(assignmentDate);
        asset.setUser(user);
        Asset savedAsset = assetRepository.save(asset);

        if (isNewAssignment) {
            AssetAssociationHistory history = new AssetAssociationHistory();
            history.setAsset(savedAsset);
            history.setUser(user);
            history.setAssetAssignmentDate(assignmentDate);
            assetAssociationHistoryRepository.save(history);
        }

        return savedAsset;
    }

    @Override
    public Asset getAssetById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new AssetNotFoundException("Asset not found!"));
    }

    @Override
    public void deleteAssetById(Long id) {
        assetRepository.findById(id).ifPresentOrElse(assetRepository::delete,
                () ->{throw new AssetNotFoundException("Asset not found!");});
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public List<Asset> getAssetsByKind(String kind) {
        return assetRepository.findByKind(kind);
    }

    @Override
    public List<Asset> getAssetsByType(String type){
        return assetRepository.findByType(type);
    }

    @Override
    public List<Asset> getAssetsByTypeAndKind(String type, String kind){
        return assetRepository.findByTypeAndKind(type, kind);
    }

    public List<AssetAssociationHistory> getAllAssetAssociations() {
        return assetAssociationHistoryRepository.findAll();
    }

    public List<AssetAssociationHistory> getAssetHistory(Long assetId) {
        return assetAssociationHistoryRepository.findByAssetId(assetId);
    }

    public List<Asset> getAssetsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return assetRepository.findByUser(user);
    }

    @Override
    public Long countAssetsByTypeAndKind(String type, String kind){
        return assetRepository.countByTypeAndKind(type, kind);
    }
}
