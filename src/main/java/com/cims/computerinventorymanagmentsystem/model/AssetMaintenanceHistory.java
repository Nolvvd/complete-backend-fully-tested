package com.cims.computerinventorymanagmentsystem.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@JsonIgnoreProperties({"maintenance"})

public class AssetMaintenanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            nullable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id", referencedColumnName = "id")

    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "maintenance_id", referencedColumnName = "id")
    private Maintenance maintenance;

    @Column(nullable = false)
    private LocalDateTime assetMaintenanceDate;

    @Column(nullable = false, length = 500)
    private String maintenanceDetails;
}
