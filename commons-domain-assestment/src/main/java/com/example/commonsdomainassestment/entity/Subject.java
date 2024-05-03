package com.example.commonsdomainassestment.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnoreProperties(value = {"childrens"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject father;

    @JsonIgnoreProperties(value = {"father"}, allowSetters = true)
    @OneToMany(mappedBy = "father", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Subject> childrens;

    public Subject() {
        this.childrens = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getFather() {
        return father;
    }

    public void setFather(Subject father) {
        this.father = father;
    }

    public List<Subject> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Subject> childrens) {
        this.childrens = childrens;
    }
}
