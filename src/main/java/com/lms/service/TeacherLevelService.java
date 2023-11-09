package com.lms.service;

import com.lms.model.Teacher;
import com.lms.model.Level;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeacherLevelService {


    @Autowired
    private LevelService levelService;

    public void assignLevelsToTeacher(Teacher teacher, List<Long> levels) {
        List<Level> levelEntityList = convertListOfIdsToListOfLevels(levels);
        teacher.setLevels(levelEntityList);
        for (Level level : levelEntityList){
            log.info(level.getName()+"*********************************************");
        }
        //return teacher;//Object is pass by reference so we will make it void;
    }

    private List<Level>convertListOfIdsToListOfLevels(List <Long> levels){
        return levels.stream().map(level -> levelService.getById(level)).toList();
    }


}
