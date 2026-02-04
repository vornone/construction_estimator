package com.vornone.construction_estimator.model;

import com.vornone.construction_estimator.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Project extends BaseEntity {
    @Column
    private String projectName;

    @Column
    private String projectDescription;

    @Column
    private String projectType;

}
