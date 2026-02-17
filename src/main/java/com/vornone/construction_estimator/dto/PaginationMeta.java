package com.vornone.construction_estimator.dto;

public record PaginationMeta(
        int page,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean isLast
) {
    public static PaginationMeta of(PagedResponse<?> paged) {
        return new PaginationMeta(
                paged.page(),
                paged.pageSize(),
                paged.totalElements(),
                paged.totalPages(),
                paged.isLast()
        );
    }
}