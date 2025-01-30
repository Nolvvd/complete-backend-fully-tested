package com.cims.computerinventorymanagmentsystem.repository;

import com.cims.computerinventorymanagmentsystem.model.AssetAssociationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetAssociationHistoryRepository extends JpaRepository<AssetAssociationHistory, Long> {
    List<AssetAssociationHistory> findByAssetId(Long assetId);
}
