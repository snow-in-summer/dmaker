package com.developers.dmaker.controller;

import com.developers.dmaker.dto.CreateDeveloper;
import com.developers.dmaker.dto.DeveloperDetailDto;
import com.developers.dmaker.dto.DeveloperDto;
import com.developers.dmaker.dto.EditDeveloper;
import com.developers.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Snow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {
    private final DMakerService dMakerService;

    @PostMapping("/create-developer")
    public ResponseEntity<CreateDeveloper.Response> createDeveloper(
            @RequestBody CreateDeveloper.Request request
    ) {
        return ResponseEntity.ok(
                dMakerService.createDeveloper(request)
        );
    }

    @GetMapping("/developers")
    public ResponseEntity<List<DeveloperDto>> getDevelopers() {
        return ResponseEntity.ok(
                dMakerService.getAllDevelopers()
        );
    }

    @GetMapping("/developer/{memberId}")
    public ResponseEntity<DeveloperDetailDto> getDeveloper(
            @PathVariable String memberId
    ) {
        return ResponseEntity.ok(
                dMakerService.getDeveloper(memberId)
        );
    }

    @PutMapping("/developer/{memberId}")
    public ResponseEntity<DeveloperDetailDto> updateDeveloper(
            @PathVariable String memberId,
            @RequestBody EditDeveloper.Request request
            ) {
        return ResponseEntity.ok(
                dMakerService.getDeveloper(memberId)
        );
    }
}
