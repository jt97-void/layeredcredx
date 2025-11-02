package com.fintech.layeredcredx.controllers;

import com.fintech.layeredcredx.dtos.BaseResponseDto;
import com.fintech.layeredcredx.dtos.TransactionCriteriaDto;
import com.fintech.layeredcredx.dtos.TransactionDto;
import com.fintech.layeredcredx.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<BaseResponseDto<TransactionDto>> create(@RequestBody TransactionDto dto) {
        return ResponseEntity.status(CREATED)
                .body(BaseResponseDto.success(service.create(dto)));
    }

    @GetMapping
    public ResponseEntity<BaseResponseDto<?>> getAll(Pageable pageable) {
        if (pageable.isUnpaged()) {
            List<TransactionDto> list = service.getAll();
            return ResponseEntity.ok(BaseResponseDto.success(list));
        }
        Page<TransactionDto> page = service.getAll(pageable);
        return ResponseEntity.ok(BaseResponseDto.success(page));
    }

    @PostMapping("/search")
    public ResponseEntity<BaseResponseDto<Page<TransactionDto>>> search(
            @RequestBody TransactionCriteriaDto criteria, Pageable pageable) {
        Page<TransactionDto> page = service.search(criteria, pageable);
        return ResponseEntity.ok(BaseResponseDto.success(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDto<TransactionDto>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(BaseResponseDto.success(service.getById(id)));
    }

    @PutMapping
    public ResponseEntity<BaseResponseDto<TransactionDto>> update(@RequestBody TransactionDto dto) {
        return ResponseEntity.ok(BaseResponseDto.success(service.update(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto<Void>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(BaseResponseDto.success(null));
    }

}
