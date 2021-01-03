package com.example.CarSell.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String link;
    private String mainInfo;
    private String about;

    public Post() {}

    public Post(String link, String mainInfo, String about) {
        this.link = link;
        this.mainInfo = mainInfo;
        this.about = about;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setMainInfo(String mainInfo) {
        this.mainInfo = mainInfo;
    }

    public String getLink() {
        return link;
    }

    public String getAbout() {
        return about;
    }

    public String getMainInfo() {
        return mainInfo;
    }
}