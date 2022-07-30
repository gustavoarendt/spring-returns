package com.springreturns.repositories;

import com.springreturns.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String courseName);
}
