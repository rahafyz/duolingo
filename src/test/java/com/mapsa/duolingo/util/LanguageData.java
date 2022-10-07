package com.mapsa.duolingo.util;

import com.mapsa.duolingo.language.Language;

public class LanguageData {

    public static Language language(int i) {
        return Language.builder()
                .id(1L+i)
                .language("language" + i)
                .build();
    }
}
