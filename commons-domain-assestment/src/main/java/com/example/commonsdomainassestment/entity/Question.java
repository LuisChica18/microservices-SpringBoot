package com.example.commonsdomainassestment.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;

    @JsonIgnoreProperties({"questions"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assestment_id")
    private Assestment assestment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Assestment getAssestment() {
        return assestment;
    }

    public void setAssestment(Assestment assestment) {
        this.assestment = assestment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id);
    }

}
