package com.mapsa.duolingo.level;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import org.springframework.stereotype.Service;

@Service
public class LevelService extends GenericService<Level, Long> implements ILevelService {
    private LevelRepository repository;

    public LevelService(GenericRepository<Level, Long> repository, LevelRepository repository1) {
        super(repository);
        this.repository = repository1;
    }
}
