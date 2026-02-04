package com.vornone.construction_estimator.model;

import com.vornone.construction_estimator.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "supplier")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier  extends BaseEntity {

    @Column(nullable = false)
    private String supplierName;

    @Column(nullable = false)
    private String supplierType;

    @Column(nullable=true)
    private String supplierAddress;

}
