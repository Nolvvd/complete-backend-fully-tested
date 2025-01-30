package com.cims.computerinventorymanagmentsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            nullable = false
    )
    private Long id;

    @Column(
            nullable = false
    )
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(
            nullable = false
    )
    private LocalDateTime completionDate;

    @Column(
            nullable = false
    )
    private String details;

    @Column(
            nullable = false
    )
    private String serviceProvider;

    @Column(
            nullable = false
    )
    private int cost;

    @ManyToOne
    @JoinColumn(name = "asset_id", referencedColumnName = "id")
    private Asset asset;

    @OneToMany(mappedBy = "maintenance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AssetMaintenanceHistory> assetMaintenanceHistory;

}
