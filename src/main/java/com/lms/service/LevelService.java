package com.lms.service;

import com.lms.model.Level;
import com.lms.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;

    public Level getById(long id) {
        return levelRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("There Are No Level With This Id")
        );
    }


    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    public Level add(Level level) {
        return levelRepository.save(level);
    }
}
