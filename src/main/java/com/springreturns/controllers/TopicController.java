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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
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
    @Cacheable(value = "topicList")
    public Page<TopicDto> list(@RequestParam(required = false) String courseName,
               @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination) {

        if (courseName == null) {
            Page<Topic> topicList = topicRepository.findAll(pagination);
            return TopicDto.convert(topicList);
        }
        Page<Topic> topicList = topicRepository.findByCourseName(courseName, pagination);
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
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<TopicDto> registry(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = form.convert(courseRepository);
        topicRepository.save(topic);

        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm form) {
        Optional<Topic> optional = topicRepository.findById(id);
        if (optional.isPresent()){
            Topic topic = form.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDto(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Topic> optional = topicRepository.findById(id);
        if (optional.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}