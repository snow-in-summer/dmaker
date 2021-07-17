package com.developers.dmaker.controller;

import com.developers.dmaker.code.DMakerErrorCode;
import com.developers.dmaker.dto.DeveloperDto;
import com.developers.dmaker.exception.DMakerException;
import com.developers.dmaker.exception.DMakerExceptionHandler;
import com.developers.dmaker.service.DMakerService;
import com.developers.dmaker.type.DeveloperLevel;
import com.developers.dmaker.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Snow
 */
@WebMvcTest(DMakerController.class)
@MockBean(JpaMetamodelMappingContext.class)
class DMakerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DMakerService dMakerService;

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    @Test
    void getAllDevelopers() throws Exception {
        //given
        DeveloperDto seniorBEDeveloper = DeveloperDto.builder()
                .developerSkillType(DeveloperSkillType.BACK_END)
                .developerLevel(DeveloperLevel.SENIOR)
                .memberId("memberId")
                .build();
        DeveloperDto juniorFEDeveloper = DeveloperDto.builder()
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .developerLevel(DeveloperLevel.JUNIOR)
                .memberId("memberId2")
                .build();
        given(dMakerService.getAllEmployedDevelopers())
                .willReturn(Arrays.asList(seniorBEDeveloper, juniorFEDeveloper));

        //when
        //then
        mockMvc.perform(get("/developers").contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.[0].developerSkillType",
                                is(DeveloperSkillType.BACK_END.name())))
                .andExpect(
                        jsonPath("$.[0].developerLevel",
                                is(DeveloperLevel.SENIOR.name())))
                .andExpect(
                        jsonPath("$.[1].developerSkillType",
                                is(DeveloperSkillType.FRONT_END.name())))
                .andExpect(
                        jsonPath("$.[1].developerLevel",
                                is(DeveloperLevel.JUNIOR.name())));
    }

    @Test
    void testErrorMessage() throws Exception {
        //given
        given(dMakerService.getAllEmployedDevelopers())
                .willThrow(new DMakerException(DMakerErrorCode.NO_DEVELOPER));

        //when
        //then
        mockMvc.perform(get("/developers").contentType(contentType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.errorCode",
                                is(DMakerErrorCode.NO_DEVELOPER.name())));

    }
}
