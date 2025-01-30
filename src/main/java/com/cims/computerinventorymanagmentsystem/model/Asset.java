package com.cims.computerinventorymanagmentsystem.model;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.List;
import java.time.LocalDateTime;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@JsonIgnoreProperties({"maintenances", "assetMaintenanceHistory", "assetAssociationHistory"})
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            updatable = false
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String kind;
    @Column(
            nullable = false
    )
    private String type;
    @Column(
            nullable = false
    )
    private String status;
    @Column(
            nullable = false
    )
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(
            nullable = false
    )
    private LocalDateTime assignmentDate;

    @Column(
            nullable = false
    )
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private User user;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Maintenance> maintenances;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AssetMaintenanceHistory> assetMaintenanceHistory;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AssetAssociationHistory> assetAssociationHistory;

}

