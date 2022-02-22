package com.mapsa.duolingo.language;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import org.springframework.stereotype.Service;

@Service
public class LanguageService extends GenericService<Language,Long> implements ILanguageService {
    private LanguageRepository repository;

    public LanguageService(GenericRepository<Language, Long> repository, LanguageRepository repository1) {
        super(repository);
        this.repository = repository1;
    }

}
