package com.vornone.construction_estimator.utils;

import com.vornone.construction_estimator.dto.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtils {
    public static Pageable toPageable(PaginationRequest request) {
        Sort sort = request.getSortDir().equalsIgnoreCase("desc")
                ? Sort.by(request.getSortBy()).descending()
                : Sort.by(request.getSortBy()).ascending();
        return PageRequest.of(request.getPage(), request.getPageSize(), sort);
    }
}