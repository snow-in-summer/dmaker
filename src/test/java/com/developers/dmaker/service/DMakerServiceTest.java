package com.developers.dmaker.service;

import com.developers.dmaker.dto.CreateDeveloper;
import com.developers.dmaker.dto.DeveloperDetailDto;
import com.developers.dmaker.entity.Developer;
import com.developers.dmaker.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.developers.dmaker.entity.DeveloperMock.createDeveloper;
import static com.developers.dmaker.type.DeveloperLevel.JUNGNIOR;
import static com.developers.dmaker.type.DeveloperLevel.JUNIOR;
import static com.developers.dmaker.type.DeveloperSkillType.FRONT_END;
import static com.developers.dmaker.type.DeveloperSkillType.FULL_STACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        Developer juniorFEDeveloper =
                createDeveloper(JUNIOR, FRONT_END, 5, "memberId");
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(juniorFEDeveloper));

        //when
        DeveloperDetailDto developer = dMakerService.getDeveloper("memberId");

        //then
        assertEquals(JUNIOR, developer.getDeveloperLevel());
        assertEquals(FRONT_END, developer.getDeveloperSkillType());
        assertEquals(5, developer.getExperienceYears());
    }

    @Test
    void createDeveloperTest() {
        //given
        CreateDeveloper.Request request = CreateDeveloper.Request.builder()
                .developerLevel(JUNGNIOR)
                .developerSkillType(FRONT_END)
                .experienceYears(7)
                .memberId("memberId")
                .name("name")
                .age(28)
                .build();
        ArgumentCaptor<Developer> captor =
                ArgumentCaptor.forClass(Developer.class);

        //when
        CreateDeveloper.Response response = dMakerService.createDeveloper(request);

        //then
        verify(developerRepository, times(1))
                .save(captor.capture());
        Developer savedDeveloper = captor.getValue();
        assertEquals(JUNGNIOR, savedDeveloper.getDeveloperLevel());
        assertEquals(FRONT_END, savedDeveloper.getDeveloperSkillType());
        assertEquals(7, savedDeveloper.getExperienceYears());

        assertEquals(JUNGNIOR, response.getDeveloperLevel());
        assertEquals(FRONT_END, response.getDeveloperSkillType());
        assertEquals(7, response.getExperienceYears());
    }
}
