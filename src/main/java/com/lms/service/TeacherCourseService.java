package com.lms.service;

import com.lms.model.Course;
import com.lms.model.Teacher;
import com.lms.model.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TeacherCourseService {

    @Autowired
    private TeacherService teacherService;


    @Autowired
    private LevelService levelService;

    // assign course to teacher.
    public void assignCourseToTeacher(long teacherId, Course course) {
        Teacher teacher = teacherService.getById(teacherId);
        course.setTeacher(teacher);
    }

    // assign course to level
    public void assignCourseToLevel(long levelId, Course course) {
        Level level = levelService.getById(levelId);
        course.setLevel(level);
    }



    public void checkIfThisTeacherCanGiveThisLevelOrNotBeforeAddingAnyCourseForThisLevel(Course course) {
        Teacher teacher = course.getTeacher();
        Level level = course.getLevel();
        teacher.getLevels()
                .stream()
                .filter(iterator -> iterator.equals(level))
                .findFirst().
                orElseThrow(
                        () -> new NoSuchElementException("This Teacher Can't Add Course For This Level")
                );
    }


}
