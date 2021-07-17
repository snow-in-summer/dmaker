package com.developers.dmaker.entity;

import com.developers.dmaker.code.StatusCode;
import com.developers.dmaker.type.DeveloperLevel;
import com.developers.dmaker.type.DeveloperSkillType;

/**
 * @author Snow
 */
public class DeveloperMock {
    public static Developer createDeveloper(
            DeveloperLevel developerLevel,
            DeveloperSkillType developerSkillType,
            Integer experienceYears,
            String memberId
    ) {
        return Developer.builder()
                .developerLevel(developerLevel)
                .developerSkillType(developerSkillType)
                .experienceYears(experienceYears)
                .memberId(memberId)
                .name("name")
                .age(28)
                .status(StatusCode.EMPLOYED)
                .build();
    }
}
