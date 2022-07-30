package com.springreturns.controllers;

import com.springreturns.controllers.dto.TopicDetailedDto;
import com.springreturns.controllers.dto.TopicDto;
import com.springreturns.controllers.form.TopicForm;
import com.springreturns.controllers.form.TopicUpdateForm;
import com.springreturns.models.Course;
import com.springreturns.models.Topic;
import com.springreturns.repositories.CourseRepository;
import com.springreturns.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicDto> list(String courseName) {
        if (courseName == null) {
            List<Topic> topicList = topicRepository.findAll();
            return TopicDto.convert(topicList);
        }
        List<Topic> topicList = topicRepository.findByCourseName(courseName);
        return TopicDto.convert(topicList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailedDto> detailed(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isPresent()) {
            return ResponseEntity.ok(new TopicDetailedDto(topic.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TopicDto> registry(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = form.convert(courseRepository);
        topicRepository.save(topic);

        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm form) {
        Optional<Topic> optional = topicRepository.findById(id);
        if (optional.isPresent()){
            Topic topic = form.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDto(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Topic> optional = topicRepository.findById(id);
        if (optional.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}