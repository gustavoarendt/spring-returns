package com.springreturns.controllers.form;

import com.springreturns.models.Topic;
import com.springreturns.repositories.TopicRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

public class TopicUpdateForm {

    @NotNull @Length(min = 5)
    private String title;
    @NotNull @Length(min = 10)
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic update(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.setTitle(this.title);
        topic.setMessage(this.message);
        return topic;
    }
}
