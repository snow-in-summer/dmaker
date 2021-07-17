package com.developers.dmaker.service;

import com.developers.dmaker.dto.CreateDeveloper;
import com.developers.dmaker.dto.DeveloperDetailDto;
import com.developers.dmaker.dto.DeveloperDto;
import com.developers.dmaker.entity.Developer;
import com.developers.dmaker.exception.DMakerException;
import com.developers.dmaker.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.developers.dmaker.code.DMakerErrorCode.NO_DEVELOPER;

/**
 * @author Snow
 */
@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;

    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
                .name(request.getName())
                .age(request.getAge())
                .build();
        developerRepository.save(developer);
        return CreateDeveloper.Response.fromEntity(developer);
    }

    public List<DeveloperDto> getAllDevelopers() {
        return developerRepository.findAll()
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeveloperDetailDto getDeveloper(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(
                        () -> new DMakerException(NO_DEVELOPER)
                );
    }
}
