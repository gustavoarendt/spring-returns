package com.springreturns.repositories;

import com.springreturns.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByCourseName(String courseName);
}
