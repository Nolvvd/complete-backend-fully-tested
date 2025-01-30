package com.cims.computerinventorymanagmentsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@JsonIgnoreProperties({"asset.associationHistory", "asset.maintenances", "asset.assetMaintenanceHistory"})
public class AssetAssociationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            nullable = false
    )
    private Long id;

    @Column(
            nullable = false
    )
    private LocalDateTime assetAssignmentDate;

    @ManyToOne
    @JoinColumn(name = "asset_id", referencedColumnName = "id", nullable = false)
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private User user;

}
