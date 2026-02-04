package com.vornone.construction_estimator.model;

import com.vornone.construction_estimator.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Material extends BaseEntity {

    enum unit {kg,lbs,bag,pieces}
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String materialCategory;

    @Column(nullable = false)
    private BigDecimal priceUSD;

    @Column(nullable = false)
    private BigDecimal priceKHR;

    @Column(nullable= true)
    private String Supplier;

    public void calculateKHR(){
        BigDecimal usdToKHR = BigDecimal.valueOf(4100);
        this.priceKHR = priceUSD.multiply(usdToKHR);
    }
}
