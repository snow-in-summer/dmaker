package com.developers.dmaker.service;

import com.developers.dmaker.code.StatusCode;
import com.developers.dmaker.dto.DeveloperDetailDto;
import com.developers.dmaker.entity.Developer;
import com.developers.dmaker.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.developers.dmaker.type.DeveloperLevel.JUNGNIOR;
import static com.developers.dmaker.type.DeveloperSkillType.FULL_STACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * @author Snow
 */
@ExtendWith(MockitoExtension.class)
class DMakerServiceTest {
    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private DMakerService dMakerService;

    @Test
    void getDeveloperTest() {
        //given
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(Developer.builder()
                        .developerLevel(JUNGNIOR)
                        .developerSkillType(FULL_STACK)
                        .experienceYears(5)
                        .memberId("memberId")
                        .name("name")
                        .age(28)
                        .status(StatusCode.EMPLOYED)
                        .build()));

        //when
        DeveloperDetailDto developer = dMakerService.getDeveloper("memberId");

        //then
        assertEquals(JUNGNIOR, developer.getDeveloperLevel());
        assertEquals(FULL_STACK, developer.getDeveloperSkillType());
        assertEquals(5, developer.getExperienceYears());
    }

    @Test
    void createDeveloperTest() {
        //given
        //when
        //then
    }
}
