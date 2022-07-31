package com.springreturns.repositories;

import com.springreturns.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findAll(Pageable pagination);
    Page<Topic> findByCourseName(String courseName, Pageable pagination);
}
