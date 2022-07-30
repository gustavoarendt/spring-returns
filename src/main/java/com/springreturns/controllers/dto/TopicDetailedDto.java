package com.springreturns.controllers.dto;

import com.springreturns.models.Answer;
import com.springreturns.models.Status;
import com.springreturns.models.Topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDetailedDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private String authorName;
    private Status status;
    private List<AnswerDto> answerDtoList;

    public TopicDetailedDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.creationDate = topic.getCreationDate();
        this.authorName = topic.getAuthor().getName();
        this.status = topic.getStatus();
        this.answerDtoList = new ArrayList<>();
        this.answerDtoList.addAll(topic.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Status getStatus() {
        return status;
    }

    public List<AnswerDto> getAnswerDtoList() {
        return answerDtoList;
    }
}
