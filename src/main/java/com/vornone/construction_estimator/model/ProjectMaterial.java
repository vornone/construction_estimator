package com.vornone.construction_estimator.model;

import com.vornone.construction_estimator.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "project_material")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMaterial extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "material_id", nullable = false)
    private Material material;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false, precision = 12, scale=2)
    private BigDecimal unitPrice;

    @Column(nullable= false, precision = 12, scale=2)
    private BigDecimal totalPrice;

    public void calculateTotalCost(){
        if(quantity > 0 && unitPrice != null){
            this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }
}
