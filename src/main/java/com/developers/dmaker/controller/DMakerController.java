package com.developers.dmaker.controller;

import com.developers.dmaker.dto.CreateDeveloper;
import com.developers.dmaker.dto.DeveloperDto;
import com.developers.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestBody CreateDeveloper.Request developerRequest
    ) {
        return ResponseEntity.ok(
                dMakerService.createDeveloper(developerRequest)
        );
    }

    @GetMapping("/developers")
    public ResponseEntity<List<DeveloperDto>> getDevelopers() {
        return ResponseEntity.ok(
                dMakerService.getAllDevelopers()
        );
    }

}
