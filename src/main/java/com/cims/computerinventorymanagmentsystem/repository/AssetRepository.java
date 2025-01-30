package com.cims.computerinventorymanagmentsystem.repository;

import com.cims.computerinventorymanagmentsystem.model.Asset;
import com.cims.computerinventorymanagmentsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByKind(String kind);

    List<Asset> findByType(String type);

    List<Asset> findByTypeAndKind(String type, String kind);

    List<Asset> findByUser(User user);

    Long countByTypeAndKind(String type, String kind);

}
