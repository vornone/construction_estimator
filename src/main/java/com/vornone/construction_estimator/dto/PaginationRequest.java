package com.vornone.construction_estimator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationRequest {
    private int page = 0;
    private int pageSize = 10;
    private String sortBy = "id";
    private String sortDir = "asc";
}