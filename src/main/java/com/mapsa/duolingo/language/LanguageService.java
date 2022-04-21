package com.mapsa.duolingo.language;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LanguageService extends GenericService<Language, Long> implements ILanguageService {
    private LanguageRepository repository;

    @Override
    protected GenericRepository<Language, Long> getRepository() {
        return repository;
    }
}