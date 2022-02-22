package com.mapsa.duolingo.language;

import com.mapsa.duolingo.common.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends GenericRepository<Language,Long> {
}
