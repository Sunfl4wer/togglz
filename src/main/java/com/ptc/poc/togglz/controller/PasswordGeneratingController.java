package com.ptc.poc.togglz.controller;

import com.ptc.poc.togglz.togglz.Features;
import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordGeneratingController {
    private static final PasswordGenerator PASSWORD_GENERATOR = new PasswordGenerator();
    private static final CharacterCharacteristicsRule RULES_WITH_SPECIAL_CHARACTERS = new CharacterCharacteristicsRule(
                3, new CharacterRule(EnglishCharacterData.LowerCase, 5),
                new CharacterRule(EnglishCharacterData.UpperCase, 5),
                new CharacterRule(EnglishCharacterData.Digit),
                new CharacterRule(EnglishCharacterData.Special)
        );
    private static final CharacterCharacteristicsRule RULES_WITHOUT_SPECIAL_CHARACTERS = new CharacterCharacteristicsRule(
            3, new CharacterRule(EnglishCharacterData.LowerCase, 5),
            new CharacterRule(EnglishCharacterData.UpperCase, 5),
            new CharacterRule(EnglishCharacterData.Digit)
    );

    @GetMapping("/generateRandomPassword")
    public String generateRandomPassword() {
        if (Features.USING_SPECIAL_CHARACTERS.isActive()) {
            return generateRandomPasswordWithSpecialCharacter();
        }
        return generateRandomPasswordWithoutSpecialCharacter();
    }

    private String generateRandomPasswordWithSpecialCharacter() {
        return PASSWORD_GENERATOR.generatePassword(12, RULES_WITH_SPECIAL_CHARACTERS.getRules());
    }

    private String generateRandomPasswordWithoutSpecialCharacter() {
        return PASSWORD_GENERATOR.generatePassword(12, RULES_WITHOUT_SPECIAL_CHARACTERS.getRules());
    }
}
