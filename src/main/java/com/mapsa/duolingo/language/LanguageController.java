package com.mapsa.duolingo.language;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LanguageController {
    private ILanguageService service;
    private LanguageMapper mapper;


    @PostMapping(value = "/admin/language")
    public ResponseEntity<LanguageDto> addLang(@RequestBody LanguageDto dto) {
        Language lan = service.save(mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDto(lan));
    }

    @GetMapping(value = "/languages")
    public ResponseEntity<List<LanguageDto>> getAllLangs() {
        List<Language> languages = service.getAll();
        return ResponseEntity.ok(mapper.toListDto(languages));
    }
}
