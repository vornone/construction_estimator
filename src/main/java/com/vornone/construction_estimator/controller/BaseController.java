package com.vornone.construction_estimator.controller;

import com.vornone.construction_estimator.dto.ApiResponse;
import com.vornone.construction_estimator.dto.PagedResponse;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    protected <T>ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    protected <T>ResponseEntity<ApiResponse<T>> ok(String message, T data) {
        return ResponseEntity.ok(ApiResponse.success(message, data));
    }

    protected <T>ResponseEntity<ApiResponse<T>> paged(PagedResponse<T> data) {
        return ResponseEntity.ok((ApiResponse<T>) ApiResponse.paged(data));
    }
}
