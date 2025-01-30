package com.cims.computerinventorymanagmentsystem.controller;

import com.cims.computerinventorymanagmentsystem.model.Asset;
import com.cims.computerinventorymanagmentsystem.model.AssetAssociationHistory;
import com.cims.computerinventorymanagmentsystem.service.asset.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    public ResponseEntity<Asset> addAsset(
            @RequestParam String kind,
            @RequestParam String type,
            @RequestParam String status,
            @RequestParam LocalDateTime assignmentDate,
            @RequestParam String username) {
        Asset newAsset = assetService.addAsset(kind, type, status, assignmentDate, username);
        return ResponseEntity.ok(newAsset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(
            @PathVariable Long id,
            @RequestParam String kind,
            @RequestParam String type,
            @RequestParam String status,
            @RequestParam LocalDateTime assignmentDate,
            @RequestParam String username) {
        Asset updatedAsset = assetService.updateAsset(id, kind, type, status, assignmentDate, username);
        return ResponseEntity.ok(updatedAsset);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        Asset asset = assetService.getAssetById(id);
        return ResponseEntity.ok(asset);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssetById(@PathVariable Long id) {
        assetService.deleteAssetById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/kind/{kind}")
    public ResponseEntity<List<Asset>> getAssetsByKind(@PathVariable String kind) {
        List<Asset> assets = assetService.getAssetsByKind(kind);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Asset>> getAssetsByType(@PathVariable String type) {
        List<Asset> assets = assetService.getAssetsByType(type);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/type/{type}/kind/{kind}")
    public ResponseEntity<List<Asset>> getAssetsByTypeAndKind(
            @PathVariable String type,
            @PathVariable String kind) {
        List<Asset> assets = assetService.getAssetsByTypeAndKind(type, kind);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/history")
    public ResponseEntity<List<AssetAssociationHistory>> getAllAssetAssociations() {
        return ResponseEntity.ok(assetService.getAllAssetAssociations());
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<AssetAssociationHistory>> getAssetHistory(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAssetHistory(id));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Asset>> getUserAssets(@PathVariable String username) {
        List<Asset> assets = assetService.getAssetsByUsername(username);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/count/type/{type}/kind/{kind}")
    public ResponseEntity<Long> countAssetsByTypeAndKind(
            @PathVariable String type,
            @PathVariable String kind) {
        Long count = assetService.countAssetsByTypeAndKind(type, kind);
        return ResponseEntity.ok(count);
    }
}
